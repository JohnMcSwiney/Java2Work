package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import sait.frms.exception.cannotBeNullException;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	// private static final ActionListener ActionEvent = null;
	private JTextField flightsF;
	private JTextField airlineF;
	private JTextField dayF;
	private JTextField timeF;
	private JTextField costF;
	private JTextField nameF;
	private JTextField citizenshipF;

	private JComboBox<String> fromCityBox;
	private JComboBox<String> toCityBox;
	private JComboBox<String> dayBox;

	int test = 1;
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	private ArrayList<Flight> flights;
	private ArrayList<Flight> foundFlights;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel leftPanel = createLeftPanel();
		leftPanel.setBorder(new EmptyBorder(10, 15, 60, 15));
		panel.add(leftPanel, BorderLayout.CENTER);

		JPanel rightPanel = createRightPanel();
		panel.add(rightPanel, BorderLayout.LINE_END);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.PAGE_END);
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createLeftPanel() {

		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		this.flights = flightManager.getFlights();

		// User can only select one item at a time.

		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		flightsList.setModel(flightsModel);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		// refreshFlightList();// use something like this!!
		panel.add(scrollPane);
		System.out.println("Flight List Updated");

		return panel;
	}

	public void refreshFlightList() {
		flightsModel.removeAllElements();
		for (Flight f : foundFlights) {
			flightsModel.addElement(f);
		}
	}

	private JPanel createRightPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title, BorderLayout.NORTH);

		JPanel reserveInfo = createReserveInfo();
		reserveInfo.setBorder(new EmptyBorder(15, 0, 15, 0));
		panel.add(reserveInfo, BorderLayout.CENTER);

		JButton reserveBTN = new JButton("Reserve");
		reserveBTN.addActionListener(new reservationMaker());

		panel.add(reserveBTN, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createReserveInfo() {

		JPanel parentPanel = new JPanel();

		parentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// flights label
		JLabel flightLabel = new JLabel("Flight: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		parentPanel.add(flightLabel, c);

		// flights field
		flightsF = new JTextField(10);
		flightsF.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		flightsF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(flightsF, c);

		// airline label
		JLabel airlineLabel = new JLabel("Airline: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		parentPanel.add(airlineLabel, c);

		// airline field
		airlineF = new JTextField(10);
		airlineF.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		airlineF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(airlineF, c);

		// day label
		JLabel dayLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		parentPanel.add(dayLabel, c);

		// day field
		dayF = new JTextField(10);
		dayF.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		dayF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(dayF, c);

		// time label
		JLabel timeLabel = new JLabel("Time: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		parentPanel.add(timeLabel, c);

		// time field
		timeF = new JTextField(10);
		timeF.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		timeF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(timeF, c);

		// cost label
		JLabel costLabel = new JLabel("Cost: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		parentPanel.add(costLabel, c);

		// cost field
		costF = new JTextField(10);
		costF.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		costF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(costF, c);

		// name label
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		parentPanel.add(nameLabel, c);

		// name field
		nameF = new JTextField(10);
		nameF.setEnabled(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		nameF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(nameF, c);

		// citizenship label
		JLabel citizenshipLabel = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		parentPanel.add(citizenshipLabel, c);

		// citizenship field
		citizenshipF = new JTextField(10);
		citizenshipF.setEnabled(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		citizenshipF.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(citizenshipF, c);

		return parentPanel;
	}

//	1 - Flight
//	2 - Airline
//	3 - Day
//	4 - Time
//	5 - Cost
//	6 - Name
//	7 - Citizenship

	// swingComponents.RIGHT

	private JPanel createSouthPanel() {

		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// title
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		panel1.add(title, c);

		// from label
//		JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
		JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;

		panel1.add(fromLabel, c);

		// from city box
		// fromCityBox = new JComboBox<String>(new
		// Vector<String>(flightManager.getAirports()));

		fromCityBox = new JComboBox<String>(new Vector<String>(flightManager.getAirportCodes()));
		// https://stackoverflow.com/a/29978343
		// fromCityBox.addItemListener(new ItemChangeListener());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		fromCityBox.setPreferredSize(new Dimension(600, 25));
		panel1.add(fromCityBox, c);

		// to label
		JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
//				JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//				c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		toLabel.setPreferredSize(new Dimension(36, 20));
		panel1.add(toLabel, c);

		// to city box
		// toCityBox = new JComboBox<String>(new
		// Vector<String>(flightManager.getAirports()));

		toCityBox = new JComboBox<String>(new Vector<String>(flightManager.getAirportCodes()));
		// toCityBox.addItemListener(new ItemChangeListener());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		panel1.add(toCityBox, c);

		// day label
		JLabel dayLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(dayLabel, c);

		// day box
		dayBox = new JComboBox<String>(new Vector<String>(flightManager.getDaysL()));
		// dayBox.addItemListener(new ItemChangeListener());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		panel1.add(dayBox, c);

		// find flight button
		JButton fFlightsBTN = new JButton("Find Flights");
		fFlightsBTN.addActionListener(new flightFinderHandler());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		panel1.add(fFlightsBTN, c);

		return panel1;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			int flightNumber = flightsList.getSelectedIndex();
			for (Flight f : foundFlights) {
			}
			if (flightNumber >= 0) {

				Flight f = foundFlights.get(flightNumber);
				flightsF.setText(f.getCode());
				airlineF.setText(f.getAirlineName());
				dayF.setText(f.getWeekday());
				timeF.setText(f.getTime());
				costF.setText(f.getCostPerSeatS());
			}
		}
	}

	private class flightFinderHandler implements ActionListener {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			// System.out.println(e.getSource());
			clearList();
			emptyFields();
			fillList2();

//			ArrayList<Flight> flights = flightManager.findFlights((String) fromCityBox.getSelectedItem(), 
//	                (String) toCityBox.getSelectedItem(), (String) dayBox.getSelectedItem());
//
//	        if (flights.size() == 0) {
//	            JOptionPane.showMessageDialog(null, "No Flights Found");
//	        }
//
//	        else {
//	            for (Flight i : flights) {
//	                flightsModel.addElement(i);
//	            }
		}

//			FlightManager flightman  = flightManager;
//			
//			String f = String.valueOf(fromCityBox.getSelectedItem());
//			String t = String.valueOf(toCityBox.getSelectedItem());
//			String d = String.valueOf(dayBox.getSelectedItem());
//			ArrayList<Flight> foundFlights = flightman.findFlights(t, f, d);
//			flightsModel.removeAllElements();
//					for ( Flight q : foundFlights) {
//						flightsModel.addElement(q);
	}

	private class reservationMaker implements ActionListener {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String tempFc = flightsF.getText();

			if (tempFc.equals("")) {
				throw new cannotBeNullException("The value entered cannot be null");
			}

			String tempN = nameF.getText();
			String tempC = citizenshipF.getText();

			try {
				if ((tempN.equals("")) || (tempC.equals(""))) {
					System.out.println("null");
					throw new cannotBeNullException("The value entered cannot be null");
				} else {
					Flight f = new Flight();
					f = flightManager.findFlightByCode(tempFc);
					// System.out.println(f);
					reservationManager.makeReservations(f, tempN, tempC);

				}

			} catch (cannotBeNullException ex) {
				System.out.println("! Error:  null input !");
				// ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Must enter: Name & Citizenship value");
			}

		}
	}

//	private void fillList() {
//        ArrayList<Flight> flights = flightManager.findFlights((String) fromCityBox.getSelectedItem(), 
//                (String) toCityBox.getSelectedItem(), (String) dayBox.getSelectedItem());
//
//        if (flights.size() == 0) {
//            JOptionPane.showMessageDialog(null, "No Flights Found");
//        }
//
//        else {
//            for (Flight i : flights) {
//                flightsModel.addElement(i);
//            }
//        }
//    }


	private void fillList2() {
		ArrayList<Flight> foundFlights = flightManager.findFlights((String) fromCityBox.getSelectedItem(),
				(String) toCityBox.getSelectedItem(), (String) dayBox.getSelectedItem());

		if (foundFlights.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Flights Found");
		}

		else {

			for (Flight i : foundFlights) {
				flightsModel.addElement(i);
				this.foundFlights = foundFlights;
			}
		}

	}

	public void emptyFields() {
		flightsF.setText("");
		airlineF.setText("");
		dayF.setText("");
		timeF.setText("");
		costF.setText("");
		nameF.setText("");
		citizenshipF.setText("");
	}

	/**
	 * Clear list of selected flights
	 */
	public void clearList() {
		flightsModel.clear();
	}

}