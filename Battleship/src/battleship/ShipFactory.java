package battleship;

import java.util.HashMap;

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
	 * Creates new ships.
	 * @param id - ship id
	 * @return new ship
	 */
	public Ship createShip(String id) {
		try {
			Class<?> shipclass =  m_RegisteredShips.get(id);
	        return (Ship) shipclass.newInstance();	
		}
		catch (Exception ex) {
			throw new IllegalArgumentException("Unregistered ship type " + id);
		}
	}
}