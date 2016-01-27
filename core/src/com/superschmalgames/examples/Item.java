import java.util.*;

public abstract class Item {
	
	private String name;
	private String description;

	public Item(String n, String descript){
		name = n;
		description = descript;

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public abstract void useItem(Player player, Item item, Map<String,Item> items);
}