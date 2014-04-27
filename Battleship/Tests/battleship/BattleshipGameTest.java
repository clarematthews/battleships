package battleship;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Observable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BattleshipGameTest {
	
	BattleshipGame game;
	ByteArrayOutputStream output;
	
	@Before
	public void setUp() {
		game = new BattleshipGame();
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	@After
	public void tearDown() {
		System.setOut(null);
	}
	

	@Test
	public void testUpdate() {
		
		GameEngine engine = mock(GameEngine.class);
		game.setEngine(engine);	
		
		Observable o = new Observable();
		
		GameEvent mockEvent = mock(GameEvent.class);
		when(mockEvent.getData()).thenReturn("The data");
		when(mockEvent.getType()).thenReturn("msg").thenReturn("quit");
		
		game.update(o, mockEvent);
		assertEquals("The data", output.toString());
		
		game.update(o, mockEvent);
		game.run();
		verify(engine, times(0)).setFireCoordinate(anyInt());
		verify(engine, times(0)).setStringCommand(anyString());
		
	}
		
}
