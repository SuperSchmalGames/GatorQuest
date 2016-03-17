package com.superschmalgames.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    //Sprite that's shown for the hero during combat.
    public Sprite combatSprite;

    //NPCs will only move in one direction if any
    public NPC(char dir, String s, String sprite, int x, int y) {
        x_pos = x;
        org_x = x;
        y_pos = y;
        org_y = y;
        script = s;
        direction = dir;
        walk = new Animator(4, 1, sprite, 0.17f);
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        triggered = false;
        los = true;

        initCombatSprite(sprite);
    }

    public void initCombatSprite(String sprite){
        char sNum;
        String dNum, tSprite;

        //If the char at string.length-6 is a number, we know to grab length-6 and length-5 for the sprite number,
        //otherwise just use length-5 for single digit number.
        if(( (int)sprite.charAt(sprite.length() - 6)) > 47 && (int)sprite.charAt(sprite.length() - 6) < 58){
            dNum = sprite.substring(sprite.length() - 6, sprite.length() - 4);
            tSprite = "visuals/sprite_sheets/sprite_walk_l" + dNum + ".png";
            Gdx.app.log("NPC sprite substring", "Sprite number = " + dNum);
        }
        else{
            sNum = sprite.charAt(sprite.length() - 5);
            tSprite = "visuals/sprite_sheets/sprite_walk_l" + sNum + ".png";
            Gdx.app.log("NPC sprite char", "Sprite number = " + sNum);
        }

        //Create a new animator using the new sprite string, then grab the first frame of it, which will be the NPC,
        //standing still, facing left.
        Animator tWalk = new Animator(4, 1, tSprite, 0.17f);
        tWalk.currentFrame = tWalk.walkAnimation.getKeyFrame(tWalk.stateTime, true);
        combatSprite = new Sprite(tWalk.currentFrame);

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
                    initiate();
                break;
            case 'r':
                if (x_pos < x - 3*MainClass.hero.width/2) {
                    x_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiate();
                break;
            case 'u':
                if (y_pos < y - 3*MainClass.hero.height/2) {
                    y_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiate();
                break;
            case 'd':
                if (y_pos > y + MainClass.hero.height/2) {
                    y_pos -= Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiate();
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

    public void initiate(){

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
        MainClass.gameScreen.window.decLock = true;
        MainClass.gameScreen.window.decision = MainClass.gameScreen.window.ok;
        MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OK_X_OFFSET;
        MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OK_Y_OFFSET;

        //Reset info for the menuIcon to use for npc dialogue windows
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(1.5f);

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
