package battleships;

public class Destroyer extends Ship implements ShipType {

	/**
	 * constructor setting ship length
	 */
	public Destroyer() {
		this.length = 2;
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Destroyer";
	}

	@Override
	public String toString() {
		if (this.isSunk)
		   return "x";
		else
		   return "S";
	}

	
	
}
