package battleships;

public class PatrolBoat extends Ship  {

	/**
	 * constructor setting ship length
	 */
	public PatrolBoat() {
		this.length = 1;
		this.hit = new boolean[length];
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
	public int getLength(){
		return length;
	}
	
}