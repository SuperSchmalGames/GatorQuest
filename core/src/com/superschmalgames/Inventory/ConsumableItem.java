package com.superschmalgames.Inventory;

//Class to represent all items that can be consumed (not equipped) by the character.


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class ConsumableItem implements InventoryItem {

    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public boolean isTemp;         //Does the item provide only a temporary bonus?
    public double boostAmt;        //How much is the stat changed.
    public int boostDuration;      //How long (in combat turns) will the boost last (if isTemp is true).
    public char itemType;          //Defines the item by Apparel, Equipment or Consumable by chars 'a', 'e' or 'c' respectively

    public ConsumableItem(String name, Texture tex, String stat, double boost, int dur, int initQuant, boolean isTemporary){
        itemName = name;
        texture = tex;
        statBoosted = stat;
        boostAmt = boost;
        boostDuration = dur;
        quantity += initQuant;
        isTemp = isTemporary;
        itemType = 'c';
    }

    @Override
    public void activateItem() {
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
        if(quantity > 0) {
            try {
                double temp1 = MainClass.hero.getClass().getField(statBoosted).getDouble(MainClass.hero) + boostAmt;
                MainClass.hero.getClass().getField(statBoosted).setDouble(MainClass.hero, temp1);
                quantity--;
            } catch (Exception e) {
                Gdx.app.log("test", "Something wrong in activateItem()!");
            }
            if(MainClass.hero.GPA > 4.0){
                //If the consumable item boosts the GPA over 4.0, cap it at 4.0.
                MainClass.hero.GPA = 4.0;
            }
        }
        else{
            Utils.errTone.play();
        }
    }

    @Override
    public void disableItem() {
        //When the item's boost wears off, decrease the stat by appropriate amount.
        try {
            double temp1 = MainClass.hero.getClass().getField(statBoosted).getDouble(MainClass.hero) - boostAmt;
            MainClass.hero.getClass().getField(statBoosted).setDouble(MainClass.hero, temp1);
        } catch (Exception e) {
            Gdx.app.log("test", "Something wrong in disableItem()!");
        }
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quant) {
        quantity = quant;
    }

    @Override
    public String getStatBoosted() {
        return statBoosted;
    }

    @Override
    public double getBoostAmt() {
        return boostAmt;
    }

    @Override
    public char getItemType() {return itemType;}

    @Override
    public Texture getTexture(){
        return texture;
    }

}
