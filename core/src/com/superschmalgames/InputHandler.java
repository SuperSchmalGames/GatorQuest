package com.superschmalgames;

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
