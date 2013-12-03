package de.mosmann.loginmgmt.components;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.ILoginService;
import de.mosmann.persistence.service.Login;


public class LoginsModel extends LoadableDetachableModel<List<Login>> {

	@Inject
	ILoginService _loginService;

	public LoginsModel() {
		Injector.get().inject(this);
	}
	
	@Override
	protected List<Login> load() {
		return _loginService.findAll();
	}

}
