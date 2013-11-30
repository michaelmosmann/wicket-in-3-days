package de.mosmann.topics.basics.validations;

import java.util.Date;

import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.StringValidator;

import de.mosmann.common.models.property.PropertyModels;
import de.mosmann.common.models.property.PropertyModels.OnModel;
import de.mosmann.topics.basics.data.FormData;

public class FormPanel extends Panel {

	public FormPanel(String id, final IModel<FormData> model) {
		super(id);

		Form<FormData> form = new Form<FormData>("form", model) {

			@Override
			protected void onSubmit() {
				super.onSubmit();
				info("submit done with " + getModelObject());
			}
		};
		OnModel<FormData> onModel = PropertyModels.on(model);

		TextField<String> nameField = new TextField<>("name", onModel.property(FormData.Name));
		TextField<Integer> sizeField = new TextField<>("size", onModel.property(FormData.Size));
		TextField<Date> birtdayField = new TextField<>("birthday", onModel.property(FormData.Birthday));
		
		nameField.add(StringValidator.minimumLength(2));
		
		FormComponentFeedbackBorder nameBorder = new FormComponentFeedbackBorder("nameBorder");
		nameBorder.setOutputMarkupId(true);
		nameBorder.add(nameField);
		
		form.add(nameBorder);
		form.add(sizeField);
		form.add(birtdayField);
		
		form.add(new Button("submit") {

			@Override
			public void onSubmit() {
				super.onSubmit();
				info("submit by button done with " + model.getObject());
			}
		});
		add(form);
		
		AjaxFormValidatingBehavior.addToAllFormComponents(form, "onkeyup",Duration.milliseconds(500));
		AjaxFormValidatingBehavior.addToAllFormComponents(form, "onblur");
	}
}
