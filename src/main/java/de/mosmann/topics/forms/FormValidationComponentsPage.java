package de.mosmann.topics.forms;

import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.ThrottlingSettings;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.validation.validator.StringValidator;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import de.mosmann.topics.BasePage;

public class FormValidationComponentsPage extends BasePage {

	public FormValidationComponentsPage() {

		add(new FeedbackPanel("feedback").setOutputMarkupId(true));

		Form<Void> form = new Form<Void>("form");

		final WebMarkupContainer box = new WebMarkupContainer("box");
		box.setOutputMarkupId(true);
		box.add(new AddCssClassOnErrorBehavior());

		final TextField<String> textField = new TextField<>("name", Model.of(""));
		textField.setRequired(true);
		textField.add(StringValidator.minimumLength(4));

		textField.add(new AjaxFormValidatingBehavior(form, "onkeyup") {

			@Override
			protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
				super.updateAjaxAttributes(attributes);

				attributes.setThrottlingSettings(new ThrottlingSettings("throttling-" + textField.getMarkupId(),
						Duration.milliseconds(500)));
				attributes.setAllowDefault(true);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				super.onError(target);

				target.add(box);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				
				target.add(box);
			}
		});

		box.add(textField);
		form.add(box);
		add(form);
	}

	static class AddCssClassOnErrorBehavior extends Behavior {

		private ImmutableList<FormComponent> formComponents;
		static final String ERROR_CLASS="error";

		@Override
		public void onComponentTag(Component component, ComponentTag tag) {
			super.onComponentTag(component, tag);
			
			IValueMap attributes = tag.getAttributes();
			if (hasAnyErrors(formComponents)) {
				String cssClasses = attributes.getString("class");
				if (cssClasses!=null) {
					cssClasses=cssClasses+" "+ERROR_CLASS;
				} else {
					cssClasses = ERROR_CLASS;
				}
				attributes.put("class", cssClasses);
			}
		}
		
		private static boolean hasAnyErrors(ImmutableList<FormComponent> formComponents) {
			return !Collections2.filter(formComponents, new Predicate<FormComponent>() {
				@Override
				public boolean apply(FormComponent input) {
					return input.hasErrorMessage();
				}
			}).isEmpty();
		}

		@Override
		public void onConfigure(Component component) {
			super.onConfigure(component);

			if (component instanceof MarkupContainer) {

				MarkupContainer container = (MarkupContainer) component;

				final List<FormComponent> formComponents = Lists.newArrayList();

				container.visitChildren(FormComponent.class, new IVisitor<FormComponent, Void>() {

					@Override
					public void component(final FormComponent component, final IVisit<Void> visit) {
						formComponents.add(component);
					}
				});

				this.formComponents=ImmutableList.copyOf(formComponents);
			} else {
				throw new WicketRuntimeException("You must bind this behavior to a MarkupContainer");
			}
		}
		
		@Override
		public void bind(Component component) {
			super.bind(component);

		}

	}
}
