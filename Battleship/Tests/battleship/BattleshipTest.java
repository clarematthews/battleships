package battleship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BattleshipTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() {
		
		String expectedOutput = "Battleship";
		String returnedOutput = null;
		Integer expectedLength = 4;
		Integer returnedLength;

		shipFactory.registerShip("battleship", Battleship.class);

		Ship battleship = shipFactory.createShip("battleship");	
		
		returnedOutput = battleship.getShipType();
		returnedLength = battleship.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
	}

}
