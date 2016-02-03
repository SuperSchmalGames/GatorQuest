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

        tmpA = new ApparelItem("Biz Casual Attire", "visuals/inv_items/apparel/business_casual_attire.png", "Social Skillz", 2.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Class Ring", "visuals/inv_items/apparel/class_ring.png", "Endurance", 1.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Gator Hat", "visuals/inv_items/apparel/gator_hat.png", "Social Skillz", 1.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Graduation Gown", "visuals/inv_items/apparel/graduation_gown.png", "Endurance", 2.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Suit and Tie", "visuals/inv_items/apparel/suit_and_tie.png", "Social Skillz", 3.0, 1);
        tmpA.addItem(this);
        tmpC = new ConsumableItem("Redbull","visuals/inv_items/consume/redbull.png","GPA",0.8,3,5,true);
        tmpC.addItem(this);
        tmpC = new ConsumableItem("Starbucks","visuals/inv_items/consume/starbucks.png","GPA",1.2,3,5,true);
        tmpC.addItem(this);
        tmpE = new EquipableItem("AVR Dragon", "visuals/inv_items/equip/avr_dragon.png", "HardwareSkillz", 3.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Cyclone III", "visuals/inv_items/equip/cyclone_III.png", "Hardware Skillz", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("DAD Board", "visuals/inv_items/equip/dad_board.png", "Attention to Detail", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Macbook Pro", "visuals/inv_items/equip/macbook_pro.png", "Software Skillz", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Nspire", "visuals/inv_items/equip/nspire.png", "Math Skillz", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Soldering Iron", "visuals/inv_items/equip/soldering_iron.png", "Endurance", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("TI-89", "visuals/inv_items/equip/ti89.png", "Math Skillz", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("USB Blaster", "visuals/inv_items/equip/usb_blaster.png", "Software Skillz", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Wire Kit", "visuals/inv_items/equip/wire_kit.png", "Hardware Skillz", 1.0, 1);
        tmpE.addItem(this);
    }

    public void incItem(String itemName){
        if(itemName.equals("Biz Casual Attire")){
            items.get(0).setQuantity(items.get(0).getQuantity() +1);
        }
        if(itemName.equals("Class Ring")){
            items.get(1).setQuantity(items.get(1).getQuantity() +1);
        }
        if(itemName.equals("Gator Hat")){
            items.get(2).setQuantity(items.get(2).getQuantity() +1);
        }
        if(itemName.equals("Graduation Gown")){
            items.get(3).setQuantity(items.get(3).getQuantity() +1);
        }
        if(itemName.equals("Suit and Tie")){
            items.get(4).setQuantity(items.get(4).getQuantity() +1);
        }
        if(itemName.equals("Redbull")){
            items.get(5).setQuantity(items.get(5).getQuantity() +1);
        }
        if(itemName.equals("Starbucks")){
            items.get(6).setQuantity(items.get(6).getQuantity() +1);
        }
        if(itemName.equals("AVR Dragon")){
            items.get(7).setQuantity(items.get(7).getQuantity() +1);
        }
        if(itemName.equals("Cyclone III")){
            items.get(8).setQuantity(items.get(8).getQuantity() +1);
        }
        if(itemName.equals("DAD Board")){
            items.get(9).setQuantity(items.get(9).getQuantity() +1);
        }
        if(itemName.equals("Macbook Pro")){
            items.get(10).setQuantity(items.get(10).getQuantity() +1);
        }
        if(itemName.equals("Nspire")){
            items.get(11).setQuantity(items.get(11).getQuantity() +1);
        }
        if(itemName.equals("Soldering Iron")){
            items.get(12).setQuantity(items.get(12).getQuantity() +1);
        }
        if(itemName.equals("TI-89")){
            items.get(13).setQuantity(items.get(13).getQuantity() +1);
        }
        if(itemName.equals("USB Blaster")){
            items.get(14).setQuantity(items.get(15).getQuantity() +1);
        }
        if(itemName.equals("Wire Kit")){
            items.get(15).setQuantity(items.get(15).getQuantity() +1);
        }
    }

    public void useItem(String itemName, HeroCharacter hero){
        if(itemName.equals("Redbull")){
            hero.gpa = items.get(5).activateItem(hero.gpa);
        }
    }

    public void removeEffect(String itemName, HeroCharacter hero){
        if(itemName.equals("Redbull")){
            hero.gpa = items.get(5).disableItem(hero.gpa);
        }
    }

    public int getNumC(){
        int numC = 0;
        for(int i=0; i<items.size(); i++)
        {
            if(items.get(i).getItemType()=='c' && items.get(i).getQuantity() > 0)
                numC +=1;
        }
        return numC;
    }

    public int getNumA(){
        int numA = 0;
        for(int i=0; i<items.size(); i++)
        {
            if(items.get(i).getItemType()=='a' && items.get(i).getQuantity() > 0)
                numA +=1;
        }
        return numA;
    }

    public int getNumE(){
        int numE = 0;
        for(int i=0; i<items.size(); i++)
        {
            if(items.get(i).getItemType()=='e' && items.get(i).getQuantity() > 0)
                numE +=1;
        }
        return numE;
    }
}
