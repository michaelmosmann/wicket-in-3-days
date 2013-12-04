package de.mosmann.loginmgmt.events;

public class ValidateLogin {

	private final String _name;
	private final String _passwd;

	public ValidateLogin(String name, String passwd) {
		_name = name;
		_passwd = passwd;
	}

	public String name() {
		return _name;
	}

	public String passwd() {
		return _passwd;
	}

}
