package com.superschmalgames;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.ShopMenu;
import com.superschmalgames.Utilities.Utils;

public class SHOP extends NPC {
    public SHOP(char dir, String sprite, int x, int y) {
        super(dir, "", sprite, x, y);
    }

    public void initiateShop()
    {
        //Set the store flag true so the store window will render to the game screen
        MainClass.gameScreen.store = true;

        //Stop character movement, if we're moving.
        MainClass.gameScreen.lWalk = false;
        MainClass.gameScreen.rWalk = false;
        MainClass.gameScreen.uWalk = false;
        MainClass.gameScreen.dWalk = false;

        //Give input control to the dialogue input handler.
        Gdx.input.setInputProcessor(MainClass.shopInputHandler);

        //Create new dialogue window containing the shop of the NPC we're talking to.
        MainClass.gameScreen.shop_window = new ShopMenu();    //480=text block width, 8=left align, true=wrap
        MainClass.gameScreen.shop_window.dialog.setText(Utils.font, script, Color.WHITE, 480, 8, true);

        MainClass.gameScreen.shop_window.decLock = false;
        MainClass.gameScreen.shop_window.quitPrompt = MainClass.gameScreen.shop_window.quit;
        // MainClass.gameScreen.shop_window.decision = MainClass.gameScreen.shop_window.okNo;
        MainClass.gameScreen.shop_window.decOffsetX = MainClass.gameScreen.shop_window.OKNO_X_OFFSET;
        MainClass.gameScreen.shop_window.decOffsetY = MainClass.gameScreen.shop_window.OKNO_Y_OFFSET;

        Utils.menuIcon.setPosition(MainClass.gameScreen.shop_window.ICON_X_OFFSET, MainClass.gameScreen.shop_window.ICON_Y_OFFSET);

    }
}
