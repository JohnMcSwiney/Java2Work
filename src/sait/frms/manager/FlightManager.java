package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.InvalidAirportCodeException;
import sait.frms.exception.flightNotFoundException;
import sait.frms.problemdomain.Flight;

public class FlightManager {

	private static final String FLIGHTS_PATH = "C:\\Users\\johnn\\OneDrive\\Desktop\\java2assign2\\EyreMcSwiney_Assign2\\CPRG251A2Skeleton\\res\\flights.csv";
	private static final String AIRPORTS_PATH = "C:\\Users\\johnn\\OneDrive\\Desktop\\java2assign2\\EyreMcSwiney_Assign2\\CPRG251A2Skeleton\\res\\airports.csv";

	public final String WEEKDAY_ANY = "ANY DAY";
	public final String WEEKDAY_MONDAY = "MONDAY";
	public final String WEEKDAY_TUESDAY = "TUESDAY";
	public final String WEEKDAY_WEDNESDAY = "WEDNESDAY";
	public final String WEEKDAY_THURSDAY = "THURSDAY";
	public final String WEEKDAY_FRIDAY = "FRIDAY";
	public final String WEEKDAY_SATURDAY = "SATURDAY";
	public final String WEEKDAY_SUNDAY = "SUNDAY";

	private ArrayList<Flight> flights = new ArrayList<Flight>();
	private ArrayList<String> airports = new ArrayList<String>();
	private ArrayList<String> airportCodes = new ArrayList<String>();
	private ArrayList<String> daysL = new ArrayList<String>();

	public FlightManager() throws FileNotFoundException {
		populateAirports();
		populateFlights();
		populateDays();

	}

	public FlightManager(ArrayList<Flight> f) throws FileNotFoundException {
		this.flights = f;
	}

	private void populateDays() {
		daysL.add(WEEKDAY_ANY);
		daysL.add(WEEKDAY_MONDAY);
		daysL.add(WEEKDAY_TUESDAY);
		daysL.add(WEEKDAY_WEDNESDAY);
		daysL.add(WEEKDAY_THURSDAY);
		daysL.add(WEEKDAY_FRIDAY);
		daysL.add(WEEKDAY_SATURDAY);
		daysL.add(WEEKDAY_SUNDAY);

	}

	//
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public ArrayList<String> getAirports() {
		return airports;
	}

	public ArrayList<String> getAirportCodes() {
		return airportCodes;
	}

	public ArrayList<String> getDaysL() {
		return daysL;
	}

	// created Monday
	// Finalized Tuesday Untested
	//
	// *Note* - the Airport ArrayList has codes and airport names staggered
	// Name is always the next value after the code
	// - Explained in populateAirports()
	public String findAirportByCode(String code) {
		boolean found = false;
		String a = "";
		for (int i = 0; i < airports.size(); ++i) {
			if (airports.get(i).equals(code)) {
				found = true;
				// System.out.println(airports.get(i + 1));
				a = airports.get(i + 1);

			}
		}
		try {
			if (found == true) {
				return a;
			} else {
				throw new InvalidAirportCodeException("Sorry, " + code + " isn't in the system, try again");
			}
		} catch (InvalidAirportCodeException ex) {
			System.out.println("ERROR: ");
			ex.printStackTrace();
			return null;
		}
	}

	// Created Monday
	// Finalized Tuesday Untested
	public Flight findFlightByCode(String code) {
		boolean found = false;
		Flight f = new Flight();

		for (int i = 0; i < flights.size(); i++) {
			Flight leggomie = flights.get(i);
			if (leggomie.getCode().equals(code)) {
				found = true;
				f = leggomie;
			}
		}
		try {
			if (found == true) {
				return f;
			} else {
				throw new flightNotFoundException("No Flights with the code: " + code + " are available");
			}
		} catch (flightNotFoundException ex) {
			System.out.println("ERROR: ");
			ex.printStackTrace();
			return null;
		}
	}

	// Created Tuesday
	// Finalized Tuesday Untested
	public ArrayList<Flight> findFlights(String to, String from, String weekday) {
		String toSearch = to;
		String fromSearch = from;
		String weekdaySearch = weekday;
		String tempCode;
		String tempAirlineName;
		String tempFrom;
		String tempTo;
		String tempWeekday;
		String tempTime;
		int tempSeats;
		double tempCostPerSeat;
		// used to store found flights
		ArrayList<Flight> foundFlightList = new ArrayList<Flight>();
		// used to end loop and have code execute successfully
		boolean found = false;
//		System.out.println(" findFlights ");
//		System.out.println(" day is " + weekday);
//		System.out.println(" from: " + fromSearch);
//		System.out.println(" to: " + toSearch);
		if ((weekday).toUpperCase() == WEEKDAY_ANY) {
//			System.out.println("A N Y ");
			for (int i = 0; i < flights.size(); i++) {
				Flight leggomie = flights.get(i);
				if ((leggomie.getTo().equals(toSearch)) && (leggomie.getFrom().equals(fromSearch))) {
					tempCode = flights.get(i).getCode();
					tempAirlineName = flights.get(i).getAirlineName();
					tempFrom = flights.get(i).getFrom();
					tempTo = flights.get(i).getTo();
					tempWeekday = flights.get(i).getWeekday();
					tempTime = flights.get(i).getTime();
					tempSeats = flights.get(i).getSeats();
					tempCostPerSeat = flights.get(i).getCostPerSeat();
					Flight temp = new Flight(tempCode, tempAirlineName, tempFrom, tempTo, tempWeekday, tempTime,
							tempSeats, tempCostPerSeat);
					foundFlightList.add(temp);
					found = true;
				}
			}
		} else {
			for (int i = 0; i < flights.size(); i++) {
				Flight leggomie = flights.get(i);
//
//				System.out.print(leggomie.getFrom() + " " + fromSearch + " ");
//				System.out.print(leggomie.getTo() + " " + toSearch);
//				System.out.println();

				if ((leggomie.getTo().equals(toSearch)) && (leggomie.getFrom().equals(fromSearch))
						&& (leggomie.getWeekday()).toLowerCase().equals((weekdaySearch).toLowerCase())) {
					tempCode = flights.get(i).getCode();
					tempAirlineName = flights.get(i).getAirlineName();
					tempFrom = flights.get(i).getFrom();
					tempTo = flights.get(i).getTo();
					tempWeekday = flights.get(i).getWeekday();
					tempTime = flights.get(i).getTime();
					tempSeats = flights.get(i).getSeats();
					tempCostPerSeat = flights.get(i).getCostPerSeat();
					Flight temp = new Flight(tempCode, tempAirlineName, tempFrom, tempTo, tempWeekday, tempTime,
							tempSeats, tempCostPerSeat);
					foundFlightList.add(temp);
				}
			}
		}
		//System.out.println(found);
		//System.out.println(foundFlightList.size());
		try {
			if (found = true) {
				return foundFlightList;
			} else {
				throw new flightNotFoundException("No Flights found with the information provided");
			}

		} catch (flightNotFoundException ex) {
			System.out.println("ERROR: ");
			ex.printStackTrace();
			return null;
		}
	}

	// Tested, working
	private void populateFlights() throws FileNotFoundException {
		Scanner fin = new Scanner(new File(FLIGHTS_PATH));
		while (fin.hasNextLine()) {
			String line = fin.nextLine();
			String[] fields = line.split(",");
			String code = fields[0];
			String airlineName = "";
			String leggomie = code.substring(0, 2);
			boolean isValid = false;

			try {
				if (leggomie.equals("OA")) {
					airlineName = "Otto Airlines";
					isValid = true;
				} else if (leggomie.equals("CA")) {
					airlineName = "Conned Air";
					isValid = true;
				} else if (leggomie.equals("TB")) {
					airlineName = "Try A Bus Airways";
					isValid = true;
				} else if (leggomie.equals("VA")) {
					airlineName = "Vertical Airlines";
					isValid = true;
				}
				if (isValid == false) {
					throw new InvalidAirportCodeException(leggomie + " Is not a vaild Airport Code");
				}
			} catch (InvalidAirportCodeException ex) {
				System.out.println("ERROR: ");
				ex.printStackTrace();
				System.out.println("	- Flight has been discarded");
			}
			String fromAirport = fields[1];
			String toAirport = fields[2];
			String weekday = fields[3];
			String time = fields[4];
			int seats = Integer.parseInt(fields[5]);
			double costPerSeat = Double.parseDouble(fields[6]);
			Flight f = new Flight(code, airlineName, fromAirport, toAirport, weekday, time, seats, costPerSeat);

			if (isValid == true) {
				flights.add(f);
			}

		}
		System.out.println("Flights populated");
		fin.close();
	}

	// Tested, working
	private void populateAirports() throws FileNotFoundException {
		Scanner ain = new Scanner(new File(AIRPORTS_PATH));
		while (ain.hasNext()) {
			String line = ain.nextLine();
			// airports.add(line);
			String[] fields = line.split(",");
			this.airportCodes.add(fields[0]); // this is the code
			this.airports.add(fields[1]); // this is the airport
			// so odds are Airport names and evens are codes
			// ***** STARTING AT 0!!!! *****
//			populateAirCodes(airports);
		}
		System.out.println("Airports populated");
	}

//	private void populateAirCodes(ArrayList<String> airports2) {
//		for (int i = 0; i > airports2.size(); i = i + 2) {
//			System.out.println(airports2.get(i));
//		}
//		
//	}

}
