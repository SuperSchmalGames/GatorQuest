package com.superschmalgames;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.superschmalgames.Hero.Move;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class ENEMY extends NPC {
    public double Software;
    public double Hardware;
    public double Writing;
    public double Endurance;
    public double Social;
    public double Math;
    public double Focus;
    Move[] attacks;
    String win_script;
    String lose_script;
    public ENEMY(char dir, String s, String win, String lose, String sprite, int x, int y, int sw, int h, int w, int e, int sc, int m, int f, Move[] a) {
        super(dir,s,sprite,x,y);
        Software = sw;
        Hardware = h;
        Writing = w;
        Endurance = e;
        Social = sc;
        Math = m;
        Focus = f;
        win_script = win;
        lose_script = lose;
        attacks = a;
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
        if(!triggered) {
            MainClass.gameScreen.window.dialog.setText(Utils.font_small, script, Color.BLUE, 480, 8, true);
        }
        else {
            MainClass.gameScreen.window.dialog.setText(Utils.font_small, win_script, Color.BLUE, 480, 8, true);
        }

        //Set certain parts of the dialogue window to certain values depending on the event type.
        MainClass.gameScreen.window.decLock = true;
        MainClass.gameScreen.window.decision = MainClass.gameScreen.window.ok;
        MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OK_X_OFFSET;
        MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OK_Y_OFFSET;

        //Set the position
        Utils.menuIcon.setPosition(MainClass.gameScreen.window.ICON_X_OFFSET, MainClass.gameScreen.window.ICON_Y_OFFSET);

        //Set the NPC's triggered field to true, since we'll have talked to him already.
        if (!triggered)  {
            triggered = true;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(0f, true);
        }
    }

    public void combat_result(boolean t) {
        triggered = t;
    }
}
