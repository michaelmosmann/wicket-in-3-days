package de.mosmann.topics.basics.models;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import de.mosmann.topics.BasePage;


public class ModelsPage extends BasePage {

	
	public ModelsPage() {
		add(new BookmarkablePageLink<>("self", ModelsPage.class));
		add(new Link<Void>("noop"){
			@Override
			public void onClick() {
				
			}
		});
		add(new ShowDatePanel("showDate"));
	}
}
