package de.mosmann.loginmgmt.login;

import java.lang.annotation.Annotation;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

public class AnnotationAuthStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

	private final Class<? extends Page> signInPageClass;

	public AnnotationAuthStrategy(Class<? extends Page> signInPageClass) {
		this.signInPageClass = signInPageClass;
	}

	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
		return isNotSecureOrSessionHasValidLogin(componentClass);
	}

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return isNotSecureOrSessionHasValidLogin(component.getClass());
	}

	@Override
	public boolean isResourceAuthorized(IResource resource, PageParameters parameters) {
		return true;
	}

	private boolean isNotSecureOrSessionHasValidLogin(Class<?> type) {
		if (!hasAnnotation(type, Secure.class)) {
			return true;
		}
		if (SessionLogin.currentLogin().isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void onUnauthorizedInstantiation(Component component) {
		if (component instanceof Page) {
			// Redirect to page to let the user sign in
			throw new RestartResponseAtInterceptPageException(signInPageClass);
		} else {
			// The component was not a page, so throw exception
			//throw new UnauthorizedInstantiationException(component.getClass());
		}
	}

	public static boolean hasAnnotation(Class<?> type, Class<? extends Annotation> annotationType) {
		if (type != null) {
			if (type.getAnnotation(annotationType) != null) {
				return true;
			}
			return hasAnnotation(type.getSuperclass(), annotationType);
		}
		return false;
	}
}
