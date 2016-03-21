package com.superschmalgames.InputHandlers;

//This class handles input for controlling selections within the character dialogue window.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class DialogueInputHandler implements InputProcessor {


    public DialogueInputHandler(){

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT && !MainClass.gameScreen.window.decLock){
            if(MainClass.gameScreen.window.proceed) {
                MainClass.gameScreen.window.proceed = false;
                Utils.menuIcon.translateX(-58);
                Gdx.app.log("D Input Test", "Pushed Left");
            }
        }
        else if(keycode == Input.Keys.RIGHT && !MainClass.gameScreen.window.decLock){
            if(!MainClass.gameScreen.window.proceed) {
                MainClass.gameScreen.window.proceed = true;
                Utils.menuIcon.translateX(58);
                Gdx.app.log("D Input Test", "Pushed Right");
            }
        }
        else if(keycode == Input.Keys.ENTER){
            MainClass.gameScreen.dial = false;

            //If we're talking to an enemy, call the combat() function to potentially start fighting. Otherwise, continue.
            if(MainClass.gameScreen.window.enemy){
                MainClass.hero.lastEnemy.combat();
            }
            //If we just talked to a friendly NPC, we don't start combat.
            else {
                //Reset the NPC to the position they were at before walking over to us.
                MainClass.hero.lastNPC.reset();
                //Give control back to the main input handler.
                Gdx.input.setInputProcessor(MainClass.inputHandler);
            }
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
