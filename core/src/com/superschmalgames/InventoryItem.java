package com.superschmalgames;

//Inventory item class for creating and managing items that can be equipped or used by the character.

import com.badlogic.gdx.graphics.Texture;

public class InventoryItem {
    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public boolean isEquippable;   //Is the item equippable? If not, it's a "useable" item like a potion.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public float boostAmt;         //How much is the stat changed.
    public int boostDuration;      //How long (in combat turns) will the boost last

    public InventoryItem(String name){
        itemName = name;

        if(itemName == "Red Bull") {
            texture = new Texture("visuals/sprites/hero.png");
            isEquippable = false;
            statBoosted = "gpa";
            boostAmt = 1.2f;
            boostDuration = 3;
        }

        //Have other if statements here to set appropriate texture.

    }

    public void equipItem(HeroCharacter hero){
        //For equippable items, this method applies it to the character and boosts the stats.

    }

    public void unequipItem(){
        //Unequips the item and removes the appropriate stat buff.
    }

    public void useItem(){
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
    }
}
