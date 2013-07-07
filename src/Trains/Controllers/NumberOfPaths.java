package Trains.Controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import Trains.Database.Database;
import Trains.Database.Database.Node;
import Trains.Database.Database.Station;

public class NumberOfPaths {
	
	private LinkedList<String> listOfNodes;
	private Database database = Database.getInstance();
	private int n;
	private int matrix[][];
	
	public NumberOfPaths() {
		n = database.getSize();
		matrix = new int[n][n];
		listOfNodes = new LinkedList<String>();
		
		// Initialize Adj Matrix
		for(int j = 0; j < n; j++) 
			for(int k =0; k < n; k++)
				matrix[j][k] = 0;

		
		int i = 0;
		HashMap<String, Integer> helper = new HashMap<String, Integer>();
		for(Station s : database.getStations()) {
			helper.put(s.getName(), new Integer(i));
			listOfNodes.add(s.getName());
			i++;
			System.out.print(s.getName() + " " + helper.get(s.getName()) + "\n");
		}
		
		// Create Adjacency Matrix
		for(Station s : database.getStations())
			for(Node node : s.getConnections()) 
				matrix[helper.get(s.getName()).intValue()][helper.get(node.getName()).intValue()] = 1;
		
		for(int j = 0; j < n; j++) {
			for(int k =0; k < n; k++) {
				System.out.print(matrix[j][k] + " ");
			}
			System.out.print('\n');
		}
		
	}
	
	
	public String findPaths() {
		
		String answer = null;
		
		
		return answer;
		
	}
	

	/*
	 * This is a reuse to avoid having to rewrite for same features
	 */
	public class DijstraNode {
		String dijstraName;
		String parent;
		LinkedList<Node> dijstraConnections;
		boolean visited;
		int dijstraLength;
		
		public void print() {
			System.out.print(dijstraName + " ");
			
			for(Node n : dijstraConnections) {
				n.print();
			}
			
		}
		
		public LinkedList<Node> getConnections() {
			return dijstraConnections;
		}
		
		public int getLength() {
			return dijstraLength;
		}
		
		public void setLength(int length) {
			this.dijstraLength = length;
		}
		
		public String getName() {
			return dijstraName;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof DijstraNode)
				return this.dijstraName.equals(((DijstraNode) o).getName());
			else
				return false;
		}
		
		
		// Copies the linked hash set to linked list to be messed with
		public DijstraNode(String name) {
			this.visited = false;
			this.dijstraLength = -1;
			this.dijstraName = name;
			this.dijstraConnections = new LinkedList<Node>();
			this.parent = "";
			
			Station temp = database.getStation(name);
			
			LinkedHashSet<Node> nodes = new LinkedHashSet<Node>(temp.getConnections());
			
			// Add node to linkedlist connections
			Iterator<Node> itr = nodes.iterator();
			while(itr.hasNext()) {
				dijstraConnections.add(itr.next());
			}
		}
		
		
	}
	
	
	
	// Two subproblems, find all paths to a node with length < . Then find all cycles with length
}
