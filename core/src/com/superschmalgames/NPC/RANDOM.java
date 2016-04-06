package com.superschmalgames.NPC;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.superschmalgames.Hero.E_Move;
import com.superschmalgames.Hero.H_Move;
import com.superschmalgames.Screens.CombatScreen;
import com.superschmalgames.Utilities.MainClass;


public class RANDOM extends ENEMY {
    public RANDOM(char dir, String s, String win, String lose, String sprite, String combat, int x, int y, int h, E_Move[] a, H_Move[] b, int e, int m) {
        super(dir, s, win, lose, sprite, combat, x, y, h, a, b, e, m);
        name = "T.A.";
        lose_script = "Congratz, it feels like you understand the concept now!";
        win_script = "Guess we are going to need to do some more studying.";
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
