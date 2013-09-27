package Trains.Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Trains.Database.Database;
import Trains.Database.Database.Station;


public class DatabaseTest {

	Database database = Database.getInstance();
	
	@Test
	public void testContains() {
		database.add('B','C', 5);
		
		if(database.contains("B"))
			System.out.println("Hashed on the String name B");
		else
			fail("Didn't contain B");
		
		if(database.contains("C"))
			System.out.println("Hashed on the String name C");
		else
			fail("Didn't contain C");
	}
	
	@Test
	public void testContainsNode() {
		database.add('B','C',5);
		
		Station B = database.getStation("B");
		
		if(B.contains("C"))
			System.out.println("Node C was hashed/matched correctly");
		else
			fail("Node C was not hashed/matched correctly");
	}

}
