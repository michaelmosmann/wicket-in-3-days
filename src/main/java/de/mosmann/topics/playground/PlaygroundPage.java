package de.mosmann.topics.playground;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import de.mosmann.topics.BasePage;


public class PlaygroundPage extends BasePage {

	
	public PlaygroundPage() {
		final Model<String> model = Model.of("border: 1px solid red");
		final Model<Integer> counter = Model.of(0);
		
		final MyContainer comp = new MyContainer("container");
		comp.add(new AttributeAppender("style", model));
		comp.add(new AjaxEventBehavior("onclick") {
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				model.setObject("border: 2px");
				comp._counter++;
				counter.setObject(comp._counter);
				target.add(comp);
			}
		});
		comp.add(new Behavior() {
			@Override
			public void onComponentTag(Component component, ComponentTag tag) {
				tag.getAttributes().put("foo", "bar");
			}
		});
		comp.add(new Label("counter",counter));
		add(comp);
		
		add(new Link<Void>("link") {
			@Override
			public void onClick() {
				model.setObject("border: 1px solid red");
			}
		});

		add(new Link<Void>("link2") {
			@Override
			public void onClick() {
				model.setObject("border: 1px solid blue");
			}
		});
	}
	
	static class MyContainer extends WebMarkupContainer {

		int _counter=0;
		
		public MyContainer(String id) {
			super(id);
		}
		
	}
}
