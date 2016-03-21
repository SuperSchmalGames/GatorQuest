package com.superschmalgames.Utilities;

//This class handles the logic that controls the game combat.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class CombatLogic {

    //Also need: state machine for handling turns, logic for player victory, logic for player death, npc decision-making
    //logic, etc. Have a boolean or some variable in the hero class that tracks if we have a shield up, a defense boost,
    //etc. Using an item that activates one of those effects will simply switch the bool to true, then we can check it
    //here to see if we need to reduce/avoid damage that would've been done by the enemy. Have some flag that controls
    //if the player can make a selection (use it to prevent button presses during combat transitions/animations).

    //The base damage done by a move. Damage is calculated using hero stats.
    public double heroBaseDmg, enemyBaseDmg;

    public enum combat_state{
        PLAYER_TURN,
        ENEMY_TURN,
        PLAYER_WIN,
        PLAYER_LOSE,
        EXIT_COMBAT
    }
    public combat_state CURRENT_STATE;

    //Array of hero's current stats, including buffs from items equipped. These stats are used to calculate base move dmg.
    public double[] heroStats = {MainClass.hero.Software_buf,MainClass.hero.Hardware_buf,MainClass.hero.Writing_buf,
            MainClass.hero.Endurance_buf,MainClass.hero.Social_buf,MainClass.hero.Math_buf,MainClass.hero.Focus_buf};

    public CombatLogic(){
        //Set the menu icon to the proper color and starting place.
        Utils.menuIcon.setPosition(40,200);
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(3.0f);

        MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";

        //Initialize the amount of life the hero and enemy have.
        MainClass.combatScreen.heroLife = "Your GPA: " + Utils.df1.format(MainClass.hero.GPA);
        MainClass.combatScreen.enemyLife = "Assignments: " + MainClass.hero.lastEnemy.enemyLife;
        heroBaseDmg = 0;
        enemyBaseDmg = 0;

        //Every time the combat is entered, reinitialize these control variables for the combat input handler.
        MainClass.combatInputHandler.rootMenu = true;
        MainClass.combatInputHandler.moveMenu = false;
        MainClass.combatInputHandler.itemMenu = false;

        //Initialize some control variables.
        MainClass.combatScreen.movePane = 0;
        MainClass.combatScreen.itemPane = 0;
        MainClass.combatScreen.p2index = 0;
        MainClass.combatScreen.temp = 0;
        MainClass.combatInputHandler.index = 0;
        MainClass.combatInputHandler.selection = 0;

        //Change font color to blue
        Utils.font.setColor(Color.BLUE);
        Utils.font_medsmall.setColor(Color.BLUE);

        //Initialize current_state to be player turn whenever new combat starts.
        CURRENT_STATE = combat_state.PLAYER_TURN;
    }

    public void execCombat(){
        if(CURRENT_STATE == combat_state.PLAYER_TURN){
            //Subtract health from enemy equal to hero damage.
            MainClass.hero.lastEnemy.enemyLife -= heroBaseDmg;

            //Reset hero damage for next turn.
            heroBaseDmg = 0;

            //Set state to enemy turn.
            CURRENT_STATE = combat_state.ENEMY_TURN;

            //If enemy life drops to 0, set state to player win.
            if(MainClass.hero.lastEnemy.enemyLife <= 0){
                MainClass.combatScreen.enemyLife = "Assignments: 0";
                CURRENT_STATE = combat_state.PLAYER_WIN;
            }

            //Update the Hero life displayed to the screen.
            MainClass.combatScreen.heroLife = "Your GPA: " + Utils.df1.format(MainClass.hero.GPA);    //MainClass.hero.GPA
            //Update the enemy life displayed to the screen.
            MainClass.combatScreen.enemyLife = "Assignments: " + MainClass.hero.lastEnemy.enemyLife;
        }
        if(CURRENT_STATE == combat_state.ENEMY_TURN){
            //Some logic for randomly picking moves.
            enemyBaseDmg = MainClass.hero.lastEnemy.attacks[0].use(heroStats);
            MainClass.hero.GPA -= enemyBaseDmg;
            Gdx.app.log("Enemy Turn", "Move Selected :" + MainClass.hero.lastEnemy.attacks[0].getMoveName());
            Gdx.app.log("Enemy Damage Test", "Damage Done: "+ enemyBaseDmg);

            //Set state back to player turn.
            CURRENT_STATE = combat_state.PLAYER_TURN;

            //If Hero GPA drops to 0, set state to player lose.
            if(MainClass.hero.GPA <= 0){
                MainClass.combatScreen.heroLife = "Your GPA: 0.0";
                CURRENT_STATE = combat_state.PLAYER_LOSE;
            }

            //Update the Hero life displayed to the screen.
            MainClass.combatScreen.heroLife = "Your GPA: " + Utils.df1.format(MainClass.hero.GPA);
        }
        if(CURRENT_STATE == combat_state.PLAYER_WIN){
            //Some stuff handling the player winning, getting exp, etc.
            Gdx.app.log("Combat Finish", "Gratz, we won!");
            CURRENT_STATE = combat_state.EXIT_COMBAT;
        }
        if(CURRENT_STATE == combat_state.PLAYER_LOSE){
            //Some stuff for player "passing out", relocating to dorm, healing back to 4.0, etc.
            Gdx.app.log("Combat Finish", "Boo, we lost!");
            MainClass.hero.GPA = 4.0;
            CURRENT_STATE = combat_state.EXIT_COMBAT;
        }
        if(CURRENT_STATE == combat_state.EXIT_COMBAT){
            //Stop the combat music.
            Utils.combatScreenMusic.stop();

            //Reset the enemy back to his original position.
            MainClass.hero.lastEnemy.reset();

            //Give control back to the main input handler, and switch back to GameScreen.
            Gdx.input.setInputProcessor(MainClass.inputHandler);
            ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
        }
    }
}
