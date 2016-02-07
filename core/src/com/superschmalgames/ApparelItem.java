package com.superschmalgames;

//Class for representing items that can be equipped/worn on the character.

import com.badlogic.gdx.graphics.Texture;

public class ApparelItem implements InventoryItem {

    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public double boostAmt;         //How much is the stat changed.
    public char itemType;          //Defines the item by Apparel, Equipment or Consumable by chars 'a', 'e' or 'c' respectively

    public ApparelItem(String name, String texPath, String stat, double boost, int initQuant){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        boostAmt = boost;
        quantity += initQuant;
        itemType = 'a';
    }

    public ApparelItem(){
        itemName = "";
        statBoosted = "";
        boostAmt = 0;
        quantity = 0;
        itemType = 'a';
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

    @Override
    public double activateItem(double boostedStat) {
        //Equip the item and apply the appropriate boost.
        return boostedStat + boostAmt;
    }

    @Override
    public double disableItem(double boostedStat) {
        //Unequip the item and remove the boost that was given.
        return boostedStat - boostAmt;
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

    public void removeApparel()
    {
        MainClass.hero.heroApparel.itemName = "";
        MainClass.hero.heroApparel.statBoosted = "";
        MainClass.hero.heroApparel.boostAmt = 0;
        MainClass.hero.inventory.calc_stats();
    }
}
