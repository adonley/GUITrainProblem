package Trains.Controllers;

import java.util.Iterator;
import java.util.LinkedList;

import Trains.Database.Database;

public class CalculateRoute {

	private LinkedList<String> route;
	private String initialStation;
	//private Controller control = Controller.getInstance();
	private Database database = Database.getInstance();
	
	public CalculateRoute(LinkedList<String> route, String initialStation) {
		this.route = route;
		this.initialStation = initialStation;
	}
	
	public int calc() {
		int total = 0;
		String temp;
		
		// NEED TO TEST THIS TO MAKE SURE CONTAINS WORKS WITH STRINGS
		// Then the next inception level with the hashset of station nodes
		// This is why linkedhashset was chosen - to save the time at these requests
		if(database.contains("B"))
			System.out.println("Worked");
		else
			System.out.println("Didnt Work");
		
		Iterator<String> itr = route.iterator();
		while(itr.hasNext()) {
			temp = itr.next();
		}
		
		return total;
	}
}
