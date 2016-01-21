package com.superschmalgames;

//This class is for the Avatar color selection screen

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class AvatarColorSel implements Screen{

    final MainClass game;

    private SpriteBatch batch;
    private Sprite sprite;

    Music titleScreenMusic;
    Sound titleScreenSelectionSound;

    OrthographicCamera camera;

    GlyphLayout layout1 = new GlyphLayout();
    String key_prompt_text = "Press the number key\ncorresponding to your\ndesired Avatar";

    public AvatarColorSel(final MainClass gam) {
        this.game = gam;

        //Set bounds of Sprite


        //Initialize all textures, sounds, etc.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/title_screen_music.wav"));
        titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
        titleScreenMusic.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(game.font, key_prompt_text);
        game.font.draw(game.batch,
                key_prompt_text,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2
        );
        sprite.draw(game.batch);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            game.hero.outfitNum = 1;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            game.hero.outfitNum = 2;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            game.hero.outfitNum = 3;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){
            game.hero.outfitNum = 4;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)){
            game.hero.outfitNum = 5;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_6)){
            game.hero.outfitNum = 6;
            game.hero.setAnimations();
            titleScreenSelectionSound.play();
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

        titleScreenMusic.play();
        batch = new SpriteBatch();

        Texture spriteTexture = new Texture("visuals/sprite_sheets/Sprite_Color_Sel.png");
        sprite = new Sprite(spriteTexture);
        sprite.setSize(sprite.getWidth(), sprite.getHeight());
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
