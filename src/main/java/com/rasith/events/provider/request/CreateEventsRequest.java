/**
 * 
 */
package com.rasith.events.provider.request;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rasiththirimanna
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventsRequest {

	private UUID batchId;
	private List<Records> records;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Records {
		private UUID transId;
		private String transTms;
		private String rcNum;
		private String clientId;
		private List<Events> events;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Events {
		private Integer eventCnt;
		private String locationCd;
		private String locationId1;
		private String locationId2;
		private String addrNbr;

	}

}
