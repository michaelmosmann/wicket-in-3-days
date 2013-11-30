package de.mosmann.topics.basics.data;

import de.mosmann.common.models.property.AbstractPropertyAccess;


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
