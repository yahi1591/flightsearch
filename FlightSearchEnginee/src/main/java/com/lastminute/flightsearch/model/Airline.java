package com.lastminute.flightsearch.model;

import java.math.BigDecimal;
import java.util.logging.Logger;

public enum Airline {
	IBERIA ("IB", new BigDecimal(10)),
	BRITISH_AIRWAYS ("BA", new BigDecimal(15)),
	LUFTHANSA ("LH", new BigDecimal(7)),
	RYANAIR ("FR", new BigDecimal(20)),
	VUELING ("VY", new BigDecimal(10)),
	TURKISH_AIRLINES ("TK", new BigDecimal(5)),
	EASYJET ("U2", new BigDecimal(19.90));
		
	private static Logger LOGGER = Logger.getLogger(Airline.class.getName());
	
	private String iataCode;
	private BigDecimal infantPrice;
	
	Airline(String iataCode, BigDecimal infantPrice) {
		this.iataCode = iataCode;
		this.infantPrice = infantPrice;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String airlineName) {
		this.iataCode = airlineName;
	}

	public BigDecimal getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(BigDecimal infantPrice) {
		this.infantPrice = infantPrice;
	}
	
	public static Airline getAirlineFromIata(String iataCode) {
		for (Airline al : Airline.values()){
			if(al.iataCode.equals(iataCode))
				return al;
		}
		LOGGER.severe("No existe aerolinea para el codigo iata ["+iataCode+"]");
		return null;
	}
	
}
