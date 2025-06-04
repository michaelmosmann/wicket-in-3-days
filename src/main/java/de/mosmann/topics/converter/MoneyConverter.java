package de.mosmann.topics.converter;

import com.google.common.base.Strings;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyConverter implements IConverter<Money> {

	@Override
	public Money convertToObject(String value, Locale locale) throws ConversionException {
		if (!Strings.isNullOrEmpty(value)) {
			String regex = "([0-9]+) ([a-zA-Z€]+)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches()) {
				String count = matcher.group(1);
				String currency = matcher.group(2);
				if (!Strings.isNullOrEmpty(currency)) {
					return new Money(Integer.parseInt(count),currency);
				}
			}
			throw new ConversionException("Could not convert "+value).setSourceValue(value)
					.setTargetType(Money.class)
					.setConverter(this)
					.setLocale(locale);
		}
		return null;
	}

	@Override
	public String convertToString(Money value, Locale locale) {
		return value!=null ? "" + value.count() + " " + value.currency() : "";
	}

}
