package com.superschmalgames;

//Class to handle all user input. Allows us to put all the code in one place, and make the render() method in
//all the other classes much cleaner.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

    //Has a button been pushed?
    public boolean pushed;

    //Int used for selecting menu options
    public int menuIndex;

    public InputHandler(){
        pushed = false;
        menuIndex = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.titleScreen){
            if(keycode == Input.Keys.DOWN && Utils.menuReady && menuIndex < 2){
                //Play the sound effect when player pushes the button.
                Utils.titleOptionSound.play(0.6f);
                //Move the menu icon down to the next menu option.
                Utils.menuIcon.translateY(-33.0f);
                //Increment the menu index.
                menuIndex++;
            }
            else if(keycode == Input.Keys.UP && Utils.menuReady && menuIndex > 0){
                Utils.titleOptionSound.play(0.6f);
                Utils.menuIcon.translateY(33.0f);
                menuIndex--;
            }
            else if(keycode == Input.Keys.ENTER && Utils.menuReady){
                if(menuIndex == 0) {
                    //Play the sound effect when player pushes the button.
                    Utils.titleScreenSelectionSound.play(0.4f);

                    //Set the game screen to be the character select screen.
                    MainClass.avatarScreen = new AvatarColorSel();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.avatarScreen);
                    MainClass.titleScreen.dispose();
                }
                else
                    Utils.errTone.play(0.5f);
            }
        }
        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.avatarScreen) {
            if(keycode == Input.Keys.NUM_1) {
                MainClass.hero.outfitNum = 7;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_2) {
                MainClass.hero.outfitNum = 11;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_3) {
                MainClass.hero.outfitNum = 10;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_4) {
                MainClass.hero.outfitNum = 9;
                pushed = true;
            }

            if(pushed) {
                pushed = false;
                //Initialize character with proper texture.
                MainClass.hero.initAnimations();

                //Play the "selection" sound effect.
                Utils.avatarScreenSelectionSound.play(0.4f);

                //Set game screen to be the main game screen.
                MainClass.gameScreen = new GameScreen();
                ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                MainClass.avatarScreen.dispose();
            }
        }
        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.gameScreen) {
            //Take keyboard input from user for character movement. Character actually stays centered on screen, and the
            //camera is translated about the map to give illusion of character movement.
            if(keycode == Input.Keys.LEFT && !Utils.isPaused){
                MainClass.gameScreen.lWalk = true;
            }
            else if(keycode == Input.Keys.RIGHT && !Utils.isPaused){
                MainClass.gameScreen.rWalk = true;
            }
            else if(keycode == Input.Keys.UP && !Utils.isPaused){
                MainClass.gameScreen.uWalk = true;
            }
            else if(keycode == Input.Keys.DOWN && !Utils.isPaused){
                MainClass.gameScreen.dWalk = true;
            }
            else if((keycode == Input.Keys.I)){
                //Play the sound effect when player pushes the button.
                Utils.inventoryScreenSelectionSound.play();

                //Set the gamescreen to be the inventory game screen.
                MainClass.inventoryScreen = new InventoryScreen();
                ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.inventoryScreen);

                MainClass.inventoryScreen.invPanel = "Consumable";
                MainClass.inventoryScreen.invPage = 0;
                MainClass.inventoryScreen.invRow = 0;
            }
            ////////////////////////////////////////////////TEST INPUTS///////////////////////////////////////////////////////
            else if(keycode == Input.Keys.R && !Utils.isPaused){
                MainClass.hero.inventory.addItem("Red Bull");
            }
            else if((keycode == Input.Keys.T && !Utils.isPaused)){
                MainClass.hero.inventory.useItem("Red Bull", MainClass.hero);
            }
            else if(keycode == Input.Keys.Y && !Utils.isPaused){
                MainClass.hero.inventory.removeEffect("Red Bull", MainClass.hero);
            }
            else if (keycode == Input.Keys.P){
                if(!Utils.isPaused) {
                    Utils.isPaused = true;
                    (Gdx.app.getApplicationListener()).pause();
                }
                else {
                    Utils.isPaused = false;
                    (Gdx.app.getApplicationListener()).resume();
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.inventoryScreen) {
            if (keycode == Input.Keys.LEFT) {

                MainClass.inventoryScreen.invRow = 0;

                if (MainClass.inventoryScreen.invPanel == "Consumable")
                    MainClass.inventoryScreen.invPanel = "Apparel";
                else if (MainClass.inventoryScreen.invPanel == "Apparel")
                    MainClass.inventoryScreen.invPanel = "Equipment";
                else if (MainClass.inventoryScreen.invPanel == "Equipment")
                    MainClass.inventoryScreen.invPanel = "Consumable";
            } else if (keycode == Input.Keys.RIGHT) {

                MainClass.inventoryScreen.invRow = 0;

                if (MainClass.inventoryScreen.invPanel == "Consumable")
                    MainClass.inventoryScreen.invPanel = "Equipment";
                else if (MainClass.inventoryScreen.invPanel == "Apparel")
                    MainClass.inventoryScreen.invPanel = "Consumable";
                else if (MainClass.inventoryScreen.invPanel == "Equipment")
                    MainClass.inventoryScreen.invPanel = "Apparel";
            } else if (keycode == Input.Keys.ESCAPE) {
                //Play the sound effect when player pushes the button.
                Utils.inventoryScreenSelectionSound.play();

                //Set the gamescreen to be the inventory game screen.
                ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
            }
            else if (keycode == Input.Keys.DOWN){
                if ((MainClass.inventoryScreen.invRow + 1)%8 == 0) {
                    MainClass.inventoryScreen.invPage += 1;
                    MainClass.inventoryScreen.invRow = 0;
                }
                else
                    MainClass.inventoryScreen.invRow += 1;
            }
            else if (keycode == Input.Keys.UP){
                if(MainClass.inventoryScreen.invRow == 0 && MainClass.inventoryScreen.invPage == 0){

                }
                else if ((MainClass.inventoryScreen.invRow)%8 == 0) {
                    MainClass.inventoryScreen.invPage -= 1;
                    MainClass.inventoryScreen.invRow += 7;
                }
                else
                    MainClass.inventoryScreen.invRow -= 1;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.gameScreen) {
            //Set the appropriate boolean value false to stop the walk animation when the button is lifted
            if (keycode == Input.Keys.LEFT) MainClass.gameScreen.lWalk = false;
            if (keycode == Input.Keys.RIGHT) MainClass.gameScreen.rWalk = false;
            if (keycode == Input.Keys.UP) MainClass.gameScreen.uWalk = false;
            if (keycode == Input.Keys.DOWN) MainClass.gameScreen.dWalk = false;
        }

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
