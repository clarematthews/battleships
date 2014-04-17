package battleships;

public class Destroyer extends Ship {

	/**
	 * constructor setting ship length
	 */
	public Destroyer() {
		this.length = 2;
		this.hit = new boolean[length];
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Destroyer";
	}

	@Override
	public int getLength(){
		return length;
	}
	
	@Override
	public String toString() {
		if (this.isSunk())
		   return "x";
		else
		   return "S";
	}

	
	
}
