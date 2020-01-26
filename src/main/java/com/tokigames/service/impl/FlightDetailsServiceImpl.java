package com.tokigames.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tokigames.dto.FlightDataDTO;
import com.tokigames.dto.FlightDetailsDTO;
import com.tokigames.service.IFlightDetailsService;

/*
 * @author Anand Kumar
 */
@Service
public class FlightDetailsServiceImpl implements IFlightDetailsService {

	@Autowired
	RestTemplate restTemplate;

	public List<FlightDetailsDTO> getFlightDetailsDTO() {

		List<FlightDetailsDTO> filghtDetailsDTOList = new ArrayList<FlightDetailsDTO>();
		getCheapFlightDetails(filghtDetailsDTOList);
		getBusinessFlightDetails(filghtDetailsDTOList);

		return filghtDetailsDTOList;

	}

	private void getCheapFlightDetails(List<FlightDetailsDTO> filghtDetailsDTOList) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		FlightDataDTO cheapFlightDataDTO = restTemplate
				.exchange("https://tokigames-challenge.herokuapp.com/api/flights/cheap", HttpMethod.GET, entity,
						FlightDataDTO.class)
				.getBody();
		
		List<FlightDetailsDTO> flightDetailsDTOList =  populateCheapFlightDetails( cheapFlightDataDTO);
		filghtDetailsDTOList.addAll(flightDetailsDTOList);
	}

	private void getBusinessFlightDetails(List<FlightDetailsDTO> filghtDetailsDTOList) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		FlightDataDTO businessFlightDataDTO = restTemplate
				.exchange("https://tokigames-challenge.herokuapp.com/api/flights/business", HttpMethod.GET, entity,
						FlightDataDTO.class)
				.getBody();
		List<FlightDetailsDTO> flightDetailsDTOList =  populateBusinessFlightDetails( businessFlightDataDTO);
		filghtDetailsDTOList.addAll(flightDetailsDTOList);
	}

	

	private List<FlightDetailsDTO> populateCheapFlightDetails(FlightDataDTO cheapFlightDataDTO) {
		List<FlightDetailsDTO> flightDetailsDTOList = new ArrayList<FlightDetailsDTO>();
		FlightDetailsDTO flightDetailsDTO = null;
		if (null != cheapFlightDataDTO && null != cheapFlightDataDTO.getData()
				&& !cheapFlightDataDTO.getData().isEmpty()) {

			for (FlightDetailsDTO flightData : cheapFlightDataDTO.getData()) {
				flightDetailsDTO = new FlightDetailsDTO();

				flightDetailsDTO.setRoute(flightData.getRoute());
				flightDetailsDTO.setArrivalTime(flightData.getArrival());
				flightDetailsDTO.setDepatureTime(flightData.getDepature());

				flightDetailsDTOList.add(flightDetailsDTO);
			}

		}

		return flightDetailsDTOList;
	}

	private List<FlightDetailsDTO> populateBusinessFlightDetails(FlightDataDTO businessFlightDataDTO) {
		List<FlightDetailsDTO> flightDetailsDTOList = new ArrayList<FlightDetailsDTO>();
		FlightDetailsDTO flightDetailsDTO = null;
		if (null != businessFlightDataDTO && null != businessFlightDataDTO.getData()
				&& !businessFlightDataDTO.getData().isEmpty()) {

			for (FlightDetailsDTO flightData : businessFlightDataDTO.getData()) {
				flightDetailsDTO = new FlightDetailsDTO();

				flightDetailsDTO.setArrival(flightData.getArrival());
				flightDetailsDTO.setDepature(flightData.getDepature());
				flightDetailsDTO.setArrivalTime(flightData.getArrivalTime());
				flightDetailsDTO.setDepatureTime(flightData.getDepatureTime());

				flightDetailsDTOList.add(flightDetailsDTO);
			}

		}

		return flightDetailsDTOList;
	}
	
	
	
}
