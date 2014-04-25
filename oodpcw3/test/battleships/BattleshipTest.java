package battleships;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import battleships.Ship;
import battleships.ShipFactory;

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
		try{
		Ship battleship = shipFactory.createShip("battleship");	
		
		returnedOutput = battleship.getShipType();
		returnedLength = battleship.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
		
		}catch( ReflectiveOperationException rex){}
	}


}
