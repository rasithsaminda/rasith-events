/**
 * 
 */
package com.rasith.events.exception;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;

import com.rasith.events.provider.EventResponse;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class EventExceptionHandler {

	private static final String CAMELEXCEPTIONCAUGHT = "CamelExceptionCaught";

	EventExceptionHandler() {
	}

	@SuppressWarnings("rawtypes")
	public static EventResponse onExceptionOccurance(Exchange exchange) {
		EventException exception = getEventException(exchange);

		return EventResponse.fail(exception.getErrorCode(), exception.getErrorMsg(), exception.getHttpCode(),
				exception.getData());
	}

	private static EventException getEventException(Exchange exchange) {
		Throwable throwable = (Throwable) exchange.getProperty(CAMELEXCEPTIONCAUGHT);
		EventException exception;

		if (throwable instanceof EventException) {
			exception = (EventException) throwable;
		} else {
			exception = new EventExceptionFactory().generateException(EventExceptionFactory.ERROR.UNHANDLED_EXCEPTION);

		}
		return exception;
	}
}
