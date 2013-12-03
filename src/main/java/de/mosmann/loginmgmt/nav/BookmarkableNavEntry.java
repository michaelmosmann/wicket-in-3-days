package de.mosmann.loginmgmt.nav;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import de.mosmann.loginmgmt.LoginBasePage;

public class BookmarkableNavEntry<T extends LoginBasePage> implements INavigationEntry {

	private final Class<T> _pageClass;
	private final String _name;

	public BookmarkableNavEntry(Class<T> pageClass, String title) {
		_pageClass = pageClass;
		_name = title;
	}

	@Override
	public AbstractLink newLink(String id) {
		return new BookmarkablePageLink<T>(id, _pageClass);
	}

	@Override
	public String name() {
		return this._name;
	}

}
