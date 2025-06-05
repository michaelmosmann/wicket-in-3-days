package de.mosmann.topics.basics.components;

import de.mosmann.WicketApplication;
import org.apache.wicket.Session;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteRenderer;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;

public class CountryAutoCompleteRenderer extends AbstractAutoCompleteRenderer<Country> {

    private IConverter<Country> converter;
    public CountryAutoCompleteRenderer() {
        converter = WicketApplication.get().getConverterLocator().getConverter(Country.class);
    }

    @Override
    protected String getTextValue(Country country) {
        return converter.convertToString(country, Session.get().getLocale());
    }

    @Override
    protected void renderChoice(Country object, Response response, String criteria) {
        String textValue = "> "+getTextValue(object)+" <";
        textValue = Strings.escapeMarkup(textValue).toString();
        response.write(textValue);
    }
}
