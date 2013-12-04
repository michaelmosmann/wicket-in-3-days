package de.mosmann.topics.paging;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.hibernate.persister.entity.Loadable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import de.flapdoodle.functions.Function1;
import de.flapdoodle.functions.Function2;
import de.flapdoodle.functions.Function3;
import de.flapdoodle.wicket.model.Models;
import de.mosmann.topics.BasePage;

public class PagingPage extends BasePage {

	public PagingPage() {
		
		final int perPage=5;
		
		IModel<Boolean> sort=Model.of(false);
		IModel<Integer> offset=Model.of(0);
		IModel<Integer> max=Model.of(5);
		
		add(new Link<Boolean>("switch",sort) {
			@Override
			public void onClick() {
				setModelObject(!getModelObject());
			}
		});
		add(new Label("offset",offset));
		add(new Link<Integer>("+",offset) {
			@Override
			public void onClick() {
				setModelObject(getModelObject()+perPage);
			}
		});
		add(new Link<Integer>("-",offset) {
			@Override
			public void onClick() {
				setModelObject(getModelObject()-perPage);
			}
		});
		
		final IModel<List<Integer>> serviceModel=Models.on(offset,max,sort).apply(new ServiceFunction(new IntegerService()));
		
		ListView<Integer> listView = new ListView<Integer>("list",serviceModel) {
			@Override
			protected void populateItem(ListItem<Integer> item) {
				item.add(new Label("entry",item.getModel()));
			}
		};
		add(listView);
		
		IModel<List<Integer>> wrong=new AbstractReadOnlyModel<List<Integer>>() {
			@Override
			public List<Integer> getObject() {
				System.out.println("Called at "+new Date());
				return serviceModel.getObject();
			}
		};
		

		wrong.getObject();
		wrong.getObject();
		wrong.getObject();
		wrong.getObject();
	}
	
	static class ServiceFunction implements Function3<List<Integer>, Integer, Integer,Boolean> {

		final IntegerService _service;
		
		
		public ServiceFunction(IntegerService service) {
			_service = service;
		}
		
		
		
		@Override
		public List<Integer> apply(Integer offset, Integer max,Boolean sort) {
			return _service.intAsList(offset, max,sort);
		}
		
	}
	
	static class IntegerService implements Serializable {

		public List<Integer> intAsList(Integer start, Integer max, Boolean sort) {
			Builder<Integer> builder = ImmutableList.builder();
			if (sort) {
				for (int i=start+max-1;i>=start;i--) {
					builder.add(i);
				}
			} else {
				for (int i=start;i<start+max;i++) {
					builder.add(i);
				}
			}
			return builder.build();
		}
	}
}
