package de.mosmann.usermgmt;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.flapdoodle.functions.Function2;
import de.flapdoodle.wicket.model.Models;
import de.mosmann.persistence.service.Dummy;


public class ShowDummiesPanel extends Panel {

	public ShowDummiesPanel(String id) {
		super(id);
		
		DummyListModel listModel = new DummyListModel();
		Model<Integer> offsetModel = Model.of(0);
		add(new Link<Integer>("up", offsetModel) {
			@Override
			public void onClick() {
				setModelObject(getModelObject()+5);
			}
		});
		add(new Link<Integer>("down", offsetModel) {
			@Override
			public void onClick() {
				int offset = getModelObject()-5;
				if (offset<0) offset=0;
				setModelObject(offset);
			}
		});
		
		IModel<List<Dummy>> sublistModel = Models.on(listModel,offsetModel).apply(new Function2<List<Dummy>, List<Dummy>, Integer>() {

			@Override
			public List<Dummy> apply(List<Dummy> source, Integer offset) {
				int end=offset+5;
				if (end>source.size()) {
					end=source.size();
				}
				return source.subList(offset, end);
			}
		});
		
		add(new ListView<Dummy>("list", sublistModel) {

			@Override
			protected void populateItem(ListItem<Dummy> item) {
				Dummy dummy = item.getModelObject();
				item.add(new Label("id",dummy.getId()));
				item.add(new Label("name", dummy.getName()));
			}
			
		});
	}

}

