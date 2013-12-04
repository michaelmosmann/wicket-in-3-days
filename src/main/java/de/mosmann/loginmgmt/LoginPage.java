package de.mosmann.loginmgmt;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import de.mosmann.loginmgmt.components.LoginPanel;


public class LoginPage extends LoginBasePage {

	
	public LoginPage() {
		add(new FeedbackPanel("feedback").setOutputMarkupId(true));
		add(new LoginPanel("login"));
	}
}
