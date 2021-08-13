/**
 * 
 */
package com.rasith.events.processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.provider.EventResponse;
import com.rasith.events.service.EventDAOService;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class DeleteEventProcessor extends EventApiProcessor {

	@Inject
	EventDAOService eventDAOService;

	@Override
	protected EventResponse process(Exchange exchange, String uniqueId) throws Exception {

		Integer eventId = getRequest(exchange, Integer.class);
		boolean isDeleted = eventDAOService.deleteEventById(eventId);
		if (isDeleted) {
			return EventResponse.ok(null, EventConstants.EVENTS_DELETED_MSG);
		}
		return EventResponse.fail("EVT99999", EventConstants.EVENTS_DELETED_FAILED_MSG, 200, null);
	}

}
