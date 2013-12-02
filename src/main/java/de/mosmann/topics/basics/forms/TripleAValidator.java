package de.mosmann.topics.basics.forms;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class TripleAValidator extends Behavior implements IValidator<String> {

	@Override
	public void validate(IValidatable<String> validatable) {
		String value = validatable.getValue();
		if ((value == null) || (!value.startsWith("AAA"))) {
			validatable.error(new ValidationError().addKey("tripleAAA").setVariable("value", value));
		}
	}

}
