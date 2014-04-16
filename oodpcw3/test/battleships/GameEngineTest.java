package battleships;

import static org.junit.Assert.*;

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
		
		expectedMsgCount = 8;
		engine.setFireCoordinate(1);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 9;
		engine.setFireCoordinate(10);
		assertEquals(expectedMsgCount, msgCounter);
		
		expectedMsgCount = 10;
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
