package com.superschmalgames;

//Inventory item class for creating and managing items that can be equipped or used by the character.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class InventoryItem {
    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public boolean canEquip;       //Is the item equippable? If not, it's a "useable" item like a potion.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public double boostAmt;         //How much is the stat changed.
    public int boostDuration;      //How long (in combat turns) will the boost last

    //Likely temporary. Need to have some sort of generic/global sound handler?
    static final Sound errTone = Gdx.audio.newSound(Gdx.files.internal("sound/effects/error_tone.wav"));

    public InventoryItem(String name, String texPath, String stat, boolean equip, double boost, int dur, int initQuant){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        canEquip = equip;
        boostAmt = boost;
        boostDuration = dur;
        quantity += initQuant;
    }

    //Method to effectively use/equip an item in the player's inventory
    public double useItem(double boostedStat){
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
        if(canEquip){
            boostedStat += boostAmt;
        }
        else{ //Item is a temp boost
            if(quantity > 0) {
                boostedStat += boostAmt;
                quantity--;
            }
            else errTone.play();
        }
        return boostedStat;
    }

    //Method to effectively unequip or "finish using" an item. It will remove the stat buff and decrease the item
    //quantity if necessary.
    public double unUseItem(double boostedStat){
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
