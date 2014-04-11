package battleships;

import battleships.Ship;
import battleships.ShipType;


public class AircraftCarrier extends Ship implements ShipType {	

	/**
	 * constructor setting ship length
	 */
	public AircraftCarrier() {
		this.length = 5;
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Aircraft Carrier";
	}
	

	@Override
	public String toString() {
		if (this.isSunk)
		   return "x";
		else
		   return "S";
	}

}
