package de.mosmann.loginmgmt.components;

import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import de.mosmann.Ajax;
import de.mosmann.loginmgmt.events.EditLogin;
import de.mosmann.loginmgmt.events.NewLogin;
import de.mosmann.loginmgmt.events.SaveLogin;
import de.mosmann.persistence.service.Login;
import de.mosmann.topics.forms.FormValidationBorder;

public class EditLoginPanel extends Panel {

	LoginModel _loginModel = new LoginModel();

	public EditLoginPanel(String id) {
		super(id);
		
		setOutputMarkupPlaceholderTag(true);

		Form<Login> form = new Form<Login>("form", new CompoundPropertyModel<>(_loginModel)) {

			@Override
			protected void onSubmit() {
				super.onSubmit();

				Login login = getModelObject();

				send(getPage(), Broadcast.BREADTH, new SaveLogin(login));
			}
		};

		FormValidationBorder nameBorder = new FormValidationBorder("name");
		TextField<String> nameField = new TextField<String>("name");
		nameField.setRequired(true);
		nameField.add(StringValidator.minimumLength(4));
		nameBorder.add(nameField);
		form.add(nameBorder);

		FormValidationBorder emailBorder = new FormValidationBorder("email");
		TextField<String> emailField = new TextField<String>("email");
		emailField.setRequired(true);
		emailField.add(EmailAddressValidator.getInstance());
		emailBorder.add(emailField);
		form.add(emailBorder);

		FormValidationBorder passwdBorder = new FormValidationBorder("passwd");
		passwdBorder.add(new PasswordTextField("passwd").setRequired(true));
		form.add(passwdBorder);

		add(form);

	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(_loginModel.getObject() != null);
	}
	

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		Object payload = event.getPayload();
		if (payload instanceof EditLogin) {
			_loginModel.setId(((EditLogin) payload).loginId());
			
			Ajax.addComponentIfAjax(this);
		}
	}
}
