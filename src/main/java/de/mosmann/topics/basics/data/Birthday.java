package de.mosmann.topics.basics.data;

import java.util.Date;

import de.mosmann.common.models.property.AbstractPropertyAccess;

public class Birthday extends AbstractPropertyAccess<Date, FormData> {

	public Birthday() {
		super(Date.class);
	}

	@Override
	public Date read(FormData source) {
		return source.getBirthday();
	}

	@Override
	public void write(FormData source, Date newValue) {
		source.setBirthday(newValue);
	}
}
