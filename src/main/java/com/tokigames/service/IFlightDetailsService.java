package com.tokigames.service;

import java.util.List;

import com.tokigames.dto.FlightDetailsDTO;

/*
 * @author Anand Kumar
 */
public interface IFlightDetailsService {
	
	List<FlightDetailsDTO> getFlightDetailsDTO();
}
