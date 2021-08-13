/**
 * 
 */
package com.rasith.events.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rasiththirimanna
 *
 */

@Getter
@Setter
public class EventResponse<T> {

	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_FAILURE = "FAILURE";
	@JsonIgnore
	private int httpCode;
	private String status;
	private String code;
	private String message;
	private T data;

	protected EventResponse() {
		this(STATUS_SUCCESS, "EVT00000", "", 200);
	}

	protected EventResponse(boolean isSuccessful, String code, String message, int httpCode) {
		this(isSuccessful ? STATUS_SUCCESS : STATUS_FAILURE, code, message, httpCode);
	}

	public EventResponse(String status, String code, String message, int httpCode) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.httpCode = httpCode;
	}

	public EventResponse(String status, String code, String message, int httpCode, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.httpCode = httpCode;
		this.data = data;
	}

	@SuppressWarnings("rawtypes")
	public static EventResponse ok() {
		return new EventResponse();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> EventResponse<T> ok(T data, String message) {
		EventResponse<T> output = new EventResponse();
		Optional.ofNullable(data).ifPresent(output::setData);
		output.setHttpCode(200);
		output.setMessage(message);
		return output;
	}

	@SuppressWarnings("rawtypes")
	public static <T> EventResponse fail(String code, String message, int httpCode, T data) {
		@SuppressWarnings("unchecked")
		EventResponse<T> output = new EventResponse(false, code, message, httpCode);
		Optional.ofNullable(data).ifPresent(output::setData);
		return output;
	}
}
