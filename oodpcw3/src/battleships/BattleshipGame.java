package battleships;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		new BattleshipGame();
	}
	
	private Ocean ocean;
	
	public BattleshipGame() {
		ocean = new Ocean();
		this.runGame();
	}
	
	private void runGame() {
		boolean play;
		int[] location;
		
		System.out.println("Welcome. Enter 'quit' at any time to exit");
		Scanner sc = new Scanner(System.in);
		
		do {	
			
			this.createGame();
			
			do {
			
				location = this.getInput(sc);
				this.fireShot(location);				
				this.ocean.toString();
				
			} while (!this.ocean.isGameOver());
			
			System.out.println("Play again? Y/N");
			String playAgain = sc.nextLine();
			play = (playAgain.equals("Y")) ? true : false;
			
		} while (play);
		
		sc.close();
		
	}
	
	
	private void createGame() {
		this.ocean.placeAllShipsRandomly();
	}
	
	private int[] getInput(Scanner sc) {
		
		int[] location = new int[2];
		System.out.println("Fire shot:");
		System.out.print("Row: ");
		location[0] = sc.nextInt(); // Check valid
		System.out.print("Column: ");
		location[1] = sc.nextInt();
		
		return location;
		
	}
	
	private void fireShot(int[] location) {
		int row = location[0];
		int col = location[1];
		
		boolean hit = this.ocean.shootAt(row, col);				
		System.out.println(hit ? "hit" : "miss");
		
		if (hit && this.ocean.getShipArray()[row][col].isSunk()) {
			System.out.println("You just sank a " + this.ocean.getShipArray()[row][col].getShipType());
		}
	}

}
