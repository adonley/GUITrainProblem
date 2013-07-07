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
	
	public DijstraNode getNode(String name) {
		boolean found = false;
		Iterator<DijstraNode> itr = dijstraList.iterator();
		DijstraNode temp = new DijstraNode(name), temp2 = null;
		
		while(itr.hasNext() && !found) {
			if(temp.equals(temp2 = itr.next()))
				found = true;
		}
		
		return temp2;
	}
	
	private void setNodeLength(String name, int length, String parent) {
		boolean found = false;
		DijstraNode tempNode = null;
		
		Iterator<DijstraNode> itr = dijstraList.iterator();
		while(itr.hasNext() && !found) {
			tempNode = itr.next();
			if(name.equals(tempNode.getName())) {
				found = true;
				tempNode.setLength(length);
				tempNode.parent = parent;
			}
		}
		
		return;
	}
	
	public DijstraNode findSmallest() {

		boolean afterFirst = false;
		DijstraNode tempNode = null, placeKeeper = null;
		
		Iterator<DijstraNode> itr = dijstraList.iterator();
		// Go through the list and find the smallest distance node and pull it out
		//   as visitied
		while(itr.hasNext()) {
			tempNode = itr.next();
			// Find the first node that
			if(tempNode.visited==false && !afterFirst && tempNode.getLength() > -1) {
				afterFirst = true;
				placeKeeper = tempNode;
			}
			// If we already have established placeKeeper
			//   and the length of the found node is less than that of placekeeper
			else if(afterFirst && tempNode.getLength() != -1 && tempNode.visited == false && tempNode.getLength() < placeKeeper.getLength()) {
				placeKeeper = tempNode;
			}
		}
		
		if(placeKeeper != null)
			placeKeeper.visited = true;
		
		// Will return null if there are no non-visited nodes
		return placeKeeper;
	}
	
	public String compute(String start, String end) {
		boolean found = false, outOfOptions = false;
		DijstraNode startNode = null, endNode = null, tempNode = null;
		LinkedList<Node> nodeList = new LinkedList<Node>();
		
		// Check to see if starting node and finishing node exists
		if( !database.contains(start) || !database.contains(end) ) 
			return "Invalid Input, station(s) do not exist.";
		
		startNode = getNode(start);
		endNode = getNode(end);
		startNode.setLength(0);
		tempNode = getNode(start);
		System.out.print("START " + tempNode.dijstraName + "\n");
		
		// While we haven't found the shortest path to the node yet
		//   or we didn't run out of options
		while(!found && !outOfOptions) {
			System.out.print(tempNode.dijstraName + '\n');
			
			nodeList = new LinkedList<Node>(tempNode.getConnections());
			
			// Need to copy position of shortest length away node
			// Relax all the other nodes
			for(Node d : nodeList) {
				
				// Current Node, so there is only one call
				DijstraNode current = getNode(d.getName());
				System.out.print(d.getName());
				
				// If the new edge is has less weight relax
				if( current.getLength() < 0 || tempNode.getLength() + d.getDistance() < current.getLength() ) {
				// Updated smallest length node needs to be checked against
				//   the end node to see if it is the smallest one
					setNodeLength(d.getName(),(tempNode.getLength() + d.getDistance()), tempNode.getName());
				}
				
				System.out.print(" " + d.getDistance() + "\n");
				
			}
			
			System.out.print(" OUT \n");
			
			tempNode = findSmallest();
			
			// Find the smallest node and take it out, this isn't actually dead..
			if(tempNode == null) {
				outOfOptions = true;
				System.out.print("Out of options.\n");
				return "There is no path from " + startNode.getName() + " to "  + endNode.getName();
			}
			
			if(tempNode == endNode) {
				found = true;
				System.out.print("Found the last node. ");
			}
			

		}
		
		System.out.print("Finished: " + tempNode.getName() + " " + tempNode.getLength() + "\n");
		
		return returnPath(startNode,endNode); 
	}
	
	public String returnPath(DijstraNode start, DijstraNode end) {
		String answer = "";
		DijstraNode tempNode = end;
		LinkedList<String> reversePath = new LinkedList<String>();
		
		answer += "Shortest Path: ";
		reversePath.add(tempNode.getName());
		while(!tempNode.equals(start)) {
			reversePath.add(tempNode.parent);
			tempNode = getNode(tempNode.parent);
		}
		
		while(!reversePath.isEmpty()) {
			answer += reversePath.pollLast();
			answer += " ";
		}
		
		answer += "Length: " + end.getLength();
		return answer;
	}
	
	// Dijstra Nodes
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
	
}
