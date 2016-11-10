package com.lastminute.flightsearch.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Logger;

import com.lastminute.flightsearch.model.Airline;
import com.lastminute.flightsearch.model.SearchData;

public class PriceCalculator {

	private static final long LIMIT_2 = 2;
	private static final long LIMIT_15 = 15;
	private static final long LIMIT_30 = 30;
	
	private static final BigDecimal PERCENT_CHILD = new BigDecimal("0.67");
	private static final BigDecimal PERCENT_80 = new BigDecimal("0.80");
	private static final BigDecimal PERCENT_100 = new BigDecimal("1");
	private static final BigDecimal PERCENT_120 = new BigDecimal("1.20");
	private static final BigDecimal PERCENT_150 = new BigDecimal("1.50");
	
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger LOGGER = Logger.getLogger(PriceCalculator.class.getName());
		
	/**
	 * Calculo precio final para todos pasajeros
	 */
	public static BigDecimal getFinalPrice(BigDecimal basePrice, Airline airline, SearchData searchData) {
		LOGGER.info("Calculating price...");
		LOGGER.info("Adultos...");
		BigDecimal adultPassengers = BigDecimal.valueOf(searchData.getAdultPassengers());
		BigDecimal adultPrice = getRulePercentage(searchData.getDepartureDate())
				.multiply(basePrice)
				.multiply(adultPassengers);
		LOGGER.info("basePrice ["+adultPassengers+"]");
		LOGGER.info("AdultPassengers ["+basePrice+"]");
		
		LOGGER.info("Niños...");
		BigDecimal childPassengers = BigDecimal.valueOf(searchData.getChildPassengers());
		BigDecimal childPrice = getRulePercentage(searchData.getDepartureDate())
				.multiply(basePrice)
				.multiply(PERCENT_CHILD).multiply(childPassengers);
		LOGGER.info("basePrice ["+basePrice+"]");
		LOGGER.info("ChildPassengers ["+childPassengers+"]");
		
		LOGGER.info("Bebés...");
		BigDecimal infantPassengers = BigDecimal.valueOf(searchData.getInfantPassengers());
		BigDecimal infantPrice = airline.getInfantPrice()
				.multiply(infantPassengers);
		LOGGER.info("infantPrice ["+airline.getInfantPrice()+"]");
		LOGGER.info("ChildPassengers ["+infantPassengers+"]");
		
		return adultPrice.add(childPrice).add(infantPrice).setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Calcula el porcentaje sobre el precio base en función 
	 * de los días de antelación a la hora de hacer la busqueda
	 * 
	 */
	public static BigDecimal getRulePercentage(Date departureDate) {
		LocalDate searchDate = LocalDate.parse(formatter.format(departureDate));
		LOGGER.info("SEARCH_DATE ["+searchDate+"]");
		//LocalDate no usa TIME-ZONE (Revisar para países?)
		LocalDate currentDate = LocalDate.parse(formatter.format(new Date()));
		LOGGER.info("CURRENT_DATE ["+currentDate+"]");
		
		long priorDays = ChronoUnit.DAYS.between(currentDate, searchDate);
		LOGGER.info("PRIOR_DAYS ["+priorDays+"]");
		if (priorDays > LIMIT_30) {
			LOGGER.info("PERCENT ["+PERCENT_80+"]");
			return PERCENT_80;
		} else if (priorDays > LIMIT_15 && priorDays <= LIMIT_30) {
			LOGGER.info("PERCENT ["+PERCENT_100+"]");
			return PERCENT_100;
		} else if (priorDays > LIMIT_2 && priorDays <= LIMIT_15) {
			LOGGER.info("PERCENT ["+PERCENT_120+"]");
			return PERCENT_120;
		} else if (priorDays <= LIMIT_2) {
			LOGGER.info("PERCENT ["+PERCENT_150+"]");
			return PERCENT_150;
		}
		return PERCENT_100; //Nunca va a llegar porque todos los valores serán al menos <= 2 o > que 30 
	}
}
