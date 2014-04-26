package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	
	
	Ocean oc;
	final int OCEAN_SIZE = 10;
	int shipcount = 0;

	@Before
	public void setUp() throws Exception {
		oc = new Ocean();
		
		
	}

	@Test
	public void testOcean() {
		oc = new Ocean();
		String occ = oc.toString();
		System.out.println(occ);
	}

	

	@Test
	public void testGetOceanSize() {
		 assertEquals(10, oc.getOceanSize());
		}

	@Test
	public void testGetHitCount() {
		
		
		Ship s1=new PatrolBoat();
		Ship s2=new PatrolBoat();
		Ship s3=new Submarine();
		s1.placeShipAt(9, 9, true, oc);
		s2.placeShipAt(0, 0, true, oc);
		s3.placeShipAt(1, 1, true, oc);
		
		assertEquals(0, oc.getHitCount());
        oc.shootAt(0,0);
        assertEquals(1, oc.getHitCount());
        oc.shootAt(0,0);
        assertEquals(1, oc.getHitCount());
        oc.shootAt(1,1);
        assertEquals(2, oc.getHitCount());
        oc.shootAt(9,9);
        
        assertEquals(3, oc.getHitCount());
        oc.shootAt(1,1);
        assertEquals(4, oc.getHitCount());
		
	}

	@Test
	public void testGetShipsSunk() {
		
		Ship s1=new PatrolBoat();
		s1.placeShipAt(9, 9, true, oc);  
		oc.shootAt(9,9);
		assertEquals(1,oc.getShipsSunk());
	}
	@Test
	public void testGetShotsFired() {
		assertEquals(0, oc.getShotsFired());
        oc.shootAt(0, 0);
        assertEquals(1, oc.getShotsFired());
        oc.shootAt(9, 9);
        assertEquals(2, oc.getShotsFired());
        oc.shootAt(0, 0);
        assertEquals(3, oc.getShotsFired());
	}

	@Test
	public void testIsGameOver() {
		
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (oc.isOccupied(row, col))
					{oc.shootAt(row,col);
					
					}
			}
		}
		assertTrue("true if equals 11",oc.isGameOver());
	}
	
	@Test
	public void testisCorrectShipCount(){
		assertTrue("True if equals 27 ship cells", oc.isCorrectShipCount());
	}
	
	

	@Test
	public void testIsOccupied() {
		
		Ship[][] ships = oc.getShipArray();
		ships[9][9] = new PatrolBoat();
		
		assertTrue(oc.isOccupied(9,9));
	}

	@Test
	public void testGetShipArray() {
		
		Ship s1=new PatrolBoat();
		s1.placeShipAt(9, 9, true, oc);
		String st = oc.getShipArray()[9][9].getShipType();
		assertEquals(st,s1.getShipType());
	}

	@Test
	public void testShootAt() {
		
		Ship s1=new PatrolBoat();
		s1.placeShipAt(9, 9, true, oc);   
		assertTrue(oc.shootAt(9, 9));
	    assertFalse(oc.shootAt(9,9));
	       
	}  

}
