package de.mosmann.topics.forms;

import de.mosmann.topics.BasePage;
import de.mosmann.topics.converter.Money;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class FormValidationComponentsPage extends BasePage {

	public FormValidationComponentsPage() {

		add(new FeedbackPanel("feedback").setOutputMarkupId(true));

		final Model<Money> moneyModel = new Model<Money>();
		
		Form<Void> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				
				info("Money: "+moneyModel.getObject());
			}
		};

		FormValidationBorder box=new FormValidationBorder("box");

		final TextField<String> textField = new TextField<>("name", Model.of(""));
		textField.setRequired(true);
		textField.add(StringValidator.minimumLength(4));

		box.add(textField);
	
		FormValidationBorder box2=new FormValidationBorder("box2");

		final TextField<String> textField2 = new TextField<>("vorname", Model.of(""));
		textField2.setRequired(true);
		textField2.add(StringValidator.minimumLength(4));
		
		box2.add(textField2);

		FormValidationBorder box3=new FormValidationBorder("box3");

		final TextField<Integer> textField3 = new TextField<>("alter", new Model<Integer>(),Integer.class);
		textField3.setRequired(true);
		textField3.add(RangeValidator.minimum(21));
		
		box3.add(textField3);

		FormValidationBorder box4=new FormValidationBorder("box4");

		final TextField<Money> textField4 = new TextField<>("money", moneyModel,Money.class);
		textField4.setRequired(true);
		
		box4.add(textField4);

		form.add(box);
		form.add(box2);
		form.add(box3);
		form.add(box4);
		add(form);
	}
}
