/**
 * 
 */
package com.rasith.events.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rasith.events.models.Event;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

/**
 * @author rasiththirimanna
 *
 */

@QuarkusTest
public class PanacheMockingTest {

	@Test
	public void testPanacheMocking() {
		PanacheMock.mock(Event.class);
		// Mocked classes always return a default value
		Assertions.assertEquals(0, Event.count());
	}

	@Test
	public void testEventCounts() {
		PanacheMock.mock(Event.class);
		Mockito.when(Event.count()).thenReturn(23L);
		Assertions.assertEquals(23, Event.count());
	}

	@Test
	public void testEventFindById() {
		PanacheMock.mock(Event.class);
		Event e = new Event();
		Mockito.when(Event.findById(1L)).thenReturn(e);
		Assertions.assertNotNull(Event.findById(1L));
	}
}
