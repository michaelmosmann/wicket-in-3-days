package de.mosmann.loginmgmt.events;

import de.mosmann.persistence.service.Login;

public class SaveLogin extends AbstractEditLoginDataEvents {

	private final Login _login;

	public SaveLogin(Login login) {
		_login = login;
	}

	public Login login() {
		return _login;
	}

}
