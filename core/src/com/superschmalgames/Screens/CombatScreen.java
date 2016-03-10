package com.superschmalgames.Screens;

//This class handles the screen used for combat between the player character and different NPCs.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class CombatScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;
    private Viewport viewport;

    public CombatScreen(){
        //Set up the combat background
        Utils.combatBackground.setSize(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Set up the sprites being rendered
        Utils.tempHero.setPosition(40, (Utils.GAME_SCREEN_HEIGHT/3)*2-40);
        Utils.tempHero.setScale(3.0f);
        Utils.tempNPC.setPosition(Utils.GAME_SCREEN_WIDTH-80, (Utils.GAME_SCREEN_HEIGHT/3)*2-40);
        Utils.tempNPC.setScale(3.0f);

        //Set up the combat ui
        Utils.combatBorder.setSize(Utils.GAME_SCREEN_WIDTH,Utils.GAME_SCREEN_HEIGHT/3+60);
        Utils.combatBorder.setPosition(0,10);

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        Utils.combatScreenMusic.play();
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
        Utils.combatBackground.draw(MainClass.batch);
        Utils.tempHero.draw(MainClass.batch);
        Utils.tempNPC.draw(MainClass.batch);
        Utils.combatBorder.draw(MainClass.batch);
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

    }

    @Override
    public void dispose() {

    }
}
