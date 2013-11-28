package de.mosmann.topics.advanced.persistence;

import de.mosmann.topics.BasePage;

public class ManageUsersPage extends BasePage {

	public ManageUsersPage() {
		add(new ShowUserPanel("showUser"));
	}
}
