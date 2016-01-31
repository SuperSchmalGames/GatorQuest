package com.superschmalgames;

//Class to represent the character's inventory.

import java.util.ArrayList;
import java.util.List;

public class HeroInventory {

    //Character inventory.
    List<InventoryItem> items;

    public HeroInventory(){
        items = new ArrayList<InventoryItem>();

        ConsumableItem tmpC;
        EquipableItem tmpE;

        tmpC = new ConsumableItem("Red Bull","visuals/inv_items/consume/red_bull.png","GPA",1.2,3,0,true);
        tmpC.addItem(this);
        tmpC = new ConsumableItem("Starbucks","visuals/inv_items/consume/starbucks.png","GPA",0.8,3,0,true);
        tmpC.addItem(this);
        tmpE = new EquipableItem("Laptop", "visuals/inv_items/equip/computer.png", "Software Skillz", 1.0, 1);
        tmpE.addItem(this);
    }

    public void addItem(String itemName){
        if(itemName.equals("Red Bull")){
            items.get(0).addItem(this);
        }
    }

    public void useItem(String itemName, HeroCharacter hero){
        if(itemName.equals("Red Bull")){
            hero.gpa = items.get(0).activateItem(hero.gpa);
        }
    }

    public void removeEffect(String itemName, HeroCharacter hero){
        if(itemName.equals("Red Bull")){
            hero.gpa = items.get(0).disableItem(hero.gpa);
        }
    }
}
