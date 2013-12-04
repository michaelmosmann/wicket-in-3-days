package de.mosmann.loginmgmt.events;

public class NewLogin extends AbstractEditLoginDataEvents {

	private final String _name;
	private final String _email;
	private final String _passwd;

	public NewLogin(String name, String email, String passwd) {
		_name = name;
		_email = email;
		_passwd = passwd;
	}

	public String getName() {
		return _name;
	}

	public String getEmail() {
		return _email;
	}

	public String getPasswd() {
		return _passwd;
	}

	@Override
	public String toString() {
		return "NewLogin(name:" + _email + ",email:" + _email + ",passwd:***)";
	}
}
