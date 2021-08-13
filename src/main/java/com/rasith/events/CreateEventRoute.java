/**
 * 
 */
package com.rasith.events;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rasith.events.processor.CreateEventProcessor;
import com.rasith.events.processor.EventApiProcessor;
import com.rasith.events.route.EventDefaultApiRouteBuilder;
import com.rasith.events.validator.EventInputValidator;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class CreateEventRoute extends EventDefaultApiRouteBuilder {

	@Inject
	EventInputValidator eventInputValidator;

	@Inject
	CreateEventProcessor createEventProcessor;

	@Override
	protected EventApiProcessor apiProcessor() {
		return createEventProcessor;
	}

	@Override
	public void configure() throws Exception {
		super.configure("direct:createevents", "createevents", eventInputValidator);
	}

}
