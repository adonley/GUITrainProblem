package Trains.Controllers;

//import java.util.HashMap;
//import java.util.LinkedList;

import Trains.Database.Database;
import Trains.Database.Database.Node;
import Trains.Database.Database.Station;

public class NumberOfPaths {
	
	private Database database = Database.getInstance();
	private Station endNode;
	private Station startNode;
	
	public NumberOfPaths(String startNode ,String endNode) {
		this.startNode = database.getStation(startNode);
		this.endNode = database.getStation(endNode);
	}
	
	public Station getStartNode() {
		return startNode;
	}
	
	public int getNumberOfPaths(int distance) {
		return findPaths(distance, startNode.getName());
	}
	
	public int findPaths(int remainingDistance, String nodeName) {
		
		int totalVal = 0;
		Station tempStation = database.getStation(nodeName);
		for(Node node : tempStation.getConnections()) {
			if(endNode.equals(database.getStation(node.getName())) && remainingDistance - node.getDistance() > 0)
				totalVal++;
			
			if(remainingDistance - node.getDistance() > 0)
				totalVal += findPaths(remainingDistance - node.getDistance(), node.getName());
		}
		
		return totalVal;
		
	}

}
