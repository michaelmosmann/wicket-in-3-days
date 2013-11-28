package de.mosmann.topics.basics.forms.models;

import de.mosmann.topics.basics.forms.FormData;
import de.mosmann.topics.basics.forms.models.property.AbstractPropertyAccess;


public class Name extends AbstractPropertyAccess<String, FormData> {

	public Name() {
		super(String.class);
	}

	@Override
	public String read(FormData source) {
		return source.getName();
	}

	@Override
	public void write(FormData source, String newValue) {
		source.setName(newValue);
	}
}
