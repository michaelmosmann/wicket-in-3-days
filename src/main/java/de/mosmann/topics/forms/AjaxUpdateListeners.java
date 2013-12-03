package de.mosmann.topics.forms;

import org.apache.wicket.ajax.AjaxRequestTarget;


public class AjaxUpdateListeners implements IAjaxUpdateListener {

	
	private final IAjaxUpdateListener[] _listener;

	public AjaxUpdateListeners(IAjaxUpdateListener... listener) {
		_listener = listener;
	}
	
	@Override
	public void onUpdate(AjaxRequestTarget target) {
		for (IAjaxUpdateListener listener : _listener) {
			listener.onUpdate(target);
		}
	}

}
