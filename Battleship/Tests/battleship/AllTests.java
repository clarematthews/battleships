package battleship;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AircraftCarrierTest.class, BattleshipGameTest.class,
		BattleshipTest.class, DestroyerTest.class, PatrolBoatTest.class,
		SubmarineTest.class,ShipTest.class,OceanTest.class,
		GameEngineTest.class,})
public class AllTests {

}
