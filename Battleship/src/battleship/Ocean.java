package battleship;

import java.util.Random;

/**
 * @authors Clare,Dorian,Raitis,David
 * Ocean is a grid containing a fleet of ships.
 */
public class Ocean {

	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	private Random rand;

	private final int TOTAL_FLEET_CELLS = 27;
	private final int OCEAN_SIZE = 10;
	private final int FLEET_SIZE = 11;

	private static ShipFactory shipFactory = new ShipFactory();
	Ship[][] ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
	Ship[] fleet = new Ship[FLEET_SIZE];

	// register ship factory classes while loading ocean class
	static {
		shipFactory.registerShip("emptySea", EmptySea.class);
		shipFactory.registerShip("aircraftCarrier", AircraftCarrier.class);
		shipFactory.registerShip("battleship", Battleship.class);
		shipFactory.registerShip("destroyer", Destroyer.class);
		shipFactory.registerShip("submarine", Submarine.class);
		shipFactory.registerShip("patrolboat", PatrolBoat.class);
	}

	/**
	 * Constructor creates ocean object by calling initGrid method.
	 */
	public Ocean() {
			initGrid();
	}

	/**
	 * Initialises ship array and fills it with emptySea objects. 
	 */
	private void initGrid() {
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int col = 0; col < OCEAN_SIZE; col++) {
				ships[row][col] = shipFactory.createShip("emptySea");
			}
		}
		initFleet();
		rand = new Random();
		this.placeAllShipsRandomly();
	}

	/**
	 * Creates ships and puts them into the fleet array used to order
	 * the placement of ships from large to small. 
	 * @throws ReflectiveOperationException
	 */
	private void initFleet() {
		fleet[0] = shipFactory.createShip("aircraftCarrier");
		fleet[1] = shipFactory.createShip("battleship");
		fleet[2] = shipFactory.createShip("battleship");
		fleet[3] = shipFactory.createShip("submarine");
		fleet[4] = shipFactory.createShip("submarine");
		fleet[5] = shipFactory.createShip("destroyer");
		fleet[6] = shipFactory.createShip("destroyer");
		fleet[7] = shipFactory.createShip("patrolboat");
		fleet[8] = shipFactory.createShip("patrolboat");
		fleet[9] = shipFactory.createShip("patrolboat");
		fleet[10] = shipFactory.createShip("patrolboat");
	}

	/**
	 * Accessor method to get size of the 2d ships array, rows and columns
	 * always same number.
	 * @return size of the 2d ships array
	 */
	public int getOceanSize() {
		return OCEAN_SIZE;
	}

	/**
	 * Accessor method to get number of ship cells hit (not emptysea), should be
	 * 27 at the end of game.
	 * @return number of ship cells hit
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * Accessor method to get the total number of ships sunk, should be 11 at
	 * the end of the game.
	 * @return the number of ships sunk
	 */
	public int getShipsSunk() {
		return shipsSunk;
	}

	/**
	 * Accessor method to get the total number of shots fired at the end of the
	 * game.
	 * @return the number of shots fired
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Accessor method to get the 2d ships array.
	 * @return ships 2d array
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	
	/**
	 * Check to see if game is over.
	 * @return true if ships sunk equals the size of the fleet, false otherwise.
	 */
	public boolean isGameOver() {
		return getShipsSunk() == FLEET_SIZE;
	}

	/**
	 * Uses the java.Random API to randomly place ships in 2d ships array. It
	 * calls the Ship method okToPlaceShipAt to check if the randomly selected
	 * position is legal and if true it calls ship method placeShipAt to place
	 * ship, if false it will repeat the process until a valid position is
	 * found.
	 */
	private void placeAllShipsRandomly() {
		int x = 0, y = 0;
		boolean placed;
		boolean dir = true;

		for (Ship s : fleet) {

			placed = false;

			while (!placed) {

				// generate random x, y and horizontal values
				x = rand.nextInt(OCEAN_SIZE);
				y = rand.nextInt(OCEAN_SIZE);
				dir = rand.nextBoolean();
				// check to see if randomly generated position is valid
				if (s.okToPlaceShipAt(x, y, dir, this)) {
					s.placeShipAt(x, y, dir, this);
					placed = true;
				}
			}
		}
	}

	/**
	 * Check to see if all ships have been successfully placed by
	 * placeAllShipsRandomly.
	 * @return true if 27 of the cells are occupied by fleet ships, false
	 *         otherwise.
	 */
	public boolean isCorrectShipCount() {
		int shipcount = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (this.isOccupied(row, col))
					shipcount++;
			}
		}

		if (shipcount != TOTAL_FLEET_CELLS)
			return false;
		return true;
	}

	/**
	 * Check to see if cell is occupied by a ship.
	 * @param row the row coordinate
	 * @param column the column coordinate
	 * @return true if cell contains ship, false otherwise
	 */
	public boolean isOccupied(int row, int column) {

		String shipType = this.ships[row][column].getShipType();
		if (shipType.equals("Empty Sea"))
			return false;

		return true;
	}

	/**
	 * Shoots at the given location and updates the number of shots that have
	 * been fired, and the number of hits. If a location contains a real ship,
	 * shootAt returns true every time the user shoots at that location.
	 * Once a ship has been sunk, additional shots at its location return
	 * false.
	 * @param row the row coordinate
	 * @param column the column coordinate
	 * @return returns true if the given location contains a real ship, still
	 *         afloat (not an EmptySea), false otherwise.
	 */
	public boolean shootAt(int row, int column) {
		Ship s = this.getShipArray()[row][column];
		++shotsFired;// always register shotsFired first

		if (!this.isOccupied(row, column)) {
			// no ship here mark as a miss
			if (s.getShipType() == "Empty Sea")
				((EmptySea) s).setMiss();
			return false;
		}
		// no longer registers hits
		else if (s.isSunk())
			return false;
		else {// successful hit call ship shootAt method to register hit
			s.shootAt(row, column);
			++hitCount;

			if (s.isSunk())
				++shipsSunk;

			return true;
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 * 
	 * To aid the user, row numbers are displayed along the left edge of the
	 * array, and column numbers are displayed along the top. Numbers are 0 to
	 * 9. The top left corner square is (0,0).
	 * @return a string representing the ocean
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		buff.append(" ");
		for (int i = 0; i < ships[0].length; i++) {
			buff.append(" ");
			buff.append(i);
		}
		buff.append("\n");

		for (int i = 0; i < OCEAN_SIZE; i++) {
			buff.append(i);
			for (int j = 0; j < ships[0].length; j++) {
				buff.append(" ");
				Ship s = ships[i][j];
				buff.append(this.setOceanCellState(s, i, j));
			}
			buff.append("\n");
		}
		return buff.toString();
	}

	/**
	 * Sets cell state to one of the below strings according to the specified
	 * criteria.
	 * Use 'S' to indicate a location that you have fired upon and hit a
	 * (real) ship. 
	 * Use '-' to indicate a location that you have fired upon and
	 * found nothing there. 
	 * Use 'x' to indication location containing a sunken ship.
	 * Use '.' to indicate a location that you have never fired upon.
	 * @param ship the ship at the location (may be empty sea)
	 * @param x the row coordinate
	 * @param y the column coordinate
	 * @return string representing cell state
	 */
	private String setOceanCellState(Ship ship, int x, int y) {
		String oceanCell = ".";

		if (ship.getShipType() == "Empty Sea") {
			if (((EmptySea) ship).getMiss())
				oceanCell = "-";
			else
				oceanCell = ".";
		} 
		else {
			if (ship.isHorizontal()) {
				boolean[] hit = ship.getHit();
				if (hit[y - ship.getBowColumn()])
					oceanCell = "S";
			}
			
			if (!ship.isHorizontal()) {
				boolean[] hit = ship.getHit();
				if (hit[x - ship.getBowRow()])
					oceanCell = "S";
			}

			if (ship.isSunk())
				oceanCell = "x";
		}
		return oceanCell;
	}
}
