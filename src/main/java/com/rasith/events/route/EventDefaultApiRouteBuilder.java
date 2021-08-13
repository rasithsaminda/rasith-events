/**
 * 
 */
package com.rasith.events.route;

import org.apache.camel.model.RouteDefinition;

import com.rasith.events.processor.EventApiProcessor;

/**
 * @author rasiththirimanna
 *
 */
public abstract class EventDefaultApiRouteBuilder extends EventApiRouteBuilder {

	@Override
	protected RouteDefinition onProcess(RouteDefinition processor) {
		return processor.process(apiProcessor());
	}

	protected abstract EventApiProcessor apiProcessor();
}
