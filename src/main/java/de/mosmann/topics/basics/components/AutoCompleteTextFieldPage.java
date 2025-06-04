package de.mosmann.topics.basics.components;

import java.util.ArrayList;
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

    private static final List<String> COUNTRIES = new ArrayList<>();
    
    static {
        COUNTRIES.add("Germany");
        COUNTRIES.add("Austria");
        COUNTRIES.add("Switzerland");
        COUNTRIES.add("United States");
        COUNTRIES.add("United Kingdom");
        COUNTRIES.add("France");
        COUNTRIES.add("Italy");
        COUNTRIES.add("Spain");
        COUNTRIES.add("Netherlands");
        COUNTRIES.add("Belgium");
        COUNTRIES.add("Sweden");
        COUNTRIES.add("Norway");
        COUNTRIES.add("Denmark");
        COUNTRIES.add("Finland");
        COUNTRIES.add("Poland");
        COUNTRIES.add("Czech Republic");
        COUNTRIES.add("Hungary");
        COUNTRIES.add("Portugal");
        COUNTRIES.add("Greece");
        COUNTRIES.add("Turkey");
    }
    
    public AutoCompleteTextFieldPage() {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        
        Form<Void> form = new Form<Void>("form");
        add(form);
        
        final Model<String> selectedCountry = Model.of("");
        
        AutoCompleteTextField<String> countryField = new AutoCompleteTextField<String>("country", selectedCountry) {
            @Override
            protected Iterator<String> getChoices(String input) {
                if (Strings.isEmpty(input)) {
                    return COUNTRIES.iterator();
                }
                
                List<String> matches = new ArrayList<>();
                for (String country : COUNTRIES) {
                    if (country.toLowerCase().startsWith(input.toLowerCase())) {
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
                String country = selectedCountry.getObject();
                if (!Strings.isEmpty(country)) {
                    info("Selected country: " + country);
                } else {
                    warn("Please select a country");
                }
            }
        });
    }
}