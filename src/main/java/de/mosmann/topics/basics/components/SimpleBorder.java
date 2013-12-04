package de.mosmann.topics.basics.components;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.request.resource.CssPackageResource;
import org.apache.wicket.request.resource.CssResourceReference;


public class SimpleBorder extends Border {

	public SimpleBorder(String id) {
		super(id);
		
		addToBorder(new Label("before","["));
		addToBorder(new Label("after","]"));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem.forReference(new CssResourceReference(SimpleBorder.class, "my.css")));
	}
}
