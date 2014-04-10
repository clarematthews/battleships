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
	public String getShipType() { return null;}
	
	
	

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




	public boolean okToPlaceShipAt(int x, int y, boolean dir, Ocean ocean) {
		// TODO Auto-generated method stub
		return false;
	}

	public void placeShipAt(int x, int y, boolean dir, Ocean ocean) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean shootAt(int x, int y) {
		return false;
	}	
	
	public boolean isSunk() {
		return false;
	}
}
