package de.mosmann.usermgmt;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.cycle.RequestCycle;

import de.mosmann.topics.BasePage;


public class AllInOneDummyPage extends BasePage {

	
	public AllInOneDummyPage() {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		
		final ShowDummiesPanel showDummiesPanel = new ShowDummiesPanel("show");
		showDummiesPanel.setOutputMarkupId(true);
		showDummiesPanel.setOutputMarkupPlaceholderTag(true);
		showDummiesPanel.setVisible(false);
		
		add(new NewDummyPanel("new") {
			@Override
			protected void newDummyCreated() {
				super.newDummyCreated();

				this.setVisible(false);
				showDummiesPanel.setVisible(true);
				
				AjaxRequestTarget ajaxTarget = RequestCycle.get().find(AjaxRequestTarget.class);
				if (ajaxTarget!=null) {
					ajaxTarget.add(this,showDummiesPanel,feedbackPanel);
				}
			}
		}.setOutputMarkupId(true));
		add(showDummiesPanel);
	}
}
