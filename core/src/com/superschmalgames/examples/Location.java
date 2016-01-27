import java.util.*;

public class Location extends Inventory{

	private String name;
	private String description;
	private Map<String,Location> exits = new HashMap<String,Location>();

	public Location(String n, String descript){
		super();
		name = n;
		description = descript;
	}

	public void addExit(String direction, Location location) {
		exits.put(direction, location);
	}

	public void removeExit(String direction, Location location) {
		exits.put(direction, location);
	}

	public String getDescription() {
		return description;
	}

	public Location getExit(String direction) {
		return exits.get(direction);
	}

	public String getName(){
		return name;
	}

	public boolean containsLocation(String direction){
		return exits.containsKey(direction);
	}
}