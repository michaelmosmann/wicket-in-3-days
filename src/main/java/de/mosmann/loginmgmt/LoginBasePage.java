package de.mosmann.loginmgmt;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.WebPage;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import de.mosmann.Ajax;
import de.mosmann.loginmgmt.components.LogoutPanel;
import de.mosmann.loginmgmt.events.EditLogin;
import de.mosmann.loginmgmt.events.LoginsUpdated;
import de.mosmann.loginmgmt.events.Logout;
import de.mosmann.loginmgmt.events.NewLogin;
import de.mosmann.loginmgmt.events.SaveLogin;
import de.mosmann.loginmgmt.events.ValidateLogin;
import de.mosmann.loginmgmt.login.SessionLogin;
import de.mosmann.loginmgmt.nav.BookmarkableNavEntry;
import de.mosmann.loginmgmt.nav.NavigationPanel;
import de.mosmann.persistence.service.ILoginService;
import de.mosmann.persistence.service.Login;
import de.mosmann.topics.forms.AjaxUpdateListeners;

public abstract class LoginBasePage extends WebPage {

	@Inject
	ILoginService _loginService;

	public LoginBasePage() {
		add(new LogoutPanel("logout"));
		add(new NavigationPanel("nav", new BookmarkableNavEntry<>(LoginPage.class, "Login"), new BookmarkableNavEntry<>(
				LoginManagementPage.class, "Start"), new BookmarkableNavEntry<>(HiddenPage.class, "Hidden")));
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		Object payload = event.getPayload();
		if (payload instanceof NewLogin) {
			NewLogin login = (NewLogin) payload;

			info("NewLogin: " + login);
			int id = _loginService.create(login);
			send(this, Broadcast.BREADTH, new EditLogin(id));
			send(this, Broadcast.BREADTH, new LoginsUpdated());
		}

		if (payload instanceof SaveLogin) {
			Login login = ((SaveLogin) payload).login();
			_loginService.save(login);
			send(this, Broadcast.BREADTH, new LoginsUpdated());
		}

		if (payload instanceof ValidateLogin) {
			ValidateLogin validateLogin = (ValidateLogin) payload;
			Optional<Login> maybeLogin = _loginService.getByName(validateLogin.name());
			if (maybeLogin.isPresent()) {
				Login login = maybeLogin.get();

				if (login.getPasswd().equals(validateLogin.passwd())) {

					SessionLogin.setCurrentLogin(login.getId(), login.getName());

					setResponsePage(LoginManagementPage.class);
					continueToOriginalDestination();
				} else {
					error("You may not pass!");
					Ajax.addAllIfAjax(this, IFeedback.class);
				}
			} else {
				error("You shall not pass!");
				Ajax.addAllIfAjax(this, IFeedback.class);
			}
		}

		if (payload instanceof Logout) {
			SessionLogin.logout();
		}
	}
}
