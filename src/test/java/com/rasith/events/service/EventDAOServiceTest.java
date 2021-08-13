/**
 * 
 */
package com.rasith.events.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rasith.events.models.Event;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

/**
 * @author rasiththirimanna
 *
 */

@QuarkusTest
public class EventDAOServiceTest {

	@InjectMock
	EventDAOService eventDAOService;

	@BeforeEach
	public void setup() throws Exception {
		Mockito.when(eventDAOService.createEvents(Mockito.any())).thenReturn(10l);
		Mockito.when(eventDAOService.updateEvents(Mockito.any())).thenReturn(true);
	}

	@Test
	public void testCreateAllEventsReturnCount() {
		List<Event> eventList = new ArrayList<>();
		Long count = null;
		try {
			count = eventDAOService.createEvents(eventList);
			Assertions.assertNotNull(count);
			Mockito.verify(eventDAOService, Mockito.times(1)).createEvents(Mockito.any());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllEventsWithEmptyList() {
		Mockito.when(eventDAOService.findEvents()).thenReturn(Collections.emptyList());
		Assertions.assertTrue(eventDAOService.findEvents().isEmpty());
	}

	@Test
	public void testFindAllEvents() {
		PanacheMock.mock(Event.class);
		List<Event> eventList = new ArrayList<>() {
			{
				add(new Event());
				add(new Event());
				add(new Event());
			}
		};
		Mockito.when(eventDAOService.findEvents()).thenReturn(eventList);
		Assertions.assertFalse(eventDAOService.findEvents().isEmpty());
	}

	@Test
	public void testFindEventById() {
		Event event = new Event();
		Mockito.when(eventDAOService.findEventById(1)).thenReturn(event);
		Assertions.assertNotNull(eventDAOService.findEventById(1));
		Mockito.verify(eventDAOService, Mockito.atLeastOnce()).findEventById(1);
	}

	@Test
	public void testupdateEventsReturn() {
		Map<Long, Event> eventMap = new HashMap<>() {
			{
				put(1l, new Event());
			}
		};
		Boolean isUpdated = false;
		try {
			isUpdated = eventDAOService.updateEvents(eventMap);
			Assertions.assertTrue(isUpdated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteEventById() {
		Mockito.when(eventDAOService.deleteEventById(1)).thenReturn(true);
		Assertions.assertTrue(eventDAOService.deleteEventById(1));
	}
}
