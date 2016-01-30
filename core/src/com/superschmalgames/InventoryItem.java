package com.superschmalgames;

//Inventory item interface for creating and managing items that can be equipped or used by the character.

import com.badlogic.gdx.graphics.Texture;

public interface InventoryItem {
    //Method to add an item to the character inventory.
    void addItem(HeroCharacter hero);

    //Method to effectively use/equip an item in the player's inventory
    void activateItem(HeroCharacter hero);

    //Method to effectively unequip or "finish using" an item. It will remove the stat buff and decrease the item
    //quantity if necessary.
    void disableItem(HeroCharacter hero);

    String getItemName();
    void setItemName(String itemName);

    int getQuantity();
    void setQuantity(int quant);

    String getStatBoosted();
    void setStatBoosted(String stat);

    double getBoostAmt();
    void setBoostAmt(double boost);

    Texture getTexture();

}
