package com.lastminute.flightsearch.model;

import java.util.Date;

public class SearchData {

	private Airport originAirport;
	private Airport destinationAirport;
	private Date departureDate;
	private int adultPassengers;
	private int childPassengers;
	private int infantPassengers;
	
	public SearchData() {
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
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date dapartureDate) {
		this.departureDate = dapartureDate;
	}
	public int getAdultPassengers() {
		return adultPassengers;
	}
	public void setAdultPassengers(int adultPassengers) {
		this.adultPassengers = adultPassengers;
	}
	public int getChildPassengers() {
		return childPassengers;
	}
	public void setChildPassengers(int childPassengers) {
		this.childPassengers = childPassengers;
	}
	public int getInfantPassengers() {
		return infantPassengers;
	}
	public void setInfantPassengers(int infantPassengers) {
		this.infantPassengers = infantPassengers;
	}
	
	public String toString(){
		return "["+departureDate+"]["+originAirport+"]["+destinationAirport+"]["+adultPassengers+"]["+childPassengers+"]["+infantPassengers+"]";
	}
}
