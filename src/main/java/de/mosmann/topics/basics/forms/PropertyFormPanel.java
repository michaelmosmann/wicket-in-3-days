package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormValidatorAdapter;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.mosmann.topics.basics.data.FormData;


public class PropertyFormPanel extends AbstractSameMarkupFormPanel  {

	public PropertyFormPanel(String id, final IModel<FormData> model) {
		super(id);

		Form<FormData> form = new Form<FormData>("form", model) {

			@Override
			protected void onSubmit() {
				super.onSubmit();
				info("submit done with " + getModelObject());
			}
			
			@Override
			public void onError() {
				super.onError();
				error("me not happy");
			}
			
			
		};
		final TextField<String> nameField = new TextField<String>("name",new PropertyModel<String>(model, "name"));
		nameField.setRequired(true);

		final TextField<Integer> sizeField = new TextField<Integer>("size",new PropertyModel<Integer>(model, "size"));

		form.add(nameField);
		form.add(sizeField);
		form.add(new TextField<>("birthday",new PropertyModel<>(model, "birthday")));
		
		form.add(new Button("submit") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				info("submit by button done with " + model.getObject());
			}
		});
		form.add(new FormValidatorAdapter(new IFormValidator() {
			
			@Override
			public void validate(Form<?> form) {
				String nameConv = nameField.getConvertedInput();
				int sizeConv=sizeField.getConvertedInput();
				if (nameConv.equals("Klaus") && (sizeConv>55)) {
					nameField.error("Nee.. die bist nicht Klaus");
					
				}
			}
			
			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[]{sizeField,nameField};
			}
		}));
		
		add(form);
	}
}
