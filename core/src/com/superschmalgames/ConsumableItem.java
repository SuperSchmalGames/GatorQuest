package com.superschmalgames;

//Class to represent all items that can be consumed (not equipped) by the character.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ConsumableItem implements InventoryItem {

    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public boolean isTemp;         //Does the item provide only a temporary bonus?
    public double boostAmt;        //How much is the stat changed.
    public int boostDuration;      //How long (in combat turns) will the boost last (if isTemp is true).

    //Likely temporary. Need to have some sort of generic/global sound handler?
    static final Sound errTone = Gdx.audio.newSound(Gdx.files.internal("sound/effects/error_tone.wav"));

    public ConsumableItem(String name, String texPath, String stat, double boost, int dur, int initQuant, boolean isTemporary){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        boostAmt = boost;
        boostDuration = dur;
        quantity += initQuant;
        isTemp = isTemporary;
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
        //For usable items, apply the appropriate buff and reduce the inventory number by one.
        if(quantity > 0) {
            quantity--;
            return boostedStat + boostAmt;
        }
        else errTone.play();
        return boostedStat;
    }

    @Override
    public double disableItem(double boostedStat) {
        //When the item's boost wears off, decrease the stat by appropriate amount.
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
    public Texture getTexture(){
        return texture;
    }
}
