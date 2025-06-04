package de.mosmann.topics.converter;


import org.apache.wicket.util.convert.ConversionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MoneyConverterTest {

	@Test
	public void validPatternShouldGiveValue() {
		Money money = new MoneyConverter().convertToObject("1 €", null);
		assertNotNull(money);
		assertEquals(1,money.count());
		assertEquals("€",money.currency());
	}

	@Test
	public void complexSampleShouldGiveValue() {
		Money money = new MoneyConverter().convertToObject("113 Euronen", null);
		assertNotNull(money);
		assertEquals(113,money.count());
		assertEquals("Euronen",money.currency());
	}

	@Test
	public void wrongSampleShouldFail() {
		assertThatThrownBy(() -> new MoneyConverter().convertToObject("113a Euronen", null))
				.isInstanceOf(ConversionException.class);
	}

	@Test
	public void withoutCurrencyShouldFail() {
		assertThatThrownBy(() -> new MoneyConverter().convertToObject("113 ", null))
				.isInstanceOf(ConversionException.class);
	}
}
