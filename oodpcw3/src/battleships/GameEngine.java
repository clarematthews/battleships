/**
 * 
 */
package battleships;

import java.util.Observable;
import java.util.Scanner;

/**
 * @author clare
 *
 */
public class GameEngine extends Observable {

	private Ocean ocean;
	private boolean waitingForRow;
	private int row;
	private boolean running;
	
	/**
	 * 
	 */
	public GameEngine() {
		ocean = new Ocean();
		waitingForRow = true;
		running = true;
	}
	
	
	public void start() {
			this.ocean.placeAllShipsRandomly();
			this.emitMessage("Welcome. Enter 'quit' at any time to exit\n"); // implement
			this.emitMessage("<Ocean display>\n");
			this.emitMessage("Fire shot\n");
			this.emitMessage("Row: ");
	}
	
	public void setFireCoordinate(double input) {
		
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
				this.emitMessage(ex.toString()+"\n");
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
			this.quit();
			break;
		case "Y":
			this.start();
			break;
		case "N":
			this.quit();
			break;
		default:
			break;
		}
	}
	

	private void quit() {
		this.emitMessage("Goodbye!");
		this.emitQuit();		
	}

	
	private void fireShot(int row, int col) {
		boolean hit = this.ocean.shootAt(row, col);				
		this.emitMessage(hit ? "hit\n" : "miss\n");
		
		if (hit && this.ocean.getShipArray()[row][col].isSunk()) {
			this.emitMessage("You just sank a " + this.ocean.getShipArray()[row][col].getShipType() + "\n");
		}
	}

	private void emitMessage(String message) {
		GameEvent event = new GameEvent();
		event.setType("msg");
		event.setData(message);
		this.emit(event);
	}
	
	private void emitQuit() {
		GameEvent event = new GameEvent();
		event.setType("quit");
		this.emit(event);
	}
	
	private void emit(GameEvent event) {
		this.setChanged();
		this.notifyObservers(event);
	}
	
	
	
}


