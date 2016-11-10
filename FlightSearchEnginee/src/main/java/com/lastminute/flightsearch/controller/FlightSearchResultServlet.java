package com.lastminute.flightsearch.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastminute.flightsearch.logic.SearchModule;
import com.lastminute.flightsearch.model.Airport;
import com.lastminute.flightsearch.model.SearchResultFlight;
import com.lastminute.flightsearch.model.SearchData;

@WebServlet("/FlightInformation")
public class FlightSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(FlightSearchResultServlet.class.getName());
	private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private SearchModule searchModule;

	public FlightSearchResultServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		//Si el CSV puede cambiar durante el uso de la aplicación -> mover al método get/post
		searchModule = new SearchModule();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchData searchData = new SearchData();

		Airport originAirport = Airport.valueOf(request.getParameter("originAirport"));
		searchData.setOriginAirport(originAirport);
		Airport destinationAirport = Airport.valueOf(request.getParameter("destinationAirport"));
		searchData.setDestinationAirport(destinationAirport);
		try {
			searchData
					.setDepartureDate(formatter.parse(request.getParameter("departureDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		searchData.setAdultPassengers(Integer.valueOf(request.getParameter("adultPassengers")));
		searchData.setChildPassengers(Integer.valueOf(request.getParameter("childPassengers")));
		searchData.setInfantPassengers(Integer.valueOf(request.getParameter("infantPassengers")));

		LOGGER.info("SEARCH_DATA [" + searchData + "]");

		ArrayList<SearchResultFlight> searchResultFlightList = searchModule.search(searchData);

		request.setAttribute("searchResultFlightList", searchResultFlightList);

		String address = "/WEB-INF/show_results.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
