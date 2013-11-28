package de.mosmann.config;

import org.apache.wicket.protocol.http.WebApplication;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import de.mosmann.WicketApplication;

public class WicketGuiceAppProvider implements Provider<WebApplication> {

	private final Injector injector;

	@Inject
	public WicketGuiceAppProvider(Injector injector) {
		this.injector = injector;
	}

	@Override
	public WebApplication get() {
		return new WicketApplication(injector);
	}
}
