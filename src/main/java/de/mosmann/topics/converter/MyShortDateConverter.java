package de.mosmann.topics.converter;

import java.text.DateFormat;
import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;


public class MyShortDateConverter implements IConverter<ShortDate> {

	@Override
	public ShortDate convertToObject(String value, Locale locale) throws ConversionException {
		return null;
	}

	@Override
	public String convertToString(ShortDate value, Locale locale) {
		return getDateFormat(locale).format(value.getDate());
	}
	
	private DateFormat getDateFormat(Locale locale) {
		if (locale == null)
		{
			locale = Locale.getDefault();
		}
		return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
	}

	
}