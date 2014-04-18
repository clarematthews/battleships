package battleships;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameEngineTest implements Observer {
	
	GameEngine engine;
	int quitCounter;
	int msgCounter;
	int hitCounter;

	@Before
	public void setUp() {
		engine = new GameEngine();
		engine.addObserver(this);
		quitCounter = 0;
		msgCounter = 0;
		hitCounter = 0;
	}
	
	@Test
	public void testSetCoordinates() {
		int expectedMsgCount = 1;
		
		engine.setFireCoordinate(3);
		assertEquals(expectedMsgCount, msgCounter);
		
		Ocean mockOcean = mock(Ocean.class);
		engine.setOcean(mockOcean);
		when(mockOcean.isGameOver()).thenReturn(false).thenReturn(true);
		
		expectedMsgCount = 4;
		engine.setFireCoordinate(5);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 5;
		engine.setFireCoordinate(1);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 9;
		engine.setFireCoordinate(1);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 10;
		engine.setFireCoordinate(10);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 11;
		engine.setFireCoordinate(-2);
		assertEquals(expectedMsgCount, msgCounter);

	}
	
	@Test
	public void testFireShot() {
		int expectedMsgCount = 4;
		int expectedHitCount = 0;
		
		Ocean mockOcean = mock(Ocean.class);
		engine.setOcean(mockOcean);
		
		when(mockOcean.isGameOver()).thenReturn(false);
		when(mockOcean.shootAt(1, 1)).thenReturn(false);
		when(mockOcean.shootAt(1, 2)).thenReturn(true);
		
		engine.setFireCoordinate(1);
		engine.setFireCoordinate(1);
		assertEquals(expectedMsgCount,msgCounter);
		assertEquals(expectedHitCount,hitCounter);
		
		expectedMsgCount = 8;
		expectedHitCount = 1;
		engine.setFireCoordinate(1);
		engine.setFireCoordinate(2);
		assertEquals(expectedMsgCount,msgCounter);
	}
	
	@Test
	public void testSetString() {
		int expectedMsgCount = 4;
		int expectedQuitCount = 1;
		
		Ocean mockOcean = mock(Ocean.class);
		engine.setOcean(mockOcean);
		
		engine.setStringCommand("Y");
		assertEquals(expectedMsgCount,msgCounter);
		
		engine.setStringCommand("N");
		assertEquals(expectedQuitCount,quitCounter);
		
		expectedQuitCount = 2;
		engine.setStringCommand("quit");
		assertEquals(expectedQuitCount,quitCounter);
		
		expectedMsgCount = 5;
		engine.setStringCommand("string");
		assertEquals(expectedMsgCount,msgCounter);
		assertEquals(expectedQuitCount,quitCounter);
		
		verify(mockOcean, times(1)).placeAllShipsRandomly();
	}

	@Test
	public void testEmit() {	
		int expectedMsgCount = 1;
		int expectedQuitCount = 1;
		
		engine.emitMessage("hit \n");
		engine.emitQuit();
		
		assertEquals(expectedMsgCount,msgCounter);
		assertEquals(expectedQuitCount,quitCounter);
		
	}
	
//	@Test
//	public void testRun() {
//		
//		GameEngine engine = new GameEngine();
//		
//		Ocean mockOcean = mock(Ocean.class);
//		engine.setOcean(mockOcean);
//		game.setEngine(engine);
//		
//		when(mockOcean.toString()).thenReturn("ocean");
//		when(mockOcean.shootAt(1, 2)).thenReturn(false);
//		when(mockOcean.shootAt(4, 1)).thenReturn(true);
//		when(mockOcean.isGameOver()).thenReturn(false).thenReturn(true);
//		when(mockOcean.getHitCount()).thenReturn(5);
//		when(mockOcean.getShotsFired()).thenReturn(8);
//		
//		String expectedString = "Welcome. Enter 'quit' at any time to exit\n" + "ocean" + "Fire shot\n" + "Row: ";
//		game.run();
//		assertEquals(expectedString, output.toString());
		
//		setInput("10");
//		expectedString = "Invalid input. Enter 0 - 9: ";
//		assertEquals(expectedString, output.toString());
//		
//		setInput("1");
//		expectedString = "Column: ";
//		assertEquals(expectedString, output.toString());
//		
//		setInput("2");
//		expectedString = "miss\n";
//		assertEquals(expectedString, output.toString());
//
//		setInput("4");
//		setInput("1");
//		expectedString = "hit\n";
//		assertEquals(expectedString, output.toString());
//		
//		expectedString = "Congratulations, you sank the fleet! \n You had 5 hits from 8 shots." + "Play again? Y/N ";
//		assertEquals(expectedString, output.toString());
//		
//		setInput("N");
//		expectedString = "Goodbye!";
//		assertEquals(expectedString, output.toString());
		
//	}
	
	private void setInput(String input) {
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	}
	


	@Override
	public void update(Observable o, Object arg) {
		GameEvent event = (GameEvent) arg;
		if (event.getType() == "quit") {
			assertEquals(event.getData(),"Goodbye!");
			quitCounter++;
		}
		else if (event.getData() == "hit \n") {
			assertEquals(event.getType(), "msg");
			msgCounter++;
			hitCounter++;
		}
		else if (event.getType() == "msg") {
			msgCounter++;
		}
	}

}
