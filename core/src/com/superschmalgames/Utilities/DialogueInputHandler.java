package com.superschmalgames.Utilities;

//This class handles input for controlling selections within the character dialogue window.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Screens.GameScreen;

public class DialogueInputHandler implements InputProcessor {


    public DialogueInputHandler(){

    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT){
            if(!MainClass.gameScreen.window.proceed) {
                MainClass.gameScreen.window.proceed = true;
                Utils.menuIcon.translateX(-58);
            }
        }
        else if(keycode == Input.Keys.RIGHT){
            if(MainClass.gameScreen.window.proceed) {
                MainClass.gameScreen.window.proceed = false;
                Utils.menuIcon.translateX(58);
            }
        }
        else if(keycode == Input.Keys.ENTER){
            MainClass.gameScreen.dial = false;
            Gdx.input.setInputProcessor(MainClass.inputHandler);
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
