package de.mosmann.persistence.service;

import java.io.Serializable;

public class Dummy implements Serializable {

	Integer _id;
	String _name;

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

}
