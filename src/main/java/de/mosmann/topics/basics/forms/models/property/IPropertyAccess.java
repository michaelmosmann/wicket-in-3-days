package de.mosmann.topics.basics.forms.models.property;

import java.io.Serializable;


public interface IPropertyAccess<T, S> extends Serializable {

	T read(S source);

	void write(S source, T newValue);

	Class<T> type();
}
