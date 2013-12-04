package de.mosmann.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.servlet.ServletModule;


public class Application extends ServletModule {  
  
	@Override  
	protected void configureServlets() {  
	 bind(WebApplication.class).toProvider(WicketGuiceAppProvider.class);  
	   
	 // avoids "Error initializing WicketFilter - you have no <filter-mapping> element..."   
	 // IllegalArgumentException  
	 Map<String, String> params = new HashMap<String, String>();    
	 params.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
	 params.put("configuration", "deployment");
	   
	 filter("/*").through(WicketGuiceFilter.class, params);  
	}  
}   
