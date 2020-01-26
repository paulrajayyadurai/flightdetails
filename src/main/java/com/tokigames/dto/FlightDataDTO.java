package com.tokigames.dto;

import java.util.List;

/*
 * @author Anand Kumar
 */
public class FlightDataDTO {
	
	private List<FlightDetailsDTO> data;

	public List<FlightDetailsDTO> getData() {
		return data;
	}

	public void setData(List<FlightDetailsDTO> data) {
		this.data = data;
	}
	
	
}
