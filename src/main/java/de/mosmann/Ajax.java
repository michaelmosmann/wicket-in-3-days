package de.mosmann;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.Optional;

public class Ajax {

	public static void addComponentIfAjax(Component component, Component... components) {
		Optional<AjaxRequestTarget> maybeTarget = ajaxRequestTarget();
		if (maybeTarget.isPresent()) {
			AjaxRequestTarget target = maybeTarget.get();
			target.add(component);
			for (Component c : components) {
				target.add(c);
			}
		}
	}

	public static Optional<AjaxRequestTarget> ajaxRequestTarget() {
		return RequestCycle.get().find(AjaxRequestTarget.class);
	}

	public static void addAllIfAjax(Component component, final Class<?> type) {
		Optional<AjaxRequestTarget> maybeTarget = ajaxRequestTarget();
		if (maybeTarget.isPresent()) {
			final AjaxRequestTarget target = maybeTarget.get();
			component.getPage().visitChildren(Component.class, new IVisitor<Component, Void>() {

				@Override
				public void component(Component object, IVisit<Void> visit) {
					if (type.isInstance(object)) {
						target.add(object);
					}
				}

			});
		}
	}
}
