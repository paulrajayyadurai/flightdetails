/**
 * 
 */
package com.tokigames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tokigames.dto.FlightDetailsDTO;
import com.tokigames.service.IFlightDetailsService;


@RestController
@RequestMapping("api/flightdetails")
public class FlightDetailsController {
	
	@Autowired
	private IFlightDetailsService iFlightDetailsService;
	
	@RequestMapping(value = "/getFlighDetails", method = RequestMethod.GET)
	public List<FlightDetailsDTO> getRiskNotification() {
		return iFlightDetailsService.getFlightDetailsDTO();
	}
}