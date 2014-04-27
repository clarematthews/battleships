package battleship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PatrolBoatTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() {
		
		String expectedOutput = "Patrol Boat";
		String returnedOutput = null;
		Integer expectedLength = 1;
		Integer returnedLength;

		shipFactory.registerShip("patrolboat", PatrolBoat.class);
 
		Ship patrolBoat = shipFactory.createShip("patrolboat");	
		
		returnedOutput = patrolBoat.getShipType();
		returnedLength = patrolBoat.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
	}

}

