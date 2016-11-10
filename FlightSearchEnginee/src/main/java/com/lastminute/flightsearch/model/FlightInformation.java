package com.lastminute.flightsearch.model;

import java.math.BigDecimal;

/**
 * Clase que contiene la información básica sobre los vuelos disponibles Los
 * datos vienen del CSV
 * 
 * @author Yahaira Risso
 *
 */
public class FlightInformation {
	private Airport originAirport;
	private Airport destinationAirport;
	private Airline airline;
	private String flightCode;
	private BigDecimal basePrice;

	public FlightInformation() {

	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String toString() {
		return "[" + flightCode + "][" + airline + "][" + originAirport + "][" + destinationAirport + "][" + basePrice + "]";
	}

}
