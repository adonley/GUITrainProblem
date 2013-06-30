package Trains.Controllers;

import java.util.Scanner;
import java.util.regex.Pattern;

import Trains.Database.*;
import Trains.GUI.FileChoose;
import Trains.GUI.GUI;
import Trains.GUI.GetNodes;

/**
 * Controller for the Trains.Database class to interface with the Trains.GUI.
 * @author Andrew Donley
 */
public class Controller {
	
	// Database Instance
	private Database database = Database.getInstance();
	
	private final static Controller controllerInstance = new Controller();
	
	private GetNodes get;
	private FileChoose file;
	
	private Controller() { }
	
	public static Controller getInstance() {
		return controllerInstance;
	}
	
	public void start() {
		GUI.createAndShowGUI();
		file = new FileChoose();
		get = new GetNodes();
		get.show();
	}
	
	public void ChangeToFileChooser() {
		//file = new FileChoose();
		file.show();
	}
	
	public void ChangeToGetNodes() {
		get.show();
	}
	
	/**
	 * Scans a string, puts it into the Model while making sure 
	 * the string is in the correct format
	 * @param input the string to be scanned
	 * @return <code>true</code> when the input is scanned and put into the model. <code>false</code>
	 * when the input string contains a 'train stop' not in the correct format.
	 */
	public boolean parseString(String input) {
		// Get rid of all the spaces in the string
		input = input.replaceAll(" ","");
		Scanner scan = new Scanner(input);
		
		// Delineate by commas and new lines
		Pattern pattern = Pattern.compile(",|\n");
		scan.useDelimiter(pattern);
		boolean badInput = false;
		String temp;
		
		// Scanner automatically commas and newline chars
		while(scan.hasNext()){
			temp = scan.next();
			// Check to see if there are two letters followed by an int
			if(Character.isLetter(temp.charAt(0)) && Character.isLetter(temp.charAt(1)) && Character.isDigit(temp.charAt(2))
					&& temp.length() == 3) {
				System.out.print("Good ");
				// TODO Add
			}
			else {
				badInput = true;
				System.out.print("Bad ");
				// TODO Reset database, and tell user that input was bad
			}
		}
		
		return (!badInput);
	}

}
