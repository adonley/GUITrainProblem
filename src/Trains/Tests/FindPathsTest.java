package Trains.Tests;

import org.junit.Test;

import Trains.Controllers.NumberOfPaths;
import Trains.Database.Database;

public class FindPathsTest {

	@Test
	public void testFindPaths() {
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
		
		NumberOfPaths paths = new NumberOfPaths("C","C");
		System.out.print(paths.findPaths(30, paths.getStartNode().getName()) + " paths\n");
	}

}
