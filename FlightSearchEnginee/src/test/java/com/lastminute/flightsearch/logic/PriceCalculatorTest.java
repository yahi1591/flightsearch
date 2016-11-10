package com.lastminute.flightsearch.logic;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.model.Airline;
import com.lastminute.flightsearch.model.SearchData;

public class PriceCalculatorTest {
	
	private static final BigDecimal PERCENT_80 = new BigDecimal("0.80");
	private static final BigDecimal PERCENT_100 = new BigDecimal("1");
	private static final BigDecimal PERCENT_120 = new BigDecimal("1.20");
	private static final BigDecimal PERCENT_150 = new BigDecimal("1.50");
	private Calendar calendar;
	
	@Before
	public void initDate() {
		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
	}
	
	@Test
	public void givenTwoMonthsAfterDateWhenGetRulePercentageThenReturnPercent80(){
		calendar.add(Calendar.DATE, 60);
		BigDecimal percent = PriceCalculator.getRulePercentage(calendar.getTime());
		assertEquals(percent, PERCENT_80);
	}
	
	@Test
	public void given30DaysAfterDateWhenGetRulePercentageThenReturnPercent100(){
		calendar.add(Calendar.DATE, 30);
		BigDecimal percent = PriceCalculator.getRulePercentage(calendar.getTime());
		assertEquals(percent, PERCENT_100);
	}
	
	@Test
	public void given15DaysAfterDateWhenGetRulePercentageThenReturnPercent120(){
		calendar.add(Calendar.DATE, 15);
		BigDecimal percent = PriceCalculator.getRulePercentage(calendar.getTime());
		assertEquals(percent, PERCENT_120);
	}
	
	@Test
	public void given2DaysAfterDateWhenGetRulePercentageThenReturnPercent150(){
		calendar.add(Calendar.DATE, 2);
		BigDecimal percent = PriceCalculator.getRulePercentage(calendar.getTime());
		assertEquals(percent, PERCENT_150);
	}
	
	@Test
	public void givenExample1WhenGetFinalPriceThenReturnPrice(){
		BigDecimal basePrice = new BigDecimal("197"); //TK2372
		SearchData searchData = new SearchData();
		searchData.setAdultPassengers(1);
		searchData.setChildPassengers(0);
		searchData.setInfantPassengers(0);
		calendar.add(Calendar.DATE, 31);
		searchData.setDepartureDate(calendar.getTime());
		BigDecimal finalPrice = PriceCalculator.getFinalPrice(basePrice, Airline.TURKISH_AIRLINES, searchData);
		assertEquals(new BigDecimal("157.60"), finalPrice);
	}
	
	@Test
	public void givenExample2WhenGetFinalPriceThenReturnPrice(){
		BigDecimal basePrice = new BigDecimal("148"); //LH1085
		SearchData searchData = new SearchData();
		searchData.setAdultPassengers(2);
		searchData.setChildPassengers(1);
		searchData.setInfantPassengers(1);
		calendar.add(Calendar.DATE, 15);
		searchData.setDepartureDate(calendar.getTime());
		BigDecimal finalPrice = PriceCalculator.getFinalPrice(basePrice, Airline.LUFTHANSA, searchData);
		assertEquals(new BigDecimal("481.19"), finalPrice);
	}
	
	@Test
	public void givenExample3WhenGetFinalPriceThenReturnPrice(){
		BigDecimal basePrice = new BigDecimal("259"); //IB2171
		SearchData searchData = new SearchData();
		searchData.setAdultPassengers(1);
		searchData.setChildPassengers(2);
		searchData.setInfantPassengers(0);
		calendar.add(Calendar.DATE, 2);
		searchData.setDepartureDate(calendar.getTime());
		BigDecimal finalPrice = PriceCalculator.getFinalPrice(basePrice, Airline.IBERIA, searchData);
		assertEquals(new BigDecimal("909.09"), finalPrice);
	}
	
	@Test
	public void givenNotPassengersWhenGetFinalPriceThenReturnZero(){
		BigDecimal basePrice = new BigDecimal("259"); 
		SearchData searchData = new SearchData();
		searchData.setAdultPassengers(0);
		searchData.setChildPassengers(0);
		searchData.setInfantPassengers(0);
		calendar.add(Calendar.DATE, 2);
		searchData.setDepartureDate(calendar.getTime());
		BigDecimal finalPrice = PriceCalculator.getFinalPrice(basePrice, Airline.IBERIA, searchData);
		assertEquals(new BigDecimal("0.00"), finalPrice);
	}
	
}
