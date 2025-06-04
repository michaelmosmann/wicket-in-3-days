package de.mosmann.bootstrap;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.core.Bootstrap;
import de.mosmann.HomePage;
import de.mosmann.topics.OverviewPage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;


public class MyBootstrapPage extends WebPage {

	
	public MyBootstrapPage() {
		ILinkFactory homePage=new ILinkFactory() {
			
			@Override
			public AbstractLink link(String id) {
				return new BookmarkablePageLink<>(id, HomePage.class);
			}
			
			@Override
			public boolean isActive() {
				return true;
			}
		};
		ILinkFactory overviewPage=new ILinkFactory() {
			
			@Override
			public AbstractLink link(String id) {
				return new BookmarkablePageLink<>(id, OverviewPage.class);
			}
			
			@Override
			public boolean isActive() {
				return false;
			}
		};
		
		add(new NavLinksPanel("navLinks",Model.ofList(Lists.newArrayList(homePage,overviewPage))));
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
	    super.renderHead(response);
	    Bootstrap.renderHead(response);
	}
}
