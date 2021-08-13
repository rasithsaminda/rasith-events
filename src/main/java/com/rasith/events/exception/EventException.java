/**
 * 
 */
package com.rasith.events.exception;

/**
 * @author rasiththirimanna
 *
 */
@SuppressWarnings("serial")
public class EventException extends RuntimeException {

	private final String errorCode;
	private final String errorMsg;
	private final int httpCode;
	private final Object data;

	public EventException(String errorCode, String errorMsg, int httpCode) {
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public Object getData() {
		return data;
	}
}
