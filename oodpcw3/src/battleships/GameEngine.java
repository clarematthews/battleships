/**
 * 
 */
package battleships;

import java.util.Observable;

/**
 * @author clare
 *
 */
public class GameEngine extends Observable {

	private Ocean ocean;
	private boolean waitingForRow;
	private int row;
	
	/**
	 * 
	 */
	public GameEngine() {
		waitingForRow = true;
		Ocean ocean = new Ocean();
		this.setOcean(ocean);
	}
	
	public void setOcean(Ocean ocean) {
		this.ocean = ocean;
	}
	
	public void start() {
			this.ocean.placeAllShipsRandomly();
			this.emitMessage("Welcome. Enter 'quit' at any time to exit\n");
			this.emitMessage("<Ocean display>\n");
			this.emitMessage("Fire shot\n");
			this.emitMessage("Row: ");
	}
	
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
			this.emitMessage("<Ocean display>\n");
			if(this.ocean.isGameOver()) {
				this.emitMessage("Play again? Y/N ");
			}
			else {
				waitingForRow = true;
				this.emitMessage("Row: ");
			}
		}
		
	}
	
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

	
	private void fireShot(int row, int col) {
		boolean hit = this.ocean.shootAt(row, col);				
		this.emitMessage(hit ? "hit\n" : "miss\n");
		
		if (hit && this.ocean.getShipArray()[row][col].isSunk()) {
			this.emitMessage("You just sank a " + this.ocean.getShipArray()[row][col].getShipType() + "\n");
		}
	}

	public void emitMessage(String message) {
		GameEvent event = new GameEvent();
		event.setType("msg");
		event.setData(message);
		this.emit(event);
	}
	
	public void emitQuit() {
		GameEvent event = new GameEvent();
		event.setType("quit");
		event.setData("Goodbye!");
		this.emit(event);
	}
	
	private void emit(GameEvent event) {
		this.setChanged();
		this.notifyObservers(event);
	}
	
	
}


