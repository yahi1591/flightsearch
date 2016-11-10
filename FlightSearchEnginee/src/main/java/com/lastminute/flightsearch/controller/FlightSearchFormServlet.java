package com.lastminute.flightsearch.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lastminute.flightsearch.model.Airport;

@WebServlet("/index")
public class FlightSearchFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Airport> airports;
	private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public FlightSearchFormServlet() {
		super();
		airports = new ArrayList<Airport>(Arrays.asList(Airport.values()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Default Date = today
		session.setAttribute("departureDate", formatter.format(new Date()));
		session.setAttribute("airports", airports);

		String address = "/WEB-INF/index.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
