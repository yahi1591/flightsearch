package com.lastminute.flightsearch.logic;

import java.io.File;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lastminute.flightsearch.model.Airport;
import com.lastminute.flightsearch.controller.FlightSearchResultServlet;
import com.lastminute.flightsearch.model.Airline;
import com.lastminute.flightsearch.model.FlightInformation;

public class FlightLoader {

	private static Logger LOGGER = Logger.getLogger(FlightSearchResultServlet.class.getName());
	private String FILE_NAME;
	
	public FlightLoader() {
		FILE_NAME = "/flights.CSV";
	}
	
	public ArrayList<FlightInformation> readFlightsFromCSV() {
		ArrayList<FlightInformation> flightList = new ArrayList<FlightInformation>();
		
		URI pathName = null;
		List<String> lines = new ArrayList<>();
		try {
			pathName = getClass().getResource(FILE_NAME).toURI();
			LOGGER.info("PATH_NAME ["+pathName+"]");
			File file = new File(pathName);
			lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			FlightInformation flight;
			for (String line : lines) {
				flight = new FlightInformation();
				String[] arrayInfo = line.split(",");
				flight.setOriginAirport(Airport.getAirportFromIata(arrayInfo[0]));
				flight.setDestinationAirport(Airport.getAirportFromIata(arrayInfo[1]));
				flight.setFlightCode(arrayInfo[2]);
				flight.setAirline(Airline.getAirlineFromIata(arrayInfo[2].substring(0, 2)));
				try {
					flight.setBasePrice(new BigDecimal(arrayInfo[3]));
					flightList.add(flight);
					LOGGER.info(flight.toString());
				} catch (Exception e){
					//Si el formato del precio base no es correcto->no se añade
					LOGGER.info("No incluido ["+flight.getFlightCode()+"] por errores en el precio");
				}
			}
		} catch (Exception e) {
			LOGGER.severe(e.getStackTrace().toString());
		}
		return flightList;
	}

	public String getFileName() {
		return FILE_NAME;
	}

	public void setFileName(String fileName) {
		FILE_NAME = fileName;
	}
}
