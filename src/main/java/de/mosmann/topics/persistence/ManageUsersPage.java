package de.mosmann.topics.persistence;

import org.apache.wicket.markup.html.WebPage;


public class ManageUsersPage extends WebPage {

	
	public ManageUsersPage() {
		add(new ShowUserPanel("showUser"));
	}
}
