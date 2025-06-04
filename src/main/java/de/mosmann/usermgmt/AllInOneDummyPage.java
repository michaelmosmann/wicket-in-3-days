package de.mosmann.usermgmt;

import de.mosmann.topics.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.cycle.RequestCycle;

import java.util.Optional;


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

				Optional<AjaxRequestTarget> ajaxTarget = RequestCycle.get().find(AjaxRequestTarget.class);
                ajaxTarget.ifPresent(ajaxRequestTarget -> ajaxRequestTarget.add(this, showDummiesPanel, feedbackPanel));
			}
		}.setOutputMarkupId(true));
		add(showDummiesPanel);
	}
}
