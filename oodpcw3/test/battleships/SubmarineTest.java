package battleships;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import battleships.Ship;
import battleships.ShipFactory;

public class SubmarineTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() {
		
		String expectedOutput = "Submarine";
		String returnedOutput = null;
		Integer expectedLength = 3;
		Integer returnedLength;
		try{
		Ship submarine = shipFactory.createShip("submarine");	
		
		returnedOutput = submarine.getShipType();
		returnedLength = submarine.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
		
		}catch( ReflectiveOperationException rex ){}
	}


}
