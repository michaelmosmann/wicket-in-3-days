package de.mosmann.topics.converter;

import static org.junit.Assert.*;

import org.apache.wicket.util.convert.ConversionException;
import org.junit.Test;


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

	@Test(expected=ConversionException.class)
	public void wrongSampleShouldFail() {
		new MoneyConverter().convertToObject("113a Euronen", null);
	}

	@Test(expected=ConversionException.class)
	public void withoutCurrencyShouldFail() {
		new MoneyConverter().convertToObject("113 ", null);
	}
}
