package battleships;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {

		BattleshipGame game = new BattleshipGame();
		game.runGame();
		
	}
	
	private Ocean ocean;
	
	public BattleshipGame() {
		ocean = new Ocean();
	}
	
	private void runGame() {
		boolean play;
		int[] location;
 		
		System.out.println("Welcome. Enter 'quit' at any time to exit");
		
		do {	
			this.createGame();
			do {
				location = this.getInput();
			} while (!this.ocean.isGameOver());
			
			System.out.println("Play again?");
			play = true;
			
		} while (play);
		
	}
	
	
	public void createGame() {
		this.ocean.placeAllShipsRandomly();
		
		///////////////////// temporary output just for testing ships DELETE!!!!!
        System.out.println(this.ocean);
        /////////////////////////////////////////////////////////
	}
	
	private int[] getInput() {
		
		int[] location = new int[2];
		
		System.out.println("Fire shots:");
		System.out.println("Row: ");
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in); 	  
		
	//	location[0] = Integer.parseInt(System.console().readLine()); // Check valid
		location[0] = scan.nextInt(); // Check valid

		System.out.println("Column: ");
	//  location[1] = Integer.parseInt(System.console().readLine());
		location[1] = scan.nextInt();
		
		////////////////////////////////////////// temporary output for testing ships DELETE!!!!!
		this.ocean.shootAt(location[1], location[0]);
		System.out.println(this.ocean);
		/////////////////////////////////////////////////////////////////////////
		
		return location;
	}
	
	private void respondToShot() {
	//	boolean hit = this.ocean.shootAt(location[0], location[1]);
	}

}
