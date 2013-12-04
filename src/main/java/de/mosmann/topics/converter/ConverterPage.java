package de.mosmann.topics.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.convert.IConverter;

import de.flapdoodle.functions.Function1;
import de.flapdoodle.functions.Function2;
import de.flapdoodle.wicket.model.Models;
import de.mosmann.topics.BasePage;

public class ConverterPage extends BasePage {

	public ConverterPage() {

		IModel<Date> model = new LoadableDetachableModel<Date>() {

			@Override
			protected Date load() {
				return new Date();
			}
		};

		IModel<ShortDate> shortModel = Models.on(model).apply(new Function1<ShortDate, Date>() {
			@Override
			public ShortDate apply(Date value) {
				return new ShortDate(value);
			}
		});
		
		add(new Label("date", model));
		add(new Label("shortdate", shortModel));
	}
}
