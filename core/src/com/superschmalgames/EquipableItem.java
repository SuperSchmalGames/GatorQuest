package com.superschmalgames;

//Class for representing items that can be equipped/worn on the character.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EquipableItem implements InventoryItem {

    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public double boostAmt;         //How much is the stat changed.
    public char itemType;          //Defines the item by Apparel, Equipment or Consumable by chars 'a', 'e' or 'c' respectively

    public EquipableItem(String name, String texPath, String stat, double boost, int initQuant){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        boostAmt = boost;
        quantity += initQuant;
        itemType = 'e';
    }

    @Override
    public void addItem(HeroInventory inv) {
        for(InventoryItem i : inv.items){
            if(this.itemName.equals(i.getItemName())){
                i.setQuantity(i.getQuantity()+1);
                return;
            }
        }
        inv.items.add(this);
    }

    //Method to equip the item and apply the appropriate boost.
    @Override
    public void activateItem() {
        //Set hero's equipped item to the item we're activating
        MainClass.hero.heroEquipment = this;

        //Get the character's buffed skill value and add this item's boost amount to it.
        try {
            double temp1 = MainClass.hero.getClass().getField(statBoosted+"_buf").getDouble(MainClass.hero) + boostAmt;
            MainClass.hero.getClass().getField(statBoosted+"_buf").setDouble(MainClass.hero, temp1);
        } catch (Exception e) {
            Gdx.app.log("test", "Something wrong in equipable activateItem()!");
        }
    }

    //Method to unequip the item and remove the boost that was given.
    @Override
    public void disableItem() {
        //Get the character's buffed skill value and subtract this item's boost amount from it.
        try {
            double temp1 = MainClass.hero.getClass().getField(statBoosted+"_buf").getDouble(MainClass.hero) - boostAmt;
            MainClass.hero.getClass().getField(statBoosted+"_buf").setDouble(MainClass.hero, temp1);
        } catch (Exception e) {
            Gdx.app.log("test", "Something wrong in equipable disableItem()!");
        }

        //Item is no longer equipped, so set character's equipment to null.
        MainClass.hero.heroEquipment = null;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
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
    public void setStatBoosted(String stat) {
        statBoosted = stat;
    }

    @Override
    public double getBoostAmt() {
        return boostAmt;
    }

    @Override
    public void setBoostAmt(double boost) {
        boostAmt = boost;
    }

    @Override
    public char getItemType() { return itemType; }

    @Override
    public Texture getTexture(){
        return texture;
    }

    public void setTexture(Texture texPass){texture = texPass;}
}
