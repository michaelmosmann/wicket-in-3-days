package de.mosmann.topics.basics.markupmagic;

import org.apache.wicket.markup.html.WebPage;

import de.mosmann.topics.BasePage;

public class MarkupMagicPage extends BasePage {

	public MarkupMagicPage() {
		add(new SimplePanel("simple"));
		add(new BasePanel("base"));
		add(new OverridePanel("override"));
		add(new ExtendPanel("extend"));
	}
}
