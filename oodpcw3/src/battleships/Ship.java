package battleships;

public class Ship {

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

	public int getLength() {
		return length;
	}


	/**
	 * To be override
	 */
	public String getShipType() {
		return null;
	}

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
	
	/**
	 * Checks for unoccupied spot on left from beginning of horizontal ship
	 */	
	public boolean isEmptySpoatOnLeft(int x, int y, boolean dir, Ocean ocean) {
		// check end                    // check end-bit
		if (y - this.length - 1 >= 0) { if (!ocean.ships[x][y - this.length - 1 ].toString().equals(".")) return false;	
		                                // check both end corners
		                                if (x+1 <= 9 && !ocean.ships[x+1][y - this.length - 1 ].toString().equals(".")) return false;	
		                                if (x-1 >= 0 && !ocean.ships[x-1][y - this.length - 1 ].toString().equals(".")) return false;	
		                              }
		
		// check front                  // check front-bit
		if ( y+1 <= 9)                { if (!ocean.ships[x][y+1 ].toString().equals(".")) return false;
		                                // check both end corners
		                                if (x+1 <= 9 && !ocean.ships[x+1][y+1 ].toString().equals(".")) return false;
		                                if (x-1 >= 0 && !ocean.ships[x-1][y+1 ].toString().equals(".")) return false;
		                              }		
		
		for (int i = 0; i <= this.length; i++) {
			// check  actual place
			if (!ocean.ships[x][y- i].toString().equals("."))   return false;
			
			// check along both sides
			if (x-1 >= 0) { if (!ocean.ships[x-1][y - i].toString().equals(".")) return false;}
			if (x+1 <= 9) { if (!ocean.ships[x+1][y - i].toString().equals(".")) return false;}
		}
		return true;
	}
	
	
	/**
	 *  Checks for unoccupied spot on top of the vertical ship 
	 */
	public boolean isEmptySpoatOnTop(int x, int y, boolean dir, Ocean ocean) {
		// check end                   // check end-bit
		if (x - this.length - 1 >= 0) {if (!ocean.ships[x - this.length - 1 ][y].toString().equals(".")) return false;
		
		
		                               // check both end corners
		                               if (y+1 <= 9 && !ocean.ships[x - this.length - 1 ][y+1].toString().equals(".")) return false;
		                               if (y-1 >= 0 && !ocean.ships[x - this.length - 1 ][y-1].toString().equals(".")) return false;
		                              }                                
		
		// check front                 // check front-bit
        if (x+1 <= 9)                 {if (!ocean.ships[x + 1 ][y].toString().equals(".")) return false;
                                       // check both front corners
                                       if (y+1 <= 9 && !ocean.ships[x + 1 ][y+1].toString().equals(".")) return false;
                                       if (y-1 >= 0 && !ocean.ships[x + 1 ][y-1].toString().equals(".")) return false;
                                      }
		
		for (int i = 0; i <= this.length; i++) {
			    // check actual place
				if (!ocean.ships[x - i][y].toString().equals(".")) return false;	
				
				// check along both sides
				if (y-1 >= 0) { if (!ocean.ships[x-i][y - 1].toString().equals(".")) return false;}
				if (y+1 <= 9) { if (!ocean.ships[x-i][y + 1].toString().equals(".")) return false;}
		}
		return true;
	}	
	
	/**
	 *  Check if ship fits in the frame
	 */
	public boolean okToPlaceShipAt(int x, int y, boolean dir, Ocean ocean) {
		
		int leftWall = y - this.length;
		int top = x - this.length;

		if (dir) {
			if (top > 0 && isEmptySpoatOnTop(x, y, dir, ocean)) {
				return true;
			}
		}
		
		if (!dir) {
			if (leftWall > 0 && isEmptySpoatOnLeft(x, y, dir, ocean)) {
				return true;
			}
		}
     return false;
	}	
	
	/**
	 * Places ship
	 */
	public void placeShipAt(int x, int y, boolean dir, Ocean ocean) {
		
		setBowRow(x);
		setBowColumn(y);
		setHorizontal(!dir);

		if (!dir) {
			for (int i = 0; i <= this.length-1; i++) {
				ocean.ships[x][y - i] = this;
			}
		}

		if (dir) {
			for (int i = 0; i <= this.length-1; i++) {
				ocean.ships[x - i][y] = this;
			}
		}
				
	}

}
