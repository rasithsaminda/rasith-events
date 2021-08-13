/**
 * 
 */
package com.rasith.events;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rasith.events.processor.DeleteEventProcessor;
import com.rasith.events.processor.EventApiProcessor;
import com.rasith.events.route.EventDefaultApiRouteBuilder;
import com.rasith.events.validator.EventInputValidator;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class DeleteEventRoute extends EventDefaultApiRouteBuilder {

	@Inject
	EventInputValidator eventInputValidator;

	@Inject
	DeleteEventProcessor deleteEventProcessor;

	@Override
	protected EventApiProcessor apiProcessor() {
		return deleteEventProcessor;
	}

	@Override
	public void configure() throws Exception {
		super.configure("direct:deleteevent", "deleteevent", eventInputValidator);
	}

}
