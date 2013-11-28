package de.mosmann.topics.basics.forms.models.property;


public abstract class AbstractPropertyAccess<T, S> implements IPropertyAccess<T, S> {

	private final Class<T> _type;

	public AbstractPropertyAccess(Class<T> type) {
		_type = type;
	}
	
	@Override
	public Class<T> type() {
		return _type;
	}
}
