package battleships;

public class EmptySea extends Ship implements ShipType {
	
	/**
	 * constructor setting ship length
	 */
	public EmptySea() {
		this.length = 1;
	}
	
	/**
	 * Shoot into empty sea
	 */
	@Override
	public boolean shootAt(int x, int y) {
		return false;
	}
	
	/**
	 * Nothing is sunk
	 */
	@Override
	public boolean isSunk() {
		return false;
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Empty Sea";
	}
	

	@Override
	public String toString() {
		return ".";
	}

}
