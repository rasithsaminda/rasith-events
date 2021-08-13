/**
 * 
 */
package com.rasith.events.provider;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author rasiththirimanna
 *
 */

@QuarkusTest
public class EventProviderTest {

//    @Test
//    public void testGetAllEventsEndpoint() {
//        given()
//          .when().get("/events/getallevents")
//          .then()
//             .statusCode(200)
//             .body(containsString("All events are loaded successfully"));
//    }

//    @Test
//    public void testGetGetEventByIdEndpoint() {
//        given()
//          .when().get("/events/getevent/"+1)
//          .then()
//             .statusCode(200)
//             .body(containsString("Event is loaded successfully"));
//    }

}
