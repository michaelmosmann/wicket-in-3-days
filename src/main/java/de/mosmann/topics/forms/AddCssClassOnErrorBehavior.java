package de.mosmann.topics.forms;


import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.value.IValueMap;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

public class AddCssClassOnErrorBehavior extends Behavior implements IAjaxUpdateListener {

	private ImmutableList<FormComponent> formComponents;
	private String errorClass;
	private String validClass;
	boolean hadErrors=false;
	
	private Component component;
	
	public AddCssClassOnErrorBehavior(String errorClass, String validClass) {
		this.errorClass = errorClass;
		this.validClass = validClass;
	}

	@Override
	public void bind(Component component) {
		super.bind(component);
		this.component=component;
	}
	
	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		
		if (hasAnyErrors(formComponents)) {
			appendCssClass(tag, errorClass);
			hadErrors=true;
		} else {
			if (hadValidation(formComponents)) {
				appendCssClass(tag, validClass);
			}
		}
	}

	private void appendCssClass(ComponentTag tag, String cssClass) {
		IValueMap attributes = tag.getAttributes();
		String cssClasses = attributes.getString("class");
		if (cssClasses!=null) {
			cssClasses=cssClasses+" "+cssClass;
		} else {
			cssClasses = cssClass;
		}
		attributes.put("class", cssClasses);
	}
	
	private static boolean hasAnyErrors(ImmutableList<FormComponent> formComponents) {
		return !Collections2.filter(formComponents, new Predicate<FormComponent>() {
			@Override
			public boolean apply(FormComponent input) {
				return input.hasErrorMessage();
			}
		}).isEmpty();
	}

	private static boolean hadValidation(ImmutableList<FormComponent> formComponents) {
		return !Collections2.filter(formComponents, new Predicate<FormComponent>() {
			@Override
			public boolean apply(FormComponent input) {
				return FormValidationMagic.hadValidation(input);
			}
		}).isEmpty();
	}

	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);

		if (component instanceof MarkupContainer) {

			MarkupContainer container = (MarkupContainer) component;

			this.formComponents=ImmutableList.copyOf(Forms.findFormComponents(container));
		} else {
			throw new WicketRuntimeException("You must bind this behavior to a MarkupContainer");
		}
	}

	@Override
	public void onUpdate(AjaxRequestTarget target) {
		if (hasAnyErrors(formComponents)) {
			target.appendJavaScript("jQuery('#"+component.getMarkupId()+ "').addClass('"+errorClass+"').removeClass('"+validClass+"')");
		} else {
			target.appendJavaScript("jQuery('#"+component.getMarkupId()+ "').addClass('"+validClass+"').removeClass('"+errorClass+"')");
		}
	}
}