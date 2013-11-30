package de.mosmann.topics.basics.data;

import de.mosmann.common.models.property.AbstractPropertyAccess;


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
