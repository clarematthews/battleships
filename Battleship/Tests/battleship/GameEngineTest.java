package battleship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
	Ocean mockOcean;
	List<String> expectedStrings;
	List<String> actualStrings;

	@Before
	public void setUp() {
		mockOcean = mock(Ocean.class);
		when(mockOcean.isCorrectShipCount()).thenReturn(true);
		when(mockOcean.toString()).thenReturn("ocean");
		when(mockOcean.getOceanSize()).thenReturn(10);
		
		engine = new GameEngine(mockOcean);
		engine.addObserver(this);
		expectedStrings = new ArrayList<>();
		actualStrings = new ArrayList<>();
		
	}
	
	@Test
	public void testStartUp() {
		expectedStrings.add(GameEngine.WELCOME);
		expectedStrings.add("ocean");
		expectedStrings.add(GameEngine.FIRE);
		expectedStrings.add(GameEngine.ROW);
		engine.start();
		
		assertEquals(expectedStrings,actualStrings);
		
	}
	
	@Test
	public void testFireShots() {
		when(mockOcean.shootAt(anyInt(), anyInt())).thenReturn(false).thenReturn(true);
		when(mockOcean.isGameOver()).thenReturn(false).thenReturn(true);
		when(mockOcean.getShotsFired()).thenReturn(4);
		when(mockOcean.getHitCount()).thenReturn(2);
		
		engine.setFireCoordinate(3);
		expectedStrings.add(GameEngine.COLUMN);
		
		engine.setFireCoordinate(5);
		expectedStrings.add(GameEngine.MISS);
		expectedStrings.add("ocean");
		expectedStrings.add(GameEngine.ROW);
		
		engine.setFireCoordinate(1);
		expectedStrings.add(GameEngine.COLUMN);
		
		engine.setFireCoordinate(1);
		expectedStrings.add(GameEngine.HIT);
		expectedStrings.add("ocean");
		expectedStrings.add(GameEngine.CONGRATULATIONS);
		expectedStrings.add(String.format(GameEngine.FINAL_SCORE, 4, 2));
		expectedStrings.add(GameEngine.PLAY_AGAIN);
		
		engine.setFireCoordinate(10);
		expectedStrings.add(GameEngine.INVALID);
		
		engine.setFireCoordinate(-2);
		expectedStrings.add(GameEngine.INVALID);
		
		assertEquals(expectedStrings, actualStrings);

	}
	
	@Test
	public void testSetString()throws ReflectiveOperationException  {
		engine.setStringCommand("Y");
		expectedStrings.add(GameEngine.WELCOME);
		expectedStrings.add("ocean");
		expectedStrings.add(GameEngine.FIRE);
		expectedStrings.add(GameEngine.ROW);
		
		engine.setStringCommand("N");
		expectedStrings.add(GameEngine.QUIT);
		
		engine.setStringCommand("quit");
		expectedStrings.add(GameEngine.QUIT);
		
		engine.setStringCommand("string");
		expectedStrings.add(GameEngine.INVALID);
		
		expectedStrings.remove(1);
		actualStrings.remove(1);
		
		assertEquals(expectedStrings, actualStrings);
	
	}
	
	@Test
	public void testNewGame() throws ReflectiveOperationException {
		engine.setStringCommand("Y");
		expectedStrings.add(GameEngine.WELCOME);
		expectedStrings.add("ocean");
		expectedStrings.add(GameEngine.FIRE);
		expectedStrings.add(GameEngine.ROW);
		
		engine.setFireCoordinate(3);
		expectedStrings.add(GameEngine.COLUMN);

		expectedStrings.remove(1);
		actualStrings.remove(1);
		
		assertEquals(expectedStrings, actualStrings);
	}

	@Override
	public void update(Observable o, Object arg) {
		GameEvent event = (GameEvent) arg;
		actualStrings.add(event.getData());
	}

}
