/**
 * 
 */
package com.rasith.events.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rasith.events.provider.EventResponse;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

/**
 * @author rasiththirimanna
 *
 */
@QuarkusTest
public class GetAllEventProcessorTest {

	@InjectMock
	GetAllEventProcessor getAllEventProcessor;

	@BeforeEach
	public void setup() {
		try {
			Mockito.when(getAllEventProcessor.process(Mockito.any(), Mockito.any())).thenReturn(EventResponse.ok());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testProcess() {
		try {
			Assertions.assertNotNull(getAllEventProcessor.process(Mockito.any(), Mockito.any()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
