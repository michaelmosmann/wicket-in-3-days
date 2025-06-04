package de.mosmann.topics.basics.components;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteTextRenderer;

public class CountryAutoCompleteRenderer extends AbstractAutoCompleteTextRenderer<Country> {
    @Override
    protected String getTextValue(Country country) {
        // kann man das überhaupt unabhängig vom converter darstellen?
        return country.getDisplayName();
    }
}
