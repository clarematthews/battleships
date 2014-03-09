package battleships;

public class Battleship extends Ship implements ShipType {

	/**
	 * constructor setting ship length
	 */
	public Battleship() {
		this.length = 4;
	}

	/**
	 * return ship type
	 *
	 */

	@Override
	public String getShipType() {
       return "Battleship";
	}
}
