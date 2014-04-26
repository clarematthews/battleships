package battleship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ShipFactoryTest {
	
	private static ShipFactory shipFactory;
	
	@BeforeClass
	public static void onlyOnce(){
		
		shipFactory = new ShipFactory();
		
        shipFactory.registerShip("emptySea", EmptySea.class);
        shipFactory.registerShip("aircraftCarrier", AircraftCarrier.class);
        shipFactory.registerShip("battleship", Battleship.class);
        shipFactory.registerShip("destroyer", Destroyer.class);
        shipFactory.registerShip("submarine", Submarine.class);
        shipFactory.registerShip("patrolboat", PatrolBoat.class);
        
	}

	@Test
	public void test() throws ReflectiveOperationException {

	    assertTrue(shipFactory.createShip("emptySea") instanceof EmptySea);
	    assertTrue(shipFactory.createShip("aircraftCarrier") instanceof AircraftCarrier);
	    assertTrue(shipFactory.createShip("battleship") instanceof Battleship);
	    assertTrue(shipFactory.createShip("aircraftCarrier") instanceof AircraftCarrier);
	    assertTrue(shipFactory.createShip("destroyer") instanceof Destroyer);
	    assertTrue(shipFactory.createShip("submarine") instanceof Submarine);
	    assertTrue(shipFactory.createShip("patrolboat") instanceof PatrolBoat);	
	    
   }
}