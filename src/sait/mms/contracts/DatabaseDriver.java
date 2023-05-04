package sait.mms.contracts;

import java.sql.ResultSet;

import java.sql.SQLException;

/**
 * Setting up the DatabaseDriver interface.
 * 
 * @author Mohummad , Jack(John) , Johnny 
 * @version Apr 22, 2022
 */
public interface DatabaseDriver {
	
	/**
	 * Connects with the database.
	 * @throws SQLException Thrown if error occurs with database.
	 */
	void connect() throws SQLException;

	/**
	 * Gets the result from the database.
	 * @param cmd the query used for retrieving, updating, inserting or deleting data in the database;
	 * @return the query.
	 * @throws SQLException Thrown if error occurs with database.
	 */
	ResultSet get(String cmd) throws SQLException;
	
	/**
	 * Updates the data in the database.
	 * @param cmd the query used for retrieving, updating, inserting or deleting data in the database;
	 * @return the query
	 * @throws SQLException Thrown if error occurs with database.
	 */
	int update(String cmd) throws SQLException;
	
	/**
	 * Disconnects the Java program from the database.
	 * @throws SQLException Thrown if error occurs with database.
	 */
	void disconnect() throws SQLException;

}
