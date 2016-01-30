package com.superschmalgames;

//Class to handle all user input. Allows us to put all the code in one place, and make the render() method in
//all the other classes much cleaner.

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
    final MainClass game;
    boolean pushed;

    public InputHandler(MainClass gam){
        game = gam;
        pushed = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(game.getScreen() == game.titleScreen){
            if(keycode == Input.Keys.SPACE){
                //Play the sound effect when player pushes the button.
                game.titleScreen.titleScreenSelectionSound.play();

                //Set the game screen to be the character select screen.
                game.avatarScreen = new AvatarColorSel(game);
                game.setScreen(game.avatarScreen);
                game.titleScreen.dispose();
            }
        }
        if(game.getScreen() == game.avatarScreen) {
            if(keycode == Input.Keys.NUM_1) {
                game.hero.outfitNum = 7;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_2) {
                game.hero.outfitNum = 11;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_3) {
                game.hero.outfitNum = 10;
                pushed = true;
            }
            else if(keycode == Input.Keys.NUM_4) {
                game.hero.outfitNum = 9;
                pushed = true;
            }

            if(pushed) {
                pushed = false;
                //Initialize character with proper texture.
                game.hero.initAnimations();

                //Play the "selection" sound effect.
                game.titleScreen.titleScreenSelectionSound.play();

                //Set game screen to be the main game screen.
                game.gameScreen = new GameScreen(game);
                game.setScreen(game.gameScreen);
                game.avatarScreen.dispose();
            }
        }
        if(game.getScreen() == game.gameScreen) {
            //Take keyboard input from user for character movement. Character actually stays centered on screen, and the
            //camera is translated about the map to give illusion of character movement.
            if(keycode == Input.Keys.LEFT){
                game.gameScreen.lWalk = true;
            }
            else if(keycode == Input.Keys.RIGHT){
                game.gameScreen.rWalk = true;
            }
            else if(keycode == Input.Keys.UP){
                game.gameScreen.uWalk = true;
            }
            else if(keycode == Input.Keys.DOWN){
                game.gameScreen.dWalk = true;
            }

            ////////////////////////////////////////////////TEST INPUTS///////////////////////////////////////////////////////
            else if(keycode == Input.Keys.R){
                ConsumableItem tmp = new ConsumableItem("Red Bull","visuals/sprites/hero.png","GPA",1.2,3,1,true);
                tmp.addItem(game.hero);
            }
            else if((keycode == Input.Keys.T)){
                if(!game.hero.inventory.isEmpty()) {
                    game.hero.inventory.get(0).activateItem(game.hero);
                }
            }
            else if(keycode == Input.Keys.Y){
                game.hero.inventory.get(0).disableItem(game.hero);
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //Set the appropriate boolean value false to stop the walk animation when the button is lifted
        if(keycode == Input.Keys.LEFT) game.gameScreen.lWalk = false;
        if(keycode == Input.Keys.RIGHT) game.gameScreen.rWalk = false;
        if(keycode == Input.Keys.UP) game.gameScreen.uWalk = false;
        if(keycode == Input.Keys.DOWN) game.gameScreen.dWalk = false;

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
