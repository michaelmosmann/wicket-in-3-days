package de.mosmann.topics.basics.forms;

import java.io.Serializable;
import java.util.Date;

import de.mosmann.topics.basics.forms.models.Birthday;
import de.mosmann.topics.basics.forms.models.Name;
import de.mosmann.topics.basics.forms.models.Size;

public class FormData implements Serializable {

	public static Name Name=new Name();
	public static Size Size=new Size();
	public static Birthday Birthday=new Birthday();
	
	String _name;
	int _size;
	Date _birthday;

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getSize() {
		return _size;
	}

	public void setSize(int size) {
		_size = size;
	}

	public Date getBirthday() {
		return _birthday;
	}

	public void setBirthday(Date birthday) {
		_birthday = birthday;
	}

	@Override
	public String toString() {
		return "Name: " + _name + ",Size: " + _size + ",Birthday: " + _birthday;
	}
}
