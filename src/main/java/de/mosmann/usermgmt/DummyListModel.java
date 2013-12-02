package de.mosmann.usermgmt;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.Dummy;
import de.mosmann.persistence.service.IDummyService;


public class DummyListModel extends LoadableDetachableModel<List<Dummy>> {

	@Inject
	IDummyService _dummyService;

	public DummyListModel() {
		Injector.get().inject(this);
	}
	
	
	@Override
	protected List<Dummy> load() {
		return _dummyService.asList();
	}

}
