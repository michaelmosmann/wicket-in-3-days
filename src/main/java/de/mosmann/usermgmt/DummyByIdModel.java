package de.mosmann.usermgmt;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.Dummy;
import de.mosmann.persistence.service.IDummyService;


public class DummyByIdModel extends LoadableDetachableModel<Dummy> {

	@Inject
	IDummyService _dummyService;
	
	Integer _dummyId;
	
	
	public DummyByIdModel() {
		Injector.get().inject(this);
	}
	
	public void setDummyId(Integer dummyId) {
		_dummyId = dummyId;
	}

	@Override
	protected Dummy load() {
		if (_dummyId!=null) {
			return _dummyService.byId(_dummyId);
		}
		return _dummyService.newInstance();
	}

}
