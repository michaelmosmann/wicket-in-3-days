package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import de.mosmann.topics.BasePage;
import de.mosmann.topics.basics.data.FormData;

public class FormsPage extends BasePage {

	public FormsPage() {
		add(new FeedbackPanel("feedback"));

		Model<FormData> model = Model.of(new FormData());

		add(new MagicFormPanel("magic", model));
		add(new PropertyFormPanel("property", model));
		add(new TypeSafeFormPanel("typesafe", model));
	}
}
