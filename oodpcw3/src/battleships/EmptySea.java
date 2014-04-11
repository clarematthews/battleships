package battleships;

public class EmptySea extends Ship implements ShipType {

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
