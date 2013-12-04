package de.mosmann.topics.converter;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

public class MoneyConverter implements IConverter<Money> {

	@Override
	public Money convertToObject(String value, Locale locale) throws ConversionException {
		if (StringUtils.isNotEmpty(value)) {
			String regex = "([0-9]+) ([a-zA-Z€]+)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches()) {
				String count = matcher.group(1);
				String currency = matcher.group(2);
				if (StringUtils.isNotEmpty(currency)) {
					return new Money(Integer.valueOf(count),currency);
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
