package battleships;

/**
 * @authors Dorian, Clare, David, Raitis
 *Abstract class that provides the main functionality for subclasses.
 *All ships face up, or to left. Other parts of the ship are in higher-numbered
 *rows or columns. 
 */
abstract public class Ship {

	//set in subclasses, must be visible
	protected int length;
	protected boolean[] hit;
	
	private int bowRow;
	private int bowColumn;
	private boolean horizontal = true;
	private boolean isMiss = false;
	
	// to be overridden in subclasses
	abstract public int getLength();
	abstract public String getShipType();

	/**get the start row coordinate of ship
	 * @return the start row coordinate of ship
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**set the start row coordinate of ship
	 * @param bowRow
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**get the start column coordinate of ship
	 * @return
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**Set the start column coordinate of ship
	 * @param bowColumn
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**Get the ship orientation,true if horizontal,false if vertical
	 * @return the ship orientation
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**Sets the ship orientation,true if horizontal,false if vertical
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**Gets the ships hit array, if a part is true then it has been hit,
	 *  this is then used to set the grid in Ocean 
	 * @return the ships hit array
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**Sets isMiss to true if an empty location is fired at,
	 * used to set cell to miss character-String "-"
	 */
	public void setMiss() {

		this.isMiss = true;
	}

	/** Gets boolean if true is used to set cell to miss character-String "-" in ocean grid.
	 * @return true if miss ,false otherwise
	 */
	public boolean getMiss() {

		return isMiss;
	}


	
	/**if a part of the ship occupies the given coordinates, 
	 * and the ship hasn't been sunk,that part of the
	 *  ship is marked as hit (in the hit array, where index 0 indicates
	 * the bow).

	 * @param x
	 * @param y
	 * @return true if hit,false otherwise
	 */
	public boolean shootAt(int x, int y) {
		//is ship and is not sunk
		if ((!this.toString().equals(".")) & (!this.isSunk())) {

			if (this.isHorizontal()) {
				//use difference to get the right index
				hit[y - this.getBowColumn()] = true;
				return true;
			} else {
				hit[x - this.getBowRow()] = true;
				return true;
			}
		} else 
			return false;
		
	}

	
	/** Check to see if ship is sunk
	 * @return true if every part of the ship has been hit,false otherwise
	 */
	public boolean isSunk() {
		for (int i = 0; i < this.getLength(); i++) {
			if (this.hit[i] == false)
				return false;
		}
		return true;
	}

	/** checks to see if it is ok to put a ship of this length with its bow at
	 *  this location with the given orientation and return false otherwise.
	 *  ship must not overlap another ship, or touch another ship (vertically,
	 *  horizontally or diagonally), and it must not stick out beyond the grid.
	 *  Does not actually change either the ship or the Ocean, just whether it is legal
	 *  to do so.
	 * @param row
	 * @param column
	 * @param hori
	 * @param ocean
	 * @return true if it is ok to place ship, false otherwise.
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean hori, Ocean ocean) {

		return ((checkAdjSides(row, column, ocean, hori)) &&
				((checkAdjBehindFront(row, column, ocean, hori))));
	}

	/** Checks along the sides of the ship using the checkAdjCells method(see javadocs )
	 *  
	 * @param row
	 * @param column
	 * @param ocean
	 * @param hori
	 * @return true if in bounds and no adjacent ships were found, false otherwise.
	 */
	private boolean checkAdjSides(int row, int column, Ocean ocean, boolean hori) {

		int x = row, y = column;

		for (int i = this.getLength(); i > 0; --i) {

			if (!inBounds(x, y))
				return false;

			if (checkAdjCells(x, y, ocean, hori))
				return false;

			if (hori) 
				++y;
			else
				++x;

		}
		return true;
	}

	/**checks behind the proposed ship location for adjacent ships on the first iteration
	 *  and then check in front on second. Returns false if occupied by another ship, true if it is okay.
	 * @param row
	 * @param column
	 * @param ocean
	 * @param hori
	 * @return
	 */
	private boolean checkAdjBehindFront(int row, int column, Ocean ocean, boolean hori) {

		int x = row, y = column;
		for (int i = 0; i < 2; i++) {

			if (hori)
				y = (i == 0) ? y -= 1 : y + this.getLength() + 1;

			else
				x = (i == 0) ? x -= 1 : x + this.getLength() + 1;

			if (!inBounds(x, y))
				return false;

			if (checkAdjCells(x, y, ocean, hori))
				return false;
		}
		return true;

	}

	/** Checks either side of proposed ship cell and if occupied by another ship returns true,
	 *  also return true if ship overlap, otherwise returns false. is used within a loop to 
	 *  check along the length of the ship.
	 * @param row
	 * @param column
	 * @param ocean
	 * @param hori
	 * @return
	 */
	private boolean checkAdjCells(int row, int column, Ocean ocean, boolean hori) {
		int x = row, y = column;

		if (ocean.isOccupied(x, y))
			return true;

		if (hori) {//for edge cases:do not check for adjacent cells outside the grid.
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

	/**Check to make sure given coordinates are within the grid
	 * and do not stick out.
	 * @param row
	 * @param column
	 * @return true if inbounds,false otherwise.
	 */
	private boolean inBounds(int row, int column) {

		if (row < 0 || row > 9)
			return false;
		if (column < 0 || column > 9)
			return false;
		return true;
	}

	/** Places the ship in the ocean. This involves giving values to 
	 * bowRow, bowColumn and horizontal instance variable in the ship,
	 * and it also involves putting a reference to the ship in each of
	 *  1 or more locations(up to 5) in the ships grid in the ocean object.
	 * @param row
	 * @param column
	 * @param hori
	 * @param ocean
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);

		ocean.getShipArray()[this.getBowRow()][this.getBowColumn()] = this;
		for (int i = 0; i < this.length; i++) {
			if (!this.isHorizontal())
				ocean.getShipArray()[this.getBowRow() + i][this.getBowColumn()] = this;

			if (this.isHorizontal())
				ocean.getShipArray()[this.getBowRow()][bowColumn + i] = this;

		}
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Returns a single-character String to use in the Ocean's toString method
	 * 
	 */
	@Override
	public String toString() {
		if (this.isSunk())
			return "x";
		else
			return "S";

	}
}
