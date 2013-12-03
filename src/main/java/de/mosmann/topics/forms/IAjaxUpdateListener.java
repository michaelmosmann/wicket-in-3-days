package de.mosmann.topics.forms;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;


public interface IAjaxUpdateListener extends Serializable {

	void onUpdate(AjaxRequestTarget target);

}
