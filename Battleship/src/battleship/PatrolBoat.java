package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing a patrol boat. 
 */
public class PatrolBoat extends Ship  {

	public PatrolBoat() {
		this.length = 1;
		this.hit = new boolean[1];
	}

	@Override
	public String getShipType() {
       return "Patrol Boat";
	}
	
	@Override
	public int getLength(){
		return length;
	}
	
}