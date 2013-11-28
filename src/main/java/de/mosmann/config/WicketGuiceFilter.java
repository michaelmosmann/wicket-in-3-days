package de.mosmann.config;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class WicketGuiceFilter extends WicketFilter {

	@Inject
	private Provider<WebApplication> appsProvider;

	@Override
	protected IWebApplicationFactory getApplicationFactory() {
		return new IWebApplicationFactory() {

			@Override
			public WebApplication createApplication(WicketFilter filter) {
				return appsProvider.get();
			}

			@Override
			public void destroy(WicketFilter filter) {
				
			}
		};
	}
}
