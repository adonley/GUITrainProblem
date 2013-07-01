package Trains.Controllers;

import java.util.Iterator;
import java.util.LinkedList;

import Trains.Database.Database;

public class CalculateRoute {

	private LinkedList<String> route;
	private String initialStation;
	//private Controller control = Controller.getInstance();
	private Database database = Database.getInstance();
	
	public CalculateRoute(LinkedList<String> route) {
		this.route = route;
		this.initialStation = route.getFirst();
	}
	
	public int calc() {
		int total = 0;
		String temp;
		
		Iterator<String> itr = route.iterator();
		while(itr.hasNext()) {
			temp = itr.next();
		}
		
		return total;
	}
}
