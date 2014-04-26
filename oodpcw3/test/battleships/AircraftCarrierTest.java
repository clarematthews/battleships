package battleships;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import battleships.Ship;
import battleships.ShipFactory;

public class AircraftCarrierTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() throws ReflectiveOperationException {
		
		String expectedOutput = "Aircraft Carrier";
		String returnedOutput = null;
		Integer expectedLength = 5;
		Integer returnedLength;

		try{	


		shipFactory.registerShip("aircraftCarrier", AircraftCarrier.class);

		Ship aircraftCarrier = shipFactory.createShip("aircraftCarrier");	
		
		returnedOutput = aircraftCarrier.getShipType();
		returnedLength = aircraftCarrier.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
		
		}catch( ReflectiveOperationException rex ){}
	
 }
}
