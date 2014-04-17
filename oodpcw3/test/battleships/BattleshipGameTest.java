package battleships;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Observable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BattleshipGameTest {
	
	BattleshipGame game;
	
	@Before
	public void setUp() {
		game = new BattleshipGame();
	}
	

	@Test
	public void testUpdate() {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		GameEngine engine = mock(GameEngine.class);
		game.setEngine(engine);	
		
		Observable o = new Observable();
		GameEvent event = mock(GameEvent.class);
		when(event.getData()).thenReturn("The data");
		when(event.getType()).thenReturn("msg").thenReturn("quit");
		
		game.update(o, event);
		assertEquals("The data", output.toString());
		
		game.update(o, event);
		game.run();
		verify(engine, times(0)).setFireCoordinate(anyInt());
		verify(engine, times(0)).setStringCommand(anyString());
		
		System.setOut(null);
	}
	
//	@Test
//	public void testRun() {
//		GameEngine engine = mock(GameEngine.class);
//		
//		game.setEngine(engine);	
//		
//		ByteArrayInputStream in = new ByteArrayInputStream("quit".getBytes());
//		System.setIn(in);
//		
//		game.run();
//		
//		verify(engine, times(1)).setFireCoordinate(anyInt());
//		verify(engine, times(1)).setStringCommand(anyString());
//	}
	
	

}
