package de.mosmann.topics.advanced.persistence;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

import de.flapdoodle.functions.Function1;
import de.flapdoodle.wicket.model.Models;
import de.mosmann.persistence.entity.User;
import de.mosmann.persistence.service.IUserService;

public class ShowUserPanel extends Panel {

	@Inject
	private IUserService service;

	public ShowUserPanel(String id) {
		super(id);
		
		IModel<User> model = new LoadableDetachableModel<User>() {

			@Override
			protected User load() {
				return service.getUser();
			}
		};

		add(new Label("user", Models.on(model).apply(new Function1<String, User>() {

			@Override
			public String apply(User value) {
				return value.getName();
			}
		})));

	}

}
