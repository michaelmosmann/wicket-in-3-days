package de.mosmann.topics.forms;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;


public class FormValidationBorder extends Border {

	public FormValidationBorder(String id) {
		super(id);
		
		final WebMarkupContainer box = new WebMarkupContainer("box");
		box.setOutputMarkupId(true);
		box.add(new AddCssClassOnErrorBehavior("error", "success"));

		//textField.add(new KeyUpValidationBehavior(form, "onkeyup", box));
		
		box.add(new FeedbackPanel("feedback", new ContainerFeedbackMessageFilter(box)).setOutputMarkupId(true));
		
		addToBorder(box);
		box.add(getBodyContainer());
	}

}
