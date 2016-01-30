package com.superschmalgames;

//Class for representing items that can be equipped/worn on the character.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class EquipableItem implements InventoryItem {

    public String itemName;        //Name of the inventory item.
    public int quantity;           //Number of that item currently in inventory. If it hits 0, remove it from inventory.
    public Texture texture;        //Texture used to render the item in the game.
    public String statBoosted;     //Which stat is affected by equipping/using the item.
    public double boostAmt;         //How much is the stat changed.

    //Likely temporary. Need to have some sort of generic/global sound handler?
    static final Sound errTone = Gdx.audio.newSound(Gdx.files.internal("sound/effects/error_tone.wav"));

    public EquipableItem(String name, String texPath, String stat, double boost, int initQuant){
        itemName = name;
        texture = new Texture(texPath);
        statBoosted = stat;
        boostAmt = boost;
        quantity += initQuant;
    }

    @Override
    public void addItem(HeroCharacter hero) {
        for(InventoryItem i : hero.inventory){
            if(this.itemName.equals(i.getItemName())){
                i.setQuantity(i.getQuantity()+1);
                return;
            }
        }
        hero.inventory.add(this);
    }

    @Override
    public void activateItem(HeroCharacter hero) {
        //Equip the item and apply the appropriate boost.
        if(quantity > 0) {
            //boostedStat += boostAmt;
        }
        else errTone.play();

    }

    @Override
    public void disableItem(HeroCharacter hero) {
        //Unequip the item and remove the boost that was given.

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
