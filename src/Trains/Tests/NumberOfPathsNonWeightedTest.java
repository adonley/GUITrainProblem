package Trains.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Trains.Controllers.NumberOfPathsNonWeighted;
import Trains.Database.Database;

public class NumberOfPathsNonWeightedTest {

	@Test
	public void testMatrixMult() {
		Database database = Database.getInstance();
		database.add('A', 'B', 5);
		database.add('B', 'C', 4);
		database.add('C', 'D', 8);
		
		database.add('D', 'C', 8);
		database.add('D', 'E', 6);
		database.add('A', 'D', 5);
		
		database.add('C', 'E', 2);
		database.add('E', 'B', 3);
		database.add('A', 'E', 7);
		
		NumberOfPathsNonWeighted non = new NumberOfPathsNonWeighted();
		
		int [][]tester = non.multMatrix(non.getMatrix(),non.getMatrix());
		int n = non.getN();
		System.out.print('\n');
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				System.out.print(tester[i][j] + " ");
			System.out.print('\n');
		}
		
		int test2 = non.exactNumberPaths("A", "C", 4);
		System.out.print("Number of Paths: " + test2 + "\n");
		
		int test3 = non.upToMaxPaths("C", "C", 3);
		System.out.print("Number of Paths to Max: " + test3 + "\n");
		
		
	}

}
