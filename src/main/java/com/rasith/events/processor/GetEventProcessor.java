/**
 * 
 */
package com.rasith.events.processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.models.Event;
import com.rasith.events.provider.EventResponse;
import com.rasith.events.service.EventDAOService;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class GetEventProcessor extends EventApiProcessor {

	@Inject
	EventDAOService eventDAOService;

	@SuppressWarnings("rawtypes")
	@Override
	protected EventResponse process(Exchange exchange, String uniqueId) throws Exception {

		Integer eventId = getRequest(exchange, Integer.class);
		Event event = null;
		try {
			event = eventDAOService.findEventById(eventId);
		} catch (Exception ex) {
			return EventResponse.fail("EVT99999", EventConstants.EVENT_NOT_FOUND_MSG, 200, null);
		}
		return EventResponse.ok(event, EventConstants.EVENTS_LOADED_MSG);
	}

}
