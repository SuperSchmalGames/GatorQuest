package com.superschmalgames.Inventory;

//Inventory item interface for creating and managing items that can be equipped or used by the character.

import com.badlogic.gdx.graphics.Texture;

public interface InventoryItem {

    //Method to effectively use/equip an item in the player's inventory
    void activateItem();

    //Method to effectively unequip or "finish using" an item. It will remove the stat buff and decrease the item
    //quantity if necessary.
    void disableItem();

    String getItemName();

    int getQuantity();
    void setQuantity(int quant);

    String getStatBoosted();

    double getBoostAmt();

    int getBoostDur();

    String getItemDes();

    char getItemType();

    Texture getTexture();

}
