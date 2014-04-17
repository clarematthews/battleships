package battleships;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import battleships.Ship;
import battleships.ShipFactory;

public class DestroyerTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() {
		
		String expectedOutput = "Destroyer";
		String returnedOutput = null;
		Integer expectedLength = 2;
		Integer returnedLength;

		Ship destroyer = shipFactory.createShip("destroyer");	
		
		returnedOutput = destroyer.getShipType();
		returnedLength = destroyer.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
	}


}
