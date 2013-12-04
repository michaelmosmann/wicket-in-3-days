package de.mosmann.loginmgmt.nav;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.AbstractLink;

public interface INavigationEntry extends Serializable {

	AbstractLink newLink(String string);

	String name();
}
