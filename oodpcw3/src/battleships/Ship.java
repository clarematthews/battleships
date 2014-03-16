package battleships;

public class Ship {
	
	/**
	 * ship length
	 */
	protected int length;
	
	/**
	 * to be override
	 */
	String getShipType() {
		return null;
	}

	public boolean okToPlaceShipAt(int x, int y, boolean dir, Ocean ocean) {
		// TODO Auto-generated method stub
		return false;
	}

	public void placeShipAt(int x, int y, boolean dir, Ocean ocean) {
		// TODO Auto-generated method stub
		
	}
}
