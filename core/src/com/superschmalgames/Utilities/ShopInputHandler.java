package com.superschmalgames.Utilities;

//This class handles input for controlling selections within the character dialogue window.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Screens.GameScreen;

public class ShopInputHandler implements InputProcessor {

    public char shop = 'o'; //Will be set to a, b or c based on which of the 3 shopkeepers you are talking to.

    public ShopInputHandler(){

    }

    @Override
    public boolean keyDown(int keycode) {
        if(shop == 'a' && keycode == Input.Keys.DOWN && MainClass.gameScreen.shop_window.currIndex < 1){
            Utils.menuIcon.translateY(-65);
            MainClass.gameScreen.shop_window.currIndex += 1;
        }
        else if(shop == 'a' && keycode == Input.Keys.UP && MainClass.gameScreen.shop_window.currIndex > 0){
            Utils.menuIcon.translateY(65);
            MainClass.gameScreen.shop_window.currIndex -= 1;
        }else if(shop == 'b' && keycode == Input.Keys.DOWN && MainClass.gameScreen.shop_window.currIndex < 4){
            Utils.menuIcon.translateY(-65);
            MainClass.gameScreen.shop_window.currIndex += 1;
        }
        else if(shop == 'b' && keycode == Input.Keys.UP && MainClass.gameScreen.shop_window.currIndex > 0){
            Utils.menuIcon.translateY(65);
            MainClass.gameScreen.shop_window.currIndex -= 1;
        }else if(shop == 'c' && keycode == Input.Keys.DOWN && MainClass.gameScreen.shop_window.currIndex < 5){
            Utils.menuIcon.translateY(-65);
            MainClass.gameScreen.shop_window.currIndex += 1;
        }
        else if(shop == 'c' && keycode == Input.Keys.UP && MainClass.gameScreen.shop_window.currIndex > 0){
            Utils.menuIcon.translateY(65);
            MainClass.gameScreen.shop_window.currIndex -= 1;
        }
        else if(keycode == Input.Keys.ENTER)
        {
            if(shop == 'a') {
                if (MainClass.gameScreen.shop_window.currIndex == 0 && MainClass.hero.gatorBucks >= 100) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.RED_BULL);
                    MainClass.hero.gatorBucks -= 100;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 1 && MainClass.hero.gatorBucks >= 300) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.STARBUCKS);
                    MainClass.hero.gatorBucks -= 300;
                    Utils.kaching.play();
                } else {
                    Utils.oob_error.play();
                }
            }
            else if(shop == 'b')
            {
                if (MainClass.gameScreen.shop_window.currIndex == 0 && MainClass.hero.gatorBucks >= 500) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.BIZ_CAS_ATTIRE);
                    MainClass.hero.gatorBucks -= 500;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 1 && MainClass.hero.gatorBucks >= 1000) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.CLASS_RING);
                    MainClass.hero.gatorBucks -= 1000;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 2 && MainClass.hero.gatorBucks >= 400) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.GATOR_HAT);
                    MainClass.hero.gatorBucks -= 400;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 3 && MainClass.hero.gatorBucks >= 1200) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.GRAD_GOWN);
                    MainClass.hero.gatorBucks -= 1200;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 4 && MainClass.hero.gatorBucks >= 2000) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.SUIT_TIE);
                    MainClass.hero.gatorBucks -= 2000;
                    Utils.kaching.play();
                }else {
                    Utils.oob_error.play();
                }
            }
            else if(shop == 'c')
            {
                if (MainClass.gameScreen.shop_window.currIndex == 0 && MainClass.hero.gatorBucks >= 700) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.CYCLONE);
                    MainClass.hero.gatorBucks -= 700;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 1 && MainClass.hero.gatorBucks >= 900) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.DAD);
                    MainClass.hero.gatorBucks -= 900;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 2 && MainClass.hero.gatorBucks >= 2500) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.MACBOOK);
                    MainClass.hero.gatorBucks -= 2500;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 3 && MainClass.hero.gatorBucks >= 650) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.NSPIRE);
                    MainClass.hero.gatorBucks -= 650;
                    Utils.kaching.play();
                } else if (MainClass.gameScreen.shop_window.currIndex == 4 && MainClass.hero.gatorBucks >= 350) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.SOLDER);
                    MainClass.hero.gatorBucks -= 350;
                    Utils.kaching.play();
                }else if (MainClass.gameScreen.shop_window.currIndex == 5 && MainClass.hero.gatorBucks >= 500) {
                    MainClass.hero.inventory.incItem(Utils.INV_ITEMS.TI89);
                    MainClass.hero.gatorBucks -= 500;
                    Utils.kaching.play();
                }else {
                    Utils.oob_error.play();
                }
            }
        }
        else if(keycode == Input.Keys.Q){
            MainClass.gameScreen.dial = false;
            MainClass.gameScreen.store = false;
            MainClass.hero.lastInteracted.reset();
            Gdx.input.setInputProcessor(MainClass.inputHandler);
        }
        else
        {
            Utils.oob_error.play();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
