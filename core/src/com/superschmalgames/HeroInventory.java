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

        tmpA = new ApparelItem("Biz Casual Attire", Utils.business_casual_attire_tex, "Social", 2.0, 0);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Class Ring", Utils.class_ring_tex, "Endurance", 1.0, 0);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Gator Hat", Utils.gator_hat_tex, "Social", 1.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Graduation Gown", Utils.graduation_gown_tex, "Endurance", 2.0, 1);
        tmpA.addItem(this);
        tmpA = new ApparelItem("Suit and Tie", Utils.suit_and_tie_tex, "Social", 3.0, 1);
        tmpA.addItem(this);
        tmpC = new ConsumableItem("Redbull",Utils.redbull_tex,"GPA",0.8,3,5,true);
        tmpC.addItem(this);
        tmpC = new ConsumableItem("Starbucks",Utils.starbucks_tex,"GPA",1.2,3,5,true);
        tmpC.addItem(this);
        tmpE = new EquipableItem("AVR Dragon", Utils.avr_dragon_tex, "Hardware", 3.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Cyclone III", Utils.cyclone_III_tex, "Hardware", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("DAD Board", Utils.dad_board_tex, "Focus", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Macbook Pro", Utils.macbook_pro_tex, "Software", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Nspire", Utils.nspire_tex, "Math Skillz", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Soldering Iron", Utils.soldering_iron_tex, "Endurance", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("TI-89", Utils.ti_89_tex, "Math Skillz", 1.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("USB Blaster", Utils.usb_blaster_tex, "Software", 2.0, 1);
        tmpE.addItem(this);
        tmpE = new EquipableItem("Wire Kit", Utils.wire_kit_tex, "Hardware", 1.0, 1);
        tmpE.addItem(this);
    }

    public void incItem(String itemName){
        if("Biz Casual Attire".equals(itemName)){
            items.get(0).setQuantity(items.get(0).getQuantity() +1);
        }
        if("Class Ring".equals(itemName)){
            items.get(1).setQuantity(items.get(1).getQuantity() +1);
        }
        if("Gator Hat".equals(itemName)){
            items.get(2).setQuantity(items.get(2).getQuantity() +1);
        }
        if("Graduation Gown".equals(itemName)){
            items.get(3).setQuantity(items.get(3).getQuantity() +1);
        }
        if("Suit and Tie".equals(itemName)){
            items.get(4).setQuantity(items.get(4).getQuantity() +1);
        }
        if("Redbull".equals(itemName)){
            items.get(5).setQuantity(items.get(5).getQuantity() +1);
        }
        if("Starbucks".equals(itemName)){
            items.get(6).setQuantity(items.get(6).getQuantity() +1);
        }
        if("AVR Dragon".equals(itemName)){
            items.get(7).setQuantity(items.get(7).getQuantity() +1);
        }
        if("Cyclone III".equals(itemName)){
            items.get(8).setQuantity(items.get(8).getQuantity() +1);
        }
        if("DAD Board".equals(itemName)){
            items.get(9).setQuantity(items.get(9).getQuantity() +1);
        }
        if("Macbook Pro".equals(itemName)){
            items.get(10).setQuantity(items.get(10).getQuantity() +1);
        }
        if("Nspire".equals(itemName)){
            items.get(11).setQuantity(items.get(11).getQuantity() +1);
        }
        if("Soldering Iron".equals(itemName)){
            items.get(12).setQuantity(items.get(12).getQuantity() +1);
        }
        if("TI-89".equals(itemName)){
            items.get(13).setQuantity(items.get(13).getQuantity() +1);
        }
        if("USB Blaster".equals(itemName)){
            items.get(14).setQuantity(items.get(15).getQuantity() +1);
        }
        if("Wire Kit".equals(itemName)){
            items.get(15).setQuantity(items.get(15).getQuantity() +1);
        }
    }

    public void useItem(String itemName, HeroCharacter hero){
        if("Redbull".equals(itemName)){
            hero.gpa = items.get(5).activateItem(hero.gpa);
        }
    }

    public void removeEffect(String itemName, HeroCharacter hero){
        if("Redbull".equals(itemName)){
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

    public void calc_stats(){
        MainClass.hero.software_buf = MainClass.hero.software;
        MainClass.hero.hardware_buf = MainClass.hero.hardware;
        MainClass.hero.readWrite_buf = MainClass.hero.readWrite;
        MainClass.hero.endurance_buf = MainClass.hero.endurance;
        MainClass.hero.social_buf = MainClass.hero.social;
        MainClass.hero.math_buf = MainClass.hero.math;
        MainClass.hero.focus_buf = MainClass.hero.focus;
        //JAVA STRING comparisons must be done with equals, typical comparisons (==, !=)
        //only check if the objects are the same.
        if("".equals(MainClass.hero.heroApparel.getItemName()))
        //if(MainClass.hero.heroApparel.getItemName() != "")
        {
            if("Biz Casual Attire".equals(MainClass.hero.heroApparel.getItemName()))
            //if(MainClass.hero.heroApparel.getItemName() == "Biz Casual Attire")
            {
                MainClass.hero.social_buf+=2;
            }
            else if("Class Ring".equals(MainClass.hero.heroApparel.getItemName()))
            //else if(MainClass.hero.heroApparel.getItemName() == "Class Ring")
            {
                MainClass.hero.endurance_buf+=1;
            }
            else if("Gator Hat".equals(MainClass.hero.heroApparel.getItemName()))
            //else if(MainClass.hero.heroApparel.getItemName() == "Gator Hat")
            {
                MainClass.hero.social_buf+=1;
            }
            else if("Graduation Gown".equals(MainClass.hero.heroApparel.getItemName()))
            //else if(MainClass.hero.heroApparel.getItemName() == "Graduation Gown")
            {
                MainClass.hero.endurance_buf+=2;
            }
            else if("Suit and Tie".equals(MainClass.hero.heroApparel.getItemName()))
            // else if(MainClass.hero.heroApparel.getItemName() == "Suit and Tie")
            {
                MainClass.hero.social_buf+=3;
            }
        }
        if("".equals(MainClass.hero.heroEquipment.getItemName()))
        //if(MainClass.hero.heroEquipment.getItemName() != "")
        {
            if("AVR Dragon".equals(MainClass.hero.heroEquipment.getItemName()))
            //if(MainClass.hero.heroEquipment.getItemName() == "AVR Dragon")
            {
                MainClass.hero.hardware_buf+=3;
            }
            else if("Cyclone III".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "Cyclone III")
            {
                MainClass.hero.hardware_buf+=2;
            }
            else if("DAD Board".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "DAD Board")
            {
                MainClass.hero.focus_buf+=1;
            }
            else if("Macbook Pro".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "Macbook Pro")
            {
                MainClass.hero.software_buf+=1;
            }
            else if("Nspire".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "Nspire")
            {
                MainClass.hero.math_buf+=2;
            }
            else if("Soldering Iron".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "Soldering Iron")
            {
                MainClass.hero.endurance_buf+=1;
            }
            else if("TI-89".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "TI-89")
            {
                MainClass.hero.math_buf+=1;
            }
            else if("USB Blaster".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "USB Blaster")
            {
                MainClass.hero.software_buf+=2;
            }
            else if("Wire Kit".equals(MainClass.hero.heroEquipment.getItemName()))
            //else if(MainClass.hero.heroEquipment.getItemName() == "Wire Kit")
            {
                MainClass.hero.hardware_buf+=1;
            }
        }
    }

    public int getCurrentItemIndex(){
        int relIndex = 0;
        int overallIndex = 0;
        if("Consumable".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNumC() > 0)
        //if(MainClass.inventoryScreen.invPanel == "Consumable" && MainClass.hero.inventory.getNumC() > 0)
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
        else if("Equipment".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNumE() > 0)
        //else if(MainClass.inventoryScreen.invPanel == "Equipment" && MainClass.hero.inventory.getNumE() > 0)
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
        else if("Apparel".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNumA() > 0)
        //else if(MainClass.inventoryScreen.invPanel == "Apparel" && MainClass.hero.inventory.getNumA() > 0)
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
