package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import de.mosmann.topics.basics.forms.models.property.PropertyModels;
import de.mosmann.topics.basics.forms.models.property.PropertyModels.OnModel;

public class TypeSafeFormPanel extends AbstractSameMarkupFormPanel {

	public TypeSafeFormPanel(String id, final IModel<FormData> model) {
		super(id);

		Form<FormData> form = new Form<FormData>("form", model) {

			@Override
			protected void onSubmit() {
				super.onSubmit();
				info("submit done with " + getModelObject());
			}
		};
		OnModel<FormData> onModel = PropertyModels.on(model);

		form.add(new TextField<>("name", onModel.property(FormData.Name)));
		form.add(new TextField<>("size", onModel.property(FormData.Size)));
		form.add(new TextField<>("birthday", onModel.property(FormData.Birthday)));

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
