package de.mosmann.topics.basics.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.PropertyModel;

import de.mosmann.topics.basics.forms.models.Birthday;
import de.mosmann.topics.basics.forms.models.Name;
import de.mosmann.topics.basics.forms.models.Size;
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
		
		form.add(new TextField<>("name", onModel.property(new Name())));
		form.add(new TextField<>("size",onModel.property(new Size())));
		form.add(new TextField<>("birthday",onModel.property(new Birthday())));
		
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
