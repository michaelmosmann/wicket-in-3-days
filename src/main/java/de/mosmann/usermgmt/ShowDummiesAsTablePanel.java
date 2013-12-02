package de.mosmann.usermgmt;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import de.mosmann.persistence.service.Dummy;
import de.mosmann.persistence.service.IDummyService;

public class ShowDummiesAsTablePanel extends Panel {

	@Inject
	IDummyService _dummyService;

	public ShowDummiesAsTablePanel(String id) {
		super(id);

		List<? extends IColumn<Dummy, String>> columns = Lists.newArrayList(
				new PropertyColumn<Dummy, String>(Model.of("ID"), "id"), new PropertyColumn<Dummy, String>(Model.of("Name"),
						"name"));

		ISortableDataProvider<Dummy, String> dataProvider = new ISortableDataProvider<Dummy, String>() {

			@Override
			public ISortState<String> getSortState() {
				return new SingleSortState<>();
			}

			@Override
			public void detach() {

			}

			@Override
			public long size() {
				return _dummyService.asList().size();
			}

			@Override
			public IModel<Dummy> model(Dummy object) {
				return new Model<Dummy>(object);
			}

			@Override
			public Iterator<? extends Dummy> iterator(long first, long count) {
				List<Dummy> ret = _dummyService.asList();
				int start = (int) first;
				int end = (int) (first + count);
				return ret.subList(start, end > ret.size()
						? ret.size()
						: end).iterator();
			}
		};

		add(new DefaultDataTable<Dummy, String>("table", columns, dataProvider, 5){
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				checkComponentTag(tag, "table");
			}
		});
	}

}
