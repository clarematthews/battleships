package battleships;

import battleships.AircraftCarrier;
import battleships.Ship;


public class ShipFactory {

	/**
	 * create ship 
	 */
	public Ship createShip(String string) {
		
		if(string.equals("aircraftCarrier")){
			return new AircraftCarrier();
		}		
		if(string.equals("battleship")){
			return new Battleship();
		}
		if(string.equals("submarine")){
			return new Submarine();
		}
		if(string.equals("destroyer")){
			return new Destroyer();
		}
		if(string.equals("patrolboat")){
			return new PatrolBoat();
		}
		return null;
		
	}

}