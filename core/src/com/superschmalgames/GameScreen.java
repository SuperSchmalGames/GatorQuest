package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

    final MainClass game;

    OrthographicCamera camera;

    Music worldMusic;
    Texture badLogic;

    public GameScreen(final MainClass gam) {
        this.game = gam;

        badLogic = new Texture("visuals/sprites/badlogic.jpg");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        worldMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/world_map_music.wav"));
        worldMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(badLogic,game.GAME_SCREEN_WIDTH/2-badLogic.getWidth()/2, game.GAME_SCREEN_HEIGHT/2-badLogic.getHeight()/2);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){

        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){

        }
    }

    @Override
    public void show() {
        //worldMusic.play();
    }


    @Override
    public void resize(int width, int height) {

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
        badLogic.dispose();
        worldMusic.dispose();
    }
}
