package de.mosmann.topics.playground;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mosmann.topics.BasePage;
import de.mosmann.topics.playground.PlaygroundPage.Data;


public class PlaygroundPage extends BasePage {

	public PlaygroundPage() {
		final Data d=new Data();
		d.name="Klaus";
		d.binData=new byte[1213131];
		
		add(new MyLink("link", d));
		
		PlaygroundPage.jumpForShowBla(7, "klaus").asLink("link");

	}
	
	public static final class MyLink extends Link<Void> {

		private final Data _d;

		public MyLink(String id, Data d) {
			super(id);
			_d = d;
		}

		@Override
		public void onClick() {
			info("Wer hat geklickt: "+_d.name);
			
			PlaygroundPage.jumpForShowBla(7, "klaus").jump();
		}
		
	}
	
	
	public static NavLink jumpForShowBla(int index, String username) {
		return new NavLink(PlaygroundPage.class, new PageParameters().add("index", index).add("q", username));
	}
	
	static class NavLink {

		private final Class<PlaygroundPage> _type;
		private final PageParameters _parameters;

		public NavLink(Class<PlaygroundPage> type, PageParameters parameters) {
			_type = type;
			_parameters = parameters;
		}

		public BookmarkablePageLink<PlaygroundPage> asLink(String id) {
			return new BookmarkablePageLink<>(id, _type, _parameters);
			
		}

		public void jump() {
			RequestCycle.get().setResponsePage(_type,_parameters);
		}
		
	}

	class Factory {
		public Link newLink(String id) {
			return new Link<Void>(id) {
				@Override
				public void onClick() {
					info("holööa");
				}
			};
		}
	}
	
	static class Data {
		String name;
		byte[] binData;
	}
}
