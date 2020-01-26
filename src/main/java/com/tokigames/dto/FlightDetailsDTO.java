package com.tokigames.dto;

import java.util.Date;

/*
 * @author Anand Kumar
 */
public class FlightDetailsDTO {
	
	private String route;
	private String depature;
	private String arrival;
	private String depatureTime;
	private String arrivalTime;
	private Date depatureDateTime;
	private Date arrivalDateTime;
	
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getDepature() {
		return depature;
	}
	public void setDepature(String depature) {
		this.depature = depature;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDepatureTime() {
		return depatureTime;
	}
	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getDepatureDateTime() {
		return depatureDateTime;
	}
	public void setDepatureDateTime(Date depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}
	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	
}
