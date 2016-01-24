package com.superschmalgames;

//This class is for the Avatar color selection screen

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class AvatarColorSel implements Screen{

    final MainClass game;

    Music titleScreenMusic;
    Sound titleScreenSelectionSound;

    OrthographicCamera camera;
    Texture avTex;

    GlyphLayout layout1 = new GlyphLayout();
    String key_prompt_text = "Press the number key\ncorresponding to your\ndesired Avatar";

    public AvatarColorSel(final MainClass gam) {
        this.game = gam;

        //Initialize all textures, sounds, etc.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        //Set up the character options texture and the font that prompts for user input.
        avTex = new Texture("visuals/sprite_sheets/Sprite_Color_Sel.png");
        game.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(game.font, key_prompt_text);

        titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/title_screen_music.wav"));
        titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
        titleScreenMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch,
                key_prompt_text,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2
        );
        game.batch.draw(avTex,0,0);
        game.batch.end();
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
