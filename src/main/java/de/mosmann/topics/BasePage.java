package de.mosmann.topics;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class BasePage extends WebPage {

	public BasePage() {
		String title = getClass().getSimpleName().replace("Page", "");
		add(new Label("_title_tag", title));
		add(new Label("_title", title));
		
		BookmarkablePageLink<OverviewPage> link = new BookmarkablePageLink<OverviewPage>("_back",OverviewPage.class);
		add(link);
		
		if (getClass()==OverviewPage.class) {
			link.setVisible(false);
		}
	}
}
