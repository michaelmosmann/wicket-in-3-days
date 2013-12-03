package de.mosmann.loginmgmt.nav;

import org.apache.wicket.markup.html.link.AbstractLink;

public interface INavigationEntry {

	AbstractLink newLink(String string);

	String name();
}
