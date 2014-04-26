package battleship;

/**
 * @author Clare, Raitis, Dorian, David
 * This class records game events of different types.
 * Event may contain data.
 */
public class GameEvent {

	private String type;
	private String data;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
