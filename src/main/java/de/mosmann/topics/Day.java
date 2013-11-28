package de.mosmann.topics;

import com.google.common.collect.ImmutableList;

public enum Day {
	One(Topic.Persistence),
	Two,
	Three;

	private ImmutableList<Topic> _topics;

	private Day(Topic... topics) {
		_topics = ImmutableList.copyOf(topics);
	}

	public ImmutableList<Topic> topics() {
		return _topics;
	}
}
