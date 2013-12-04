package de.mosmann.topics.converter;

import java.io.Serializable;

public class Money implements Serializable {

	private final int _count;
	private final String _currency;

	public Money(int count, String currency) {
		_count = count;
		_currency = currency;
	}

	public int count() {
		return _count;
	}

	public String currency() {
		return _currency;
	}

	@Override
	public String toString() {
		return "Money("+_count+" ´"+_currency+"´)";
	}
}
