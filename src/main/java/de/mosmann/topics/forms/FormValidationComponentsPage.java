package de.mosmann.topics.forms;

import java.util.Collection;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;


import de.mosmann.topics.BasePage;

public class FormValidationComponentsPage extends BasePage {

	public FormValidationComponentsPage() {

		add(new FeedbackPanel("feedback").setOutputMarkupId(true));

		Form<Void> form = new Form<Void>("form");

		final WebMarkupContainer box = new WebMarkupContainer("box");
		box.setOutputMarkupId(true);
		box.add(new AddCssClassOnErrorBehavior("error", "success"));

		final TextField<String> textField = new TextField<>("name", Model.of(""));
		textField.setRequired(true);
		textField.add(StringValidator.minimumLength(4));

		textField.add(new KeyUpValidationBehavior(form, "onkeyup", box));

		
		final WebMarkupContainer box2 = new WebMarkupContainer("box2");
		box2.setOutputMarkupId(true);
		box2.add(new AddCssClassOnErrorBehavior("error", "success"));

		final TextField<String> textField2 = new TextField<>("vorname", Model.of(""));
		textField2.setRequired(true);
		textField2.add(StringValidator.minimumLength(4));

		textField2.add(new KeyUpValidationBehavior(form, "onkeyup", box2));

		FormValidationBorder box3=new FormValidationBorder("box3");

		final TextField<Integer> textField3 = new TextField<>("alter", new Model<Integer>(),Integer.class);
		textField3.setRequired(true);
		textField3.add(RangeValidator.minimum(21));
		
		box3.add(textField3);

		form.add(box);
		form.add(box2);
		form.add(box3);
		box.add(textField);
		box2.add(textField2);
		add(form);
	}
}
