package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing an aircraft carrier. 
 */
public class AircraftCarrier extends Ship {	

	public AircraftCarrier() {
		this.length = 5;
		this.hit = new boolean[length];
	}
	
	@Override
	public String getShipType() {
       return "Aircraft Carrier";
	} 
	
	@Override
	public int getLength(){
		return length;
	}
	
}