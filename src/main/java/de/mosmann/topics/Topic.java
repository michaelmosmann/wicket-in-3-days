package de.mosmann.topics;

import java.util.Objects;

import de.mosmann.topics.basics.components.AutoCompleteTextFieldPage;
import org.apache.wicket.markup.html.WebPage;

import de.mosmann.topics.advanced.persistence.ManageUsersPage;
import de.mosmann.topics.basics.components.ComponentOverviewPage;
import de.mosmann.topics.basics.events.EventsPage;
import de.mosmann.topics.basics.forms.FormsPage;
import de.mosmann.topics.basics.markupmagic.MarkupMagicPage;
import de.mosmann.topics.basics.models.ModelsPage;
import de.mosmann.topics.basics.validations.ValidationsPage;
import de.mosmann.topics.converter.ConverterPage;
import de.mosmann.topics.forms.FormValidationComponentsPage;
import de.mosmann.topics.paging.PagingPage;
import de.mosmann.topics.playground.PlaygroundPage;


public enum Topic {

	Components(ComponentOverviewPage.class),
	AutoComplete(AutoCompleteTextFieldPage.class),
	MarkupMagic(MarkupMagicPage.class),
	Forms(FormsPage.class),
	Validation(ValidationsPage.class),
	Events(EventsPage.class),
	Models(ModelsPage.class),
	
	FormValidationComponents(FormValidationComponentsPage.class),
	//Behaviors
	Converter(ConverterPage.class),
	Paging(PagingPage.class),
	
	Persistence(ManageUsersPage.class),
	
	Playground(PlaygroundPage.class);
	
	private final Class<? extends WebPage> _page;

	private Topic(Class<? extends WebPage> page) {
		_page = Objects.requireNonNull(page,"page is null");
	}
	
	public Class<? extends WebPage> page() {
		return _page;
	}
}
