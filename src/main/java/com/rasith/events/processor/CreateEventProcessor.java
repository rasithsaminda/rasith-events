/**
 * 
 */
package com.rasith.events.processor;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.models.Event;
import com.rasith.events.provider.EventResponse;
import com.rasith.events.provider.request.CreateEventsRequest;
import com.rasith.events.provider.response.CreateEventsReponse;
import com.rasith.events.service.EventDAOService;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class CreateEventProcessor extends EventApiProcessor {

	@Inject
	EventDAOService eventDAOService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected EventResponse process(Exchange exchange, String uniqueId) throws Exception {

		CreateEventsRequest eventsRequest = getRequest(exchange, CreateEventsRequest.class);
		CreateEventsReponse eventsReponse = new CreateEventsReponse();
		List<Event> events = extractEventsFromRequest(eventsRequest);
		long eventCount = 0l;
		try {
			eventCount = eventDAOService.createEvents(events);
		} catch (Exception ex) {
			return EventResponse.fail("EVT99999", EventConstants.EVENT_STORED_FAILED_MSG, 200, null);
		}
		eventsReponse.setEventcount(eventCount);
		return new EventResponse(EventConstants.SUCCESS, "EVT00000", EventConstants.EVENTS_STORED_MSG,
				EventConstants.EVENTS_CREATED, eventsReponse);
	}

	/**
	 * 
	 * @param request
	 * @return list of Events
	 */
	private List<Event> extractEventsFromRequest(CreateEventsRequest request) {

		List<Event> eventList = new ArrayList<>();
		for (CreateEventsRequest.Records r : request.getRecords()) {
			for (CreateEventsRequest.Events e : r.getEvents()) {
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
				eventList.add(ev);
			}
		}
		return eventList;
	}

}
