/**
 * 
 */
package com.rasith.events;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rasith.events.processor.EventApiProcessor;
import com.rasith.events.processor.GetEventProcessor;
import com.rasith.events.route.EventDefaultApiRouteBuilder;
import com.rasith.events.validator.EventInputValidator;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class GetEventRoute extends EventDefaultApiRouteBuilder {

	@Inject
	EventInputValidator eventInputValidator;

	@Inject
	GetEventProcessor eventProcessor;

	@Override
	protected EventApiProcessor apiProcessor() {
		return eventProcessor;
	}

	@Override
	public void configure() throws Exception {
		super.configure("direct:getevent", "getevent", eventInputValidator);
	}
}
