package de.mosmann.loginmgmt;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import de.mosmann.loginmgmt.components.EditLoginPanel;
import de.mosmann.loginmgmt.components.LoginsPanel;
import de.mosmann.loginmgmt.components.NewLoginPanel;


public class LoginManagementPage extends LoginBasePage {

	
	public LoginManagementPage() {
		add(new FeedbackPanel("feedback").setOutputMarkupId(true));
		
		add(new NewLoginPanel("newLogin"));
		add(new EditLoginPanel("editLogin"));
		add(new LoginsPanel("logins"));
	}
}
