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
            if(keycode == Input.Keys.LEFT && !game.isPaused){
                game.gameScreen.lWalk = true;
            }
            else if(keycode == Input.Keys.RIGHT && !game.isPaused){
                game.gameScreen.rWalk = true;
            }
            else if(keycode == Input.Keys.UP && !game.isPaused){
                game.gameScreen.uWalk = true;
            }
            else if(keycode == Input.Keys.DOWN && !game.isPaused){
                game.gameScreen.dWalk = true;
            }

            ////////////////////////////////////////////////TEST INPUTS///////////////////////////////////////////////////////
            else if(keycode == Input.Keys.R && !game.isPaused){
                game.hero.inventory.addItem("Red Bull");
            }
            else if((keycode == Input.Keys.T && !game.isPaused)){
                game.hero.inventory.useItem("Red Bull", game.hero);
            }
            else if(keycode == Input.Keys.Y && !game.isPaused){
                game.hero.inventory.removeEffect("Red Bull", game.hero);
            }
            else if (keycode == Input.Keys.P){
                if(!game.isPaused) {
                    game.isPaused = true;
                    game.pause();
                }
                else {
                    game.isPaused = false;
                    game.resume();
                }
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
