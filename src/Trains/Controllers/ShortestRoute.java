package Trains.Controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import Trains.Database.Database;
import Trains.Database.Database.Node;
import Trains.Database.Database.Station;

public class ShortestRoute {
	
	private LinkedList<DijstraNode> dijstraList;
	private Database database = Database.getInstance();
	
	public ShortestRoute() {
		
		dijstraList = new LinkedList<DijstraNode>();
		LinkedHashSet <Station> tempStations = new LinkedHashSet<Station> (database.getStations());
		Station temp;

		// Add all the stations
		Iterator<Station> itr = tempStations.iterator();
		while(itr.hasNext()) {
			temp = itr.next();
			dijstraList.add(new DijstraNode(temp.getName()));
		}
		
	}
	
	public String compute(String start, String end) {
		String value = "";
		
		
		return value; 
	}
	
	// Dijstra Nodes
	public class DijstraNode {
		String dijstraName;
		LinkedList<Node> dijstraConnections;
		boolean visited;
		
		public void print() {
			System.out.print(dijstraName + " ");
			
			for(Node n : dijstraConnections) {
				n.print();
			}
			
		}
		
		// Copies the linked hash set to linked list to be messed with
		public DijstraNode(String name) {
			this.visited = false;
			
			this.dijstraName = name;
			this.dijstraConnections = new LinkedList<Node>();
			
			Station temp = database.getStation(name);
			
			LinkedHashSet<Node> nodes = new LinkedHashSet<Node>(temp.getConnections());
			
			// Add node to linkedlist connections
			Iterator<Node> itr = nodes.iterator();
			while(itr.hasNext()) {
				dijstraConnections.add(itr.next());
			}
		}
		
		
	}
	
}
