package battleship;


public class PatrolBoat extends Ship  {

	/**
	 * constructor setting ship length
	 */
	public PatrolBoat() {
		this.length = 1;
		this.hit = new boolean[1];
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