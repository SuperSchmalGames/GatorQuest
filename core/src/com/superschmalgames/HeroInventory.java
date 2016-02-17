package com.superschmalgames;

//Class to represent the character's inventory.

import java.util.ArrayList;
import java.util.List;

public class HeroInventory {

    //Character inventory.
    List<InventoryItem> items;

    public HeroInventory(){
        items = new ArrayList<InventoryItem>();

        ApparelItem tmpA;
        ConsumableItem tmpC;
        EquipableItem tmpE;

        tmpA = new ApparelItem("Biz Casual Attire", "visuals/inv_items/apparel/business_casual_attire.png", "Social", 2.0, 0);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Class Ring", "visuals/inv_items/apparel/class_ring.png", "Endurance", 1.0, 0);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Gator Hat", "visuals/inv_items/apparel/gator_hat.png", "Social", 1.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Graduation Gown", "visuals/inv_items/apparel/graduation_gown.png", "Endurance", 2.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Suit and Tie", "visuals/inv_items/apparel/suit_and_tie.png", "Social", 3.0, 1);
        tmpA.addItem(this);
        tmpC = new ConsumableItem("Redbull","visuals/inv_items/consume/redbull.png","GPA",0.8,3,5,true);
        tmpC.addItem(this);
        tmpC = new ConsumableItem("Starbucks","visuals/inv_items/consume/starbucks.png","GPA",1.2,3,5,true);
        tmpC.addItem(this);
        tmpE = new EquipableItem("AVR Dragon", "visuals/inv_items/equip/avr_dragon.png", "Hardware", 3.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Cyclone III", "visuals/inv_items/equip/cyclone_III.png", "Hardware", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("DAD Board", "visuals/inv_items/equip/dad_board.png", "Focus", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Macbook Pro", "visuals/inv_items/equip/macbook_pro.png", "Software", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Nspire", "visuals/inv_items/equip/nspire.png", "Math", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Soldering Iron", "visuals/inv_items/equip/soldering_iron.png", "Endurance", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("TI-89", "visuals/inv_items/equip/ti89.png", "Math", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("USB Blaster", "visuals/inv_items/equip/usb_blaster.png", "Software", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Wire Kit", "visuals/inv_items/equip/wire_kit.png", "Hardware", 1.0, 1);
        tmpE.addItem(this);
    }

    public void incItem(Utils.INV_ITEMS itemName){
        //Pass in the enum for the item being inc'ed, use it to get the appropriate index of arraylist, then inc the quantity of items at that index.
        items.get(itemName.getNumVal()).setQuantity(items.get(itemName.getNumVal()).getQuantity() +1);
    }

    public void useItem(Utils.INV_ITEMS itemName){
        //Pass in enum for item being used/equipped, then find item's index and call activate() for that item.
        items.get(itemName.getNumVal()).activateItem();
    }

    public void removeEffect(Utils.INV_ITEMS itemName){
        //Pass in enum for item being unequipped, then find item's index and call disable() for that item.
        items.get(itemName.getNumVal()).disableItem();
    }

    //Method to return the number of items of a certain type in the character's inventory.
    public int getNum(char type){
        int tempNum = 0;
        for(InventoryItem i : items){
            if(i.getItemType()==type && i.getQuantity() > 0)
                tempNum++;
        }
        return tempNum;
    }

    public int getCurrentItemIndex(){
        int relIndex = 0;
        int overallIndex = 0;
        if("Consumable".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('c') > 0)
        {
            if(MainClass.inventoryScreen.invRow== 0 && MainClass.inventoryScreen.invPage == 0)
            {
                boolean wait = true;
                while(wait)
                {
                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'c') {
                        wait = false;
                    }
                    overallIndex = overallIndex + 1;
                }
            }
            else{
                while(relIndex < (1+MainClass.inventoryScreen.invRow + MainClass.inventoryScreen.invPage*8)){

                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'c') {
                        relIndex += 1;
                    }
                    overallIndex+=1;
                }
            }
        }
        else if("Equipment".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('e') > 0)
        {
            if(MainClass.inventoryScreen.invRow== 0 && MainClass.inventoryScreen.invPage == 0)
            {
                boolean wait = true;
                while(wait)
                {
                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'e') {
                        wait = false;
                    }
                    overallIndex = overallIndex + 1;
                }
            }
            else{
                while(relIndex < (1+MainClass.inventoryScreen.invRow + MainClass.inventoryScreen.invPage*8)){

                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'e') {
                        relIndex += 1;
                    }
                    overallIndex+=1;
                }
            }
        }
        else if("Apparel".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('a') > 0)
        {
            if(MainClass.inventoryScreen.invRow== 0 && MainClass.inventoryScreen.invPage == 0)
            {
                boolean wait = true;
                while(wait)
                {
                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'a') {
                        wait = false;
                    }
                    overallIndex = overallIndex + 1;
                }
            }
            else{
                while(relIndex < (1+MainClass.inventoryScreen.invRow + MainClass.inventoryScreen.invPage*8)){

                    if(MainClass.hero.inventory.items.get(overallIndex).getQuantity()>0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'a') {
                        relIndex += 1;
                    }
                    overallIndex+=1;
                }
            }
        }
        return overallIndex-1;
    }
}
