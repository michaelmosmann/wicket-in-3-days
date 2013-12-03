package de.mosmann.topics.forms;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.ThrottlingSettings;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.time.Duration;

public final class KeyUpValidationBehavior extends AjaxFormComponentUpdatingBehavior {

	/**
	 * 
	 */
	private final IAjaxUpdateListener _listener;

	public KeyUpValidationBehavior(String event, IAjaxUpdateListener listener) {
		super(event);
		_listener = listener;
	}
	
	@Override
	protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
		super.updateAjaxAttributes(attributes);

		attributes.setThrottlingSettings(new ThrottlingSettings("throttling-" + getComponent().getMarkupId(),
				Duration.milliseconds(500)));
		attributes.setAllowDefault(true);
	}

	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		_listener.onUpdate(target);

		FormValidationMagic.markAsValidationTriggered(getComponent());
	}
	
	@Override
	protected void onError(AjaxRequestTarget target, RuntimeException e) {
		super.onError(target, e);
		_listener.onUpdate(target);
		
		FormValidationMagic.markAsValidationTriggered(getComponent());
	}
}