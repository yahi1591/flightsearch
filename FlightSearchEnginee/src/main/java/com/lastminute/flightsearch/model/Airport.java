package com.lastminute.flightsearch.model;

import java.util.logging.Logger;

public enum Airport {
	MADRID ("MAD"),
	BARCELONA ("BCN"),
	LONDON ("LHR"),
	PARIS ("CDG"),
	FRAKFURT ("FRA"),
	ISTANBUL ("IST"),
	AMSTERDAM ("AMS"),
	ROME ("FCO"),
	COPENHAGEN ("CPH");
	/*MAD ("Madrid"),
	BCN ("Barcelona"),
	LHR ("London"),
	CDG ("Paris"),
	FRA ("Frakfurt"),
	IST ("Istanbul"),
	AMS ("Amsterdam"),
	FCO ("Rome"),
	CHP ("Copenhagen");
	
	private final String airportName;
	
	Airport (String airportName) {
		this.airportName = airportName;
	}*/
	
	private static Logger LOGGER = Logger.getLogger(Airport.class.getName());
	private final String iataCode;
	
	Airport (String iataCode){
		this.iataCode = iataCode;
	}
		
	public static Airport getAirportFromIata(String iataCode) {
		for (Airport ap : Airport.values()){
			if(ap.iataCode.equals(iataCode))
				return ap;
		}
		LOGGER.severe("No existe aeropuerto para el codigo iata ["+iataCode+"]");
		return null;
	}
	
}
