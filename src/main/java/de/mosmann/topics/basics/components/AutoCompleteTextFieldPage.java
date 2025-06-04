package de.mosmann.topics.basics.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import de.mosmann.topics.BasePage;

public class AutoCompleteTextFieldPage extends BasePage {

    public enum Country {
        GERMANY("Germany"),
        AUSTRIA("Austria"),
        SWITZERLAND("Switzerland"),
        UNITED_STATES("United States"),
        UNITED_KINGDOM("United Kingdom"),
        FRANCE("France"),
        ITALY("Italy"),
        SPAIN("Spain"),
        NETHERLANDS("Netherlands"),
        BELGIUM("Belgium"),
        SWEDEN("Sweden"),
        NORWAY("Norway"),
        DENMARK("Denmark"),
        FINLAND("Finland"),
        POLAND("Poland"),
        CZECH_REPUBLIC("Czech Republic"),
        HUNGARY("Hungary"),
        PORTUGAL("Portugal"),
        GREECE("Greece"),
        TURKEY("Turkey");
        
        private final String displayName;
        
        Country(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
    
    public AutoCompleteTextFieldPage() {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        
        Form<Void> form = new Form<Void>("form");
        add(form);
        
        final Model<Country> selectedCountry = Model.of();
        
        AutoCompleteTextField<Country> countryField = new AutoCompleteTextField<Country>("country", selectedCountry) {
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