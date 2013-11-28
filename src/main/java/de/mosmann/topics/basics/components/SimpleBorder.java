package de.mosmann.topics.basics.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;


public class SimpleBorder extends Border {

	public SimpleBorder(String id) {
		super(id);
		
		addToBorder(new Label("before","["));
		addToBorder(new Label("after","]"));
	}

}
