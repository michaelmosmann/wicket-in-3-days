package de.mosmann.topics.basics.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.string.Strings;

import de.mosmann.topics.BasePage;

public class AutoCompleteTextFieldPage extends BasePage {

    public AutoCompleteTextFieldPage() {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        
        Form<Void> form = new Form<Void>("form");
        add(form);
        
        final Model<Country> selectedCountry = Model.of();
        
        AutoCompleteTextField<Country> countryField = new AutoCompleteTextField<Country>("country", selectedCountry, Country.class, new CountryAutoCompleteRenderer(), new AutoCompleteSettings()) {
            @Override
            protected Iterator<Country> getChoices(String input) {
                if (Strings.isEmpty(input)) {
                    return Arrays.asList(Country.values()).iterator();
                }
                
                List<Country> matches = new ArrayList<>();
                for (Country country : Country.values()) {
                    if (country.getDisplayName().toLowerCase().startsWith(input.toLowerCase())) {
                        matches.add(country);
                    }
                }
                return matches.iterator();
            }

            @Override
            public void renderHead(final IHeaderResponse response)
            {
                super.renderHead(response);

                response.render(CssHeaderItem.forReference(new CssResourceReference(
                        DefaultCssAutoCompleteTextField.class, "DefaultCssAutoCompleteTextField.css")));
            }
        };
        
        form.add(countryField);
        
        form.add(new SubmitLink("submit") {
            @Override
            public void onSubmit() {
                Country country = selectedCountry.getObject();
                if (country != null) {
                    info("Selected country: " + country.getDisplayName() + " (" + country.name() + ")");
                } else {
                    warn("Please select a country");
                }
            }
        });
    }
}