package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;

/**
 * Main method for MovieManagementSystem
 * 
 * @author Mohummad , Jack(John) , Johnny 
 * @version Apr 22, 2022
 */
public class appDriver {

	public static void main(String[] args) throws SQLException {
		MovieManagementSystem mms = new MovieManagementSystem();

		mms.displayMenu();
	}

}
