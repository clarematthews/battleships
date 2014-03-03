package battleships;

public class Submarine extends Ship implements ShipType {

	/**
	 * constructor setting ship length
	 */
	public Submarine() {
		this.length = 3;
	}

	/**
	 * return ship type
	 *
	 */

	@Override
	public String getShipType() {
       return "Submarine";
	}
}
