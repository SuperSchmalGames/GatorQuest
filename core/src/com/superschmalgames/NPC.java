package com.superschmalgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.superschmalgames.Utilities.Animator;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.superschmalgames.Utilities.ShopMenu;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class NPC {

    public Animator walk;
    public char direction;
    //used for movement
    public int x_pos,y_pos;
    //used to reset position
    public int org_x, org_y;
    public boolean triggered;
    public String script;
    public boolean los;

    //NPCs will only move in one direction if any
    public NPC(char dir, String s, String sprite, int x, int y) {
        x_pos = x;
        org_x = x;
        y_pos = y;
        org_y = y;
        script = s;
        direction = dir;
        walk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_" + sprite + ".png", 0.17f);
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        triggered = false;
        los = true;
    }

    //move to players location.
    public void move(float delta, float x, float y) {
        boolean temp = false;
        switch(direction) {
            case 'l':
                if (x_pos > x + MainClass.hero.width/2) {
                    x_pos -= Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateDialogue(0);
                break;
            case 'r':
                if (x_pos < x - 3*MainClass.hero.width/2) {
                    x_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateDialogue(0);
                break;
            case 'u':
                if (y_pos < y - 3*MainClass.hero.height/2) {
                    y_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateDialogue(0);
                break;
            case 'd':
                if (y_pos > y + MainClass.hero.height/2) {
                    y_pos -= Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateDialogue(0);
                break;
        }
        if(temp) {
            walk.stateTime += delta;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        }
    }

    public void reset() {
        x_pos = org_x;
        y_pos = org_y;
    }

    public String getScript() {
        return script;
    }

    public boolean getTriggered() {
        return triggered;
    }

    public void setTriggered(boolean set) {
        triggered = set;
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
        MainClass.gameScreen.shop_window.decision = MainClass.gameScreen.shop_window.okNo;
        MainClass.gameScreen.shop_window.decOffsetX = MainClass.gameScreen.shop_window.OKNO_X_OFFSET;
        MainClass.gameScreen.shop_window.decOffsetY = MainClass.gameScreen.shop_window.OKNO_Y_OFFSET;

        Utils.menuIcon.setPosition(MainClass.gameScreen.shop_window.ICON_X_OFFSET, MainClass.gameScreen.shop_window.ICON_Y_OFFSET);

    }

    public void initiateDialogue(int event){

        //Set the dialogue flag true so the window will render to the game screen.
        MainClass.gameScreen.dial = true;

        //Stop character movement, if we're moving.
        MainClass.gameScreen.lWalk = false;
        MainClass.gameScreen.rWalk = false;
        MainClass.gameScreen.uWalk = false;
        MainClass.gameScreen.dWalk = false;

        //Give input control to the dialogue input handler.
        Gdx.input.setInputProcessor(MainClass.dialogueInputHandler);

        //Create new dialogue window containing the dialogue of the NPC we're talking to.
        MainClass.gameScreen.window = new CharacterDialogue();    //480=text block width, 8=left align, true=wrap
        MainClass.gameScreen.window.dialog.setText(Utils.font_small, script, Color.BLUE, 480, 8, true);

        //Set certain parts of the dialogue window to certain values depending on the event type.
        switch(event) {
            //NPC Event
            case 0:
                MainClass.gameScreen.window.decLock = true;
                MainClass.gameScreen.window.decision = MainClass.gameScreen.window.ok;
                MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OK_X_OFFSET;
                MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OK_Y_OFFSET;
                break;
            //Shop Event
            case 1:
                MainClass.gameScreen.window.decLock = false;
                MainClass.gameScreen.window.decision = MainClass.gameScreen.window.okNo;
                MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OKNO_X_OFFSET;
                MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OKNO_Y_OFFSET;
                break;
            //Boss Event
            case 2:
                MainClass.gameScreen.window.decLock = false;
                MainClass.gameScreen.window.decision = MainClass.gameScreen.window.okNo;
                MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OKNO_X_OFFSET;
                MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OKNO_Y_OFFSET;
                break;
        }

        //Set the position
        Utils.menuIcon.setPosition(MainClass.gameScreen.window.ICON_X_OFFSET, MainClass.gameScreen.window.ICON_Y_OFFSET);

        //Set the NPC's triggered field to true, since we'll have talked to him already.
        if (!triggered)  {
            triggered = true;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(0f, true);
        }
    }

    public void initiateCombat() {

        if (!triggered)  {
            triggered = true;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(0f, true);
        }
        reset();
    }
}
