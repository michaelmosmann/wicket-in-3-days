package de.mosmann;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Session;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.convert.converter.DateConverter;
import org.apache.wicket.util.time.TimeFrame;

import com.google.inject.Injector;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.mosmann.bootstrap.MyBootstrapPage;
import de.mosmann.config.All;
import de.mosmann.loginmgmt.HiddenPage;
import de.mosmann.loginmgmt.LoginManagementPage;
import de.mosmann.loginmgmt.LoginPage;
import de.mosmann.loginmgmt.login.AnnotationAuthStrategy;
import de.mosmann.topics.OverviewPage;
import de.mosmann.topics.converter.Money;
import de.mosmann.topics.converter.MoneyConverter;
import de.mosmann.topics.converter.MyShortDateConverter;
import de.mosmann.topics.converter.ShortDate;
import de.mosmann.usermgmt.AllInOneDummyPage;
import de.mosmann.usermgmt.UserMgmtPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see de.mosmann.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	private final Injector _injector;

	public WicketApplication(Injector injector) {
		_injector = injector;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return OverviewPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, _injector));

	// best place to do this is in Application#init()
	//	Bootstrap.install(Application.get(), new BootstrapSettings());
		getDebugSettings().setOutputMarkupContainerClassName(true);
		
		AnnotationAuthStrategy authStrategy = new AnnotationAuthStrategy(LoginPage.class);
		getSecuritySettings().setAuthorizationStrategy(authStrategy);
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(authStrategy);
		
		mountPage("/login", LoginPage.class);
		mountPage("/foo/hidden", HiddenPage.class);
	}
	
	@Override
	protected IConverterLocator newConverterLocator() {
		ConverterLocator converterLocator = new ConverterLocator();
		/*
		converterLocator.set(Date.class, new DateConverter() {
			@Override
			public DateFormat getDateFormat(Locale locale) {
				if (locale == null)
				{
					locale = Locale.getDefault();
				}
				
				return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
			}
		});
		*/
		converterLocator.set(ShortDate.class, new MyShortDateConverter());
		converterLocator.set(Money.class, new MoneyConverter());
		return converterLocator;
	}
}
