/**
 * 
 */
package com.rasith.events;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rasith.events.processor.EventApiProcessor;
import com.rasith.events.processor.UpdateEventProcessor;
import com.rasith.events.route.EventDefaultApiRouteBuilder;
import com.rasith.events.validator.EventInputValidator;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class UpdateEventRoute extends EventDefaultApiRouteBuilder {

	@Inject
	EventInputValidator eventInputValidator;

	@Inject
	UpdateEventProcessor updateEventProcessor;

	@Override
	protected EventApiProcessor apiProcessor() {
		return updateEventProcessor;
	}

	@Override
	public void configure() throws Exception {
		super.configure("direct:updateevent", "updateevent", eventInputValidator);
	}

}
