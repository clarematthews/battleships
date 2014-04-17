package battleships;

public class Battleship extends Ship {

	/**
	 * constructor setting ship length
	 */
	public Battleship() {
		this.length = 4;
		this.hit = new boolean[4];
	}

	/**
	 * return ship type
	 *
	 */
	@Override
	public String getShipType() {
       return "Battleship";
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
