package com.superschmalgames;

//Inventory item class for creating and managing items that can be equipped or used by the character.

import com.badlogic.gdx.graphics.Texture;

public class InventoryItem {
    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public boolean canEquip;       //Is the item equippable? If not, it's a "useable" item like a potion.
    public boolean equipped;       //prevents an equipable item from being equipped twice
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public float boostAmt;         //How much is the stat changed.
    public int boostDuration;      //How long (in combat turns) will the boost last

    public InventoryItem(String name, String texPath, String stat, boolean equip, float boost, int dur, int initQuant){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        canEquip = true;
        boostAmt = boost;
        boostDuration = dur;
        quantity += initQuant;
    }

    //hero needs to be passed in when useItem is called. Everything then handled here
    //possibly need to think about making this an interface, then have a consumableitem
    //class and an equipmentitem class extend this

    //Method to effectively use/equip an item in the player's inventory
    public float useItem(float boostedStat){
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
        if(canEquip && !equipped){
            boostedStat += boostAmt;
            equipped = true;
        }
        else{ //Item is a temp boost
            boostedStat += boostAmt;
            quantity--;
        }
        return boostedStat;
    }

    //Method to effectively unequip or "finish using" an item. It will remove the stat buff and decrease the item
    //quantity if necessary.
    public float unUseItem(float boostedStat){
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
        if(canEquip){
            boostedStat -= boostAmt;
        }
        else{
            boostedStat -= boostAmt;
        }
        return boostedStat;
    }
}
