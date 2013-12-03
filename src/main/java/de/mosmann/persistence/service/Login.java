package de.mosmann.persistence.service;

public class Login {

	Integer _id;

	String _name;
	String _email;
	String _passwd;

	
	public Login() {
		
	}
	
	public Login(Login old) {
		set(old);
	}
	
	public void set(Login old) {
		_name=old._name;
		_email=old._email;
		_passwd=old._passwd;
		_id=old._id;
	}
	
	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getPasswd() {
		return _passwd;
	}

	public void setPasswd(String passwd) {
		_passwd = passwd;
	}

}
