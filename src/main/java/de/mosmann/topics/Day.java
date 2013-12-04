package de.mosmann.topics;

import com.google.common.collect.ImmutableList;

public enum Day {
	One(Topic.MarkupMagic,Topic.Components,Topic.Forms,Topic.Validation,Topic.Events,Topic.Models),
	Two(Topic.FormValidationComponents),
	Three(Topic.Converter),
	ToDo(Topic.Persistence),
	Any(Topic.Playground);

	private ImmutableList<Topic> _topics;

	private Day(Topic... topics) {
		_topics = ImmutableList.copyOf(topics);
	}

	public ImmutableList<Topic> topics() {
		return _topics;
	}
}
