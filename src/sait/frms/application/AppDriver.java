package sait.frms.application;

import sait.frms.gui.MainWindow;

import java.io.FileNotFoundException;

import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow mainWindow = null;
		try {
			mainWindow = new MainWindow();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow.display();
	}

}
