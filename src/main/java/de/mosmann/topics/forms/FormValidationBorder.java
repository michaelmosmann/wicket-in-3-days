package de.mosmann.topics.forms;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

public class FormValidationBorder extends Border implements IAjaxUpdateListener {

	private WebMarkupContainer container;
	private AddCssClassOnErrorBehavior cssMarker;
	private Component feedbackPanel;

	public FormValidationBorder(String id) {
		super(id);

		final WebMarkupContainer box = new WebMarkupContainer("box");
		box.setOutputMarkupId(true);
		AddCssClassOnErrorBehavior behavior = new AddCssClassOnErrorBehavior("error", "success");
		box.add(behavior);

		//textField.add(new KeyUpValidationBehavior(form, "onkeyup", box));

		Component feedbackPanel = new FeedbackPanel("feedback", new ContainerFeedbackMessageFilter(box)).setOutputMarkupId(true);
		box.add(feedbackPanel);

		addToBorder(box);
		box.add(getBodyContainer());

		this.container = box;
		this.cssMarker = behavior;
		this.feedbackPanel = feedbackPanel;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		List<FormComponent> components = Forms.findFormComponents(container);
		for (FormComponent component : components) {
			component.add(new KeyUpValidationBehavior("onkeyup", new AjaxUpdateListeners(cssMarker, this)));
		}

	}

	@Override
	public void onUpdate(AjaxRequestTarget target) {
		target.add(feedbackPanel);
	}
}
