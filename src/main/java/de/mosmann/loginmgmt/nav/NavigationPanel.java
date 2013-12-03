package de.mosmann.loginmgmt.nav;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;


public class NavigationPanel extends Panel {

	public NavigationPanel(String id,INavigationEntry... navEntries) {
		this(id,Lists.newArrayList(navEntries));
	}
	
	public NavigationPanel(String id,List<INavigationEntry> navEntries) {
		super(id);
		
		add(new ListView<INavigationEntry>("list",Model.ofList(navEntries)) {
			@Override
			protected void populateItem(ListItem<INavigationEntry> item) {
				INavigationEntry entry = item.getModelObject();
				AbstractLink link = entry.newLink("link");
				link.add(new Label("label",entry.name()));
				item.add(link);
			}
		});
	}

}
