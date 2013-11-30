package de.mosmann.topics.basics.events;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class ActionPanel extends Panel {

	public ActionPanel(String id,boolean useAjax, int delta) {
		super(id);
		
		add(new Label("ajax", useAjax ? "yes" : "no"));
		
		IDeltaLinkFactory linkFactory = useAjax ? new AjaxDeltaLinkFactory() : new DeltaLinkFactory();
		AbstractLink incLink = linkFactory.newInstance("inc",delta);
		incLink.add(new Label("value",""+delta));
		add(incLink);
		
		AbstractLink decLink = linkFactory.newInstance("dec",-delta);
		decLink.add(new Label("value",""+(-delta)));
		add(decLink);
	}

	interface IDeltaLinkFactory extends Serializable {
		AbstractLink newInstance(String id, int delta);
	}

	static class DeltaLinkFactory implements IDeltaLinkFactory {

		@Override
		public AbstractLink newInstance(String id, int delta) {
			return new Link<Integer>(id,Model.of(delta)) {
				@Override
				public void onClick() {
					send(getPage(), Broadcast.BREADTH, new Increment(getModelObject()));
				}
			};
		}
	}
	
	static class AjaxDeltaLinkFactory implements IDeltaLinkFactory {

		@Override
		public AbstractLink newInstance(String id, int delta) {
			return new AjaxFallbackLink<Integer>(id,Model.of(delta)) {
				@Override
				public void onClick(AjaxRequestTarget target) {
					send(getPage(), Broadcast.BREADTH, new Increment(getModelObject()));
					
				}
			};
		}
		
	}
}
