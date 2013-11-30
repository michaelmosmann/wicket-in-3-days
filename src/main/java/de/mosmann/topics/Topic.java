package de.mosmann.topics;

import java.util.Objects;

import org.apache.wicket.markup.html.WebPage;

import de.mosmann.topics.advanced.persistence.ManageUsersPage;
import de.mosmann.topics.basics.components.ComponentOverviewPage;
import de.mosmann.topics.basics.forms.FormsPage;
import de.mosmann.topics.basics.markupmagic.MarkupMagicPage;
import de.mosmann.topics.basics.validations.ValidationsPage;


public enum Topic {

	Components(ComponentOverviewPage.class),
	MarkupMagic(MarkupMagicPage.class),
	Forms(FormsPage.class),
	Validation(ValidationsPage.class),
	
	Persistence(ManageUsersPage.class);
	
	private final Class<? extends WebPage> _page;

	private Topic(Class<? extends WebPage> page) {
		_page = Objects.requireNonNull(page,"page is null");
	}
	
	public Class<? extends WebPage> page() {
		return _page;
	}
}
