package com.jack.ws;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.jack.ws.restmiddle.GenericExceptionMapper;
import com.jack.ws.restmiddle.RestApiExceptionMapper;

@ApplicationPath("resources")
public class JerseyConfig extends ResourceConfig {
	private org.apache.log4j.Logger log4jLogger = org.apache.log4j.Logger.getLogger(JerseyConfig.class);

	public JerseyConfig() {
		log4jLogger.info("JerseyConfig started!");

		property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, true);
		property(ServerProperties.PROVIDER_PACKAGES, "com.jack.ws");
		// property(ServerProperties.TRACING,"ALL");

		Logger javaLogger = Logger.getLogger(JerseyConfig.class.getName());

		javaLogger.setFilter(new Filter() {
			@Override
			public boolean isLoggable(LogRecord record) {
				log4jLogger.info(record.getMessage());
				return false;
			}
		});

		register(new LoggingFilter(javaLogger, true));

		/*
		 * Currently the feature is broke as of jersey 2.22.2 for java generics
		 * and nested objects We will watch and use this once issues are fixed.
		 */
		// register(EntityFilteringFeature.class);

		// Jackson JSON marshalling
		register(JacksonFeature.class);
		register(MultiPartFeature.class);

		GenericExceptionMapper myExceptionMapper = new GenericExceptionMapper();
		register(myExceptionMapper);

		RestApiExceptionMapper ssApiExceptionMapper = new RestApiExceptionMapper();
		register(ssApiExceptionMapper);
	}
}
