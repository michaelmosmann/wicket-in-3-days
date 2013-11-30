package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
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
		};
		form.add(new TextField<>("name",new PropertyModel<>(model, "name")));
		form.add(new TextField<>("size",new PropertyModel<>(model, "size")));
		form.add(new TextField<>("birthday",new PropertyModel<>(model, "birthday")));
		
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
