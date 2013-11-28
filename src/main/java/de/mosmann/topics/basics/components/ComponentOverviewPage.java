package de.mosmann.topics.basics.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mosmann.topics.BasePage;


public class ComponentOverviewPage extends BasePage {

	public ComponentOverviewPage() {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		
		add(feedbackPanel);
		
		add(new Label("label","Label"));
		
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
	}
}
