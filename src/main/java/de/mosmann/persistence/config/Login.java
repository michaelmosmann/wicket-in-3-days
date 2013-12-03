package de.mosmann.persistence.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import de.mosmann.persistence.service.ILoginService;
import de.mosmann.persistence.service.LoginService;

public class Login extends AbstractModule {

	@Override
	protected void configure() {
		bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
	}

}
