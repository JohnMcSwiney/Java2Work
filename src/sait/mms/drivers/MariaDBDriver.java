package sait.mms.drivers;

import java.sql.*;

import sait.mms.contracts.DatabaseDriver;

/**
 * Setting up the methods from DatabaseDriver interface
 * 
 * @author Mohummad , Jack(John) , Johnny 
 * @version Apr 22, 2022
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	Connection connection = null;

	@Override
	public void connect() throws SQLException {
		final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE,
				USERNAME, PASSWORD);
		connection = DriverManager.getConnection(DB_URL);

	}

	@Override
	public ResultSet get(String cmd) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery(cmd);

		return results;
	}

	@Override
	public int update(String cmd) throws SQLException {
		Statement stmt = connection.createStatement();
		int rows = stmt.executeUpdate(cmd);

		return rows;
	}

	@Override
	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed())
			connection.close();
	}

}
