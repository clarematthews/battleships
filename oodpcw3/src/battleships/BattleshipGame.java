package battleships;

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
	}
	
	private int[] getInput() {
		
		int[] location = new int[2];
		
		System.out.println("Fire shot:");
		System.out.println("Row: ");
		location[0] = Integer.parseInt(System.console().readLine()); // Check valid
		System.out.println("Column: ");
		location[1] = Integer.parseInt(System.console().readLine());
		
		return location;
		
	}
	
	private void respondToShot() {
	//	boolean hit = this.ocean.shootAt(location[0], location[1]);
	}

}
