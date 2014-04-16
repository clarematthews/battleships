package battleships;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Observable;
import java.util.Observer;

/**
 * This class accepts user input, passes it to the GameEngine class and responds to updates of GameEvents. 
 * @author clare
 *
 */
public class BattleshipGame implements Observer {

	public static void main(String[] args) {
		BattleshipGame game = new BattleshipGame();
		game.run();
	}
	
	private GameEngine engine;
	private boolean running;
	
	public BattleshipGame() {
		running = true;
		GameEngine engine = new GameEngine();
		this.setEngine(engine);
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
		engine.addObserver(this);
	}
	
	/**
	 * Start game engine, accept user inputs and pass to engine.
	 */
	public void run() {
		engine.start();
		InputStreamReader isr = new InputStreamReader(System.in);
		StreamTokenizer st = new StreamTokenizer(isr);
		
		while (running) {
			try {
				switch ( st.nextToken () ) {
				case StreamTokenizer.TT_NUMBER:
					engine.setFireCoordinate(st.nval);
					break;
				case StreamTokenizer.TT_WORD:
					engine.setStringCommand(st.sval);
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	}
	
	/**
	 * Updates of GameEvents
	 * @param o the GameEngine observed
	 * @param arg the GameEvent that occurred
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		GameEvent event = (GameEvent) arg;

		System.out.print(event.getData());

		if(event.getType() == "quit") {
			running = false;
		}	
		
	}
}
