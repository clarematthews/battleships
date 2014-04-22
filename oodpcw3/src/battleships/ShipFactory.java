package battleships;

import java.util.HashMap;
import battleships.Ship;

public class ShipFactory {

	/**
	 * create ship 
	 */	
	private static HashMap<String, Class<?>> m_RegisteredShips = new HashMap<String, Class<?>>();

    public void registerShip (String id, Class<?> shipclass)
    {
        m_RegisteredShips.put(id, shipclass);
    }

	public Ship createShip(String id) throws InstantiationException, IllegalAccessException 
	{
		Class<?> shipclass =  m_RegisteredShips.get(id);
        return (Ship) shipclass.newInstance();
	}

}