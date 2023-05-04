package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

//import sait.frms.gui.FlightsTab.MyListSelectionListener;
import sait.frms.manager.ReservationManager;
//import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {

	JTextField flightsR;
	JTextField airlineR;
	JTextField dayR;
	JTextField costR;
	JTextField timeR;
	JTextField nameR;
	JTextField citizenshipR;
	JComboBox<String> statusBox;

	JTextField codeSR;
	JTextField airlineSField;
	JTextField daySField;
	JButton reservationSBTN;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	private JList<Reservation> reservationsList;

	private DefaultListModel<Reservation> reservationModel;

	private ArrayList<Reservation> resList;
	
	private ArrayList<Reservation> foundRes;

	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
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

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	private JPanel createLeftPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		reservationModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationModel);

		this.foundRes = reservationManager.getReservationList();

		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservationsList.setModel(reservationModel);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);

		reservationsList.addListSelectionListener(new reservationListSelListener());
		// no idea why it doesn't like this
		// come back and fix later

		//refreshResList();
		panel.add(scrollPane);

		return panel;

	}

	private void refreshResList() {
		reservationModel.removeAllElements();
		for (Reservation r : foundRes) {
			reservationModel.addElement(r);
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

		JButton reserveBTNr = new JButton("Reserve");
		reserveBTNr.addActionListener(new reservationUpdateListener());
		panel.add(reserveBTNr, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createReserveInfo() {

		JPanel parentPanel = new JPanel();

		parentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// flights label
		JLabel flightLabel = new JLabel("Flight:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		parentPanel.add(flightLabel, c);

		// flights field
		flightsR = new JTextField(10);
		flightsR.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		flightsR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(flightsR, c);

		// airline label
		JLabel airlineLabel = new JLabel("Airline:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		parentPanel.add(airlineLabel, c);

		// airline field
		airlineR = new JTextField(10);
		airlineR.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		airlineR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(airlineR, c);

		// day label
		JLabel dayLabel = new JLabel("Day:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		parentPanel.add(dayLabel, c);

		// day field
		dayR = new JTextField(10);
		dayR.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		dayR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(dayR, c);

		// cost label
		JLabel costLabel = new JLabel("Cost:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		parentPanel.add(costLabel, c);

		// cost field
		costR = new JTextField(10);
		costR.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		costR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(costR, c);

		// name label
		JLabel nameLabel = new JLabel("Name:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		parentPanel.add(nameLabel, c);

		// name field
		nameR = new JTextField(10);
		nameR.setEnabled(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		nameR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(nameR, c);

		// citizenship label
		JLabel citizenshipLabel = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		parentPanel.add(citizenshipLabel, c);

		// citizenship field
		citizenshipR = new JTextField(10);
		citizenshipR.setEnabled(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		citizenshipR.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(citizenshipR, c);

		// status label
		JLabel statusLabel = new JLabel("Status:", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		parentPanel.add(statusLabel, c);

		// status box
		statusBox = new JComboBox<String>(new String[] { "Active", "Inactive" });
		statusBox.setEnabled(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		statusBox.setPreferredSize(new Dimension(40, 20));
		parentPanel.add(statusBox, c);

		return parentPanel;
	}

//1 - Flight
//2 - Airline
//3 - Day
//4 - Time
//5 - Cost
//6 - Name
//7 - Citizenship

//swingComponents.RIGHT

	private JPanel createSouthPanel() {

		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// title
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		panel1.add(title, c);

		// from label
		JLabel codeLabel = new JLabel("Code: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//	c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		codeLabel.setPreferredSize(new Dimension(36, 20));
		panel1.add(codeLabel, c);

		// code search field
		codeSR = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		codeSR.setPreferredSize(new Dimension(600, 25));
		panel1.add(codeSR, c);

		// airline label
		JLabel airlineSLabel = new JLabel("Airline: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//	c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;

		panel1.add(airlineSLabel, c);

		// airline search field
		airlineSField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		airlineSField.setPreferredSize(new Dimension(600, 25));
		panel1.add(airlineSField, c);

		// name label
		JLabel nameLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		c.fill = GridBagConstraints.HORIZONTAL;
//	c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(nameLabel, c);

		// name search Field
		daySField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		daySField.setPreferredSize(new Dimension(600, 25));
		panel1.add(daySField, c);

		// find search reservation button
		reservationSBTN = new JButton("Find Reservations");
		reservationSBTN.addActionListener(new reservationFinderHandler());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		panel1.add(reservationSBTN, c);

		return panel1;
	}

	private class reservationFinderHandler implements ActionListener {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {

			String code = codeSR.getText();
			String airline = airlineSField.getText();
			String day = daySField.getText();
			//System.out.println(code);
			clearList();
			emptyFields();
			fillList();

		}
	}

	private class reservationListSelListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int reservationNumber = reservationsList.getSelectedIndex();

			if (reservationNumber >= 0) {
				Reservation r = foundRes.get(reservationNumber);
				//System.out.println(reservationNumber);
				// codeR.setText(r.getCode());
				flightsR.setText(r.getFlightCode());
				airlineR.setText(r.getAirline());
				dayR.setText(r.getDay());
				costR.setText((r.getCost()).toString());
				nameR.setText(r.getName());
				citizenshipR.setText(r.getCitizenship());
				statusBox.setSelectedIndex(0);
				/// gets printed twice ///
			}
		}

	}

	private class reservationUpdateListener implements ActionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int reservationNumber = reservationsList.getSelectedIndex();

//		Reservation r = resList.get(reservationNumber);
			// codeR.setText(r.getCode());
			String s1 = nameR.getText();
			String s2 = citizenshipR.getText();
			int temp = statusBox.getSelectedIndex();
			boolean b;
			if (temp == 0) {
				b = true;
			} else {
				b = false;
			}
			if(!s1.equals(null)&&!s1.equals(" ")) {
				if(!s2.equals(null)&&!s2.equals(" ")) {
					reservationManager.updateReservation(s1, s2, b, reservationNumber);
					JOptionPane.showMessageDialog(null, "Reservation updated!");
					emptyFields();
					updateList();
				
				}
			}else {
				JOptionPane.showMessageDialog(null, "inputs cannot be empty");
			}
			

		}
		

	}
	private void updateList() {
		//System.out.println("List Updated");
		this.resList = reservationManager.getReservationList();
		
	}
	private void fillList() {
		ArrayList<Reservation> reservations = reservationManager.findReservations(codeSR.getText(),
				airlineSField.getText(), daySField.getText());

		if (reservations.size() == 0) {
			JOptionPane.showMessageDialog(null, "No reservations Found");
		}

		else {
			this.foundRes = reservations;
			for (Reservation i : foundRes) {
				reservationModel.addElement(i);
			}
		}
		refreshResList();
	}

	public void emptyFields() {
		flightsR.setText("");
		airlineR.setText("");
		dayR.setText("");
		costR.setText("");
		nameR.setText("");
		citizenshipR.setText("");
		statusBox.setSelectedIndex(0);
	}

	public void clearList() {
		reservationModel.clear();
	}

}
