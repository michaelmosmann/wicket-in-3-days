package de.mosmann.loginmgmt.components;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.ILoginService;
import de.mosmann.persistence.service.Login;


public class LoginModel extends LoadableDetachableModel<Login> {
	
	@Inject
	ILoginService _loginService;
	
	Integer _id;
	
	public LoginModel() {
		Injector.get().inject(this);
	}
	
	
	public void setId(Integer id) {
		_id = id;
	}
	
	@Override
	protected Login load() {
		if (_id!=null) {
			return _loginService.getById(_id);
		}
		return null;
	}


	public Integer getId() {
		return _id;
	}

}
