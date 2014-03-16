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
				this.fireShot(location);				
				this.ocean.toString();
				
			} while (!this.ocean.isGameOver());
			
			System.out.println("Play again? Y/N");
			String playAgain = System.console().readLine();
			play = (playAgain.equals("Y")) ? true : false;
			
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
