package com.lastminute.flightsearch.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase usada para devolver los vuelos que cumplen 
 * los parámetro de la búsqueda realizada.
 * @author Yahaira Risso
 *
 */
public class SearchResultFlight implements Serializable {

	private static final long serialVersionUID = 1L;

	private String flightCode;
	private BigDecimal totalFlightPrice;
	
	public SearchResultFlight() {
		
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public BigDecimal getTotalFlightPrice() {
		return totalFlightPrice;
	}

	public void setTotalFlightPrice(BigDecimal totalFlightPrice) {
		this.totalFlightPrice = totalFlightPrice;
	}
}
