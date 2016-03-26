package com.superschmalgames.Utilities;

//This class handles the logic that controls the game combat.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class CombatLogic {

    //Need some logic to have NPC randomly pick a move in his arsenal.
    //
    //Have a boolean or some variable in the hero class that tracks if we have a shield up, a defense boost,
    //etc. Using an item that activates one of those effects will simply switch the bool to true, then we can check it
    //here to see if we need to reduce/avoid damage that would've been done by the enemy. ACTUALLY, we can have just a
    //double that acts as a multiplier for incoming damage. It's normally set to 1.0, but damage reducers will sub like
    //0.5 from that, and shields will subtract the full 1.0 for some number of turns.
    //
    //Check a string in combatinputhandler to see if the item used boosts health or is a defense/shield item. In combatscreen,
    //only show the health update if a health boost item was used.
    //
    //Use a string to display the Enemy's winning String if the Hero loses the fight.
    //
    //Ensure that only creating a single combat screen won't break any of my previous logic.


    //The base damage done by a move. Damage is calculated using hero stats.
    public double heroBaseDmg, enemyBaseDmg, heroHeal;

    //Boolean used to determine when we're transitioning from one state to another.
    public boolean transition, hDone, eDone, hWin, eWin, move;

    //Enumeration of possible combat states.
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

    //Constructor.
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
        Utils.font_small.setColor(Color.BLUE);
        Utils.font_medsmall.setColor(Color.BLUE);

        //Initialize current_state to be player turn whenever new combat starts.
        CURRENT_STATE = combat_state.PLAYER_TURN;
        transition = false;
        hDone = false;
        eDone = false;
        hWin = false;
        eWin = false;
    }

    //Method gets called every time the Hero makes a Move or uses an Item. Handles flow of logic, transition between
    //Hero and Enemy turn, and overall combat outcome.
    public void execCombat(){

        //Tell the combat screen to display transition to next combat state, and restrict player input during transition.
        MainClass.combatInputHandler.playerControl = false;
        transition = true;

        //Series of states to control combat logic flow.
        if(CURRENT_STATE == combat_state.PLAYER_TURN){

            //Set state to enemy turn.
            CURRENT_STATE = combat_state.ENEMY_TURN;

            //If enemy life drops to 0, set state to player win.
            if(MainClass.hero.lastEnemy.enemyLife - heroBaseDmg <= 0){
                CURRENT_STATE = combat_state.PLAYER_WIN;
            }

            //Signify we successfully completed the Hero's part of this combat round.
            hDone = true;
        }
        if(CURRENT_STATE == combat_state.ENEMY_TURN){

            //Some logic for randomly picking moves.
            enemyBaseDmg = MainClass.hero.lastEnemy.attacks[0].use(heroStats);

            //Set the enemy's combat string that will be displayed on the combat screen.
            MainClass.combatScreen.eMovDesc = "Enemy used " + MainClass.hero.lastEnemy.attacks[0].getMoveName() + "!";

            //Set state back to player turn.
            CURRENT_STATE = combat_state.PLAYER_TURN;

            //If Hero GPA drops to 0, set state to player lose.
            if(MainClass.hero.GPA - enemyBaseDmg <= 0){
                CURRENT_STATE = combat_state.PLAYER_LOSE;
            }

            //Signify we successfully completed the Enemy's part of this round of combat.
            eDone = true;
        }
        if(CURRENT_STATE == combat_state.PLAYER_WIN){

            //Some stuff handling the player winning, getting exp, etc.
            MainClass.hero.winCombat(MainClass.hero.lastEnemy.exp,MainClass.hero.lastEnemy.money);

            //Set the combat ending text.
            MainClass.combatScreen.combatEndScript = "Experience gained: "+ MainClass.hero.lastEnemy.exp +
                    "\n\nGatorbucks earned: " + MainClass.hero.lastEnemy.money;

            //Set the Enemy's ending combat script to their losing script.
            MainClass.combatScreen.enemyScript = "Enemy: " + MainClass.hero.lastEnemy.lose_script;

            //Display a message that we won the battle.
            MainClass.combatScreen.eMovDesc = MainClass.hero.name + " won the battle!";

            //Signify the Hero has won and we need to end combat.
            hWin = true;
        }
        if(CURRENT_STATE == combat_state.PLAYER_LOSE){

            //Change the description to inform the play the Hero lost the battle.
            MainClass.combatScreen.description = MainClass.hero.name + " was defeated!";

            //Set the combat ending text.
            MainClass.combatScreen.combatEndScript = "The stress of your low GPA makes you pass out!";

            //Set the Enemy's ending combat script to their winning script.
            MainClass.combatScreen.enemyScript = "Enemy: " + MainClass.hero.lastEnemy.win_script;

            //Signify the Enemy has won and we need to end combat.
            eWin = true;
        }
    }

    //Method to update te amount of health the Hero has.
    public void updateHero(int timesCalled){
        //If this is the second time we've called this, we know we can sub enemy dmg from hero health. Otherwise, we
        //just update the amount of health shown on screen to show health boost if we used an item.
        if(timesCalled == 2) {
            //Subtract Enemy damage from Hero life
            MainClass.hero.GPA -= enemyBaseDmg;
            enemyBaseDmg = 0; //Zero this out so we don't accidentally subtract too much health.
        }

        //Update the Hero life displayed to the screen.
        if(MainClass.hero.GPA <= 0.0){
            MainClass.combatScreen.heroLife = "Your GPA: 0.0";
        }
        else
            MainClass.combatScreen.heroLife = "Your GPA: " + Utils.df1.format(MainClass.hero.GPA);
    }

    //Method to update the amount of health the Enemy has.
    public void updateEnemy(){
        //Subtract Hero damage from Enemy life
        MainClass.hero.lastEnemy.enemyLife -= heroBaseDmg;
        heroBaseDmg = 0;

        //Update the enemy life displayed to the screen.
        if(MainClass.hero.lastEnemy.enemyLife <= 0){
            MainClass.combatScreen.enemyLife = "Assignments: 0";
        }
        else
            MainClass.combatScreen.enemyLife = "Assignments: " + MainClass.hero.lastEnemy.enemyLife;
    }

    //Method to set the necessary variables to display the additions/subtractions to character health in red/green
    public void healthChanges(char turn){
        //If we're showing updates immediately after the Hero's turn.
        if(turn == 'h'){
            //If Hero used an attack, show the drop in Enemy's health in red, beneath Enemy's remaining life.
            if(move){
                Utils.font_small.setColor(Color.RED);
                MainClass.combatScreen.healthChangeXPos = Utils.GAME_SCREEN_WIDTH - 85;
                MainClass.combatScreen.healthChangeDesc = "-" + heroBaseDmg;
            }
            //If hero used an Item, show boost in Hero's health in green, beneath Hero's remaining life.
            else{
                Utils.font_small.setColor(Color.GREEN);
                MainClass.combatScreen.healthChangeXPos = 255;
                MainClass.combatScreen.healthChangeDesc = "+" + heroHeal;
            }
        }
        //If we're showing updates immediately after the Enemy's turn
        else if(turn == 'e'){
            //Show the drop in Hero's health in red, beneath Hero's remaining life.
            Utils.font_small.setColor(Color.RED);
            MainClass.combatScreen.healthChangeXPos = 255;
            MainClass.combatScreen.healthChangeDesc = "-" + enemyBaseDmg;
        }
    }

    //Method to handle cleaning up and resetting the last few necessary things before combat fully exits.
    public void exitCombat(){
        //If we lost the fight.
        if(eWin){
            //Keep the enemy from being triggered so we'll fight him again.
            MainClass.hero.lastEnemy.triggered = false;

            //Heal Hero back to 4.0.
            MainClass.hero.GPA = 4.0;

            //Transport hero back to his dorm room.
            MainClass.gameScreen.setMap(Utils.dorm, Utils.start_x, Utils.start_y, 5);
            MainClass.openWorldScreen.camera.position.set(2700f,830f,0f);
        }

        //Stop the combat music.
        Utils.combatScreenMusic.stop();

        //Reset font_small color to white.
        Utils.font_small.setColor(Color.WHITE);

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
