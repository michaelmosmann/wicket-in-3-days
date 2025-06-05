package de.mosmann.topics.basics.components;

import de.mosmann.topics.BasePage;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.select2.ChoiceProvider;
import org.wicketstuff.select2.Response;
import org.wicketstuff.select2.Select2Choice;
import org.wicketstuff.select2.Select2MultiChoice;

import java.util.*;
import java.util.stream.Collectors;

public class Select2TextFieldPage extends BasePage {

    public Select2TextFieldPage() {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        
        Form<Void> form = new Form<Void>("form");
        add(form);
        
        final Model<Country> selectedCountry = Model.of();

        ChoiceProvider<Country> choiceProvider = new ChoiceProvider<>() {
            @Override
            public String getDisplayValue(Country country) {
                return country.getDisplayName();
            }

            @Override
            public String getIdValue(Country country) {
                return country.name();
            }

            @Override
            public void query(String input, int page, Response<Country> response) {
                System.out.println("input: " + input);
                System.out.println("page: " + page);
                if (Strings.isEmpty(input)) {
                    response.addAll(Arrays.asList(Country.values()));
                } else {
                    for (Country country : Country.values()) {
                        if (country.getDisplayName().toLowerCase().startsWith(input.toLowerCase())) {
                            response.add(country);
                        }
                    }
                }
            }

            @Override
            public Collection<Country> toChoices(Collection<String> collection) {
                return collection.stream()
                        .map(Country::valueOf)
                        .collect(Collectors.toList());
            }
        };
        Select2Choice<Country> countryField = new Select2Choice<Country>("country", selectedCountry, choiceProvider);
        IModel<Collection<Country>> selectedCountries = Model.of(Collections.emptyList());
        Select2MultiChoice<Country> countriesField = new Select2MultiChoice<>("countries", selectedCountries, choiceProvider);

//        AutoCompleteTextField<Country> countryField = new AutoCompleteTextField<Country>("country", selectedCountry, Country.class, new CountryAutoCompleteRenderer(), new AutoCompleteSettings()) {
//            @Override
//            protected Iterator<Country> getChoices(String input) {
//                if (Strings.isEmpty(input)) {
//                    return Arrays.asList(Country.values()).iterator();
//                }
//
//                List<Country> matches = new ArrayList<>();
//                for (Country country : Country.values()) {
//                    if (country.getDisplayName().toLowerCase().startsWith(input.toLowerCase())) {
//                        matches.add(country);
//                    }
//                }
//                return matches.iterator();
//            }
//
//            @Override
//            public void renderHead(final IHeaderResponse response)
//            {
//                super.renderHead(response);
//
//                response.render(CssHeaderItem.forReference(new CssResourceReference(
//                        DefaultCssAutoCompleteTextField.class, "DefaultCssAutoCompleteTextField.css")));
//            }
//        };

        form.add(countryField);
        form.add(countriesField);

        form.add(new SubmitLink("submit") {
            @Override
            public void onSubmit() {
                Country country = selectedCountry.getObject();
                if (country != null) {
                    info("Selected country: " + country.getDisplayName() + " (" + country.name() + ")");
                } else {
                    warn("Please select a country");
                }
                info("Selected countries: " + selectedCountries.getObject());
            }
        });
    }
}