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
    Texture heroTexture;

    public GameScreen(final MainClass gam) {
        this.game = gam;

        heroTexture = new Texture("visuals/sprites/hero.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        worldMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/world_map_music.wav"));
        worldMusic.setLooping(true);

        //Set initial info for the main character.
        game.hero.name = "Matt";
        game.hero.width = 50;
        game.hero.height = 87;
        game.hero.xPos = game.GAME_SCREEN_WIDTH/2 - game.hero.width/2;
        game.hero.yPos = game.GAME_SCREEN_HEIGHT/2 - game.hero.height/2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(heroTexture,game.hero.xPos, game.hero.yPos);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            game.hero.xPos -= 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            game.hero.xPos += 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            game.hero.yPos += 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            game.hero.yPos -= 200 * Gdx.graphics.getDeltaTime();
        }
        //Include guards that keep the character on the screen.
        if(game.hero.xPos < 0) game.hero.xPos = 0;
        if(game.hero.yPos < 0) game.hero.yPos = 0;
        if(game.hero.xPos > game.GAME_SCREEN_WIDTH - game.hero.width) game.hero.xPos = game.GAME_SCREEN_WIDTH - game.hero.width;
        if(game.hero.yPos > game.GAME_SCREEN_HEIGHT - game.hero.height) game.hero.yPos = game.GAME_SCREEN_HEIGHT - game.hero.height;
    }

    @Override
    public void show() {
        worldMusic.play();
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
        heroTexture.dispose();
        worldMusic.dispose();
    }
}
