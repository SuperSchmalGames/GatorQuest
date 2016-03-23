package com.superschmalgames.NPC;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class REST extends NPC{
    public REST(char dir, String s, String sprite, int x, int y) {
        super(dir,s,sprite,x,y);
    }

    public void initiate() {
        //Set the dialogue flag true so the window will render to the game screen.
        MainClass.gameScreen.dial = true;

        //Set this as the last friendly NPC the Hero has interacted with.
        MainClass.hero.lastNPC = this;

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

        //Set the position
        Utils.menuIcon.setPosition(MainClass.gameScreen.window.ICON_X_OFFSET, MainClass.gameScreen.window.ICON_Y_OFFSET);

        //heal hero
        MainClass.hero.fillGPA();
    }
}
