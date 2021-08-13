/**
 * 
 */
package com.rasith.events.route;

import javax.inject.Inject;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;

import com.rasith.events.exception.EventExceptionHandler;
import com.rasith.events.validator.EventApiInputValidator;

/**
 * @author rasiththirimanna
 *
 */
public abstract class EventApiRouteBuilder extends RouteBuilder {

	@Inject
	EventExceptionHandler eventExceptionHandler;

	/**
	 * This method will generate the overall route process for a API call
	 *
	 * @param routeName         route name, e.g. direct:api
	 * @param apiName           API name, e.g. api
	 * @param apiInputValidator API input validator. Must extend from
	 *                          EventApiInputValidator
	 */
	protected void configure(final String routeName, final String apiName,
			final EventApiInputValidator apiInputValidator) {
		// Exception Handling
		this.afterException(
				this.onException(Exception.class).handled(true).bean(this.eventExceptionHandler, "onExceptionOccurance")
						.log(LoggingLevel.ERROR, "Exception occurs in route"));

		// Overall API process
		this.afterProcess(this.onProcess(
				this.from(routeName).setProperty("SERVICE_API", this.simple(apiName)).process(apiInputValidator)));
	}

	/**
	 * The actual API call process.
	 *
	 * @param processor the input processor
	 */
	protected abstract RouteDefinition onProcess(RouteDefinition processor);

	/**
	 * Override this if extra action is required after exception handling
	 *
	 * @param processor the input processor
	 */
	protected void afterException(final OnExceptionDefinition processor) {
	}

	/**
	 * Override this if extra async action is required after response generated
	 *
	 * @param processor the input processor
	 */
	protected void afterProcess(final RouteDefinition processor) {
	}

}
