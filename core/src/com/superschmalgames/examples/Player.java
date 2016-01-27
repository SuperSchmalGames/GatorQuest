public class Player extends Inventory{
	
	private static Location currentLocation;
	private static String name;
	private static int health;

	public Player(String n, Location location) {
		super();
		name = n;
		currentLocation = location;
		health = Parameters.START_HEALTH;
	}

	public String getName(){
		return name;
	}

	public int getHealth(){
		return health;
	}

	public void setHealth(int hp){
		health += hp;
	}

	public Location getLocation(){
		return currentLocation;
	}

	public void setLocation(Location location){
		currentLocation = location;
		System.out.println(currentLocation.getName());
	}
}