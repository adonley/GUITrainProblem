package Trains.Controllers;

import java.util.LinkedList;

import Trains.Database.Database;
import Trains.Database.Database.Station;

public class CalculateRoute {
	
	//private Controller control = Controller.getInstance();
	private Database database = Database.getInstance();
	
	/**
	 * Calculates the length of a path. O( n + e ) because the LinkedHashSet
	 * doesn't have a get function in O(1) time.
	 * @return length
	 */
	public int calc(LinkedList<String> route) {
		int total = 0;
		String temp;
		Station station;
		
		while(route.size() >  1 && total != -1) {
			temp = route.pollFirst();
			// If the database has this element
			if(database.contains(temp)) {
				// Get the element
				station = database.getStation(temp);
				// See if it has the next stop
				if(station.contains(route.getFirst())) {
					// Get the distance of the next station
					total += station.getNode(route.getFirst()).getDistance();
				}
				// Return -1 if it doesn't have the next stop
				else
					total = -1;
			}
		}
		
		return total;
	}
}
