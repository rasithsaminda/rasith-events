/**
 * 
 */
package com.rasith.events.validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;

import com.rasith.events.exception.EventExceptionFactory;
import com.rasith.events.provider.request.CreateEventsRequest;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class EventInputValidator extends EventApiInputValidator {

	@Inject
	EventExceptionFactory exceptionFactory;

	@Override
	protected void validate(Exchange exchange) {

		CreateEventsRequest request = getRequest(exchange, CreateEventsRequest.class);
		if (request != null) {
			if (request.getBatchId() == null) {
				throw exceptionFactory.generateCustomizedException("9000", "Batch Id is null", 200);
			}
			if (request.getRecords().isEmpty()) {
				throw exceptionFactory.generateCustomizedException("9001", "Records are null", 200);
			}
			if (!request.getRecords().isEmpty() && request.getRecords().get(0).getEvents().isEmpty()) {
				throw exceptionFactory.generateCustomizedException("9002", "Events are null", 200);
			}
		}
	}
}
