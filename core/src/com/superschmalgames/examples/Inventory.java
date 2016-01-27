import java.util.*;

public class Inventory {
	
	private List<Item> inventory;

	public Inventory() {
		inventory = new ArrayList<Item>();
	}

	public void addItem(Item obj) {
		inventory.add(obj);
	}

	public void removeItem(Item obj) {
		inventory.remove(obj);
	}

	public boolean contains(Item obj) {
		return inventory.contains(obj);
	}

	public int inventorySize() {
		return inventory.size();
	}

	public void getInventory() {
		for (int i=0; i<inventory.size(); i++){
			System.out.println(inventory.get(i).getName());
		}
	}
}