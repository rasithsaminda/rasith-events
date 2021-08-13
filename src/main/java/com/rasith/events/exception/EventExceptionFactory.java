/**
 * 
 */
package com.rasith.events.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response.Status;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class EventExceptionFactory {

	private static final List<EventExceptionFactory> INSTANCES = new ArrayList<>();
	protected final Map<String, EventException> errorMappings = new HashMap<>();

	public EventExceptionFactory() {
		errorMappings.put(ERROR.PROPERTY_NOT_FOUND, new EventException(ERROR.PROPERTY_NOT_FOUND,
				MESSAGE.PROPERTY_NOT_FOUND, Status.INTERNAL_SERVER_ERROR.getStatusCode()));
		errorMappings.put(ERROR.UNHANDLED_EXCEPTION, new EventException(ERROR.UNHANDLED_EXCEPTION,
				MESSAGE.UNHANDLED_EXCEPTION, Status.INTERNAL_SERVER_ERROR.getStatusCode()));
		errorMappings.put(ERROR.UNKNOWN_ERROR, new EventException(ERROR.UNKNOWN_ERROR, MESSAGE.UNKNOWN_ERROR,
				Status.INTERNAL_SERVER_ERROR.getStatusCode()));
		errorMappings.put(ERROR.REQUEST_NOT_PROVIDED, new EventException(ERROR.REQUEST_NOT_PROVIDED,
				MESSAGE.REQUEST_NOT_PROVIDED, Status.BAD_REQUEST.getStatusCode()));
		errorMappings.put(ERROR.CONNECTION_TIMED_OUT, new EventException(ERROR.CONNECTION_TIMED_OUT,
				MESSAGE.CONNECTION_TIMED_OUT, Status.GATEWAY_TIMEOUT.getStatusCode()));

		INSTANCES.add(this);
	}

	public Map<String, EventException> getErrorMappings() {
		return errorMappings;
	}

	public List<EventExceptionFactory> getInstances() {
		return INSTANCES;
	}

	public EventException generateCustomizedException(String code, String message, int httpCode) {
		return new EventException(code, message, httpCode);
	}

	public static EventException unknownException() {
		return new EventException(ERROR.UNKNOWN_ERROR, MESSAGE.UNKNOWN_ERROR,
				Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}

	public EventException generateException(String code) {
		return errorMappings.get(code);
	}

	public static class ERROR {

		private static final String PREFIX = "EVT";
		public static final String OK = PREFIX + "00000";
		public static final String PROPERTY_NOT_FOUND = PREFIX + "9995";
		public static final String REQUEST_NOT_PROVIDED = PREFIX + "9996";
		public static final String CONNECTION_TIMED_OUT = PREFIX + "9997";
		public static final String UNHANDLED_EXCEPTION = PREFIX + "9998";
		public static final String UNKNOWN_ERROR = PREFIX + "9999";

		private ERROR() {
		}
	}

	public static class MESSAGE {
		public static final String PROPERTY_NOT_FOUND = "Internal error occurred.";
		public static final String REQUEST_NOT_PROVIDED = "Valid request must be provided.";
		public static final String CONNECTION_TIMED_OUT = "Operation timed out.";
		public static final String UNHANDLED_EXCEPTION = "Unknown error occurs. Please contact the service provider.";
		public static final String UNKNOWN_ERROR = "Unknown error occurs.";

		private MESSAGE() {
		}
	}
}
