package de.mosmann.topics.basics.models;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;


public class ShowDatePanel extends Panel {

	public ShowDatePanel(String id) {
		super(id);
		
		Date now=now();
		
		add(new Label("nowAsString",""+now));
		add(new Label("nowAsModel",Model.of(now)));
		add(new Label("nowAsLDM",new LoadableDetachableModel<Date>() {
			@Override
			protected Date load() {
				return now();
			}
		}));
	}

	private static Date now() {
		return new Date();
	}

}
