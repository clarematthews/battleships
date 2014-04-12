package battleships;

public class Submarine extends Ship implements ShipType {

	/**
	 * constructor setting ship length
	 */
	public Submarine() {
		this.length = 3;
		this.hit = new boolean[3];
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Submarine";
	}
	
	@Override
	public String toString() {
		if (this.isSunk())
		   return "x";
		else
		   return "S";
	}
}
