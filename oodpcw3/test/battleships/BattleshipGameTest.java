package battleships;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BattleshipGameTest {
	
	BattleshipGame game;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		game = new BattleshipGame();
	}

	@Test
	public void test() {

		String expectedMessage = "Welcome. Enter 'quit' at any time to exit.";
		
		assertEquals(expectedMessage, outContent.toString());		
		
	}
	
	

}
