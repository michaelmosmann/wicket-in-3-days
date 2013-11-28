package de.mosmann.topics.basics.forms.models;

import de.mosmann.topics.basics.forms.FormData;
import de.mosmann.topics.basics.forms.models.property.AbstractPropertyAccess;


public class Size extends AbstractPropertyAccess<Integer, FormData> {

	public Size() {
		super(int.class);
	}

	@Override
	public Integer read(FormData source) {
		return source.getSize();
	}

	@Override
	public void write(FormData source, Integer newValue) {
		source.setSize(newValue);
	}
}
