package com.superschmalgames.Screens;

//This class handles the screen used for combat between the player character and different NPCs.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.Utilities.CombatLogic;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class CombatScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;
    public Viewport viewport;

    //Variables for displaying combat info to the screen.
    public GlyphLayout rootList, moveDesc;
    public int movePane, itemPane, p2index, temp;
    public String description, heroLife, enemyLife;
    public String hMovDesc, eMovDesc;

    //Variables for displaying the red/green health change indicators during combat transitions.
    public String healthChangeDesc;
    public int healthChangeXPos;

    //Control variables for combat animations/transitions.
    public boolean statusUpdate;
    public double waitTime;

    public CombatScreen(){
        //Set up the combat background.
        Utils.combatBackground.setSize(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Set up the sprites being rendered.
        MainClass.hero.combatSprite.setPosition(40, (Utils.GAME_SCREEN_HEIGHT/3)*2-40);
        MainClass.hero.combatSprite.setScale(3.0f);
        MainClass.hero.lastEnemy.combatSprite.setPosition(Utils.GAME_SCREEN_WIDTH-80, (Utils.GAME_SCREEN_HEIGHT/3)*2-40);
        MainClass.hero.lastEnemy.combatSprite.setScale(3.0f);

        //Set up the combat ui.
        Utils.combatBorder.setSize(Utils.GAME_SCREEN_WIDTH,Utils.GAME_SCREEN_HEIGHT/3+60);
        Utils.combatBorder.setPosition(0, 10);
        Utils.hpBack.setSize(350,100);
        Utils.hpBack.setPosition(10, 490);
        Utils.hpBack2.setSize(385,100);
        Utils.hpBack2.setPosition(630, 490);
        rootList = new GlyphLayout(Utils.font,"Moves\n\nItems",Color.BLUE,200,8,true);
        moveDesc = new GlyphLayout(Utils.font_medsmall, "",Color.BLUE,200,8,true);
        waitTime = 0;
        statusUpdate = false;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        //Play the combat music, and stop the gameScreen music.
        Utils.gameMusic.stop();
        Utils.combatScreenMusic.play();

        //Create new combat logic object for this specific fight.
        MainClass.combatLogic = new CombatLogic();

        //Allow the player to make selections in the combat screen's move menu and item menu.
        MainClass.combatInputHandler.playerControl = true;
    }

    @Override
    public void render(float delta) {
        //Clear the screen once per refresh.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Open our spritebatch and draw to it.
        MainClass.batch.begin();
        Utils.combatBackground.draw(MainClass.batch);                        //Draw the map we're fighting on.
        MainClass.hero.combatSprite.draw(MainClass.batch);                   //Draw the hero character.
        MainClass.hero.lastEnemy.combatSprite.draw(MainClass.batch);         //Draw the NPC we're fighting.
        Utils.combatBorder.draw(MainClass.batch);                            //Draw the UI window containing moves/items/etc.

        //Display the remaining hero and enemy life.
        Utils.hpBack.draw(MainClass.batch);
        Utils.hpBack2.draw(MainClass.batch);
        Utils.font.draw(MainClass.batch, heroLife, 30, Utils.GAME_SCREEN_HEIGHT - 50);
        Utils.font.draw(MainClass.batch, enemyLife, Utils.GAME_SCREEN_WIDTH - 360, Utils.GAME_SCREEN_HEIGHT - 50);

        //If we're transitioning from one state to another. SET IT FALSE AT END OF EVERY POSSIBLE BRANCH HERE
        if(MainClass.combatLogic.transition){

            //Update wait time if we're in the middle of a transition
            waitTime += delta;

            //Conditional here for transition from hero to enemy turn.
            if(MainClass.combatLogic.hDone){

                //Display hMoveDesc here
                Utils.font.draw(MainClass.batch, hMovDesc, 55, 223);

                //We'll draw the post-hMove add/sub for health here.
                if(waitTime < 1.5){
                    if(!statusUpdate){
                        statusUpdate = true;
                        MainClass.combatLogic.healthChanges('h');
                    }
                    Utils.font_small.draw(MainClass.batch, healthChangeDesc, healthChangeXPos, Utils.GAME_SCREEN_HEIGHT - 90);
                }
                else{
                    statusUpdate = false;
                }

                //Update the amount of hero/enemy health shown.
                if(waitTime >= 1.5 && waitTime < 3){
                    if(!statusUpdate) {
                        statusUpdate = true;
                        MainClass.combatLogic.updateHero(1);   //Update Hero to show added health from item
                        MainClass.combatLogic.updateEnemy();  //Update enemy to show reduced health from hero attack
                    }
                }

                if(waitTime >= 3) {
                    MainClass.combatLogic.hDone = false;
                    waitTime = 0;
                    statusUpdate = false;

                    if(MainClass.combatLogic.hWin) {
                        MainClass.combatLogic.eDone = false;
                    }
                }
            }
            //Conditional here for transition from enemy to hero turn.
            else if(MainClass.combatLogic.eDone){

                //Display the eMovDesc here.
                Utils.font.draw(MainClass.batch, eMovDesc, 55, 223);

                //We'll draw the post-hMove add/sub for health here.
                if(waitTime < 1.5){
                    if(!statusUpdate){
                        statusUpdate = true;
                        MainClass.combatLogic.healthChanges('e');
                    }
                    Utils.font_small.draw(MainClass.batch, healthChangeDesc, healthChangeXPos, Utils.GAME_SCREEN_HEIGHT - 90);
                }
                else{
                    statusUpdate = false;
                }

                //Update the amount of hero health shown.
                if(waitTime >= 1.5 && waitTime < 3){
                    if(!statusUpdate) {
                        statusUpdate = true;
                        MainClass.combatLogic.updateHero(2);
                    }
                }

                if(waitTime >= 3) {
                    MainClass.combatLogic.eDone = false;
                    waitTime = 0;
                    statusUpdate = false;

                    if(MainClass.combatLogic.eWin) {
                        //If enemy won, move to the enemy win state and ake sure hWin is false.
                        MainClass.combatLogic.hWin = false;
                    }
                    else{
                        //If enemy didn't win, end transition and give control back to the player.
                        MainClass.combatInputHandler.playerControl = true;
                        MainClass.combatLogic.transition = false;
                    }
                }
            }
            //Conditional here for transition from hero to hero victory.
            else if(MainClass.combatLogic.hWin){

                //Display the win message. We use eMovDesc here since the enemy didn't need it.
                Utils.font.draw(MainClass.batch, eMovDesc, 55, 223);

                //After displaying the win message for 2 seconds, call method to exit combat.
                if(waitTime > 2) {
                    MainClass.combatLogic.exitCombat();
                }
            }
            //Conditional here for transition from enemy to enemy victory.
            else if(MainClass.combatLogic.eWin){

                //Display the win message. We use description here since it won't be needed for anything else.
                Utils.font.draw(MainClass.batch, description, 55, 223);

                //After displaying the lose message for 2 seconds, call method to exit combat.
                if(waitTime > 2) {
                    MainClass.combatLogic.exitCombat();
                }
            }
        }

        //If we're in the player's turn and not transitioning.
        else {
            Utils.menuIcon.draw(MainClass.batch);                          //Draw the icon used to select menu options.
            //When combat first begins, show the option to choose a move or a list.
            if (MainClass.combatInputHandler.rootMenu) {
                Utils.font.draw(MainClass.batch, rootList, 85, 223);

                //Draw the description for the current menu selection.
                Utils.font_medsmall.draw(MainClass.batch, description, 530, 223);
            }

            //If the player chooses to make a move, show the move list.
            else if (MainClass.combatInputHandler.moveMenu) {
                temp = 0;
                p2index = 0;
                if (movePane == 0) {
                    for (int i = 0; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 4) {
                            Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(), 85, 223 - 45 * temp);
                            temp += 1;
                        }
                    }
                } else if (movePane == 1) {
                    for (int i = 0; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 4) {
                            if (temp == 3) {
                                p2index = i + 1;
                            }
                            temp += 1;
                        }
                    }
                    for (int i = p2index; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 8) {
                            Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(), 85, 223 - 45 * (temp - 4));
                            temp += 1;
                        }
                    }
                } else if (movePane == 2) {
                    for (int i = 0; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 8) {
                            if (temp == 7) {
                                p2index = i + 1;
                            }
                            temp += 1;
                        }
                    }
                    for (int i = p2index; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 12) {
                            Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(), 85, 223 - 45 * (temp - 8));
                            temp += 1;
                        }
                    }
                } else if (movePane == 3) {
                    for (int i = 0; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 12) {
                            if (temp == 11) {
                                p2index = i + 1;
                            }
                            temp += 1;
                        }
                    }
                    for (int i = p2index; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 16) {
                            Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(), 85, 223 - 45 * (temp - 12));
                            temp += 1;
                        }
                    }
                } else if (movePane == 4) {
                    for (int i = 0; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 16) {
                            if (temp == 15) {
                                p2index = i + 1;
                            }
                            temp += 1;
                        }
                    }
                    for (int i = p2index; i < 18; i++) {
                        if (MainClass.hero.moves.attacks[i].obtained && temp < 20) {
                            Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(), 85, 223 - 45 * (temp - 16));
                            temp += 1;
                        }
                    }
                }

                //Draw the description for the currently selected Hero Move.
                Utils.font_medsmall.draw(MainClass.batch, description, 530, 223);
            }

            //If the player chooses to use an item, show the item list.
            else if (MainClass.combatInputHandler.itemMenu) {
                temp = 0;
                p2index = 0;
                if (itemPane == 0) {
                    for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                        if (MainClass.hero.inventory.items.get(i) != null &&
                                MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                                MainClass.hero.inventory.items.get(i).getItemType() == 'c' &&
                                temp < 4) {
                            Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                            + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                    85, 223 - 45 * temp);
                            temp += 1;
                        }
                    }
                } else if (itemPane == 1) {
                    for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                        if (MainClass.hero.inventory.items.get(i) != null &&
                                MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                                MainClass.hero.inventory.items.get(i).getItemType() == 'c' &&
                                temp < 4) {
                            if (temp == 3) {
                                p2index = i + 1;
                            }
                            temp += 1;
                        }
                    }
                    for (int i = p2index; i < MainClass.hero.inventory.items.size(); i++) {
                        if (MainClass.hero.inventory.items.get(i) != null &&
                                MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                                MainClass.hero.inventory.items.get(i).getItemType() == 'c' &&
                                temp < 8) {
                            Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                            + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                    85, 223 - 45 * (temp - 4));
                            temp += 1;
                        }
                    }
                }

                //Draw the description for the currently selected Consumable Item.
                Utils.font_medsmall.draw(MainClass.batch, description, 530, 223);
            }
        }

        MainClass.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Utils.font.setColor(Color.WHITE);
        Utils.font_medsmall.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}
