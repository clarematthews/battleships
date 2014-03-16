package battleship;

/**
 * @author Dorian Redman
 */

import java.util.Random;

public class Ocean {

	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	private Random rand;

	private static int OCEAN_SIZE = 10;
	private static int FLEET_SIZE = 11;
	Ship[][] ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
	Ship[] fleet = new Ship[FLEET_SIZE];

	public Ocean() {
		// initialise playing Grid
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int col = 0; col < OCEAN_SIZE; col++) {
				ships[row][col] = new EmptySea();
			}
		}
		initFleet();

	}

	private void initFleet() {

		// fleet array used to order the placement of ships from big to small
		fleet[0] = new AirCraftCarrier();
		fleet[1] = new Battleship();
		fleet[2] = new Battleship();
		fleet[3] = new Submarine();
		fleet[4] = new Submarine();
		fleet[5] = new Destroyer();
		fleet[6] = new Destroyer();
		fleet[7] = new PatrolBoat();
		fleet[8] = new PatrolBoat();
		fleet[9] = new PatrolBoat();
		fleet[10] = new PatrolBoat();
	}

	public void placeAllShipsRandomly() {
		rand = new Random();
		int x = 0, y = 0;
		boolean placed;
		boolean dir = true;
		int count = 0;
		int maxloop = 10000;// probably infinite loop if it reaches here.

		for (Ship s : fleet) {

			placed = false;

			while (!placed) {

				count++;
				// count used to break out of infinite loop
				// generate random x, y, horizontal

				x = rand.nextInt(OCEAN_SIZE);
				y = rand.nextInt(OCEAN_SIZE);
				dir = rand.nextBoolean();

				if (s.okToPlaceShipAt(x, y, dir, this)) {
					s.placeShipAt(x, y, dir, this);
					placed = true;
				}

				if (count > maxloop)
					return;// to BattleshipGame main to re-init debug

			}
		}
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public boolean isGameOver() {
		// Check if fleet has been sunk
		return getShipsSunk() == FLEET_SIZE;
	}

	public boolean isOccupied(int row, int column) {

		String shipType = this.ships[row][column].getShipType();
		if (shipType.equals("Empty Sea"))
			return false;

		return true;
	}

	public Ship[][] getShipArray() {

		return ships;

	}

	public boolean shootAt(int row, int column) {

		if (isOccupied(row, column)) {
			++hitCount;
			++shotsFired;
			return true;
		}
		++shotsFired;
		return false;
	}

	/*
	 * @Override public String toString() {
	 * 
	 * }
	 */
	
	
}
