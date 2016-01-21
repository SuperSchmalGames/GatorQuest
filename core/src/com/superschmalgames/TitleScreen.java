package com.superschmalgames;

//This class is for the title screen you see when the game first loads up.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

public class TitleScreen implements Screen {

    final MainClass game;

    Music titleScreenMusic;
    Sound titleScreenSelectionSound;

    OrthographicCamera camera;

    GlyphLayout layout1 = new GlyphLayout();
    GlyphLayout layout2 = new GlyphLayout();
    String GatorQuest = "GatorQuest";
    String PressSpace = "Press space to start.";

    public TitleScreen(final MainClass gam) {
        this.game = gam;

        //Initialize all textures, sounds, etc.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/title_screen_music.wav"));
        titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
        titleScreenMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(game.font, GatorQuest);
        layout2.setText(game.font, PressSpace);
        game.font.draw(game.batch,
                GatorQuest,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2
                );
        game.font.draw(game.batch,
                PressSpace,
                Gdx.graphics.getWidth()/2 - layout2.width/2,
                Gdx.graphics.getHeight()/2 + layout2.height/2-100
        );
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            titleScreenSelectionSound.play();
            game.setScreen(new AvatarColorSel(game));
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
