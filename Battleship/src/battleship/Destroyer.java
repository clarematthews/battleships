package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing an aircraft carrier. 
 */
public class Destroyer extends Ship {

	public Destroyer() {
		this.length = 2;
		this.hit = new boolean[length];
	}

	@Override
	public String getShipType() {
       return "Destroyer";
	}

	@Override
	public int getLength(){
		return length;
	}
	
}
	

