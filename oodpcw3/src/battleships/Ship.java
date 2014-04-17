package battleships;

abstract public class Ship {

	protected int length;
	protected boolean[] hit;
	private boolean sunk = false;
	private int bowRow;
	private int bowColumn;
	private boolean horizontal = false;
	
	/**
	 * Getters and setters
	 */
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
	
	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}
	//to be overridden in subclasses
	abstract public int getLength(); 
	abstract public String getShipType();
	
	/**
	 * Marks the hit the array if the ship has been shoot
	 */
	public boolean shootAt(int x, int y) {
		for (int i=0;i<=this.length;i++) {
			if (this.bowRow == x + i && !isHorizontal()) {
				this.hit[i] = true;				
                isSunk();
                return true;
				}
			if (this.bowColumn == y + i && isHorizontal() ) {
				this.hit[i] = true;				
                isSunk();
                return true;
				}	
		}	
		return false;
	}	

	/**
	 * Returns true is ship is sunk
	 */
	public boolean isSunk() {
		for (int ii=0;ii<this.hit.length;ii++) {
			if (hit[ii] == false)
			return false;
		}
		setSunk(true);
		return true;
	}
	

	public boolean okToPlaceShipAt(int row, int column, boolean horizontal,
			Ocean ocean) {

		return ((checkAdjsides(row, column, ocean, horizontal)) && 
				((checkAdjBehindFront(row, column, ocean, horizontal))));
	}

	private boolean checkAdjsides(int row, int column, Ocean ocean, boolean horizontal) {

		int x = row, y = column;

		for (int i = this.getLength(); i > 0; --i) {

			if (!inBounds(x, y)) 
				return false;

			if (checkAdjcells(x, y, ocean, horizontal))
				return false;

			if (horizontal)
				++y; 
			else
				++x;

		}
		return true;
	}

	private boolean checkAdjBehindFront(int row, int column, Ocean ocean,boolean horizontal) {

		int x = row, y = column;
		for (int i = 0; i < 2; i++) {

			if (horizontal) 
				y = (i == 0) ? y -= 1 : y + this.getLength() + 1;

			else 
				x = (i == 0) ? x -= 1 : x + this.getLength() + 1;

			if (!inBounds(x, y))
				return false;

			if (checkAdjcells(x, y, ocean, horizontal))
				return false;
		}
		return true;

	}

	private boolean checkAdjcells(int row, int column, Ocean ocean,
			boolean horizontal) {
		int x = row, y = column;

		if (ocean.isOccupied(x, y))
			return true;

		if (horizontal) {
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
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);


		ocean.getShipArray()[row][column] = this;
		for (int i = 0; i < this.length; i++) {
			if (!horizontal) 
				ocean.getShipArray()[bowRow + i][bowColumn] = this;

			if (horizontal) 
				ocean.getShipArray()[bowRow][bowColumn + i] = this;

		}
	}
	
}