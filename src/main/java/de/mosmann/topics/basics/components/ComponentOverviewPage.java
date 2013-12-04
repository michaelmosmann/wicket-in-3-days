package de.mosmann.topics.basics.components;

import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource.Attributes;

import com.google.common.collect.Lists;

import de.mosmann.topics.BasePage;
import de.mosmann.topics.basics.markupmagic.SimplePanel;


public class ComponentOverviewPage extends BasePage {

	public ComponentOverviewPage() {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		
		add(feedbackPanel);
		
		// content
		add(new Label("label","<a href=aLabel.htm>not a Link</a>"));
		add(new MultiLineLabel("multiline","Foo\nMan\nChu"));
		
		// Links
		add(new Link<String>("link",Model.of("Link")) {
			@Override
			public void onClick() {
				info(getModelObject()+" klicked");
			}
		});
		
		add(new AjaxLink<String>("ajaxLink",Model.of("AjaxLink")) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				info(getModelObject()+" klicked");
				target.add(feedbackPanel);
			}
		});
		
		add(new BookmarkablePageLink<ComponentOverviewPage>("bookmark",ComponentOverviewPage.class,new PageParameters().add("foo", "bar")));
		add(new ExternalLink("external", "http://www.wicket-praxis.de/blog/2012/07/18/wicket-komponentenubersicht-6-0-0-beta3/"));
		
		// repeater
		add(new ListView<String>("list",Model.ofList(Lists.newArrayList("A","ListView","with","some","stuff"))){
			@Override
			protected void populateItem(ListItem<String> item) {
				Label label = new Label("item",item.getModel());
				label.setOutputMarkupId(true);
				item.add(label);
			}
		});
		RepeatingView repeatingView = new RepeatingView("repeatView");
		repeatingView.add(new Label(repeatingView.newChildId(),"Label"));
		repeatingView.add(new Link<Void>(repeatingView.newChildId()){
			@Override
			public void onClick() {
				info("Crazy link clicked");
			}
		});
		add(repeatingView);
		
		// grouping
		WebMarkupContainer container = new WebMarkupContainer("container");
		container.add(new Label("foo","Foo"));
		WebMarkupContainer innerContainer = new WebMarkupContainer("bar");
		innerContainer.add(new Label("foo","FooBar"));
		container.add(innerContainer);
		add(container);
		
		
		add(new SimpleBorder("border").add(new Label("inner","Whohooo")));
		add(new SimplePanel("panel"));
		add(new Fragment("fragment", "source", this));
		
		add(new Image("img", new RenderedDynamicImageResource(100,100/*, "image/jpeg"*/) {

			@Override
			protected boolean render(Graphics2D graphics, Attributes attributes) {
				graphics.setColor(new Color(255,0,0));
				graphics.setBackground(new Color(255,128,0));
				graphics.clearRect(0, 0, 10, 10);
				return true;
			}
		}));
	}
}
