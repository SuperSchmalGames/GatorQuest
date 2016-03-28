package com.superschmalgames.InputHandlers;

//Class to handle all user input. Allows us to put all the code in one place, and make the render() method in
//all the other classes much cleaner.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.sun.javaws.Main;
import com.superschmalgames.Inventory.ApparelItem;
import com.superschmalgames.Inventory.EquipableItem;
import com.superschmalgames.Screens.GameScreen;
import com.superschmalgames.Utilities.DataManager;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.SaveData;
import com.superschmalgames.Utilities.Utils;

public class InputHandler implements InputProcessor {

    //Has a button been pushed?
    public boolean pushed;

    //Int used for selecting menu options
    public int menuIndex;

    //The index in the ArrayList of the item currently being hovered over
    public int currentItemIndex;

    public InputHandler() {
        pushed = false;
        menuIndex = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                           TITLESCREEN
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.titleScreen) {
                if (keycode == Input.Keys.DOWN && Utils.menuReady && menuIndex < 2) {
                    //Play the sound effect when player pushes the button.
                    Utils.menuOptionSound.play(0.6f);
                    //Move the menu icon down to the next menu option.
                    Utils.menuIcon.translateY(-33.0f);
                    //Increment the menu index.
                    menuIndex++;
                } else if (keycode == Input.Keys.UP && Utils.menuReady && menuIndex > 0) {
                    Utils.menuOptionSound.play(0.6f);
                    Utils.menuIcon.translateY(33.0f);
                    menuIndex--;
                } else if (keycode == Input.Keys.ENTER && Utils.menuReady) {
                    if (menuIndex == 0) {
                        //Play the sound effect and stop the music when player pushes the button.
                        Utils.titleScreenMusic.stop();
                        Utils.titleScreenSelectionSound.play();
                        MainClass.titleScreen.titleDone = true;
                    } else if (menuIndex == 1){
                        try {
                            SaveData data = (SaveData) DataManager.load(Utils.savefile);
                            MainClass.hero.outfitNum = data.outfitNum;
                            MainClass.hero.name = data.name;
                            MainClass.hero.GPA = data.GPA;
                            MainClass.hero.semester = data.semester;
                            MainClass.hero.gatorBucks = data.gatorBucks;
                            MainClass.hero.lvl = data.lvl;
                            MainClass.hero.experience = data.experience;
                            MainClass.hero.expCap = data.expCap;
                            MainClass.hero.Software = data.Software;
                            MainClass.hero.Hardware = data.Hardware;
                            MainClass.hero.Writing = data.Writing;
                            MainClass.hero.Endurance = data.Endurance;
                            MainClass.hero.Social = data.Social;
                            MainClass.hero.Math = data.Math;
                            MainClass.hero.Focus =  data.Focus;
                            MainClass.hero.Software_buf = data.Software_buf;
                            MainClass.hero.Hardware_buf = data.Hardware_buf;
                            MainClass.hero.Writing_buf = data.Writing_buf;
                            MainClass.hero.Endurance_buf = data.Endurance_buf;
                            MainClass.hero.Social_buf = data.Social_buf;
                            MainClass.hero.Math_buf = data.Math_buf;
                            MainClass.hero.Focus_buf = data.Focus_buf;
                            for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                                MainClass.hero.inventory.items.get(i).setQuantity(data.quantities[i]);
                                if(i == data.apparel) {
                                    MainClass.hero.heroApparel = (ApparelItem) MainClass.hero.inventory.items.get(i);
                                }
                                if(i == data.equipped) {
                                    MainClass.hero.heroEquipment = (EquipableItem) MainClass.hero.inventory.items.get(i);
                                }
                            }
                            for (int i = 0; i < Utils.NEB_enemies.length; i++) {
                                 Utils.NEB_enemies[i].triggered = data.neb[i];
                            }
                            for (int i = 0; i < Utils.CISE_enemies.length; i++) {
                                Utils.CISE_enemies[i].triggered = data.cise[i];
                            }
                            for (int i = 0; i < Utils.Turlington_enemies.length; i++) {
                                Utils.Turlington_enemies[i].triggered = data.turlington[i];
                            }
                            for (int i = 0; i < Utils.Dorm_enemies.length; i++) {
                                Utils.Dorm_enemies[i].triggered = data.dorm[i];
                            }
                            for (int i = 0; i < Utils.Marston_enemies.length; i++) {
                                Utils.Marston_enemies[i].triggered = data.marston[i];
                            }
                            for (int i = 0; i < Utils.Bookstore_enemies.length; i++) {
                                Utils.Bookstore_enemies[i].triggered = data.bookstore[i];
                            }
                            switch(data.map) {
                                    //Bookstore
                                case 6:
                                    MainClass.gameScreen.setMap(Utils.bookstore,data.cam_x,data.cam_y,data.map);
                                    break;
                                //Dorm
                                case 5:
                                    MainClass.gameScreen.setMap(Utils.dorm,data.cam_x,data.cam_y,data.map);
                                    break;
                                //Marston
                                case 4:
                                    MainClass.gameScreen.setMap(Utils.marston,data.cam_x,data.cam_y,data.map);
                                    break;
                                //NEB
                                case 3:
                                    MainClass.gameScreen.setMap(Utils.neb,data.cam_x,data.cam_y,data.map);
                                    break;
                                //CISE
                                case 2:
                                    MainClass.gameScreen.setMap(Utils.cise,data.cam_x,data.cam_y,data.map);
                                    break;
                                //Turlington
                                case 1:
                                    MainClass.gameScreen.setMap(Utils.turlington,data.cam_x,data.cam_y,data.map);
                                    break;
                            }
                            Utils.titleScreenMusic.stop();
                            MainClass.hero.initAnimations();

                            //Play the "selection" sound effect.
                            Utils.avatarScreenSelectionSound.play(0.4f);

                            //Set game screen to be the main game screen.
                            //MainClass.gameScreen = new GameScreen();
                            ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                            MainClass.avatarScreen.dispose();
                        } catch (Exception e) {
                            System.out.print("Unable to load savefile");
                            Utils.titleScreenMusic.stop();
                            Utils.titleScreenSelectionSound.play();
                            MainClass.titleScreen.titleDone = true;
                        }
                    }else if (menuIndex == 2) {
                        Gdx.app.exit();
                    } else
                        Utils.errTone.play(0.5f);
                }
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                           AVATARSCREEN
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.avatarScreen) {
                if (keycode == Input.Keys.NUM_1) {
                    MainClass.hero.outfitNum = 7;
                    pushed = true;
                } else if (keycode == Input.Keys.NUM_2) {
                    MainClass.hero.outfitNum = 11;
                    pushed = true;
                } else if (keycode == Input.Keys.NUM_3) {
                    MainClass.hero.outfitNum = 10;
                    pushed = true;
                } else if (keycode == Input.Keys.NUM_4) {
                    MainClass.hero.outfitNum = 9;
                    pushed = true;
                }

                if (pushed) {
                    pushed = false;
                    //Initialize character with proper texture.
                    MainClass.hero.initAnimations();

                    //Play the "selection" sound effect.
                    Utils.avatarScreenSelectionSound.play(0.4f);

                    //Set game screen to be the main game screen.
                    //MainClass.gameScreen = new GameScreen();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    MainClass.avatarScreen.dispose();
                }

            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                           GAMESCREEN
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.gameScreen && MainClass.hero.canMove()) {
                //Take keyboard input from user for character movement. Character actually stays centered on screen, and the
                //camera is translated about the map to give illusion of character movement.
                if (keycode == Input.Keys.LEFT && !Utils.isPaused) {
                    MainClass.gameScreen.lWalk = true;
                    MainClass.hero.lastDir = 'L';
                } else if (keycode == Input.Keys.RIGHT && !Utils.isPaused) {
                    MainClass.gameScreen.rWalk = true;
                    MainClass.hero.lastDir = 'R';
                } else if (keycode == Input.Keys.UP && !Utils.isPaused) {
                    MainClass.gameScreen.uWalk = true;
                    MainClass.hero.lastDir = 'U';
                } else if (keycode == Input.Keys.DOWN && !Utils.isPaused) {
                    MainClass.gameScreen.dWalk = true;
                    MainClass.hero.lastDir = 'D';
                } else if ((keycode == Input.Keys.I && !Utils.isPaused)) {
                    //Play the sound effect when player pushes the button.
                    Utils.inventoryScreenSelectionSound.play();
                    //Set the gamescreen to be the inventory game screen.
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.inventoryScreen);
                    MainClass.inventoryScreen.invPanel = "Consumable";
                    MainClass.inventoryScreen.invPage = 0;
                    MainClass.inventoryScreen.invRow = 0;
                } else if ((keycode == Input.Keys.H && !Utils.isPaused)) {
                    //Play the sound effect when player pushes the button.
                    Utils.inventoryScreenSelectionSound.play();
                    //Set the gamescreen to be the inventory game screen.
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.heroScreen);
                    MainClass.heroScreen.heroPanel = "Statistics";
                    MainClass.heroScreen.heroPage = 0;
                    MainClass.heroScreen.heroRow = 0;
                } else if(keycode == Input.Keys.ENTER){
                    //Interact with the intended NPC.
                    MainClass.gameScreen.interact();
                } else if(keycode == Input.Keys.F1) {
                    //saving
                    SaveData data = new SaveData();
                    data.outfitNum = MainClass.hero.outfitNum;
                    data.name = MainClass.hero.name;
                    data.GPA = MainClass.hero.GPA;
                    data.semester = MainClass.hero.semester;
                    data.gatorBucks = MainClass.hero.gatorBucks;
                    data.lvl = MainClass.hero.lvl;
                    data.experience = MainClass.hero.experience;
                    data.expCap = MainClass.hero.expCap;
                    data.Software = MainClass.hero.Software;
                    data.Hardware = MainClass.hero.Hardware;
                    data.Writing = MainClass.hero.Writing;
                    data.Endurance = MainClass.hero.Endurance;
                    data.Social = MainClass.hero.Social;
                    data.Math = MainClass.hero.Math;
                    data.Focus = MainClass.hero.Focus;
                    data.Software_buf = MainClass.hero.Software_buf;
                    data.Hardware_buf = MainClass.hero.Hardware_buf;
                    data.Writing_buf = MainClass.hero.Writing_buf;
                    data.Endurance_buf = MainClass.hero.Endurance_buf;
                    data.Social_buf = MainClass.hero.Social_buf;
                    data.Math_buf = MainClass.hero.Math_buf;
                    data.Focus_buf = MainClass.hero.Focus_buf;
                    data.apparel = 300;     //set to unequippable range
                    data.equipped = 300;
                    for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                        data.quantities[i] = MainClass.hero.inventory.items.get(i).getQuantity();
                        if(MainClass.hero.inventory.items.get(i) == MainClass.hero.heroApparel) {
                            data.apparel = i;
                        }
                        if(MainClass.hero.inventory.items.get(i) == MainClass.hero.heroEquipment) {
                            data.equipped = i;
                        }
                    }
                    for (int i = 0; i < Utils.NEB_enemies.length; i++) {
                        data.neb[i] = Utils.NEB_enemies[i].triggered;
                    }
                    for (int i = 0; i < Utils.CISE_enemies.length; i++) {
                        data.cise[i] = Utils.CISE_enemies[i].triggered;
                    }
                    for (int i = 0; i < Utils.Turlington_enemies.length; i++) {
                        data.turlington[i] = Utils.Turlington_enemies[i].triggered;
                    }
                    for (int i = 0; i < Utils.Dorm_enemies.length; i++) {
                        data.dorm[i] = Utils.Dorm_enemies[i].triggered;
                    }
                    for (int i = 0; i < Utils.Marston_enemies.length; i++) {
                        data.marston[i] = Utils.Marston_enemies[i].triggered;
                    }
                    for (int i = 0; i < Utils.Bookstore_enemies.length; i++) {
                        data.bookstore[i] = Utils.Bookstore_enemies[i].triggered;
                    }
                    data.cam_x = (int)MainClass.gameScreen.camera.position.x;
                    data.cam_y = (int)MainClass.gameScreen.camera.position.y;
                    data.map = MainClass.gameScreen.location;
                    try {
                        DataManager.save(data, Utils.savefile);
                        System.out.print("Save successful");
                    } catch (Exception e) {
                        System.out.print("Unable to save data\n");
                    }
                }

                ////////////////////////////////////////////////TEST INPUTS///////////////////////////////////////////////////////
                else if (keycode == Input.Keys.P) {
                    if (!Utils.isPaused) {
                        Utils.isPaused = true;
                        (Gdx.app.getApplicationListener()).pause();
                    } else {
                        Utils.isPaused = false;
                        Utils.isPaused = false;
                        (Gdx.app.getApplicationListener()).resume();
                    }
                }
                else if (keycode == Input.Keys.A) {
                    MainClass.gameScreen.advising = !MainClass.gameScreen.advising;
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //THIS IS THE CODE FOR THE INVENTORY SCREEN
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.inventoryScreen) {
                if (keycode == Input.Keys.LEFT) {
                    Utils.page.play();
                    MainClass.inventoryScreen.invRow = 0;
                    MainClass.inventoryScreen.invPage = 0;
                    currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();

                    if ("Consumable".equals(MainClass.inventoryScreen.invPanel)) {
                    //if (MainClass.inventoryScreen.invPanel == "Consumable") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Apparel";
                    } else if ("Apparel".equals(MainClass.inventoryScreen.invPanel)) {
                    //} else if (MainClass.inventoryScreen.invPanel == "Apparel") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Equipment";
                    } else if ("Equipment".equals(MainClass.inventoryScreen.invPanel)) {
                    //} else if (MainClass.inventoryScreen.invPanel == "Equipment") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Consumable";
                    }
                } else if (keycode == Input.Keys.RIGHT) {
                    Utils.page.play();
                    MainClass.inventoryScreen.invRow = 0;
                    MainClass.inventoryScreen.invPage = 0;
                    currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();

                    if ("Consumable".equals(MainClass.inventoryScreen.invPanel)) {
                    //if (MainClass.inventoryScreen.invPanel == "Consumable") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Equipment";
                    } else if ("Apparel".equals(MainClass.inventoryScreen.invPanel)) {
                    //} else if (MainClass.inventoryScreen.invPanel == "Apparel") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Consumable";
                    } else if ("Equipment".equals(MainClass.inventoryScreen.invPanel)) {
                    //} else if (MainClass.inventoryScreen.invPanel == "Equipment") {
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                        MainClass.inventoryScreen.invPanel = "Apparel";
                    }
                } else if (keycode == Input.Keys.I) {
                    //Play the sound effect when player pushes the button.
                    Utils.inventoryScreenSelectionSound.play();
                    //Set the gamescreen to be the inventory game screen.
                    Utils.invOpen = false;
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                } else if (keycode == Input.Keys.DOWN) {
                    Utils.rustling.play();
                    if (MainClass.inventoryScreen.invPage * 8 + MainClass.inventoryScreen.invRow + 1 < MainClass.hero.inventory.getNum('c')
                            && "Consumable".equals(MainClass.inventoryScreen.invPanel)) {

                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();

                        if ((MainClass.inventoryScreen.invRow + 1) % 8 == 0) {
                            MainClass.inventoryScreen.invPage += 1;
                            MainClass.inventoryScreen.invRow = 0;
                        } else
                            MainClass.inventoryScreen.invRow += 1;
                    } else if (MainClass.inventoryScreen.invPage * 8 + MainClass.inventoryScreen.invRow + 1 < MainClass.hero.inventory.getNum('a')
                            && "Apparel".equals(MainClass.inventoryScreen.invPanel)) {

                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();

                        if ((MainClass.inventoryScreen.invRow + 1) % 8 == 0) {
                            MainClass.inventoryScreen.invPage += 1;
                            MainClass.inventoryScreen.invRow = 0;
                        } else
                            MainClass.inventoryScreen.invRow += 1;
                    } else if (MainClass.inventoryScreen.invPage * 8 + MainClass.inventoryScreen.invRow + 1 < MainClass.hero.inventory.getNum('e')
                            && "Equipment".equals(MainClass.inventoryScreen.invPanel)) {

                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();

                        if ((MainClass.inventoryScreen.invRow + 1) % 8 == 0) {
                            MainClass.inventoryScreen.invPage += 1;
                            MainClass.inventoryScreen.invRow = 0;
                        } else
                            MainClass.inventoryScreen.invRow += 1;
                    } else
                        Utils.oob_error.play();
                } else if (keycode == Input.Keys.UP) {
                    Utils.rustling.play();
                    if (MainClass.inventoryScreen.invRow == 0 && MainClass.inventoryScreen.invPage == 0) {
                        Utils.oob_error.play();
                    } else if ((MainClass.inventoryScreen.invRow) % 8 == 0) {
                        MainClass.inventoryScreen.invPage -= 1;
                        MainClass.inventoryScreen.invRow += 7;
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                    } else {
                        MainClass.inventoryScreen.invRow -= 1;
                        currentItemIndex = MainClass.hero.inventory.getCurrentItemIndex();
                    }
                } else if (keycode == Input.Keys.E) {
                    if ("Equipment".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('e') > 0) {
                        //First we unequip the item that was previously equipped.
                        if(MainClass.hero.heroEquipment != null)
                            MainClass.hero.heroEquipment.disableItem();

                        //Get the enum item associated with our current index, then call method to equip that item.
                        MainClass.hero.inventory.useItem(Utils.INV_ITEMS.getItem(currentItemIndex));
                    } else if ("Apparel".equals(MainClass.inventoryScreen.invPanel) && MainClass.hero.inventory.getNum('a') > 0) {
                        //First we unequip the apparel that was previously equipped.
                        if(MainClass.hero.heroApparel != null)
                            MainClass.hero.heroApparel.disableItem();

                        //Get the enum item associated with our current index, then call method to equip that item.
                        MainClass.hero.inventory.useItem(Utils.INV_ITEMS.getItem(currentItemIndex));
                    } else {
                        Utils.errTone.play();
                    }
                } else if (keycode == Input.Keys.R) {
                    if ("Equipment".equals(MainClass.inventoryScreen.invPanel)) {
                        //Unequip the currently equipped item.
                        if (MainClass.hero.heroEquipment != null)
                            MainClass.hero.heroEquipment.disableItem();
                    }
                    else if ("Apparel".equals(MainClass.inventoryScreen.invPanel)) {    //APPLY CHANGES HERE!!!
                        //Unequip the currently equipped apparel.
                        if (MainClass.hero.heroApparel != null)
                            MainClass.hero.heroApparel.disableItem();
                    }
                    else
                        Utils.errTone.play();
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //THIS IS THE CODE FOR THE HERO SCREEN
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.heroScreen) {
                if (keycode == Input.Keys.LEFT) {
                    Utils.page.play();
                    MainClass.heroScreen.heroRow = 0;
                    MainClass.heroScreen.heroPage = 0;
                    if ("Statistics".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Moves";
                    } else if ("Moves".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Degree Audit";
                    } else if ("Degree Audit".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Statistics";
                    }
                }
                else if (keycode == Input.Keys.RIGHT) {
                    Utils.page.play();
                    MainClass.heroScreen.heroRow = 0;
                    MainClass.heroScreen.heroPage = 0;
                    if ("Statistics".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Degree Audit";
                    } else if ("Moves".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Statistics";
                    } else if ("Degree Audit".equals(MainClass.heroScreen.heroPanel)) {
                        MainClass.heroScreen.heroPanel = "Moves";
                    }
                }
                else if (keycode == Input.Keys.H) {
                    //Play the sound effect when player pushes the button.
                    Utils.inventoryScreenSelectionSound.play();
                    //Set the gamescreen to be the inventory game screen.
                    Utils.heroOpen = false;
                    ((Game) Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                } else if (keycode == Input.Keys.DOWN) {
                    Utils.rustling.play();
                    if (MainClass.heroScreen.heroPage * 8 + MainClass.heroScreen.heroRow + 1 < 7
                            && "Statistics".equals(MainClass.heroScreen.heroPanel)) {

                        if ((MainClass.heroScreen.heroRow + 1) % 8 == 0) {
                            MainClass.heroScreen.heroPage += 1;
                            MainClass.heroScreen.heroRow = 0;
                        } else
                            MainClass.heroScreen.heroRow += 1;
                    }
                    else if (MainClass.heroScreen.heroPage * 8 + MainClass.heroScreen.heroRow + 1 < 18
                            && "Moves".equals(MainClass.heroScreen.heroPanel)) {
                        if ((MainClass.heroScreen.heroRow + 1) % 8 == 0) {
                            MainClass.heroScreen.heroPage += 1;
                            MainClass.heroScreen.heroRow = 0;
                        } else
                            MainClass.heroScreen.heroRow += 1;
                    } else
                        Utils.oob_error.play();
                } else if (keycode == Input.Keys.UP) {
                    Utils.rustling.play();
                    if (MainClass.heroScreen.heroRow == 0 && MainClass.heroScreen.heroPage == 0) {
                        Utils.oob_error.play();
                    } else if ((MainClass.heroScreen.heroRow) % 8 == 0) {
                        MainClass.heroScreen.heroPage -= 1;
                        MainClass.heroScreen.heroRow += 7;
                    } else
                        MainClass.heroScreen.heroRow -= 1;
                }
            }

        else if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.openWorldScreen) {
            if (keycode == Input.Keys.UP) {
                MainClass.openWorldScreen.uwalk = true;
            }
            else if (keycode == Input.Keys.DOWN) {
                MainClass.openWorldScreen.dwalk = true;
            }
            else if (keycode == Input.Keys.LEFT) {
                MainClass.openWorldScreen.lwalk = true;
            }
            else if (keycode == Input.Keys.RIGHT) {
                MainClass.openWorldScreen.rwalk = true;
            }
            else if (keycode == Input.Keys.ENTER) {
                MainClass.openWorldScreen.select();
            }
        }
        return false;

    }

    @Override
    public boolean keyUp(int keycode) {

        //Set the appropriate boolean value false to stop the walk animation when the button is lifted
        if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.gameScreen) {
            if (keycode == Input.Keys.LEFT) MainClass.gameScreen.lWalk = false;
            else if (keycode == Input.Keys.RIGHT) MainClass.gameScreen.rWalk = false;
            else if (keycode == Input.Keys.UP) MainClass.gameScreen.uWalk = false;
            else if (keycode == Input.Keys.DOWN) MainClass.gameScreen.dWalk = false;
        }

        else if(((Game)Gdx.app.getApplicationListener()).getScreen() == MainClass.openWorldScreen){
            if (keycode == Input.Keys.LEFT) MainClass.openWorldScreen.lwalk = false;
            else if (keycode == Input.Keys.RIGHT) MainClass.openWorldScreen.rwalk = false;
            else if (keycode == Input.Keys.UP) MainClass.openWorldScreen.uwalk = false;
            else if (keycode == Input.Keys.DOWN) MainClass.openWorldScreen.dwalk = false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(int amount) { return false; }
}
