package de.mosmann.loginmgmt.components;

import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import de.mosmann.loginmgmt.events.Logout;
import de.mosmann.loginmgmt.login.Secure;

@Secure
public class LogoutPanel extends Panel {

	public LogoutPanel(String id) {
		super(id);

		add(new Link<Void>("logout") {

			@Override
			public void onClick() {
				send(getPage(), Broadcast.BREADTH, new Logout());
			}
		});
	}

}
