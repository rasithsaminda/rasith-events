/**
 * 
 */
package com.rasith.events;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rasith.events.processor.EventApiProcessor;
import com.rasith.events.processor.GetAllEventProcessor;
import com.rasith.events.route.EventDefaultApiRouteBuilder;
import com.rasith.events.validator.EventInputValidator;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class GetAllsEventRoute extends EventDefaultApiRouteBuilder {

	@Inject
	EventInputValidator eventInputValidator;

	@Inject
	GetAllEventProcessor allEventProcessor;

	@Override
	protected EventApiProcessor apiProcessor() {
		return allEventProcessor;
	}

	@Override
	public void configure() throws Exception {
		super.configure("direct:getallevents", "getallevents", eventInputValidator);
	}

}
