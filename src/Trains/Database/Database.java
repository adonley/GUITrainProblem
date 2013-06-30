package Trains.Database;

import java.beans.IntrospectionException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * A singleton database to hold the graphical information about trains.
 * @author Andrew Donley
 */
public class Database {
	
	/** 
	 * ArrayList containing all the train stations. List implementation of the train
	 * station graph.
	 */
	LinkedHashSet<LinkedList<String>> nodes;
	
	/**
	 * The singleton database object that is eagerly initialized.
	 */
	public static final Database db = new Database();
	
	/** 
	 * <code>private</code> constructor that initializes the database node list.
	 */
	private Database() {
		nodes = new LinkedHashSet<LinkedList<String>>();
	}
	
	/**
	 * Returns an instance of the Database class that was initiated previously.
	 * @return <code>db</code> the database instance that is associated with this object.
	 */
	public static Database getInstance() {
		return db;
	}
	
	public void add(String first, String second, int distance) {
		
	}
	
	/**
	 * This object represents stations that a train can go to. This object
	 * also maintains all its connections in a LinkedHashSet.
	 * @author Andrew Donley
	 *
	 */
	public class Station {
		
		private String name;
		private LinkedHashSet<Node> connections;
		
		public boolean equals(String s) {
			if(name.equals(s))
				return true;
			else
				return false;
		}
		
		public Station() {
			connections = new LinkedHashSet<Node>();
			setName("");
		}
		
		public Station(String tempName) {
			connections = new LinkedHashSet<Node>();
			setName(tempName);
		}
		
		public void addConnection(String name, int distance) {
			connections.add(new Node(name, distance));
			return;
		}
		
		public void removeConnection(String tempStation, int distance) {
			connections.remove(new Node(tempStation, distance));
			return;
		}
		
		public boolean contains(String name) {
			Iterator <Node> itr = connections.iterator();
			boolean found = false;
			Node tempNode;
			
			// Iterate through to find an equal element
			while(itr.hasNext()) {
				tempNode = itr.next();
				if(name.equals(tempNode.getName())) {
					found = true;
					break;
				}
			}
			return found;
		}
		/**
		 * Searches for a node with the String name, returns distance to that node.
		 * @param name the String name to be matched for when looking for a station
		 * @return -1 if a route to the station with name does not exist from this node,
		 * distance to the node otherwise.
		 */
		public int getDistance(String name) {
			int distance = -1;
			Iterator <Node> itr = connections.iterator();
			Node tempNode;
			
			// Iterate through to find an equal element
			while(itr.hasNext()) {
				tempNode = itr.next();
				if(name.equals(tempNode.getName())) {
					distance = tempNode.getDistance();
					break;
				}
			}
			return distance;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public class Node {
		
		private String name;
		private int distance;
		
		public Node(String name, int distance) {
			this.setName(name);
			this.setDistance(distance);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}
		
	}

}
