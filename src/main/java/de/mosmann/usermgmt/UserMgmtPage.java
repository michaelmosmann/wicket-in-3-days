package de.mosmann.usermgmt;

import de.mosmann.topics.BasePage;


public class UserMgmtPage extends BasePage {
	
	public UserMgmtPage() {
		add(new NewUserPanel("new"));
	}
}
