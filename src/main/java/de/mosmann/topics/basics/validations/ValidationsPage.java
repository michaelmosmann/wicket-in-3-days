package de.mosmann.topics.basics.validations;

import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import de.mosmann.topics.BasePage;
import de.mosmann.topics.basics.data.FormData;

public class ValidationsPage extends BasePage {

	public ValidationsPage() {
		add(new FeedbackPanel("feedback").setOutputMarkupId(true));

		add(new FormPanel("default", Model.of(new FormData())));
	}
	
}
