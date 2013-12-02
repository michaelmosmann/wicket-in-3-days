package de.mosmann.usermgmt;

import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.Dummy;
import de.mosmann.persistence.service.IDummyService;

public class NewDummyPanel extends Panel {

	@Inject
	IDummyService _dummyService;

	public NewDummyPanel(String id) {
		super(id);

		final DummyByIdModel model = new DummyByIdModel();

		Form<Dummy> form = new Form<Dummy>("form", new CompoundPropertyModel<Dummy>(model)) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				Dummy dummy = getModelObject();
				
				Integer id=_dummyService.save(dummy);
//				model.setDummyId(id);
				
				info("User " + dummy.getName() + " saved");
//				setResponsePage(ShowDummiesPage.class);
				
				newDummyCreated();
			}

		};
		
		form.add(new TextField<String>("name").setRequired(true));
		form.add(new AjaxSubmitLink("submit") {
			
		});
		add(form);
	}

	protected void newDummyCreated() {
		
	}
}
