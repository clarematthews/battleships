package battleships;

import java.util.HashMap;
import battleships.Ship;

/**
 * @author Raitis
 *
 */
public class ShipFactory {

	private static HashMap<String, Class<?>> m_RegisteredShips = new HashMap<String, Class<?>>();

    /**
     * Creates map between the ship identifier and the class type. 
     * When a new ship type is added to the application it has to be registered to the factory.
     * @param id - ship id
     * @param shipclass - Ship class
     */
    public void registerShip (String id, Class<?> shipclass)
    {
        m_RegisteredShips.put(id, shipclass);
    }
    
	/**
	 * Return a Class instance of the indicated class according on id.
	 * @param id - ship id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Ship createShip(String id) throws InstantiationException, IllegalAccessException 
	{
		Class<?> shipclass =  m_RegisteredShips.get(id);
        return (Ship) shipclass.newInstance();
	}

}