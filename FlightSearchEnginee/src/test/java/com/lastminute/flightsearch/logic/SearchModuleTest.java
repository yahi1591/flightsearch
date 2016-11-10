package com.lastminute.flightsearch.logic;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.lastminute.flightsearch.model.Airline;
import com.lastminute.flightsearch.model.Airport;
import com.lastminute.flightsearch.model.FlightInformation;
import com.lastminute.flightsearch.model.SearchData;
import com.lastminute.flightsearch.model.SearchResultFlight;

public class SearchModuleTest {

	SearchModule searchModule = new SearchModule();
	
	@Test
	public void givenNotDepartureDateWhenSearchThenNotCalled() {
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.COPENHAGEN);
		searchData.setDestinationAirport(Airport.AMSTERDAM);
		searchData.setAdultPassengers(1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -18); //Fecha Pasada
		searchData.setDepartureDate(calendar.getTime());
		ArrayList<SearchResultFlight> result = searchModule.search(searchData);
		assertEquals(0, result.size());
	}
	
	@Test
	public void givenOldDepartureDateWhenSearchThenNotCalled() {
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.COPENHAGEN);
		searchData.setDestinationAirport(Airport.AMSTERDAM);
		searchData.setDepartureDate(new Date());
		ArrayList<SearchResultFlight> result = searchModule.search(searchData);
		assertEquals(0, result.size());
	}
	
	@Test
	public void givenNotPassengersWhenSearchThenNotCalled() {
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.COPENHAGEN);
		searchData.setDestinationAirport(Airport.AMSTERDAM);
		searchData.setDepartureDate(new Date());
		ArrayList<SearchResultFlight> result = searchModule.search(searchData);
		assertEquals(0, result.size());
	}
	
	@Test
	public void givenCorrectSearchDataWhenSearchThenReturnTwoFlights() {
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.COPENHAGEN);
		searchData.setDestinationAirport(Airport.AMSTERDAM);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 18); // Entre 16-30->100%
		searchData.setDepartureDate(calendar.getTime());
		searchData.setAdultPassengers(1);
		searchModule.setAllAvailableFlights(getFakeFlights());
		ArrayList<SearchResultFlight> flights = searchModule.search(searchData);
		assertEquals(2, flights.size());
		// Se llama a getResultFlight
		assertEquals(flights.get(0).getFlightCode(), getFakeResultFlights().get(0).getFlightCode());
		assertEquals(flights.get(1).getTotalFlightPrice(), new BigDecimal("10.00"));
	}
	
	@Test
	public void givenSearchDataWhenGetMatchingFlightsThenReturnTwoFlights(){
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.COPENHAGEN);
		searchData.setDestinationAirport(Airport.AMSTERDAM);
		searchModule.setAllAvailableFlights(getFakeFlights());
		ArrayList<FlightInformation> flights = searchModule.getMatchingFlights(searchData);
		assertEquals(2, flights.size());
		assertEquals(flights.get(0).getFlightCode(), getFakeResultFlights().get(0).getFlightCode());
		assertEquals(flights.get(1).getFlightCode(), getFakeResultFlights().get(1).getFlightCode());
	}
	
	@Test
	public void givenSearchDataWhenGetMatchingFlightsThenReturnZeroFlights(){
		SearchData searchData = new SearchData();
		searchData.setOriginAirport(Airport.BARCELONA);
		searchData.setDestinationAirport(Airport.ISTANBUL);
		searchModule.setAllAvailableFlights(getFakeFlights());
		ArrayList<FlightInformation> flights = searchModule.getMatchingFlights(searchData);
		assertEquals(0, flights.size());
	}
	
	@Test
	public void givenBadSearchDataWhenGetMatchingFlightsThenReturnZeroFlights(){
		SearchData searchData = new SearchData();
		searchModule.setAllAvailableFlights(getFakeFlights());
		ArrayList<FlightInformation> flights = searchModule.getMatchingFlights(searchData);
		assertEquals(0, flights.size());
	}

	public ArrayList<FlightInformation> getFakeFlights() {
		ArrayList<FlightInformation> infoList = new ArrayList<FlightInformation>();
		
		FlightInformation info = new FlightInformation();
		info.setAirline(Airline.IBERIA);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.MADRID);
		info.setOriginAirport(Airport.PARIS);
		info.setFlightCode("IB1111");
		infoList.add(info);
		
		info = new FlightInformation();
		info.setAirline(Airline.LUFTHANSA);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.AMSTERDAM);
		info.setOriginAirport(Airport.COPENHAGEN);
		info.setFlightCode("LH2222");
		infoList.add(info);
		
		info = new FlightInformation();
		info.setAirline(Airline.BRITISH_AIRWAYS);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.AMSTERDAM);
		info.setOriginAirport(Airport.COPENHAGEN);
		info.setFlightCode("BA3333");
		infoList.add(info);
		
		info = new FlightInformation();
		info.setAirline(Airline.RYANAIR);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.LONDON);
		info.setOriginAirport(Airport.ISTANBUL);
		info.setFlightCode("FR4444");
		infoList.add(info);
		
		info = new FlightInformation();
		info.setAirline(Airline.VUELING);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.ROME);
		info.setOriginAirport(Airport.FRAKFURT);
		info.setFlightCode("VY5555");
		infoList.add(info);
		
		return infoList;
	}

	public ArrayList<FlightInformation> getFakeResultFlights() {
		ArrayList<FlightInformation> infoList = new ArrayList<FlightInformation>();
		
		FlightInformation info = new FlightInformation();
		info.setAirline(Airline.LUFTHANSA);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.AMSTERDAM);
		info.setOriginAirport(Airport.COPENHAGEN);
		info.setFlightCode("LH2222");
		infoList.add(info);
		
		info = new FlightInformation();
		info.setAirline(Airline.BRITISH_AIRWAYS);
		info.setBasePrice(BigDecimal.TEN);
		info.setDestinationAirport(Airport.AMSTERDAM);
		info.setOriginAirport(Airport.COPENHAGEN);
		info.setFlightCode("BA3333");
		infoList.add(info);
		
		return infoList;
	}
}