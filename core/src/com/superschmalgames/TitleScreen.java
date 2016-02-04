package com.superschmalgames;

//This class is for the title screen you see when the game first loads up. Input is handled through the InputHandler
//class, which processes input events and takes care of everything.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TitleScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    public GlyphLayout titleLayout1;
    public GlyphLayout titleLayout2;

    public TitleScreen() {

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize the text that will print to the screen.
        titleLayout1 = new GlyphLayout();
        titleLayout2 = new GlyphLayout();
        titleLayout1.setText(Utils.font, Utils.GatorQuest);
        titleLayout2.setText(Utils.font, Utils.PressSpace);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();
        Utils.font.draw(MainClass.batch,
                Utils.GatorQuest,
                Utils.GAME_SCREEN_WIDTH/2 - titleLayout1.width/2,
                Utils.GAME_SCREEN_HEIGHT/2 + titleLayout1.height/2+50
                );
        Utils.font.draw(MainClass.batch,
                Utils.PressSpace,
                Utils.GAME_SCREEN_WIDTH/2 - titleLayout2.width/2,
                Utils.GAME_SCREEN_HEIGHT/2 + titleLayout2.height/2-50
        );
        MainClass.batch.end();
    }

    @Override
    public void show() {
        Utils.titleScreenMusic.play();
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
        Utils.titleScreenMusic.stop();
    }

    @Override
    public void dispose() {

    }
}
