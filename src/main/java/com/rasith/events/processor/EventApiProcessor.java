/**
 * 
 */
package com.rasith.events.processor;

import org.apache.camel.Exchange;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.provider.EventResponse;

/**
 * @author rasiththirimanna
 *
 */
public abstract class EventApiProcessor extends EventProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setHeader(EventConstants.HEADER_UNIQUE_ID_NAME, getUniqueID(exchange));
		exchange.getIn().setBody(process(exchange, getUniqueID(exchange)));
	}

	@SuppressWarnings("rawtypes")
	protected abstract EventResponse process(Exchange exchange, String uniqueId) throws Exception;
}
