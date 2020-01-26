package com.tokigames.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
		List<FlightDetailsDTO> sortedDetailsDTOList = filghtDetailsDTOList.stream()
				.sorted(Comparator.comparing(FlightDetailsDTO::getArrivalDateTime)).collect(Collectors.toList());
		return sortedDetailsDTOList;

	}

	private void getCheapFlightDetails(List<FlightDetailsDTO> filghtDetailsDTOList) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		FlightDataDTO cheapFlightDataDTO = restTemplate
				.exchange("https://tokigames-challenge.herokuapp.com/api/flights/cheap", HttpMethod.GET, entity,
						FlightDataDTO.class)
				.getBody();

		List<FlightDetailsDTO> flightDetailsDTOList = populateCheapFlightDetails(cheapFlightDataDTO);
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
		List<FlightDetailsDTO> flightDetailsDTOList = populateBusinessFlightDetails(businessFlightDataDTO);
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
				flightDetailsDTO.setArrivalDateTime(convertTimeStamp(flightData.getArrival()));
				flightDetailsDTO.setDepatureDateTime(convertTimeStamp(flightData.getDeparture()));

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
				flightDetailsDTO.setDeparture(flightData.getDeparture());
				flightDetailsDTO.setArrivalDateTime(convertTimeStamp(flightData.getArrivalTime()));
				flightDetailsDTO.setDepatureDateTime(convertTimeStamp(flightData.getDepartureTime()));

				flightDetailsDTOList.add(flightDetailsDTO);
			}

		}

		return flightDetailsDTOList;
	}

	private Date convertTimeStamp(String timeStampString) {
		Date date = null;
		if (null != timeStampString) {
			try {
				SimpleDateFormat sf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
				date = new Date(Long.parseLong(timeStampString.substring(0, timeStampString.indexOf("."))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return date;
	}

}
