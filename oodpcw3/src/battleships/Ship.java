package battleships;

public class Ship {

	/**
	 * ship length
	 */
	protected int length;

	private int bowRow;
	private int bowColumn;
	private boolean isHorizontal;

	/**
	 * to be override
	 */
	public String getShipType() {
		return null;
	}

	public int getBowRow() {
		return bowRow;
	}

	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	public int getBowColumn() {
		return bowColumn;
	}

	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	public void setHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}

	public boolean isEmptySpoat(int x, int y, boolean dir, Ocean ocean) {
		for (int i = 0; i <= this.length; i++) {
			if (y - this.length > 0 && x - this.length > 0)
			{
				if (ocean.ships[x][y - i].equals(".")
						&& ocean.ships[x - 1][y].equals("."))
					return true;
			}
		}

		return false;
	}

	public boolean okToPlaceShipAt(int x, int y, boolean dir, Ocean ocean) {

		int leftWall = y - this.length;
		int top = x - this.length;

		if (!dir) {
			if (leftWall > 0 && isEmptySpoat(x, y, dir, ocean)) {
				System.out.print(x);
				System.out.print(y);
				System.out.println(dir);
				return true;
			}
		}
		if (dir) {
			if (top > 0 && isEmptySpoat(x, y, dir, ocean)) {
				System.out.print(x);
				System.out.print(y);
				System.out.println(dir);
				return true;
			}
		}

		// TODO Auto-generated method stub
		return false;
	}

	public void placeShipAt(int x, int y, boolean dir, Ocean ocean) {
		setBowRow(x);
		setBowColumn(y);
		setHorizontal(dir);

		if (!dir) {
			for (int i = 0; i <= this.length; i++) {
				ocean.ships[x][y - i] = this;
				System.out.print(i);
			}
		}

		if (dir) {
			for (int i = 0; i <= this.length; i++) {
				ocean.ships[x - i][y] = this;
				System.out.print(i);
			}
		}

	}

	public boolean shootAt(int x, int y) {
		return false;
	}

	public boolean isSunk() {
		return false;
	}
}
