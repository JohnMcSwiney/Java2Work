package sait.frms.problemdomain;

import java.util.*;

import sait.frms.exception.InvalidAirportCodeException;

public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	private boolean domestic;

	public Flight() {
	}

	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;

	}

	public String getCode() {
		return code;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public boolean isDomestic() {
		boolean toDomestic = false;
		boolean fromDomestic = false;
		boolean domestic = false;

		// Determine if to is in Canada
		switch (to) {

		case "YYC":
			// Calgary International Airport
			toDomestic = true;
			break;

		case "YEG":
			// Edmonton International Airport
			toDomestic = true;
			break;

		case "YUL":
			// Montreal Pierre Elliott Trudeau International Airport
			toDomestic = true;
			break;

		case "YOW":
			// Ottawa Macdonald-Cartier International Airport
			toDomestic = true;
			break;

		case "YYZ":
			// Toronto Pearson International Airport
			toDomestic = true;
			break;

		case "YVR":
			// Vancouver International Airport
			toDomestic = true;
			break;

		case "YWG":
			// Winnipeg James Armstrong Richardson International Airport
			toDomestic = true;
			break;
		default:
			toDomestic = false;
		}

		// Determine if from is in Canada
		switch (from) {

		case "YYC":
			// Calgary International Airport
			fromDomestic = true;
			break;

		case "YEG":
			// Edmonton International Airport
			fromDomestic = true;
			break;

		case "YUL":
			// Montreal Pierre Elliott Trudeau International Airport
			fromDomestic = true;
			break;

		case "YOW":
			// Ottawa Macdonald-Cartier International Airport
			fromDomestic = true;
			break;

		case "YYZ":
			// Toronto Pearson International Airport
			fromDomestic = true;
			break;

		case "YVR":
			// Vancouver International Airport
			fromDomestic = true;
			break;

		case "YWG":
			// Winnipeg James Armstrong Richardson International Airport
			fromDomestic = true;
			break;
		default:
			fromDomestic = false;
		}

		if (toDomestic == true && fromDomestic == true) {
			domestic = true;
			return domestic;
		}
		this.domestic = domestic;
		return domestic;

	}

	// Should work untested ~ Monday
	public static void parseCode(String code) throws InvalidAirportCodeException {
		String tempcode = code;
		String fullName = null;

		if (tempcode.equals("OA")) {
			fullName = "Otto Airlines";
		} else if (tempcode.equals("CA")) {
			fullName = "Conned Air";
		} else if (tempcode.equals("TB")) {
			fullName = "Try A Bus Airways";
		} else if (tempcode.equals("VA")) {
			fullName = "Vertical Airlines";
		}
		if (fullName == null) {
			throw new InvalidAirportCodeException(tempcode + " Is not a vaild Airport Code");
		}
	}

	@Override
	public String toString() {
		return code + "," + from + "," + to + "," + weekday + "," + time + "," + seats + "," + costPerSeat;
	}

	public String getCostPerSeatS() {
		String s = Double.toString(costPerSeat);
		return s;
	}

}
