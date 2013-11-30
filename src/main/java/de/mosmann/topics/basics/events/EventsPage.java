package de.mosmann.topics.basics.events;

import de.mosmann.topics.BasePage;


public class EventsPage extends BasePage {

	
	public EventsPage() {
		add(new CounterPanel("counter"));
		
		add(new ActionPanel("changeOne", false, 1));
		add(new ActionPanel("changeOneAjax", true, 1));
		add(new ActionPanel("changeFive", false, 5));
		add(new ActionPanel("changeFiveAjax", true, 5));
	}
}
