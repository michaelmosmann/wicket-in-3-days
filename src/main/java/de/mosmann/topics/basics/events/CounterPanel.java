package de.mosmann.topics.basics.events;

import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;

public class CounterPanel extends Panel {

	private Model<Integer> _counter;
	private Label _label;

	public CounterPanel(String id) {
		super(id);

		_counter = Model.of(0);

		_label = new Label("counter", _counter);
		_label.setOutputMarkupId(true);
		
		add(_label);
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);
		
		Object payload = event.getPayload();
		if (payload instanceof Increment) {
			_counter.setObject(_counter.getObject()+ ((Increment) payload).delta());
		
			AjaxRequestTarget ajaxRequestTarget = RequestCycle.get().find(AjaxRequestTarget.class);
			if (ajaxRequestTarget!=null) {
				ajaxRequestTarget.add(_label);
			}
		}
	}
}
