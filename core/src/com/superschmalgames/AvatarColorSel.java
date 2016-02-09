package com.superschmalgames;

//This class is for the Avatar color selection screen

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class AvatarColorSel implements Screen{

    OrthographicCamera camera;
    public GlyphLayout avatarLayout;

    public AvatarColorSel() {
        //Initialize camera.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize text to be printed on screen.
        avatarLayout = new GlyphLayout();
        avatarLayout.setText(Utils.font, Utils.key_prompt_text);
        Utils.font.setColor(1,1,1,1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        MainClass.batch.begin();
        Utils.font.draw(MainClass.batch,
                Utils.key_prompt_text,
                Gdx.graphics.getWidth()/2 - avatarLayout.width/2,
                Gdx.graphics.getHeight()/2 + avatarLayout.height/2+50
        );
        MainClass.batch.draw(Utils.avatarTexture, Utils.GAME_SCREEN_WIDTH/2-Utils.avatarTexture.getWidth()/2,0);
        MainClass.batch.end();
    }

    @Override
    public void show() {
        Utils.avatarScreenMusic.play();
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
        Utils.avatarScreenMusic.stop();
    }

    @Override
    public void dispose() {

    }
}
