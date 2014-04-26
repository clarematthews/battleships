package battleship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DestroyerTest {

private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		shipFactory = new ShipFactory();
	}

	@Test
	public void test() throws ReflectiveOperationException {
		
		String expectedOutput = "Destroyer";
		String returnedOutput = null;
		Integer expectedLength = 2;
		Integer returnedLength;

	
		try{


		shipFactory.registerShip("destroyer", Destroyer.class);

		Ship destroyer = shipFactory.createShip("destroyer");	
		
		returnedOutput = destroyer.getShipType();
		returnedLength = destroyer.length;
		
		assertEquals(expectedOutput, returnedOutput);
		assertEquals(expectedLength, returnedLength);
		
		}catch( ReflectiveOperationException rex ){}
	}


}
