/**
 * 
 */
package com.rasith.events.models;

import java.util.UUID;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * @author rasiththirimanna
 *
 */

@Entity
public class Event extends PanacheEntity {

	private UUID trans_id;
	private String trans_time;
	private String rc_number;
	private String client_id;
	private Integer event_count;
	private String location_code;
	private String location_id1;
	private String location_id2;
	private String addr_number;

	public UUID getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(UUID trans_id) {
		this.trans_id = trans_id;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getRc_number() {
		return rc_number;
	}

	public void setRc_number(String rc_number) {
		this.rc_number = rc_number;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public Integer getEvent_count() {
		return event_count;
	}

	public void setEvent_count(Integer event_count) {
		this.event_count = event_count;
	}

	public String getLocation_code() {
		return location_code;
	}

	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}

	public String getLocation_id1() {
		return location_id1;
	}

	public void setLocation_id1(String location_id1) {
		this.location_id1 = location_id1;
	}

	public String getAddr_number() {
		return addr_number;
	}

	public void setAddr_number(String addr_number) {
		this.addr_number = addr_number;
	}

	public String getLocation_id2() {
		return location_id2;
	}

	public void setLocation_id2(String location_id2) {
		this.location_id2 = location_id2;
	}
}
