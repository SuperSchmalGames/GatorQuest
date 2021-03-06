package com.superschmalgames.Hero;

//Class to represent the character's inventory.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.superschmalgames.Inventory.ApparelItem;
import com.superschmalgames.Inventory.ConsumableItem;
import com.superschmalgames.Inventory.EquipableItem;
import com.superschmalgames.Inventory.InventoryItem;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class HeroInventory {

    //Character inventory.
    public List<InventoryItem> items;

    public HeroInventory(){
        items = new ArrayList<>();

        ApparelItem tmpA;
        ConsumableItem tmpC;
        EquipableItem tmpE;

        tmpA = new ApparelItem("Biz Casual Attire", Utils.business_casual_attire_tex, "Social", 2.0, 0, "Casual clothes suitable\nfor class.");
        items.add(tmpA);
        tmpA = new ApparelItem("Class Ring", Utils.class_ring_tex, "Endurance", 1.0, 0, "Fancy ring for Gators.");
        items.add(tmpA);
        tmpA = new ApparelItem("Gator Hat", Utils.gator_hat_tex, "Social", 1.0, 0, "A UF Gators hat. Go Gators!");
        items.add(tmpA);
        tmpA = new ApparelItem("Graduation Gown", Utils.graduation_gown_tex, "Endurance", 2.0, 0, "The robe that seniors get to\nwear.");
        items.add(tmpA);
        tmpA = new ApparelItem("Suit and Tie", Utils.suit_and_tie_tex, "Social", 3.0, 0, "Get fancy. Career Showcase\nattire.");
        items.add(tmpA);
        tmpC = new ConsumableItem("Redbull",Utils.redbull_tex,"Attack",0.5,3,2,true, "Ups your assignment destroying\npower.");
        items.add(tmpC);
        tmpC = new ConsumableItem("Starbucks",Utils.starbucks_tex,"Defense",-0.5,3,1,true, "Take less damage from your\nworkload.");
        items.add(tmpC);
        tmpC = new ConsumableItem("Chegg Login",Utils.chegg_tex,"GPA",2.0,3,5,true, "This site has tons of\nassignment answers. Sure\nto help your GPA!");
        items.add(tmpC);
        tmpC = new ConsumableItem("TutorZone Pkt",Utils.tutoringzone_tex,"GPA",1.0,3,5,true, "Get studying! Helps you ace\nassignments. Should boost GPA.");
        items.add(tmpC);
        tmpC = new ConsumableItem("Quiz Drop",Utils.quiz_drop_tex,"Defense",-1.0,2,5,true, "Take less damage from your\nworkload. Save for a\ntough class!");
        items.add(tmpC);
        tmpC = new ConsumableItem("Test Drop",Utils.test_drop_tex,"Defense",-1.0,4,3,true, "Take less damage from your\nworkload. Save for a\nreally tough class!");
        items.add(tmpC);
        tmpE = new EquipableItem("AVR Dragon", Utils.avr_dragon_tex, "Hardware", 3.0, 0, "A fancy board suitable for uP.");
        items.add(tmpE);
        tmpE = new EquipableItem("Cyclone III", Utils.cyclone_III_tex, "Hardware", 2.0, 0, "A good board to use in\nDigital Design.");
        items.add(tmpE);
        tmpE = new EquipableItem("DAD Board", Utils.dad_board_tex, "Focus", 1.0, 0, "Helps you carefully analyze\nall your outputs.");
        items.add(tmpE);
        tmpE = new EquipableItem("Macbook Pro", Utils.macbook_pro_tex, "Software", 1.0, 1, "Go Apple. xCode for dayz.");
        items.add(tmpE);
        tmpE = new EquipableItem("Nspire", Utils.nspire_tex, "Math", 2.0, 0, "Solves anything math-y.");
        items.add(tmpE);
        tmpE = new EquipableItem("Soldering Iron", Utils.soldering_iron_tex, "Endurance", 1.0, 0, "Solder your hardware together\nwith this.");
        items.add(tmpE);
        tmpE = new EquipableItem("TI-89", Utils.ti_89_tex, "Math", 1.0, 0, "Makes math stuff easier.");
        items.add(tmpE);
        tmpE = new EquipableItem("USB Blaster", Utils.usb_blaster_tex, "Software", 2.0, 0, "This lets you program your\nboard.");
        items.add(tmpE);
        tmpE = new EquipableItem("Wire Kit", Utils.wire_kit_tex, "Hardware", 1.0, 1, "Build circuits in a snap.");
        items.add(tmpE);
    }

    public void incItem(Utils.INV_ITEMS itemName){
        //Pass in the enum for the item being inc'ed, use it to get the appropriate index of arraylist, then inc the quantity of items at that index.
        items.get(itemName.getNumVal()).setQuantity(items.get(itemName.getNumVal()).getQuantity() +1);
    }

    public void useItem(Utils.INV_ITEMS itemName){
        //Pass in enum for item being used/equipped, then find item's index and call activate() for that item.
        items.get(itemName.getNumVal()).activateItem();
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

        //If we're in the combat screen, use itemPane and index to find current Item.
        if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.combatScreen){
            while (relIndex <= MainClass.combatInputHandler.index) {
                if (MainClass.hero.inventory.items.get(overallIndex).getQuantity() > 0
                        && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'c') {
                    relIndex += 1;
                }
                overallIndex += 1;
            }
            return overallIndex-1;
        }

        //If we're not in combat, use invRow and invPage to find current Item.
        if("Consumable".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('c') > 0)
        {
            if (MainClass.inventoryScreen.invRow == 0 && MainClass.inventoryScreen.invPage == 0) {
                boolean wait = true;
                while (wait) {
                    if (MainClass.hero.inventory.items.get(overallIndex).getQuantity() > 0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'c') {
                        wait = false;
                    }
                    overallIndex = overallIndex + 1;
                }
            }
            else {
                while (relIndex < (1 + MainClass.inventoryScreen.invRow + MainClass.inventoryScreen.invPage * 8)) {
                    if (MainClass.hero.inventory.items.get(overallIndex).getQuantity() > 0
                            && MainClass.hero.inventory.items.get(overallIndex).getItemType() == 'c') {
                        relIndex += 1;
                    }
                    overallIndex += 1;
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
