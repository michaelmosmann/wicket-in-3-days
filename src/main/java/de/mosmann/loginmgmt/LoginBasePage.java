package de.mosmann.loginmgmt;

import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;

import com.google.inject.Inject;

import de.mosmann.loginmgmt.events.EditLogin;
import de.mosmann.loginmgmt.events.LoginsUpdated;
import de.mosmann.loginmgmt.events.NewLogin;
import de.mosmann.loginmgmt.events.SaveLogin;
import de.mosmann.loginmgmt.nav.BookmarkableNavEntry;
import de.mosmann.loginmgmt.nav.NavigationPanel;
import de.mosmann.persistence.service.ILoginService;
import de.mosmann.persistence.service.Login;


public abstract class LoginBasePage extends WebPage {

	@Inject
	ILoginService _loginService;
	
	public LoginBasePage() {
		add(new NavigationPanel("nav",new BookmarkableNavEntry<>(LoginManagementPage.class, "Start")));
	}
	
	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);
		
		Object payload = event.getPayload();
		if (payload instanceof NewLogin) {
			NewLogin login=(NewLogin) payload;
			
			info("NewLogin: "+login);
			int id=_loginService.create(login);
			send(this, Broadcast.BREADTH, new EditLogin(id));
			send(this,Broadcast.BREADTH, new LoginsUpdated());
		}
		
		if (payload instanceof SaveLogin) {
			Login login = ((SaveLogin) payload).login();
			_loginService.save(login);
			send(this,Broadcast.BREADTH, new LoginsUpdated());
		}
	}
}
