package sait.frms.manager;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import sait.frms.exception.flightIsFullException;
import sait.frms.exception.reservationNotFoundException;
import sait.frms.problemdomain.*;

public class ReservationManager {

	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private static final String PATH = "C:\\Users\\johnn\\OneDrive\\Desktop\\java2assign2\\CPRG251_A2_v0.1\\CPRG251A2Skeleton\\res\\reservations.dat";
	private static final String MODE = "rw";

	public ReservationManager() {
		try {
			populateFromBinary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	// Created Wednesday
	// Untested
	public Reservation makeReservations(Flight f, String Name, String citShip) {
		Flight flight = f;
		String resName = Name;
		String citizenship = citShip;
		boolean isActive = true;
		String resCode = generateReservationCode(flight);

		Reservation r = new Reservation(flight, resCode, resName, citizenship, isActive);

		// System.out.println(r);
		// check if the flight is full or not
		// if the reservation can be completed decrement the number of seats
		try {
			if (f.getSeats() > 0) {
				JOptionPane.showMessageDialog(null, "Reservation created. Your code is " + r.getCode() + ". ");
				reservationList.add(r);
				try {
					persist();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return r;
			} else {
				JOptionPane.showMessageDialog(null,
						"The flight you're trying to reserve has " + f.getSeats() + " seats left");
				throw new flightIsFullException(f.getSeats() + " seats left");

			}
		} catch (flightIsFullException ex) {
			System.out.println("ERROR: ");
			ex.printStackTrace();

			return null;
		}
	}

	// Created Wednesday
	// Untested
	public ArrayList<Reservation> findReservations(String code, String airline, String day) {
		String searchCode = code;
		String searchDay = day;
		String searchAirline = airline;
		ArrayList<Reservation> foundResList = new ArrayList<Reservation>();
		boolean found = false;
		for (int i = 0; i < reservationList.size(); i++) {
			Reservation leggomie = reservationList.get(i);
			if (leggomie.getCode().equals(searchCode)
					|| ((leggomie.getDay()).toLowerCase()).equals((searchDay).toLowerCase())
					|| (leggomie.getAirline().toLowerCase()).equals((searchAirline).toLowerCase())) {
				found = true;
				foundResList.add(leggomie);
				//System.out.println(leggomie);
			}
		}
		try {
			if (found == true) {
				return foundResList;
			} else {
				JOptionPane.showMessageDialog(null, "No Reservations found with info provided");
				throw new reservationNotFoundException("No Reservations found with info provided");
			}
		} catch (reservationNotFoundException ex) {
			// System.out.println("ERROR: ");
			// ex.printStackTrace();
			return null;
		}
	}

	private String generateReservationCode(Flight eff) {
		Flight f = eff;

		String resCode = "";
		char firstChar;
		int fourDigit;
		if (f.isDomestic() == true) {
			firstChar = 'D';
		} else {
			firstChar = 'I';
		}
		Random r = new Random();
		fourDigit = 1000 + r.nextInt(10000);
		resCode = firstChar + Integer.toString(fourDigit);
		return resCode;

	}

	public void updateReservation(String newName, String newCShip, boolean status, int index) {

		Reservation r = reservationList.get(index);
		r.setName(newName);
		r.setCitizenship(newCShip);
		r.setActive(status);

		reservationList.add(r);
		reservationList.remove(index);
		try {
			persist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void persist() throws IOException {

		RandomAccessFile inout = new RandomAccessFile(
				"C:\\Users\\johnn\\OneDrive\\Desktop\\java2assign2\\EyreMcSwiney_Assign2\\CPRG251A2Skeleton\\res\\lois.dat",
				"rw");

		for (int i = 0; i < reservationList.size(); i++) {
			inout.writeUTF(reservationList.get(i).toString());

		}
	}

	private void populateFromBinary() throws FileNotFoundException {

		boolean endOfFile = false;
		String line;
		ArrayList<Reservation> bottleList2 = new ArrayList<Reservation>();
		RandomAccessFile filestream = new RandomAccessFile(
				"C:\\Users\\johnn\\OneDrive\\Desktop\\java2assign2\\EyreMcSwiney_Assign2\\CPRG251A2Skeleton\\res\\lois.dat",
				"rw");

		while (endOfFile == false) {
			try {
				line = filestream.readUTF();
				String[] fields = line.split(",");
				Reservation w = new Reservation(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
						fields[6], (Double.parseDouble(fields[7])), (Boolean.parseBoolean(fields[8])));

				bottleList2.add(w);
			} catch (IOException e) {
				endOfFile = true;
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

	}

}