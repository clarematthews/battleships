package battleships;

abstract public class Ship {

	protected int length;
	protected boolean[] hit;
	private int bowRow;
	private int bowColumn;
	private boolean horizontal = true;
	private boolean emp = false;

	// Getters and setters

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

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public boolean[] getHit() {
		return this.hit;
	}

	public void setMiss() {

		this.emp = true;
	}

	public boolean getMiss() {

		return this.emp;
	}

	// to be overridden in subclasses
	abstract public int getLength();

	abstract public String getShipType();

	/**
	 * Marks the hit array if the ship has been shot
	 */

	public boolean shootAt(int x, int y) {

		if ((!this.toString().equals(".")) && (!this.isSunk())) {

			if (this.isHorizontal()) {
				hit[y - bowColumn] = true;
				return true;
			} else {
				hit[x - bowRow] = true;
				return true;
			}
		} else {

			
			return false;
		}
	}

	/**
	 * Returns true if ship is sunk
	 */
	public boolean isSunk() {
		for (int i = 0; i < this.getLength(); i++) {
			if (this.hit[i] == false)
				return false;
		}
		return true;
	}

	public boolean okToPlaceShipAt(int row, int column, boolean hori,
			Ocean ocean) {

		return ((checkAdjsides(row, column, ocean, hori)) && ((checkAdjBehindFront(
				row, column, ocean, hori))));
	}

	private boolean checkAdjsides(int row, int column, Ocean ocean, boolean hori) {

		int x = row, y = column;

		for (int i = this.getLength(); i > 0; --i) {

			if (!inBounds(x, y))
				return false;

			if (checkAdjcells(x, y, ocean, hori))
				return false;

			if (hori)
				++y;
			else
				++x;

		}
		return true;
	}

	private boolean checkAdjBehindFront(int row, int column, Ocean ocean,
			boolean hori) {

		int x = row, y = column;
		for (int i = 0; i < 2; i++) {

			if (hori)
				y = (i == 0) ? y -= 1 : y + this.getLength() + 1;

			else
				x = (i == 0) ? x -= 1 : x + this.getLength() + 1;

			if (!inBounds(x, y))
				return false;

			if (checkAdjcells(x, y, ocean, hori))
				return false;
		}
		return true;

	}

	private boolean checkAdjcells(int row, int column, Ocean ocean, boolean hori) {
		int x = row, y = column;

		if (ocean.isOccupied(x, y))
			return true;

		if (hori) {
			int xminus = (x == 0) ? 0 : x - 1;
			int xplus = (x == 9) ? 9 : x + 1;

			if ((ocean.isOccupied(xminus, y)) || (ocean.isOccupied(xplus, y)))
				return true;
		} else {
			int yminus = (y == 0) ? 0 : y - 1;
			int yplus = (y == 9) ? 9 : y + 1;

			if ((ocean.isOccupied(x, yminus)) || (ocean.isOccupied(x, yplus)))
				return true;
		}
		return false;
	}

	private boolean inBounds(int row, int column) {

		if (row < 0 || row > 9)
			return false;
		if (column < 0 || column > 9)
			return false;
		return true;
	}

	public void placeShipAt(int row, int column, boolean hori, Ocean ocean) {

		setBowRow(row);
		setBowColumn(column);
		setHorizontal(hori);

		ocean.getShipArray()[this.getBowRow()][this.getBowColumn()] = this;
		for (int i = 0; i < this.length; i++) {
			if (!this.isHorizontal())
				ocean.getShipArray()[this.getBowRow() + i][this.getBowColumn()] = this;

			if (this.isHorizontal())
				ocean.getShipArray()[this.getBowRow()][bowColumn + i] = this;

		}
	}

	@Override
	public String toString() {
		if (this.isSunk())
			return "x";
		else
			return "S";

	}
}
