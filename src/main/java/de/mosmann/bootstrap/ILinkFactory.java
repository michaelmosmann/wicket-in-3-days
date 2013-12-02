package de.mosmann.bootstrap;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.AbstractLink;


public interface ILinkFactory extends Serializable {
	AbstractLink link(String id);
	boolean isActive();
}
