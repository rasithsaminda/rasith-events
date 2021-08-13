/**
 * 
 */
package com.rasith.events.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.models.Event;
import com.rasith.events.provider.EventResponse;
import com.rasith.events.provider.request.UpdateEventRequest;
import com.rasith.events.service.EventDAOService;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class UpdateEventProcessor extends EventApiProcessor {

	@Inject
	EventDAOService eventDAOService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected EventResponse process(Exchange exchange, String uniqueId) throws Exception {

		UpdateEventRequest eventsRequest = getRequest(exchange, UpdateEventRequest.class);
		Map<Long, Event> events = extractEventsFromRequest(eventsRequest);
		boolean isUpdated = false;
		List<Event> eventList = new ArrayList<>();
		try {

			isUpdated = eventDAOService.updateEvents(events);
			eventList = eventDAOService.findEvents();

		} catch (Exception ex) {
			return EventResponse.fail("EVT99999", EventConstants.EVENTS_UPDATED_FAILED_MSG, 200, null);
		}

		if (!isUpdated) {
			return EventResponse.fail("EVT99999", EventConstants.EVENTS_UPDATED_FAILED_MSG, 200, null);
		}
		return new EventResponse(EventConstants.SUCCESS, "EVT00000", EventConstants.EVENTS_UPDATED_MSG,
				EventConstants.EVENTS_SUCCESS, eventList);
	}

	/**
	 * 
	 * @param request
	 * @return list of Events
	 */
	private Map<Long, Event> extractEventsFromRequest(UpdateEventRequest request) {

		Map<Long, Event> eventMap = new HashMap<>();
		for (UpdateEventRequest.Records r : request.getRecords()) {
			for (UpdateEventRequest.Events e : r.getEvents()) {
				Event ev = new Event();
				ev.setTrans_id(r.getTransId());
				ev.setTrans_time(r.getTransTms());
				ev.setRc_number(r.getRcNum());
				ev.setClient_id(r.getClientId());
				ev.setAddr_number(e.getAddrNbr());
				ev.setEvent_count(e.getEventCnt());
				ev.setLocation_code(e.getLocationCd());
				ev.setLocation_id1(e.getLocationId1());
				ev.setLocation_id2(e.getLocationId2());
				eventMap.put(e.getEventId(), ev);
			}
		}
		return eventMap;
	}
}
