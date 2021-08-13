/**
 * 
 */
package com.rasith.events.provider;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.rasith.events.helper.CamelHelper;
import com.rasith.events.provider.request.CreateEventsRequest;
import com.rasith.events.provider.request.UpdateEventRequest;

import io.smallrye.common.annotation.Blocking;

/**
 * @author rasiththirimanna
 *
 */

@Path("events")
public class EventProvider {

	private static final String CREATEEVENTS = "createevents";
	private static final String GETEVENT = "getevent";
	private static final String GETALLEVENTS = "getallevents";
	private static final String UPDATEEVENT = "updateevent";
	private static final String DELETEEVENT = "deleteevent";

	@Inject
	ProducerTemplate producerTemplate;

	@Context
	HttpHeaders headers;

	@POST
	@Path("/" + CREATEEVENTS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "maxCreateEventTimer", description = "A measure of how long it takes to store Events.", unit = MetricUnits.MILLISECONDS)
	@Counted(description = "How many create events", absolute = true, name = "createEvents")
	@Blocking
	public Response createEvents(CreateEventsRequest request) {
		return CamelHelper.makeRouteCall(producerTemplate, "direct:createevents", request, headers);
	}

	@GET
	@Path("/" + GETALLEVENTS)
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "maxGetAllEventTimer", description = "A measure of how long it takes to get all Events.", unit = MetricUnits.MILLISECONDS)
	@Counted(description = "How many get all events", absolute = true, name = "getAllEvents")
	@Blocking
	public Response getAllEvents() {
		return CamelHelper.makeRouteCall(producerTemplate, "direct:getallevents", null, headers);
	}

	@GET
	@Path("/" + GETEVENT + "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "maxGetEventTimer", description = "A measure of how long it takes to get an Event.", unit = MetricUnits.MILLISECONDS)
	@Counted(description = "How many get an event", absolute = true, name = "getEventById")
	@Blocking
	public Response getEventById(@PathParam("id") Integer eventId) {
		return CamelHelper.makeRouteCall(producerTemplate, "direct:getevent", eventId, headers);
	}

	@PUT
	@Path("/" + UPDATEEVENT)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "maxUpdateEventTimer", description = "A measure of how long it takes to update Events.", unit = MetricUnits.MILLISECONDS)
	@Counted(description = "How many update events", absolute = true, name = "updateEvent")
	@Blocking
	public Response updateEvent(UpdateEventRequest request) {
		return CamelHelper.makeRouteCall(producerTemplate, "direct:updateevent", request, headers);
	}

	@DELETE
	@Path("/" + DELETEEVENT + "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "maxDeleteEventTimer", description = "A measure of how long it takes to deleta an Event.", unit = MetricUnits.MILLISECONDS)
	@Counted(description = "How many delete events", absolute = true, name = "deleteEventById")
	@Blocking
	public Response deleteEventById(@PathParam("id") Integer eventId) {
		return CamelHelper.makeRouteCall(producerTemplate, "direct:deleteevent", eventId, headers);
	}

}
