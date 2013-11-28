package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import de.mosmann.topics.BasePage;


public class FormsPage extends BasePage {

	
	public FormsPage() {
		add(new FeedbackPanel("feedback"));
		
		add(new MagicFormPanel("magic", Model.of(new FormData())));
	}
}
