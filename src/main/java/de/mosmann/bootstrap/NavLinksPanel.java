package de.mosmann.bootstrap;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class NavLinksPanel extends Panel {

	public NavLinksPanel(String id, IModel<List<ILinkFactory>> linkFactories) {
		super(id);
		
		add(new ListView<ILinkFactory>("links",linkFactories) {
			@Override
			protected void populateItem(ListItem<ILinkFactory> item) {
				ILinkFactory linkFactory = item.getModelObject();
				AbstractLink link = linkFactory.link("link");
				item.add(link);
				
				item.add(new AttributeAppender("class", Model.of(linkFactory.isActive() ? "active" : "")));
			}
		});
	}

}
