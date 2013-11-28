package de.mosmann.topics;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.google.common.collect.ImmutableList;

public class OverviewPage extends WebPage {

	public OverviewPage() {

		add(new ListView<Day>("days", ImmutableList.copyOf(Day.values())) {

			@Override
			protected void populateItem(ListItem<Day> item) {
				item.add(new Label("day", item.getModel()));
				item.add(new ListView<Topic>("topics", item.getModelObject().topics()) {

					@Override
					protected void populateItem(ListItem<Topic> item) {
						Topic topic = item.getModel().getObject();
						BookmarkablePageLink<WebPage> link = new BookmarkablePageLink<WebPage>("link", topic.page());
						link.add(new Label("topic", topic));
						item.add(link);
					}
				});
			}
		});
	}
}
