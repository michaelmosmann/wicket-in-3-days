package de.mosmann.topics.forms;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.html.form.FormComponent;


public class FormValidationMagic {

	static MetaDataKey<Boolean> HAD_STUFF=new MetaDataKey<Boolean>() {};
	
	public static void markAsValidationTriggered(Component formCompent) {
		formCompent.setMetaData(HAD_STUFF, true);
	}

	public static boolean hadValidation(Component formCompent) {
		Boolean ret = formCompent.getMetaData(HAD_STUFF);
		return ret!=null ? ret : false;
	}
}
