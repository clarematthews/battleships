package battleships;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	Ship ship;
	Ocean oc;
	final int OCEAN_SIZE = 10;
	
	@Before
	public void setUp() throws Exception {
		ship = new Battleship();
		oc = new Ocean();
	}

	@Test
	public void testSetAndGetBowRow() {
		ship.setBowRow(1);
		assertEquals(1,ship.getBowRow());
	}

	
	@Test
	public void testSetAndGetBowColumn() {
		 ship.setBowColumn(9);
	     assertEquals(9, ship.getBowColumn());
	}
	

	@Test
	public void testSetAndGetIsHorizontal() {
		ship.setHorizontal(true);
        assertTrue(ship.isHorizontal());
        ship.setHorizontal(false);
        assertFalse(ship.isHorizontal());
	}


	@Test
	public void testGetHit() {
		ship.placeShipAt(6, 6, true, oc); 
		assertTrue(ship.shootAt(6,6));
		boolean[] isHit = ship.getHit();
		assertTrue(isHit[0]);
		assertFalse(isHit[1]);
	}

	@Test
	public void testSetAndGetMiss() {
		//Ship s;
		assertFalse(oc.isOccupied(0,0));
		oc.getShipArray()[0][0].setMiss();
		assertTrue(oc.getShipArray()[0][0].getMiss());
	}

	@Test
	public void testShootAt() {
		
		ship.placeShipAt(6, 6, true, oc); 
		assertTrue(ship.shootAt(6,6));
		assertTrue(ship.hit[0]);
		assertFalse(ship.hit[1]);
		Ship emptysea = new EmptySea();
		emptysea.placeShipAt(9, 9, true, oc);
		assertFalse(emptysea.shootAt(9, 9));
	}

	@Test
	public void testIsSunk() {
		ship.placeShipAt(6, 6, true, oc); 
		assertFalse(ship.isSunk());
		oc.shootAt(6, 6);
		assertFalse(ship.isSunk());
		oc.shootAt(6, 7);
		oc.shootAt(6, 8);
		oc.shootAt(6, 9);
		assertTrue(ship.isSunk());
	}

	@Test
	public void testOkToPlaceShipAt() {
		
		Ship[][]ships =oc.getShipArray();
		Ship acship = new AircraftCarrier();
		
		//make sure empty
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int col = 0; col < OCEAN_SIZE; col++) {
				ships[row][col] = new EmptySea();
				
			}
		}
		
		acship.placeShipAt(0, 5, true, oc);
		assertFalse(ship.okToPlaceShipAt(0,6, true, oc));
	
	}

	@Test
	public void testPlaceShipAt() {
		
		ship.placeShipAt(0,0, true, oc);//BattleShip
		assertTrue(oc.shootAt(0,0));
		assertTrue(oc.shootAt(0,1));
		assertTrue(oc.shootAt(0,2));
		assertTrue(oc.shootAt(0,3));
		
	}

}
