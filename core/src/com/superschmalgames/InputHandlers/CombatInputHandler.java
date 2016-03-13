package com.superschmalgames.InputHandlers;

//Class to handle input from player during combat scenarios.

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;


public class CombatInputHandler implements InputProcessor{

    public boolean rootMenu, moveMenu, itemMenu;
    public int rootIndex, moveIndex, itemIndex;    //MOVE ALL OF THESE TO COMBATLOGIC EVENTUALLY!!

    public CombatInputHandler(){

    }

    @Override
    public boolean keyDown(int keycode) {
        if(rootMenu) {
            if (keycode == Input.Keys.UP) {
                Utils.menuIcon.translateY(65);
            }
            else if (keycode == Input.Keys.DOWN) {
                Utils.menuIcon.translateY(-65);
            }
            else if (keycode == Input.Keys.ENTER) {
                rootMenu = false;
                moveMenu = true;
                Utils.menuIcon.setPosition(50,200);
            }
        }
        else if(moveMenu){
            if (keycode == Input.Keys.UP) {
                if (moveIndex % 4 == 0) {
                    moveIndex--;
                    MainClass.combatScreen.movePane -= 1;   //Switch view to the next 4 Moves in the list.
                    Utils.menuIcon.setPosition(50,65);     //Reset menu icon back to original position
                }
                else {
                    moveIndex--;
                    Utils.menuIcon.translateY(45);         //Translate menu icon down to next move in list.
                }
            }
            else if (keycode == Input.Keys.DOWN) {
                if ((moveIndex + 1) % 4 == 0) {
                    moveIndex++;
                    MainClass.combatScreen.movePane += 1;   //Switch view to the next 4 Moves in the list.
                    Utils.menuIcon.setPosition(50,200);     //Reset menu icon back to original position
                }
                else {
                    moveIndex++;
                    Utils.menuIcon.translateY(-45);         //Translate menu icon down to next move in list.
                }
            }
            else if (keycode == Input.Keys.ENTER) {
                Utils.menuIcon.setPosition(50,50);
            }
            else if(keycode == Input.Keys.ESCAPE){
                moveMenu = false;
                rootMenu = true;
                MainClass.combatScreen.movePane = 0;
                moveIndex = 0;
                Utils.menuIcon.setPosition(50,200);
            }
        }
        else if(itemMenu){
            if (keycode == Input.Keys.UP) {
                Utils.menuIcon.translateY(50);
            }
            else if (keycode == Input.Keys.DOWN) {
                Utils.menuIcon.translateY(-50);
            }
            else if (keycode == Input.Keys.ENTER) {
                Utils.menuIcon.setPosition(50,50);
            }
            else if(keycode == Input.Keys.ESCAPE){
                Utils.menuIcon.setPosition(50,200);
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
