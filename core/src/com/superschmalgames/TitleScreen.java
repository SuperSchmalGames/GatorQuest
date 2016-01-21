package com.superschmalgames;

//This class is for the title screen you see when the game first loads up.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TitleScreen implements Screen {
    //An instance of our actual game object.
    final MainClass game;

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    //Textures and audio used in the title screen.
    Music titleScreenMusic;
    Sound titleScreenSelectionSound;

    public TitleScreen(final MainClass gam) {
        this.game = gam;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        //Initialize the music. Load an audio file from our assets into the Music object.
        titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/title_screen_music.wav"));
        titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
        titleScreenMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {
        //Clear the screen once per refresh.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera that the game sees.
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        game.batch.begin();
        game.font.draw(game.batch, "GatorQuest", game.GAME_SCREEN_WIDTH/3, game.GAME_SCREEN_HEIGHT/2+20);
        game.font.draw(game.batch, "Press Space to Begin", camera.viewportWidth/3, camera.viewportHeight/2-20);
        game.batch.end();

        //Take keyboard input from the user.
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {
        titleScreenMusic.play();
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
        titleScreenMusic.stop();
    }

    @Override
    public void dispose() {
        titleScreenMusic.dispose();
        //titleScreenSelectionSound.dispose();  If we dispose here, it keeps the effect from playing when the space
        //bar is pressed.
    }
}
