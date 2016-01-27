import java.util.*;

public class DemonSlayer extends Item {
	
	public DemonSlayer(String name, String description){
		super(name, description);
	}

	@Override
	public void useItem(Player player, Item item, Map<String,Item> items) {
		if (player.getLocation().getName() == Parameters.HOME && Parameters.HOME_SECRET) {
			player.getLocation().addExit(Parameters.SOUTH, Parameters.UNDERWORLD);
			System.out.println(Parameters.DEMONSLAYER_USEA);
		} else if (player.getLocation().getName() == Parameters.UNDERWORLDA || player.getLocation().getName() == Parameters.UNDERWORLDB){
			System.out.println(Parameters.DEMONSLAYER_USEB);
		} else {
			System.out.println(Parameters.NOT_USABLE);
		}
	}
}


