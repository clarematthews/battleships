package battleship;

import java.util.Observable;

/**
 * @author Clare, Dorian, Raitis, David
 * This class fires shots at an Ocean and returns results and messages to the BattleshipGame class. 
 */
public class GameEngine extends Observable {

	private Ocean ocean;
	private boolean waitingForRow;
	private int row;
	private int oceanSize;
	
	public static final String WELCOME = "Welcome. Enter 'quit' at any time to exit\n";
	public static final String CONGRATULATIONS = "Congratulations, you sank the fleet!\n";
	public static final String FINAL_SCORE = "You fired %d shots and made %d hits. ";
	public static final String PLAY_AGAIN = "Play again? Y/N ";
	public static final String QUIT = "Goodbye!";
	public static final String FIRE = "Fire shot\n";
	public static final String ROW = "Row: ";
	public static final String COLUMN = "Column: ";
	public static final String INVALID = "Invalid input. Try again: ";
	public static final String HIT = "hit\n";
	public static final String MISS = "miss\n";
	public static final String SANK = "You just sank a %s\n";
	
	public GameEngine() {
		this(new Ocean());
	}
	
	public GameEngine(Ocean ocean) {
		waitingForRow = true;
		this.ocean = ocean;
		oceanSize = ocean.getOceanSize();
	}
	
	/**
	 * Start a new game.
	 */
	public void start() {
				emitMessage(WELCOME);
				emitMessage(ocean.toString());
				emitMessage(FIRE);
				emitMessage(ROW);	
	}
	
	/**
	 * Accept row and column coordinates for firing, until game is over.
	 * @param input the coordinate fired at
	 */
	public void setFireCoordinate(double input) {		
		if (input < 0 || input >= oceanSize) {
			emitMessage(INVALID);
			return;
		}
		
		if (waitingForRow) {
			row = (int)input;
			waitingForRow = false;
			emitMessage(COLUMN);
		}
		else {
			try {
				fireShot(row, (int) input);
			}
			catch(Exception ex) {
				System.err.println(ex.toString());
			}
			emitMessage(ocean.toString());
			if(ocean.isGameOver()) {
				emitMessage(CONGRATULATIONS);
				emitMessage(String.format(FINAL_SCORE, ocean.getShotsFired(), ocean.getHitCount()));
				emitMessage(PLAY_AGAIN);
			}
			else {
				waitingForRow = true;
				emitMessage(ROW);
			}
		}	
	}
	
	/**
	 * Interpret user inputs from the command line to play again or quit.
	 * @param input the user command line input
	 */
	public void setStringCommand(String input) {
		input = input.toLowerCase();
		switch(input) {
		case "quit":
			emitQuit();
			break;
		case "y":
			ocean = new Ocean();
			waitingForRow = true;
			start();
			break;
		case "n":
			emitQuit();
			break;
		default:
			emitMessage(INVALID);
			break;
		}
	}

	/**
	 * Fires a shot at the Ocean and responds with hit or miss, and the type of ship if a ship is sunk.
	 * @param row the row coordinate to shoot at
	 * @param col the column coordinate to shoot at
	 */
	private void fireShot(int row, int col) {
		boolean hit = ocean.shootAt(row, col);				
		emitMessage(hit ? HIT : MISS);
		
		if (hit && ocean.getShipArray()[row][col].isSunk()) {
			emitMessage(String.format(SANK, ocean.getShipArray()[row][col].getShipType()));
		}
	}

	/**
	 * Sends a GameEvent to an observer, with a message.
	 * @param message the message to send to an observer
	 */
	public void emitMessage(String message) {
		GameEvent event = new GameEvent();
		event.setType("msg");
		event.setData(message);
		emit(event);
	}
	
	/**
	 * Sends a GameEvent to an observer to quit the game 
	 */
	public void emitQuit() {
		GameEvent event = new GameEvent();
		event.setType("quit");
		event.setData(QUIT);
		emit(event);
	}
	
	/**
	 * Notifies an observer of a GameEvent.
	 * @param event the GameEvent sent to an observer
	 */
	private void emit(GameEvent event) {
		setChanged();
		notifyObservers(event);
	}
	
}


