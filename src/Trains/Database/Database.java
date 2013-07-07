package Trains.Database;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * A singleton database to hold the graphical information about trains.
 * @author Andrew Donley
 */
public class Database {
	
	/** 
	 * ArrayList containing all the train stations. List implementation of the train
	 * station graph.
	 */
	LinkedHashSet<Station> stations;
	
	/**
	 * The singleton database object that is eagerly initialized.
	 */
	public static final Database db = new Database();
	
	/** 
	 * <code>private</code> constructor that initializes the database node list.
	 */
	private Database() {
		stations = new LinkedHashSet<Station>();
	}
	
	/**
	 * Returns an instance of the Database class that was initiated previously.
	 * @return <code>db</code> the database instance that is associated with this object.
	 */
	public static Database getInstance() {
		return db;
	}
	
	public void reset() {
		db.stations.clear();
	}
	
	public LinkedHashSet<Station> getStations() {
		return stations;
	}
	
	public int getSize() {
		return stations.size();
	}
	
	public void add(char first, char second, int distance) {
		
		Station temp;
		// Try to create the station, hashed set implementation - O(1)
		stations.add(new Station(String.valueOf(first)));
		// Have to add the second one too (even though this is a directed graph
		stations.add(new Station(String.valueOf(second)));
		// Get the station
		temp = getStation(String.valueOf(first));
		// Add connection to the station
		temp.addConnection(String.valueOf(second), distance);
		
	}
	
	public boolean contains(String name) {
		return stations.contains(new Station(name));
	}
	
	public Station getStation(String name) {
		Iterator<Station> itr = stations.iterator();
		Station temp;
		while(itr.hasNext()) {
			temp = itr.next();
			if(name.equals(temp.getName()))
				return temp;
		}
		
		return null;
	}
	
	public void print() {
		Iterator<Station> itr = stations.iterator();
		Station temp;
		
		System.out.print('\n');
		while(itr.hasNext()) {
			temp = itr.next();
			temp.print();
		}
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
		
		public void print() {
			Iterator <Node> itr = connections.iterator();
			Node temp;
			
			System.out.print(this.name + " ");
			while(itr.hasNext()) {
				temp = itr.next();
				temp.print();
			}
			System.out.print("\n");
		}
		
		public Node getNode(String name) {
			Node node = new Node(name);
			boolean found = false;
			Iterator<Node> itr = connections.iterator();
			
			while(itr.hasNext() && !found) {
				node = itr.next();
				if(node.equals(new Node(name))) {
					// This breaks the loop, return found node
					found = true;
				}
			}
			
			// Return null if the node was not found
			if(!found)
				node = null;
			
			return node;
			
		}
		
		public boolean contains(String name) {
			// The 0 doesn't matter here because it compares only the names
			//   in the prompt there was no duplicate routes
			return this.connections.contains(new Node(name,0));
		}
		
		@Override
		public boolean equals(Object o) {
		    if(o instanceof Station) {
				return name.equals(((Station)o).getName());
		    }
			else if(o instanceof String) {
				String temp = (String)o.toString();
				return this.name.equals(temp);
			}
			else {
				return false;
			}
		}
		
		@Override
		public int hashCode() {
			int hashed;
			hashed = name.hashCode();
			return hashed;
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
		
		public LinkedHashSet<Node> getConnections() {
			return connections;
		}
		
		
		/** 
		 * There is no real use for this function, was used before
		 * the equals and hash function was overwritten.
		 * @param name the name of the station to find
		 * @return <code>true</code> if the station was found </code>
		 */
		public boolean containsLinear(String name) {
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
		
		public Node(String name) {
			this.setName(name);
			this.setDistance(0);
		}
		
		@Override
		public int hashCode() {
			int hash;
			hash = name.hashCode();
			return hash;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Node)
				return this.name.equals(((Node) o).getName());
			else
				return false;
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
		
		public void print() {
			System.out.print(this.name + this.distance + " ");
		}
		
	}

}
