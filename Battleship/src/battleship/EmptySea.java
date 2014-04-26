package battleship;

/**
 * @authors Dorian, Clare, David, Raitis
 * Class representing the empty sea. 
 */
public class EmptySea extends Ship {

	public EmptySea() {
		this.length = 1;

	}

	@Override
	public int getLength() {
		return length;
	}

	/**
	 * Shoot into empty sea.
	 * @param x the row coordinate
	 * @param y the column coordinate
	 */
	@Override
	public boolean shootAt(int x, int y) {
		return false;
	}

	/**
	 * Nothing is sunk.
	 * @return false 
	 */
	@Override
	public boolean isSunk() {
		return false;
	}

	@Override
	public String getShipType() {
		return "Empty Sea";
	}

	@Override
	public String toString() {
		return ".";
	}

}
