package Trains.Controllers;

import java.util.HashMap;
import java.util.LinkedList;

import Trains.Database.Database;
import Trains.Database.Database.Node;
import Trains.Database.Database.Station;

public class NumberOfPathsNonWeighted {

	private int n;
	private int matrix[][];
	HashMap<String, Integer> helper = new HashMap<String, Integer>();
	private LinkedList<String> listOfNodes;
	private Database database = Database.getInstance();
	
	public NumberOfPathsNonWeighted() {
		n = database.getSize();
		matrix = new int[n][n];
		listOfNodes = new LinkedList<String>();
	
		// Initialize Adj Matrix
		for(int j = 0; j < n; j++) 
			for(int k =0; k < n; k++)
				matrix[j][k] = 0;

	
		int i = 0;
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
	
		// Let's see how that adj matrix looks in memory
		for(int j = 0; j < n; j++) {
			for(int k =0; k < n; k++) {
				System.out.print(matrix[j][k] + " ");
			}
			System.out.print('\n');
		}
	
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int getN() {
		return n;
	}
	
	public int exactNumberPaths(String start, String end, int times) {
		int total = 0;
		int startIndex = helper.get(start);
		int endIndex = helper.get(end);
		int [][] multValue = matrix;
		
		for(int i = 0; i < times - 1; i++) {
			multValue = multMatrix(multValue,matrix);
		}
		
		total = multValue[startIndex][endIndex];
		
		return total;
	}
	
	public int upToMaxPaths(String start, String end, int times) {
		int total = 0;
		int startIndex = helper.get(start);
		int endIndex = helper.get(end);
		int [][] multValue = matrix;
		
		total += multValue[startIndex][endIndex];
		
		for(int i = 0; i < times - 1; i++) {
			multValue = multMatrix(multValue,matrix);
			total += multValue[startIndex][endIndex];
		}
		
		return total;
	}
	
	public int[][] multMatrix(int matrix1[][], int matrix2[][]) {
		
		int result[][] = new int[n][n];
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				result[i][j] = 0;
		
		// Could probably do this matrix squaring in parallel 
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					result[j][i] += matrix1[j][k] * matrix2[k][i];
				}
			}
		}
		
		return result;
	}
}
