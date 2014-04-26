package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing a submarine. 
 */
public class Submarine extends Ship {

	public Submarine() {
		this.length = 3;
		this.hit = new boolean[length];
	}

	@Override
	public String getShipType() {
       return "Submarine";
	}
	
	@Override
	public int getLength(){
		return length;
	}
	
}
