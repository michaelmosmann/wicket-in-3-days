package de.mosmann.loginmgmt.events;


public class EditLogin extends AbstractLoginEvents {

	private final int _loginId;

	public EditLogin(int loginId) {
		_loginId = loginId;
	}

	public int loginId() {
		return _loginId;
	}
}
