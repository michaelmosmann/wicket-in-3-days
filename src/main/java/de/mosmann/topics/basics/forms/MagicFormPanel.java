package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

import de.mosmann.topics.basics.data.FormData;

public class MagicFormPanel extends AbstractSameMarkupFormPanel {

	public MagicFormPanel(String id, final IModel<FormData> model) {
		super(id);

		Form<FormData> form = new Form<FormData>("form", new CompoundPropertyModel<FormData>(model)) {

			@Override
			protected void onSubmit() {
				super.onSubmit();
				info("submit done with " + getModelObject());
			}
		};
		TextField<Object> nameField = new TextField<>("name");
		nameField.setRequired(true);
		nameField.add(StringValidator.minimumLength(2));
		nameField.add(new TripleAValidator());
		form.add(nameField);
		form.add(new TextField<>("size"));
		form.add(new TextField<>("birthday"));
		form.add(new Button("submit") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				info("submit by button done with " + model.getObject());
			}
		});
		add(form);
	}
}
