/**
 * 
 */
package com.rasith.events.validator;

import javax.inject.Inject;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.rasith.events.exception.EventException;
import com.rasith.events.provider.request.CreateEventsRequest;

import io.quarkus.test.junit.QuarkusTest;

/**
 * @author rasiththirimanna
 *
 */
@QuarkusTest
public class EventInputValidatorTest {

	@Inject
	EventInputValidator validator;

	@Test
	public void testValidate() {
		DefaultCamelContext defaultContext = new DefaultCamelContext();
		DefaultExchange defaultExchange = new DefaultExchange(defaultContext);
		CreateEventsRequest request = new CreateEventsRequest();
		defaultExchange.getIn().setBody(request);
		Assertions.assertThrows(EventException.class, () -> validator.validate(defaultExchange));
	}

}
