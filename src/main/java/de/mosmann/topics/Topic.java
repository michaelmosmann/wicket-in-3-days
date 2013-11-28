package de.mosmann.topics;

import java.util.Objects;

import org.apache.wicket.markup.html.WebPage;

import de.mosmann.topics.persistence.ManageUsersPage;


public enum Topic {

	Persistence(ManageUsersPage.class);
	
	private final Class<? extends WebPage> _page;

	private Topic(Class<? extends WebPage> page) {
		_page = Objects.requireNonNull(page,"page is null");
	}
	
	public Class<? extends WebPage> page() {
		return _page;
	}
}
