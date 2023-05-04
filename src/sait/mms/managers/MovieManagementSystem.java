package sait.mms.managers;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Scanner;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * Setting up the Movie Management System, where the user can add, delete movies
 * to the database or select a year to see which movies were released in that
 * year as well as print a random number of movies and display it back to the
 * user.
 * 
 * @author Mohummad , Jack(John) , Johnny 
 * @version Apr 22, 2022
 */
public class MovieManagementSystem {

	private DatabaseDriver database;

	/**
	 * Creates a new MovieManagementSystem object.
	 * 
	 * @throws SQLException Thrown if error occurs with database.
	 */
	public MovieManagementSystem() throws SQLException {
		this.database = new MariaDBDriver();
		this.database.connect();
	}

	/**
	 * Displays the main menu to the user.
	 * 
	 * @throws SQLExceptionThrown if error occurs with database.
	 */
	public void displayMenu() throws SQLException {
		Scanner in = new Scanner(System.in);

		System.out.println("Jim's Movie Manager");
		System.out.printf("%s %18s", "1.", "Add New Movie\n");
		System.out.printf("%s %34s", "2.", "Print movies released in year\n");
		System.out.printf("%s %32s", "3.", "Print random list of movies\n");
		System.out.printf("%s %19s", "4.", "Delete a movie\n");
		System.out.printf("%s %9s", "5.", "Exit\n");
		System.out.println();

		System.out.print("Enter an option: ");
		int userInput = in.nextInt();

		while (userInput != 5) {

			switch (userInput) {
			case 1:
				addMovie();
				break;
			case 2:
				printMoviesInYear();
				break;
			case 3:
				printRandomMovies();
				break;
			case 4:
				deleteMovie();
				break;
			}
		}
		System.out.println("Goodbye!");
		System.exit(0);

	}

	/**
	 * Allows user to add a movie to the database.
	 * 
	 * @throws SQLException Thrown if error occurs with database.
	 */
	public void addMovie() throws SQLException {
		Scanner in = new Scanner(System.in);

		int duration;
		String title;
		int year;

		System.out.print("Enter a movie title: ");
		title = in.nextLine();

		System.out.print("Enter duration: ");
		duration = in.nextInt();

		System.out.print("Enter year: ");
		year = in.nextInt();

		String cmd = "INSERT INTO movies (duration, title, year) VALUES (" + duration + ", '" + title + "', " + year
				+ ")";

		int rows = database.update(cmd);

		System.out.println("Added movie to database");

		System.out.println();

		displayMenu();
	}

	/**
	 * User can find movies released in a specific year and shows the total duration
	 * of the movies.
	 * 
	 * @throws SQLException Thrown if error occurs with database.
	 */
	public void printMoviesInYear() throws SQLException {
		Scanner in = new Scanner(System.in);
		int year;

		System.out.print("Enter a year: ");
		year = in.nextInt();
		System.out.println();

		String cmd = "SELECT * FROM movies WHERE year = " + year + ";";

		ResultSet results = database.get(cmd);

		int totalDuration = 0;

		System.out.println("Movie List");
		System.out.printf("%-5s %4s %6s  \n", "Duration", "Year", "Title");

		while (results.next()) {
			totalDuration += results.getInt("duration");
			System.out.printf("%-8d %-5d %-6s \n", results.getInt("Duration"), results.getInt("Year"),
					results.getString("Title"));
		}

		System.out.println();

		System.out.println("Total duration: " + totalDuration + " minutes");

		results.close();

		System.out.println();

		displayMenu();
	}

	/**
	 * User can choose a number of random movies to be displayed and shows the total
	 * duration of the movies.
	 * 
	 * @throws SQLException Thrown if error occurs with database.
	 */
	public void printRandomMovies() throws SQLException {
		Scanner in = new Scanner(System.in);

		int numOfMovies;

		System.out.print("Enter number of movies: ");
		numOfMovies = in.nextInt();
		System.out.println();

		String cmd = "SELECT * FROM movies ORDER BY RAND() LIMIT " + numOfMovies + ";";

		ResultSet results = database.get(cmd);

		int totalDuration = 0;

		System.out.println("Movie List");
		System.out.printf("%-5s %4s %6s  \n", "Duration", "Year", "Title");

		while (results.next()) {
			totalDuration += results.getInt("duration");
			System.out.printf("%-8d %-5d %-6s \n", results.getInt("Duration"), results.getInt("Year"),
					results.getString("Title"));
		}
		System.out.println();

		System.out.println("Total duration: " + totalDuration + " minutes");

		results.close();

		System.out.println();

		displayMenu();
	}

	/**
	 * Allows user to delete a specific movie by the ID.
	 * 
	 * @throws SQLException Thrown if error occurs with database.
	 */
	public void deleteMovie() throws SQLException {
		Scanner in = new Scanner(System.in);

		int deleteID;

		System.out.print("Enter the movie ID that you want to delete: ");
		deleteID = in.nextInt();

		String cmd = "DELETE FROM movies WHERE id = " + deleteID + ";";

		int rows = database.update(cmd);

		System.out.println("Movie " + deleteID + " is deleted");

		System.out.println();

		displayMenu();
	}

}
