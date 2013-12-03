package de.mosmann.loginmgmt.components;

import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import de.mosmann.loginmgmt.events.NewLogin;
import de.mosmann.topics.forms.FormValidationBorder;


public class NewLoginPanel extends Panel {

	public NewLoginPanel(String id) {
		super(id);
		
		final Model<String> nameModel = new Model<String>();
		final Model<String> emailModel = new Model<String>();
		final Model<String> passwdModel = new Model<String>();
		
		Form<Void> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				
				String name=nameModel.getObject();
				String email=emailModel.getObject();
				String passwd=passwdModel.getObject();
				
				send(getPage(), Broadcast.BREADTH, new NewLogin(name,email,passwd));
				
				nameModel.setObject(null);
				emailModel.setObject(null);
				passwdModel.setObject(null);
			}
		};
		
		FormValidationBorder nameBorder = new FormValidationBorder("name");
		TextField<String> nameField = new TextField<String>("name",nameModel,String.class);
		nameField.setRequired(true);
		nameField.add(StringValidator.minimumLength(4));
		nameBorder.add(nameField);
		form.add(nameBorder);
		
		FormValidationBorder emailBorder = new FormValidationBorder("email");
		TextField<String> emailField = new TextField<String>("email",emailModel,String.class);
		emailField.setRequired(true);
		emailField.add(EmailAddressValidator.getInstance());
		emailBorder.add(emailField);
		form.add(emailBorder);
		
		FormValidationBorder passwdBorder = new FormValidationBorder("passwd");
		passwdBorder.add(new PasswordTextField("passwd",passwdModel).setRequired(true));
		form.add(passwdBorder);
		
		add(form);
	}
	
	static class FormBean {
		String name;
	}
}
