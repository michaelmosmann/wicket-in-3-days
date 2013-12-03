package de.mosmann.topics.forms;

import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.google.common.collect.Lists;


public class Forms {

	public static List<FormComponent> findFormComponents(MarkupContainer container) {
		final List<FormComponent> formComponents = Lists.newArrayList();
	
		container.visitChildren(FormComponent.class, new IVisitor<FormComponent, Void>() {
	
			@Override
			public void component(final FormComponent component, final IVisit<Void> visit) {
				formComponents.add(component);
			}
		});
		return formComponents;
	}

}
