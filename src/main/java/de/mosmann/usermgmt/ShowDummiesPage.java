package de.mosmann.usermgmt;

import de.mosmann.topics.BasePage;


public class ShowDummiesPage extends BasePage {

	public ShowDummiesPage() {
		add(new ShowDummiesPanel("dummyPanel"));
		add(new ShowDummiesAsTablePanel("table"));
	}
	
	

}
