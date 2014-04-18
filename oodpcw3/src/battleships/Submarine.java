package battleships;

public class Submarine extends Ship {

	/**
	 * constructor setting ship length
	 */
	public Submarine() {
		this.length = 3;
		this.hit = new boolean[length];
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Submarine";
	}
	
	@Override
	public int getLength(){
		return length;
	}
	
}
