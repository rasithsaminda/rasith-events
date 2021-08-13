/**
 * 
 */
package com.rasith.events.processor;

import java.util.List;

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
public class GetAllEventProcessor extends EventApiProcessor {

	@Inject
	EventDAOService eventDAOService;

	@SuppressWarnings("rawtypes")
	@Override
	protected EventResponse process(Exchange exchange, String uniqueId) throws Exception {
		List<Event> eventList = eventDAOService.findEvents();
		return EventResponse.ok(eventList, EventConstants.EVENTS_ALL_LOADED_MSG);
	}

}
