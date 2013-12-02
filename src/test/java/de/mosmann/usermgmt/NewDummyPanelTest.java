package de.mosmann.usermgmt;

import static org.junit.Assert.*;

import org.apache.wicket.Component;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.mosmann.HomePage;
import de.mosmann.WicketApplication;
import de.mosmann.config.Tests;
import de.mosmann.persistence.service.IDummyService;
import de.mosmann.topics.OverviewPage;

public class NewDummyPanelTest {

	private WicketTester tester;

	@Inject
	IDummyService _service;
	
	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new Tests());
		tester = new WicketTester(new WicketApplication(injector));
		injector.injectMembers(this);
	}

	@Test
	public void newUser() {
		NewDummyPanel testedComp = new NewDummyPanel("dummy") {
			@Override
			protected void newDummyCreated() {
				super.newDummyCreated();
				
				setResponsePage(HomePage.class);
			}
		};
		
		tester.startComponentInPage(testedComp);
		
		FormTester formTester = tester.newFormTester("dummy:form");
		formTester.setValue("name", "Klaus");
		//formTester.submit();
		tester.clickLink("dummy:form:submit", true);

		tester.assertRenderedPage(HomePage.class);
		assertEquals(1,_service.asList().size());
		assertEquals("Klaus",_service.asList().get(0).getName());
	}

	@Test
	public void validationError() {
		NewDummyPanel testedComp = new NewDummyPanel("dummy") {
			@Override
			protected void newDummyCreated() {
				super.newDummyCreated();
				
				setResponsePage(HomePage.class);
			}
		};
		
		tester.startComponentInPage(testedComp);
		
		FormTester formTester = tester.newFormTester("dummy:form");
		//formTester.setValue("name", "Klaus");
		//formTester.submit();
		tester.clickLink("dummy:form:submit", true);
		assertTrue(tester.getComponentFromLastRenderedPage("dummy:form:name").hasErrorMessage());

		assertEquals(0,_service.asList().size());
	}

}
