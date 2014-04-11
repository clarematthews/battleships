package battleships;

public class PatrolBoat extends Ship implements ShipType {

	/**
	 * constructor setting ship length
	 */
	public PatrolBoat() {
		this.length = 1;
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Patrol Boat";
	}

	@Override
	public String toString() {
		if (this.isSunk)
		   return "x";
		else
		   return "S";
	}
}
