package com.superschmalgames.Utilities;

//This class handles the logic that controls the game combat.

import com.badlogic.gdx.graphics.Color;

public class CombatLogic {

    //Also need: state machine for handling turns, logic for player victory, logic for player death, npc decision-making
    //logic, etc. Have a boolean or some variable in the hero class that tracks if we have a shield up, a defense boost,
    //etc. Using an item that activates one of those effects will simply switch the bool to true, then we can check it
    //here to see if we need to reduce/avoid damage that would've been done by the enemy.

    //The base damage done by a move. Damage is calculated using hero stats.
    public double heroBaseDmg;

    public enum combat_state{
        PLAYER_TURN,
        ENEMY_TURN,
        PLAYER_WIN,
        PLAYER_LOSE
    }
    public combat_state CURRENT_STATE;

    //Array of hero's current stats, including buffs from items equipped. These stats are used to calculate base move dmg.
    public double[] stats = {MainClass.hero.Software_buf,MainClass.hero.Hardware_buf,MainClass.hero.Writing_buf,
            MainClass.hero.Endurance_buf,MainClass.hero.Social_buf,MainClass.hero.Math_buf,MainClass.hero.Focus_buf};

    public CombatLogic(){
        //Set the menu icon to the proper color and starting place.
        Utils.menuIcon.setPosition(50,200);
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(3.0f);

        MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";

        //Initialize the amount of life the hero and enemy have.
        MainClass.combatScreen.heroLife = "Your GPA: " + MainClass.hero.GPA;
        MainClass.combatScreen.enemyLife = "Assignments: " + MainClass.hero.lastEnemy.enemyLife;

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
                CURRENT_STATE = combat_state.PLAYER_WIN;
            }
        }
        if(CURRENT_STATE == combat_state.ENEMY_TURN){
            //some logic for randomly picking moves

            //Set state back to player turn.
            CURRENT_STATE = combat_state.PLAYER_TURN;

            //If Hero GPA drops to 0, set state to player lose.
            if(MainClass.hero.GPA <= 0){
                CURRENT_STATE = combat_state.PLAYER_LOSE;
            }
        }
        if(CURRENT_STATE == combat_state.PLAYER_WIN){
            //some stuff handling the player winning, getting exp, etc
        }
        if(CURRENT_STATE == combat_state.PLAYER_LOSE){
            //some stuff for player "passing out", relocating to dorm, healing back to 4.0, etc.
        }
    }
}
