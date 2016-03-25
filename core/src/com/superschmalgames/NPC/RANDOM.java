package com.superschmalgames.NPC;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.superschmalgames.Hero.E_Move;
import com.superschmalgames.Screens.CombatScreen;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

/**
 * Created by Cory on 3/23/2016.
 */
public class RANDOM extends ENEMY {
    public RANDOM(char dir, String s, String win, String lose, String sprite, String combat, int x, int y, int h, int origH, E_Move[] a, int e, int m) {
        super(dir, s, win, lose, sprite, combat, x, y, h, origH, a, e, m);
    }

    public void combat() {
        MainClass.gameScreen.lWalk = false;
        MainClass.gameScreen.rWalk = false;
        MainClass.gameScreen.uWalk = false;
        MainClass.gameScreen.dWalk = false;
        
        MainClass.hero.lastEnemy = this;
        //Give input control to the combat input handler.
        Gdx.input.setInputProcessor(MainClass.combatInputHandler);

        //Create combat screen and set is as the current screen.
        MainClass.combatScreen = new CombatScreen();
        ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.combatScreen);
    }
}