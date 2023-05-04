package sait.frms.problemdomain;

import java.util.Random;

import javax.swing.plaf.TextUI;

public class Reservation {

	private String code;
	private String flightCode;
	private String airline;
	private String day;
	private String time;
	private String name;
	private String citizenship;
	private Double cost;
	private Boolean active;

	@Override
	public String toString() {
		return ("Code: " + code);
	}

	public Reservation() {

	}

	public Reservation(// String code,
//    					String flightCode, String airline, 
			Flight f, String resCode, String name, String citizenship, boolean active
//    		, Double cost 
	) {
		// Create method to generate the reservation code
		// LDDDD
		// Letter [D/I] (either domestic or international)
		// Digits [1000-9999]
		// Doesn't need to be unique
		// Domestic is Canadian to Canadian
		// Method to determine this needs to be added to flights

//    	this.f = flight;
		this.name = name;
		this.citizenship = citizenship;
		this.code = resCode;
		// Name and citizenship cannot be null
		// no pattern
		this.day = f.getWeekday();
		this.time = f.getTime();
		this.flightCode = f.getCode();
		this.airline = f.getAirlineName();
		this.cost = f.getCostPerSeat();
		this.active = active;

	}

	public Reservation(String code, String flightCode, String airline, String day, String time, String name,
			String citizenship, Double cost, Boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.day = day;
		this.time = time;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;

	}

	// Get & Set code
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Get & Set flightCode
	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	// Get & Set Airline
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	// Get & Set Day
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	// Get & Set Time
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// Get & Set Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Get & Set Citizenship
	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	// Get & Set Cost
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	// Get & Set Active
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
