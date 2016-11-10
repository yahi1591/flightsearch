package com.lastminute.flightsearch.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.lastminute.flightsearch.model.FlightInformation;

public class FlightLoaderTest {

	FlightLoader loader = new FlightLoader();
	
	@Test
	public void givenCSVOnReadFlightsFromCVSThenReturn10Results(){
		loader.setFileName("/flights_test1.CSV");
		ArrayList<FlightInformation> list = loader.readFlightsFromCSV();
		assertEquals(10, list.size());
	}
	
	@Test
	public void givenNotExistCSVOnReadFlightsFromCVSThenReturn0(){
		loader.setFileName("/flights_test_not_exist.CSV");
		ArrayList<FlightInformation> list = loader.readFlightsFromCSV();
		assertEquals(0, list.size());
	}
	
	@Test
	public void givenCSVWithBadAirportOnReadFlightsFromCVSThenReturn10Results(){
		loader.setFileName("/flights_test2.CSV");
		ArrayList<FlightInformation> list = loader.readFlightsFromCSV();
		assertEquals(10, list.size());
		assertNull(list.get(0).getOriginAirport());
	}
	
	@Test
	public void givenCSVWithBadPriceOnReadFlightsFromCVSThenReturn10Results(){
		loader.setFileName("/flights_test3.CSV");
		ArrayList<FlightInformation> list = loader.readFlightsFromCSV();
		assertEquals(9, list.size());
	}
	
	@Test
	public void givenEmptyCSVWithBadPriceOnReadFlightsFromCVSThenReturn10Results(){
		loader.setFileName("/flights_test4.CSV");
		ArrayList<FlightInformation> list = loader.readFlightsFromCSV();
		assertEquals(0, list.size());
	}
}
