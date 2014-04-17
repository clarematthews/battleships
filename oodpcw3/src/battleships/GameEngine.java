package battleships;

import java.util.Observable;

/**
 * This class fires shots at an Ocean and returns results and messages to the BattleshipGame class. 
 * @author Clare Matthews
 *
 */
public class GameEngine extends Observable {

	private Ocean ocean;
	private boolean waitingForRow;
	private int row;
	
	public GameEngine() {
		waitingForRow = true;
		Ocean ocean = new Ocean();
		this.setOcean(ocean);
	}
	
	public void setOcean(Ocean ocean) {
		this.ocean = ocean;
	}
	
	/**
	 * Start a new game
	 */
	public void start() {
			int shipcount = 0;
			this.ocean.placeAllShipsRandomly();
			
			
		//put in method put shipcount in unit test	
			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					if (ocean.getShipArray()[row][col]
							.getShipType() != "Empty Sea")
						shipcount++;//count for ships
					}
				}
			
			if(shipcount != 27) //check to make sure all ships are placed
				new BattleshipGame().run();//if not reinitialise grid
			
			else{
				this.emitMessage("Welcome. Enter 'quit' at any time to exit\n");
				this.emitMessage(ocean.toString());
				this.emitMessage("Fire shot\n");
				this.emitMessage("Row: ");
			
			}
	}
	
	/**
	 * Accept row and column coordinates for firing, until game is over
	 * @param input the coordinate fired at
	 */
	public void setFireCoordinate(double input) {		
		if (input < 0 || input > 9) {
			this.emitMessage("Invalid input. Enter 0 - 9: ");
			return;
		}
		
		if (waitingForRow) {
			row = (int)input;
			waitingForRow = false;
			this.emitMessage("Column: ");
		}
		else {
			try {
				this.fireShot(row, (int) input);
			}
			catch(Exception ex) {
				System.err.println(ex.toString());
			}
			this.emitMessage(ocean.toString());
			if(this.ocean.isGameOver()) {
				this.emitMessage("Play again? Y/N ");
			}
			else {
				waitingForRow = true;
				this.emitMessage("Row: ");
			}
		}	
	}
	
	/**
	 * Interpret user inputs from the command line to play again or quit
	 * @param input the user command line input
	 */
	public void setStringCommand(String input) {
		switch(input) {
		case "quit":
			this.emitQuit();
			break;
		case "Y":
			this.start();
			break;
		case "N":
			this.emitQuit();
			break;
		default:
			this.emitMessage("Invalid input. Try again: ");
			break;
		}
	}

	/**
	 * Fires a shot at the Ocean and responds with hit or miss, and the type of ship if a ship is sunk
	 * @param row the row coordinate to shoot at
	 * @param col the column coordinate to shoot at
	 */
	private void fireShot(int row, int col) {
		boolean hit = this.ocean.shootAt(row, col);				
		this.emitMessage(hit ? "hit\n" : "miss\n");
		
		if (hit && this.ocean.getShipArray()[row][col].isSunk()) {
			this.emitMessage("You just sank a " + this.ocean.getShipArray()[row][col].getShipType() + "\n");
		}
	}

	/**
	 * Sends a GameEvent to an observer, with a message
	 * @param message the message to send to an observer
	 */
	public void emitMessage(String message) {
		GameEvent event = new GameEvent();
		event.setType("msg");
		event.setData(message);
		this.emit(event);
	}
	
	/**
	 * Sends a GameEvent to an observer to quit the game 
	 */
	public void emitQuit() {
		GameEvent event = new GameEvent();
		event.setType("quit");
		event.setData("Goodbye!");
		this.emit(event);
	}
	
	/**
	 * Notifies an observer of a GameEvent
	 * @param event the GameEvent sent to an observer
	 */
	private void emit(GameEvent event) {
		this.setChanged();
		this.notifyObservers(event);
	}
	
	
}


