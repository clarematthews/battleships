package battleships;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Observable;
import java.util.Observer;

public class BattleshipGame implements Observer {

	public static void main(String[] args) {
		new BattleshipGame();
	}
	
	private GameEngine engine;
	
	public BattleshipGame() {
		
		engine = new GameEngine();
		engine.addObserver(this);
		engine.start();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		StreamTokenizer st = new StreamTokenizer(isr);
		
		while (true) {
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
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.print(arg);
	}
}
