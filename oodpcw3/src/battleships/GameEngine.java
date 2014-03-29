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
	boolean waitingForRow;
	int row;
	boolean running;
	
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
			this.emit("Welcome. Enter 'quit' at any time to exit\n"); // implement
			this.emit("<Ocean display>\n");
			this.emit("Fire shot\n");
			this.emit("Row: ");
	}
	
	public void setFireCoordinate(double input) {
		
		if (waitingForRow) {
			row = (int)input;
			waitingForRow = false;
			this.emit("Column: ");
		}
		else {
			try {
				this.fireShot(row, (int) input);
			}
			catch(Exception ex) {
				this.emit(ex.toString()+"\n");
			}
			this.emit("<Ocean display>\n");
			if(this.ocean.isGameOver()) {
				this.emit("Play again? Y/N ");
			}
			else {
				waitingForRow = true;
				this.emit("Row: ");
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
		this.emit("Goodbye!");
		running = false;		
	}

	
	private void fireShot(int row, int col) {
		boolean hit = this.ocean.shootAt(row, col);				
		this.emit(hit ? "hit\n" : "miss\n");
		
		if (hit && this.ocean.getShipArray()[row][col].isSunk()) {
			this.emit("You just sank a " + this.ocean.getShipArray()[row][col].getShipType() + "\n");
		}
	}
	
	private void emit(String message) {
		this.setChanged();
		this.notifyObservers(message);
	}
	
	private void emitQuitEvent() {
		
	}
}
