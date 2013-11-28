package de.mosmann.topics.basics.forms.models;

import java.util.Date;

import de.mosmann.topics.basics.forms.FormData;
import de.mosmann.topics.basics.forms.models.property.AbstractPropertyAccess;

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
