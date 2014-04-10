package battleships;

public class EmptySea extends Ship implements ShipType {

	@Override
	public boolean shootAt(int x, int y) {
		return false;
	}

	@Override
	public boolean isSunk() {
		return false;
	}

	@Override
	public String toString() {
		return ".";
	}

}
