package com.superschmalgames.NPC;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.superschmalgames.Hero.E_Move;
import com.superschmalgames.Hero.H_Move;
import com.superschmalgames.Screens.CombatScreen;
import com.superschmalgames.Utilities.Animator;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class ENEMY extends NPC {

    public E_Move[] attacks;
    public H_Move[] weakness;
    public String win_script;
    public String lose_script;
    public String name;
    public int enemyLife, exp, money, origLife;
    //Sprite that's shown for the hero during combat.
    public Sprite combatSprite;

    public ENEMY(char dir, String s, String win, String lose, String sprite, String combat, int x, int y, int h, E_Move[] a, H_Move[] b, int e, int m) {
        super(dir,s,sprite,x,y);
        win_script = win;
        lose_script = lose;
        attacks = a;
        weakness= b;
        enemyLife = h;
        origLife = h;
        exp = e;
        money = m;
        combatSprite = new Sprite(new Texture(combat));
        name = "Professor";
    }

    public void initiate(){

        //Set the dialogue flag true so the window will render to the game screen.
        MainClass.gameScreen.dial = true;

        //Set this as the last Enemy the Hero has interacted with.
        MainClass.hero.lastEnemy = this;

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
            MainClass.gameScreen.window.dialog.setText(Utils.font_small, lose_script, Color.BLUE, 480, 8, true);
        }

        //Set certain parts of the dialogue window to certain values depending on the event type.
        MainClass.gameScreen.window.decLock = true;
        MainClass.gameScreen.window.enemy = true;
        MainClass.gameScreen.window.decision = MainClass.gameScreen.window.ok;
        MainClass.gameScreen.window.decOffsetX = MainClass.gameScreen.window.OK_X_OFFSET;
        MainClass.gameScreen.window.decOffsetY = MainClass.gameScreen.window.OK_Y_OFFSET;

        //Reset info for the menuIcon to use for enemy dialogue windows
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(1.5f);

        //Set the position
        Utils.menuIcon.setPosition(MainClass.gameScreen.window.ICON_X_OFFSET, MainClass.gameScreen.window.ICON_Y_OFFSET);

        //Set the NPC's triggered field to true, since we'll have talked to him already.
        if (!triggered)  {
            walk.currentFrame = walk.walkAnimation.getKeyFrame(0f, true);
        }
    }

    public void combat_result(boolean t) {
        triggered = t;
    }

    public void reset() {
        x_pos = org_x;
        y_pos = org_y;
        MainClass.hero.lastEnemy.enemyLife = MainClass.hero.lastEnemy.origLife;
        MainClass.combatScreen.dispose();
    }

    public void combat() {
        if(MainClass.gameScreen.window.proceed && !triggered){
            triggered = true;

            //Give input control to the combat input handler.
            Gdx.input.setInputProcessor(MainClass.combatInputHandler);

            //Create combat screen and set is as the current screen.
            MainClass.combatScreen = new CombatScreen();
            ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.combatScreen);
        }
        else{
            //Give control back to the main input handler if we're not fighting.
            Gdx.input.setInputProcessor(MainClass.inputHandler);
        }
    }
}
