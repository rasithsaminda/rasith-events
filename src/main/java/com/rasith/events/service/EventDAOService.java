/**
 * 
 */
package com.rasith.events.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.rasith.events.models.Event;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class EventDAOService {

	@Transactional
	public long createEvents(List<Event> events) throws Exception {
		List<Event> eventList = events;
		for (Event e : eventList) {
			e.persist();
		}
		return countAllEvents();
	}

	public List<Event> findEvents() {
		List<Event> allEvents = Event.listAll();
		return allEvents;
	}

	public Event findEventById(int eventid) {
		Event event = Event.findById(Long.valueOf(eventid));
		if (event == null) {
			throw new NotFoundException();
		}
		return event;
	}

	public long countAllEvents() {
		long count = Event.count();
		return count;
	}

	@Transactional
	public boolean deleteEventById(int eventid) {
		boolean isDeleted = Event.deleteById(Long.valueOf(eventid));
		return isDeleted;
	}

	@Transactional
	public int updateEventById(int eventid) {
		int isUpdated = Event.update(null, eventid);
		return isUpdated;
	}

	@Transactional
	public boolean updateEvents(Map<Long, Event> eventMap) throws NotFoundException {

		eventMap.entrySet().stream().forEach(e -> {

			Event event = Event.findById(e.getKey());
			if (event == null) {
				throw new NotFoundException();
			}
			// map all fields from the person parameter to the existing entity
			event.setAddr_number(e.getValue().getAddr_number());
			event.setClient_id(e.getValue().getClient_id());
			event.setLocation_code(e.getValue().getLocation_code());
			event.setEvent_count(e.getValue().getEvent_count());
			event.setLocation_id1(e.getValue().getLocation_id1());
			event.setLocation_id2(e.getValue().getLocation_id2());
			event.setRc_number(e.getValue().getRc_number());
			event.setTrans_id(e.getValue().getTrans_id());
			event.setTrans_time(e.getValue().getTrans_time());

		});

		return true;
	}
}
