package Trains;

import java.lang.String;

import Trains.Controllers.Controller;

/**
 * Driver class for the trains problem
 * @author Andrew Donley
 *
 */
public class Trains {

	public static void main(String[]args) {
		Controller controller = Controller.getInstance();
		// Start the program
		controller.start();
	}
	
}
