package Trains.Controllers;

import java.util.Scanner;

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
		
		Scanner scan = new Scanner(input);
		
		return true;
	}

}
