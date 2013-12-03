package de.mosmann.loginmgmt.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import de.mosmann.Ajax;
import de.mosmann.loginmgmt.events.AbstractLoginEvents;
import de.mosmann.loginmgmt.events.EditLogin;
import de.mosmann.persistence.service.Login;

public class LoginsPanel extends Panel {

	private LoginsModel _model;

	public LoginsPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);

		_model = new LoginsModel();

		add(new ListView<Login>("list", _model) {

			@Override
			protected void populateItem(ListItem<Login> item) {
				Login login = item.getModelObject();
				item.add(new Label("id", login.getId()));
				item.add(new Label("name", login.getName()));
				item.add(new Label("email", login.getEmail()));
				item.add(new Label("passwd", login.getPasswd()));
				item.add(new AjaxLink<Integer>("edit", Model.of(login.getId())) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						send(getPage(),Broadcast.BREADTH,new EditLogin(getModelObject()));
						
					}
				});
			}
		});
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		setVisible(_model.getObject().size() > 0);
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		if (event.getPayload() instanceof AbstractLoginEvents) {
			Ajax.addComponentIfAjax(this);
		}
	}
}
