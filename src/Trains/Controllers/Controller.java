package Trains.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import Trains.Database.*;
import Trains.GUI.FileChoose;
import Trains.GUI.GUI;
import Trains.GUI.GetNodes;
import Trains.GUI.NumberOfPathsDisplay;
import Trains.GUI.SelectSolutionType;
import Trains.GUI.ShortestRouteDisplay;
import Trains.GUI.SpecificRoute;

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
	private SelectSolutionType select;
	private SpecificRoute specific;
	private ShortestRouteDisplay shortest;
	private NumberOfPathsDisplay number;
	
	private Controller() { }
	
	public static Controller getInstance() {
		return controllerInstance;
	}
	
	public void shortestRoute(String input) {
		
		input = input.replaceAll(" |-|\n|,", "");
		
		// If input was invalid (shoulda done this with a regex)
		if(input.length() > 2 || input.length() <= 1 || 
				!Character.isLetter(input.charAt(0)) || !Character.isLetter(input.charAt(0))) {
			// Update with invalid input
			shortest.updateAnswer("Invalid Input");
			return;
		}
		
		ShortestRoute shortCompute = new ShortestRoute();
		
		//System.out.print("Substrings: " + input.substring(0, 1) + " " + input.substring(1) + " ");
		// Update the display
		shortest.updateAnswer(shortCompute.compute(input.substring(0, 1), input.substring(1)));
		
		return;
		
	}
	
	public void start() {
		GUI.createAndShowGUI();
		file = new FileChoose();
		get = new GetNodes();
		select = new SelectSolutionType();
		specific = new SpecificRoute();
		shortest = new ShortestRouteDisplay();
		number = new NumberOfPathsDisplay();
		get.show();
	}
	
	public void ChangeToNumberOfPaths() {
		number.show();
	}
	
	public void ChangeToShortestRoute() {
		shortest.show();
	}
	
	public void ChangeToFileChooser() {
		//file = new FileChoose();
		file.show();
	}
	
	public void ChangeToGetNodes() {
		get.show();
	}
	
	public void ChangeToSelectSolutionType() {
		select.show();
	}
	
	public void ChangeToSpecificRoute() {
		specific.show();
	}
	
	/**
	 * Scans a string, puts it into the Model while making sure 
	 * the string is in the correct format
	 * @param input the string to be scanned
	 * @return <code>null</code> when the input is scanned and put into the model. <code>String</code>
	 * with the values that were incorrect input containing a 'train stop' not in the correct format.
	 */
	// TODO make this return String, for display working correctly, and pass back what went wrong if it doesnt
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
				// Make sure everything is in uppercase here
				temp = temp.toUpperCase();
				System.out.print("Good ");
				database.add(temp.charAt(0), temp.charAt(1), Character.getNumericValue(temp.charAt(2)));
			}
			else {
				badInput = true;
				System.out.print("Bad ");
				// Reset database due to bad input
				database.reset();
			}
		}
		
		/* This is just for testing to see the database if there was good
		 * input.
		 */
		if(!badInput) {
			database.print();
		}
		
		return (!badInput);
	}
	
	/**
	 * Takes a route and computes the length of that route if it exists.
	 * @param input the route of stations to visit.
	 * @return a String giving information on whether the route exists, the route was input incorrectly, and
	 * the cost of going that specific route. This is used in the specific route view.
	 */
	public String parseRoute(String input) {
		
		boolean badInput = false;
		LinkedList<String> routeToPass = new LinkedList<String>();
		String firstStation = null, routeString = "";
		
		// Get rid of all the spaces in the string
		input = input.replaceAll(" ","");
		// Make sure everything is in uppercase for database access
		input = input.toUpperCase();
		Scanner scan = new Scanner(input);
		
		// Delineate by commas and new lines
		Pattern pattern = Pattern.compile("-");
		scan.useDelimiter(pattern);
		
		// Check to see if there is any first station
		if(scan.hasNext()) {
			firstStation = scan.next();
			//System.out.println("First Station: " + firstStation + " length: " + firstStation.length());
			routeToPass.add(firstStation);
			if(firstStation.length() > 1 || !Character.isLetter(firstStation.charAt(0)))
				badInput = true;
		}
		else 
			badInput = true;
		
		// Scan for other stations to visit
		while(scan.hasNext() && !badInput) {
			routeToPass.add(routeString = scan.next());
			//System.out.println("Other Stations: " + routeString + " length: " + routeString.length());
			if(routeString.length() > 1 || !Character.isLetter(routeString.charAt(0)))
				badInput = true;
		}
			
		// Create an output string if the input was good.
		if(!badInput) {
			LinkedList<String> originalRoute = new LinkedList<String>(routeToPass);
			if(routeToPass.size() == 1) {
				routeString = "The length from a station to itself is 0.\n";
				specific.updateAnswer(routeString);
				//System.out.println(routeString);
			}
			else {
				System.out.println("Good Input\n");
				CalculateRoute calculate = new CalculateRoute();
				int length = calculate.calc(routeToPass);
				
				// If the route doesn't exist
				if(length < 0) {
					routeString = "Route ";
					for(String s : originalRoute) {
						routeString = routeString + s + " ";
					}
					routeString += "does not exist.\n";
					specific.updateAnswer(routeString);
					//System.out.println(routeString);
				}
				
				// Route exists and length is found
				else {
					// Reinitialize this
					routeString = "Route from: "; 
					for(String s : originalRoute) {
						routeString = routeString + s + " ";
					}
					routeString += "\nLength: " + length;
					specific.updateAnswer(routeString);
					//System.out.println(routeString);
				}
			}
		}
		else {
			//System.out.println("Bad Input");
			routeString = "Invalid Input\n";
			specific.updateAnswer(routeString);
		}
		
		return routeString;
	}
	
	
	/**
	 * Reads in a file and then parses it to be converted into a station list.
	 * @param file the file to be read in.
	 * @return <cod>true</code> if the file was read in correctly and parsed correctly for stations. <code>false</code> when
	 * there was in issue reading the file, or there was corrupt information in the file.
	 * @throws IOException
	 */
	public boolean readFile(String file) throws IOException {
		String contents = "", temp = "";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((temp = reader.readLine()) != null)
			contents = contents + temp + ", ";
		
		reader.close();
		System.out.print(contents);
		return parseString(contents);
	}

}
