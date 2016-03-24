package com.superschmalgames.Utilities;

//This class handles the logic that controls the game combat.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class CombatLogic {

    //Also need: state machine for handling turns, logic for player victory, logic for player death, npc decision-making
    //logic, etc. Have a state TRANSITION that holds a loop running while things in the combat screen transition for a second
    //or two, then have combat screen change the state so that the loop breaks, allowing the next combat state to occur.
    //
    //Maybe make the removal/addition of health point their own methods. Calling in proper order could fix the issue
    //of having health update too quickly when trying to show that the hero used a restorative item.
    //
    //Have a boolean or some variable in the hero class that tracks if we have a shield up, a defense boost,
    //etc. Using an item that activates one of those effects will simply switch the bool to true, then we can check it
    //here to see if we need to reduce/avoid damage that would've been done by the enemy. ACTUALLY, we can have just a
    //double that acts as a multiplier for incoming damage. It's normally set to 1.0, but damage reducers will sub like
    //0.5 from that, and shields will subtract the full 1.0 for some number of turns.
    //
    //Have the amount of health lost/gained show up under the hero.enemy health in combat screen in green/red in order
    //to show what's happening to those stats.
    //
    //Use a string to display the Enemy's winning String if the Hero loses the fight.
    //
    //Make sure game doesn't crash if hitting enter on item list when we have no items.

    //The base damage done by a move. Damage is calculated using hero stats.
    public double heroBaseDmg, enemyBaseDmg;

    //Boolean used to determine when we're transitioning from one state to another.
    public boolean transition, hDone, eDone, hWin, eWin;

    public enum combat_state{
        PLAYER_TURN,          //Player makes a selection and the effects are applied.
        ENEMY_TURN,           //Enemy makes a selection and the effects are applied.
        PLAYER_WIN,           //Enemy health reaches zero. Victory effects are applied, move to exit_combat.
        PLAYER_LOSE,          //Hero health reaches zero. Loss effects are applied, move to exit_combat.
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
        transition = false;
        hDone = false;
        eDone = false;
        hWin = false;
        eWin = false;
    }

    public void execCombat(){
        //Tell the combat screen to display transition to next combat state, and restrict player input during transition.
        MainClass.combatInputHandler.playerControl = false;
        transition = true;

        //Series of states to control combat logic flow.
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

            //Signify we successfully completed the Hero's part of this combat round.
            hDone = true;
        }

        if(CURRENT_STATE == combat_state.ENEMY_TURN){
            //Some logic for randomly picking moves.
            enemyBaseDmg = MainClass.hero.lastEnemy.attacks[0].use(heroStats);
            MainClass.hero.GPA -= enemyBaseDmg;
            Gdx.app.log("Enemy Turn", "Move Selected: " + MainClass.hero.lastEnemy.attacks[0].getMoveName());
            Gdx.app.log("Enemy Damage Test", "Damage Done: "+ enemyBaseDmg);

            //Set the enemy's combat string that will be displayed on the combat screen.
            MainClass.combatScreen.eMovDesc = "Enemy used " + MainClass.hero.lastEnemy.attacks[0].getMoveName() + "!";

            //Set state back to player turn.
            CURRENT_STATE = combat_state.PLAYER_TURN;

            //If Hero GPA drops to 0, set state to player lose.
            if(MainClass.hero.GPA <= 0){
                CURRENT_STATE = combat_state.PLAYER_LOSE;
            }

            //Signify we successfully completed the Enemy's part of this round of combat.
            eDone = true;
        }

        if(CURRENT_STATE == combat_state.PLAYER_WIN){
            //Some stuff handling the player winning, getting exp, etc.
            MainClass.hero.winCombat(MainClass.hero.lastEnemy.exp,MainClass.hero.lastEnemy.money);

            //Display a message that we won the battle.
            MainClass.combatScreen.eMovDesc = MainClass.hero.name + " won the battle!";

            //Signify the Hero has won and we need to end combat.
            hWin = true;

            Gdx.app.log("Combat Finish", "Gratz, we won!");
        }
        if(CURRENT_STATE == combat_state.PLAYER_LOSE){
            //Some stuff for player "passing out", relocating to dorm, healing back to 4.0, etc.
            MainClass.combatScreen.description = MainClass.hero.name + " was defeated!";

            //Signify the Enemy has won and we need to end combat.
            eWin = true;

            //Heal Hero back to 4.0.
            MainClass.hero.GPA = 4.0;

            //Transport hero back to his dorm room.
            MainClass.gameScreen.setMap(Utils.dorm, Utils.start_x, Utils.start_y, 5);
            MainClass.openWorldScreen.camera.position.set(2700f,830f,0f);

            Gdx.app.log("Combat Finish", "Boo, we lost!");
        }
    }

    public void updateHero(){
        //Update the Hero life displayed to the screen.
        if(MainClass.hero.GPA <= 0.0){
            MainClass.combatScreen.heroLife = "Your GPA: 0.0";
        }
        else
            MainClass.combatScreen.heroLife = "Your GPA: " + Utils.df1.format(MainClass.hero.GPA);
    }

    public void updateEnemy(){
        //Update the enemy life displayed to the screen.
        if(MainClass.hero.lastEnemy.enemyLife <= 0){
            MainClass.combatScreen.enemyLife = "Assignments: 0";
        }
        else
            MainClass.combatScreen.enemyLife = "Assignments: " + MainClass.hero.lastEnemy.enemyLife;
    }

    public void exitCombat(){
        //Stop the combat music.
        Utils.combatScreenMusic.stop();

        //Lock player input (within the CombatInputHandler) until next fight starts.
        MainClass.combatInputHandler.playerControl = false;

        //Reset the enemy back to his original position.
        MainClass.hero.lastEnemy.reset();

        //Give control back to the main input handler, and switch back to GameScreen.
        Gdx.input.setInputProcessor(MainClass.inputHandler);
        MainClass.hero.canMove = true;
        ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
    }
}
