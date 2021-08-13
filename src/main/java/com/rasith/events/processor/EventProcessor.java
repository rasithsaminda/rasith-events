/**
 * 
 */
package com.rasith.events.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.rasith.events.constants.EventConstants;

/**
 * @author rasiththirimanna
 *
 */
public abstract class EventProcessor implements Processor {

	protected <T> T getRequest(Exchange exchange, Class<T> cls) {
		return exchange.getIn().getBody(cls);
	}

	protected <T> void setRequest(Exchange exchange, T obj, Class<T> clazz) {
		exchange.getIn().setBody(obj, clazz);
	}

	protected String getUniqueID(Exchange exchange) {
		return exchange.getProperty(EventConstants.HEADER_UNIQUE_ID_NAME, String.class);
	}

}