package battleships;

/**
 * @author Dorian Redman
 */

import java.util.Random;

public class Ocean {

	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	private Random rand;
	private ShipFactory shipFactory = new ShipFactory();
	private static int TOTAL_FLEET_SPACES = 27;
	private static int OCEAN_SIZE = 10;
	private static int FLEET_SIZE = 11;
	Ship[][] ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
	Ship[] fleet = new Ship[FLEET_SIZE];

	public Ocean() {
		// initialise playing Grid
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int col = 0; col < OCEAN_SIZE; col++) {
				ships[row][col] = shipFactory.createShip("emptySea");
			}
		}

		initFleet();

	}

	private void initFleet() {
		

		// fleet array used to order the placement of ships from big to small
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

	public void placeAllShipsRandomly() {
		rand = new Random();
		int x = 0, y = 0;
		boolean placed;
		boolean dir = true;
		
		for (Ship s : fleet) {

			placed = false;

			while (!placed) {

				// count used to break out of infinite loop
				// generate random x, y, horizontal

				x = rand.nextInt(OCEAN_SIZE);
				y = rand.nextInt(OCEAN_SIZE);
				dir = rand.nextBoolean();

				if (s.okToPlaceShipAt(x, y, dir, this)) {
					s.placeShipAt(x, y, dir, this);
					placed = true;
				}
			}
		}
		
	}
	public boolean isCorrectShipCount(){
		int shipcount=0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (this.getShipArray()[row][col].getShipType() != "Empty Sea")
					shipcount++;//count for ships
				}
			}
		
		if(shipcount != TOTAL_FLEET_SPACES) 
			return false;
		return true;
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

	public String toString() { 
		
		StringBuilder buff = new StringBuilder();
		buff.append(" ");
		for (int i = 0; i < ships[0].length; i++) {
			buff.append(" ");
			buff.append(i);
		}
		buff.append("\n");

		for (int i = 0; i < ships.length; i++) {
			buff.append(i);
			for (int j = 0; j < ships[0].length; j++) {
				buff.append(" ");
				buff.append(ships[i][j]);
			}
			buff.append("\n");
		}
		return buff.toString();
	}
	
	
}
