/**
 * 
 */
package com.rasith.events.validator;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;

import com.rasith.events.processor.EventProcessor;

/**
 * @author rasiththirimanna
 *
 */

@ApplicationScoped
public class EventApiInputValidator extends EventProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {
		this.validate(exchange);
	}

	protected void validate(Exchange exchange) {
		// this method is intended to be overridden by the custom Validator extending
		// this class
	}

}
