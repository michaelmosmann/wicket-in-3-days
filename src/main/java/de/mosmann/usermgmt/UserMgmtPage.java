package de.mosmann.usermgmt;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import de.mosmann.topics.BasePage;


public class UserMgmtPage extends BasePage {
	
	public UserMgmtPage() {
		add(new FeedbackPanel("feedback"));
		add(new FeedbackPanel("feedback2"));
		add(new NewUserPanel("new"));
	}
}
