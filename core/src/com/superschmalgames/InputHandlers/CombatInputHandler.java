package com.superschmalgames.InputHandlers;

//Class to handle input from player during combat scenarios.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;


public class CombatInputHandler implements InputProcessor{

    public boolean rootMenu, moveMenu, itemMenu;
    public int index;

    public CombatInputHandler(){

    }

    @Override
    public boolean keyDown(int keycode) {
        if(rootMenu) {
            if (keycode == Input.Keys.UP && index > 0) {
                Utils.menuIcon.translateY(65);
                index--;
            }
            else if (keycode == Input.Keys.DOWN && index < 1) {
                Utils.menuIcon.translateY(-65);
                index++;
            }
            else if (keycode == Input.Keys.ENTER) {
                if(index == 0) {
                    rootMenu = false;
                    moveMenu = true;
                    Utils.menuIcon.setPosition(50, 200);
                }
                else{
                    rootMenu = false;
                    itemMenu = true;
                    index = 0;
                    Utils.menuIcon.setPosition(50, 200);
                    //Gdx.app.log("test","Items dont work yet!\n");
                }
            }
        }
        else if(moveMenu){
            if (keycode == Input.Keys.UP && index > 0) {    //If UP is pressed and we're not at the top of the list.
                if (index % 4 == 0) {
                    index--;
                    MainClass.combatScreen.movePane -= 1;   //Switch view to the next 4 Moves in the list.
                    Utils.menuIcon.setPosition(50,65);      //Reset menu icon back to original position.
                }
                else {
                    index--;
                    Utils.menuIcon.translateY(45);         //Translate menu icon up to previous Move in list.
                }
            }
            else if (keycode == Input.Keys.DOWN && index < MainClass.hero.moves.getNum()-1) {
                if ((index + 1) % 4 == 0) {
                    index++;
                    MainClass.combatScreen.movePane += 1;   //Switch view to the next 4 Moves in the list.
                    Utils.menuIcon.setPosition(50,200);     //Reset menu icon back to original position.
                }
                else {
                    index++;
                    Utils.menuIcon.translateY(-45);         //Translate menu icon down to next move in list.
                }
            }
            else if (keycode == Input.Keys.ENTER) {
                Gdx.app.log("test", "Moves don't work yet!\n");  //Pressing ENTER will use the selected Move.
            }
            else if(keycode == Input.Keys.ESCAPE){
                moveMenu = false;                             //Reset the flags to root being true.
                rootMenu = true;
                MainClass.combatScreen.movePane = 0;         //Reset our move panes and index for next time.
                index = 0;
                Utils.menuIcon.setPosition(50,200);          //Reset the menu icon to the top of the list.
            }
        }
        else if(itemMenu){
            if (keycode == Input.Keys.UP && index > 0) {
                if (index % 4 == 0) {
                    index--;
                    MainClass.combatScreen.itemPane -= 1;   //Switch view to the next 4 Items in the list.
                    Utils.menuIcon.setPosition(50, 65);     //Reset menu icon back to bottom position of previous pane.
                }
                else {
                    index--;
                    Utils.menuIcon.translateY(45);          //Translate menu icon down to next Item in list.
                }
            }
            else if (keycode == Input.Keys.DOWN && index < (MainClass.hero.inventory.getNum('c')-1)) {
                if ((index + 1) % 4 == 0) {
                    index++;
                    MainClass.combatScreen.itemPane += 1;   //Switch view to the next 4 Items in the list.
                    Utils.menuIcon.setPosition(50,200);     //Reset menu icon back to original position
                }
                else {
                    index++;
                    Utils.menuIcon.translateY(-45);         //Translate menu icon down to next Item in list.
                }
            }
            else if (keycode == Input.Keys.ENTER) {
                Gdx.app.log("test","Items dont work yet!\n");
            }
            else if(keycode == Input.Keys.ESCAPE){
                itemMenu = false;
                rootMenu = true;
                MainClass.combatScreen.itemPane = 0;
                index = 0;
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
