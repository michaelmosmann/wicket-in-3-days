package de.mosmann.topics.basics.components;

import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;

public class CountryConverter implements IConverter<Country> {

    @Override
    public Country convertToObject(String value, Locale locale) throws ConversionException {
        if (!Strings.isEmpty(value)) {
            for (Country country : Country.values()) {
                if (country.getDisplayName().equals(value)) {
                    return country;
                }
            }
            throw new ConversionException("Could not convert '" + value + "' to Country")
                    .setSourceValue(value)
                    .setTargetType(Country.class)
                    .setConverter(this)
                    .setLocale(locale);
        }
        return null;
    }

    @Override
    public String convertToString(Country value, Locale locale) {
        return value != null ? value.getDisplayName() : "";
    }
}