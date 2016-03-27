package com.superschmalgames.InputHandlers;

//Class to handle input from player during combat scenarios.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.superschmalgames.Hero.H_Move;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;


public class CombatInputHandler implements InputProcessor{

    //Bools to tell which combat sub-menu we're currently in.
    public boolean rootMenu, moveMenu, itemMenu;
    public boolean crit_hit;

    //Indexes to tell what move/item we're selecting.
    public int index, selection;

    //Bool to allow player to make selections or lock input during combat transitions.
    public boolean playerControl;

    public CombatInputHandler(){
        playerControl = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        //If it's during transition, we can hit enter to move to the next thing being displayed.
        if(MainClass.combatLogic.transition && (MainClass.combatScreen.waitTime/(MainClass.combatScreen.cont+1)) > 2){
            if(keycode == Input.Keys.ENTER){
                MainClass.combatScreen.cont++;
                //MainClass.combatScreen.waitTime = 0;
            }
        }

        //If it's the player's turn, not during transition, and we have control to pick a Move/Item
        if(playerControl) {
            if (rootMenu) {
                if (keycode == Input.Keys.UP && index > 0) {
                    Utils.menuIcon.translateY(65);
                    index--;
                    MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";
                }
                else if (keycode == Input.Keys.DOWN && index < 1) {
                    Utils.menuIcon.translateY(-65);
                    index++;
                    MainClass.combatScreen.description = "Select a consumable \ninventory item to use.";
                }
                else if (keycode == Input.Keys.ENTER) {
                    if (index == 0) {
                        rootMenu = false;
                        moveMenu = true;
                        Utils.menuIcon.setPosition(50, 200);
                        MainClass.combatScreen.description = MainClass.hero.moves.attacks[MainClass.hero.moves.getCurrentMove()].getDescription();
                    }
                    else {
                        rootMenu = false;
                        itemMenu = true;
                        index = 0;
                        Utils.menuIcon.setPosition(50, 200);
                        MainClass.combatScreen.description = MainClass.hero.inventory.items.get(MainClass.hero.inventory.getCurrentItemIndex()).getItemDes();
                    }
                }
            }
            else if (moveMenu) {
                if (keycode == Input.Keys.UP && index > 0) {    //If UP is pressed and we're not at the top of the list.
                    if (index % 4 == 0) {
                        index--;
                        MainClass.combatScreen.movePane -= 1;   //Switch view to the next 4 Moves in the list.
                        Utils.menuIcon.setPosition(50, 65);      //Reset menu icon back to original position.
                    }
                    else {
                        index--;
                        Utils.menuIcon.translateY(45);         //Translate menu icon up to previous Move in list.
                    }
                    MainClass.combatScreen.description = MainClass.hero.moves.attacks[MainClass.hero.moves.getCurrentMove()].getDescription();
                }
                else if (keycode == Input.Keys.DOWN && index < MainClass.hero.moves.getNum() - 1) {
                    if ((index + 1) % 4 == 0) {
                        index++;
                        MainClass.combatScreen.movePane += 1;   //Switch view to the next 4 Moves in the list.
                        Utils.menuIcon.setPosition(50, 200);     //Reset menu icon back to original position.
                    }
                    else {
                        index++;
                        Utils.menuIcon.translateY(-45);         //Translate menu icon down to next move in list.
                    }
                    MainClass.combatScreen.description = MainClass.hero.moves.attacks[MainClass.hero.moves.getCurrentMove()].getDescription();
                }
                else if (keycode == Input.Keys.ENTER) {
                    //Let the combat logic handler know that we used a move, not an item.
                    MainClass.combatLogic.move = true;

                    //Pressing ENTER will choose to make the selected move.
                    selection = MainClass.hero.moves.getCurrentMove();

                    //We will make the call to use() here. The returned value will be assigned to a base damage variable
                    //contained in CombatLogic.
                    MainClass.combatLogic.heroBaseDmg = MainClass.hero.moves.attacks[selection].use(MainClass.combatLogic.heroStats);

                    //check for weakness:
                    for(H_Move move : MainClass.hero.lastEnemy.weakness) {
                        if (MainClass.hero.moves.attacks[selection].getMoveName().equals(move.getMoveName())) {
                            crit_hit = true;
                            break;
                        }
                        crit_hit = false;
                    }
                    if (crit_hit) {
                        MainClass.combatLogic.heroBaseDmg *= 2;
                        Gdx.app.log("Hero Damage Test", "Critical Damage Done: " + MainClass.combatLogic.heroBaseDmg);
                    }
                    else
                        Gdx.app.log("Hero Damage Test", "Damage Done: " + MainClass.combatLogic.heroBaseDmg);

                    //Set the hero's move string that will be displayed on the combat screen.
                    MainClass.combatScreen.hMovDesc = MainClass.hero.name + " used " + MainClass.hero.moves.attacks[selection].moveName + "!";
                    if(crit_hit){
                        MainClass.combatScreen.hMovDesc += " It's hella effective!";
                    }

                    //Once move has been selected, back us out to the root menu for our next turn.
                    moveMenu = false;                             //Reset the flags to root being true.
                    rootMenu = true;
                    MainClass.combatScreen.movePane = 0;         //Reset our move panes and index for next time.
                    index = 0;
                    Utils.menuIcon.setPosition(50, 200);          //Reset the menu icon to the top of the list.
                    MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";

                    //Everything is now ready, so start this round of combat. Control will return to the player once
                    //the enemy has made a move.
                    MainClass.combatLogic.execCombat();
                }
                else if (keycode == Input.Keys.ESCAPE) {
                    moveMenu = false;                             //Reset the flags to root being true.
                    rootMenu = true;
                    MainClass.combatScreen.movePane = 0;         //Reset our move panes and index for next time.
                    index = 0;
                    Utils.menuIcon.setPosition(50, 200);          //Reset the menu icon to the top of the list.
                    MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";
                }
            }
            else if (itemMenu) {
                if (keycode == Input.Keys.UP && index > 0) {
                    if (index % 4 == 0) {
                        index--;
                        MainClass.combatScreen.itemPane -= 1;   //Switch view to the next 4 Items in the list.
                        Utils.menuIcon.setPosition(50, 65);     //Reset menu icon back to bottom position of previous pane.
                    }
                    else {
                        index--;
                        Utils.menuIcon.translateY(45);          //Translate menu icon down to next Item in list.
                    }
                    MainClass.combatScreen.description = MainClass.hero.inventory.items.get(MainClass.hero.inventory.getCurrentItemIndex()).getItemDes();       //!!!!!!!Add item descriptions!!!!!!!!!!!!!!!!
                }
                else if (keycode == Input.Keys.DOWN && index < (MainClass.hero.inventory.getNum('c') - 1)) {
                    if ((index + 1) % 4 == 0) {
                        index++;
                        MainClass.combatScreen.itemPane += 1;   //Switch view to the next 4 Items in the list.
                        Utils.menuIcon.setPosition(50, 200);     //Reset menu icon back to original position
                    }
                    else {
                        index++;
                        Utils.menuIcon.translateY(-45);         //Translate menu icon down to next Item in list.
                    }
                    MainClass.combatScreen.description = MainClass.hero.inventory.items.get(MainClass.hero.inventory.getCurrentItemIndex()).getItemDes();       //!!!!!!!Add item descriptions!!!!!!!!!!!!!!!!
                }
                else if (keycode == Input.Keys.ENTER) {
                    //Only let player select an Item if there are actual Items to choose from.
                    if(MainClass.hero.inventory.getNum('c') > 0) {

                        //Let the combat logic handler know that we used an item, not a move.
                        MainClass.combatLogic.move = false;

                        //Get the actual array index of the Item we selected.
                        selection = MainClass.hero.inventory.getCurrentItemIndex();

                        //Make the call to activate the Item we've selected.
                        MainClass.hero.inventory.items.get(selection).activateItem();
                        MainClass.combatLogic.heroHeal = MainClass.hero.inventory.items.get(selection).getBoostAmt();

                        //Set the hero's move string that will be displayed on the combat screen.
                        MainClass.combatScreen.hMovDesc = MainClass.hero.name + " used a " + MainClass.hero.inventory.items.get(selection).getItemName() + "!";

                        //Once the item has been selected, back us out to the root menu for our next turn.
                        itemMenu = false;
                        rootMenu = true;
                        MainClass.combatScreen.itemPane = 0;
                        index = 0;
                        Utils.menuIcon.setPosition(50, 200);
                        MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";

                        //Everything is now ready, so start this round of combat. Control will return to the player once
                        //the enemy has made a move.
                        MainClass.combatLogic.execCombat();
                    }
                }
                else if (keycode == Input.Keys.ESCAPE) {
                    itemMenu = false;
                    rootMenu = true;
                    MainClass.combatScreen.itemPane = 0;
                    index = 0;
                    Utils.menuIcon.setPosition(50, 200);
                    MainClass.combatScreen.description = "Select a move to \nuse against the enemy.";
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
