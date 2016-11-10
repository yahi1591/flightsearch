package com.lastminute.flightsearch.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.lastminute.flightsearch.model.FlightInformation;
import com.lastminute.flightsearch.model.SearchData;
import com.lastminute.flightsearch.model.SearchResultFlight;

public class SearchModule {

	private ArrayList<FlightInformation> allAvailableFlights;
	private static Logger LOGGER = Logger.getLogger(SearchModule.class.getName());

	public SearchModule() {
		FlightLoader loader = new FlightLoader();
		//Leer en el contructor para que no se llame cada vez que se realiza una llamada
		allAvailableFlights = loader.readFlightsFromCSV(); 
	}

	/**
	 * Búsqueda de vuelos que encajan en los parametros de búsqueda
	 * Requisitos para realizar búsqueda: 
	 * Al menos un adulto o un niño 
	 * Fecha de salida no nula y > CURRENT_DAY
	 * (Aeropuertos salida/llegada no van a ser nulos porque en el front se seleccionan de una lista, pero se podría controlar tb en back
	 */
	public ArrayList<SearchResultFlight> search(SearchData searchData) {
		Date currentDate = getDateWithoutTimestamp();
		LOGGER.info("CURRENT_DATE ["+currentDate+"]");
		if((searchData.getAdultPassengers()>0 || searchData.getChildPassengers()>0)
				&& searchData.getDepartureDate() != null
				&& searchData.getDepartureDate().compareTo(currentDate) >= 0) {
			ArrayList<FlightInformation> matchingFlightList = getMatchingFlights(searchData);
			return getResultFlights(matchingFlightList, searchData);
		} else {
			LOGGER.severe("No se dispone de los suficientes datos como para realizar la busqueda");
			return new ArrayList<SearchResultFlight>();
		}
	}

	/**
	 * Obtiene la lista de vuelos con el precio final ya calculado
	 * @param matchingFlights - Vuelos válidos (Coinciden en origen-destino)
	 * @param searchData - parametros de búsqueda
	 * @return
	 */
	protected ArrayList<SearchResultFlight> getResultFlights(ArrayList<FlightInformation> matchingFlights,
			SearchData searchData) {
		ArrayList<SearchResultFlight> resultFlightList = new ArrayList<SearchResultFlight>();
		SearchResultFlight resultFlight;
		for (FlightInformation flight : matchingFlights) {
			resultFlight = new SearchResultFlight();
			resultFlight.setFlightCode(flight.getFlightCode());
			BigDecimal totalFlightPrice = PriceCalculator.getFinalPrice(flight.getBasePrice(), flight.getAirline(), searchData);
			resultFlight.setTotalFlightPrice(totalFlightPrice);
			resultFlightList.add(resultFlight);
		}
		return resultFlightList;
	}

	/**
	 * Obtiene los vuelos que tienen el mismo origen-destino que loscriterios de búsqueda
	 * @param searchData - parametros de búsqueda
	 */
	protected ArrayList<FlightInformation> getMatchingFlights(SearchData searchData) {
		ArrayList<FlightInformation> matchingFlightList = new ArrayList<FlightInformation>();
		for (FlightInformation flightInformation : getAllAvailableFlights()) {
			if (flightInformation.getOriginAirport().equals(searchData.getOriginAirport())
					&& flightInformation.getDestinationAirport().equals(searchData.getDestinationAirport())) {
				matchingFlightList.add(flightInformation);
			}
		}
		return matchingFlightList;
	}

	private Date getDateWithoutTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public ArrayList<FlightInformation> getAllAvailableFlights() {
		return allAvailableFlights;
	}

	public void setAllAvailableFlights(ArrayList<FlightInformation> allAvailableFlights) {
		this.allAvailableFlights = allAvailableFlights;
	}
}
