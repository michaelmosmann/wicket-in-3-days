package de.mosmann.topics.basics.forms.models.property;


public interface IPropertyAccess<T, S> {

	T read(S source);

	void write(S source, T newValue);

	Class<T> type();
}
