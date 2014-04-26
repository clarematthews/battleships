package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing a battleship. 
 */
public class Battleship extends Ship {

	public Battleship() {
		this.length = 4;
		this.hit = new boolean[4];
	}

	@Override
	public String getShipType() {
       return "Battleship";
	}

	@Override
	public int getLength(){
		return length;
	}
	
}

