/**
 * 
 */
package com.rasith.events.helper;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.camel.ProducerTemplate;

import com.rasith.events.constants.EventConstants;
import com.rasith.events.provider.EventResponse;

/**
 * @author rasiththirimanna
 *
 */
public class CamelHelper {

	private CamelHelper() {
	}

	/**
	 * Used to invoke a Camel Route from Java method. Used for invocation of primary
	 * route from Provider Class
	 * 
	 * @return Response
	 */
	@SuppressWarnings("rawtypes")
	public static Response makeRouteCall(ProducerTemplate producerTemplate, String routerName, Object request,
			HttpHeaders httpHeaders) {

		Map<String, Object> headers = httpHeaders.getRequestHeaders().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().get(0)));

		EventResponse eventResponse = producerTemplate.requestBodyAndHeaders(routerName, request, headers,
				EventResponse.class);

		ResponseBuilder responseBuilder = Response.status(200).entity(eventResponse);

		responseBuilder.header(EventConstants.HEADER_UNIQUE_ID_NAME, headers.get(EventConstants.HEADER_UNIQUE_ID_NAME));

		return responseBuilder.build();
	}
}
