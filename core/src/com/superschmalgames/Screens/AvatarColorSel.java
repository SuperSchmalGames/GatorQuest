package com.superschmalgames.Screens;

//This class is for the Avatar color selection screen

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.Utilities.IntroLogic;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class AvatarColorSel implements Screen{

    OrthographicCamera camera;
    public GlyphLayout avatarLayout;
    private Viewport viewport;

    public boolean setName, setAvatar;

    public AvatarColorSel() {
        //Initialize camera.
        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize text to be printed on screen.
        avatarLayout = new GlyphLayout();
        avatarLayout.setText(Utils.font_small, Utils.introText1, Color.BLUE, 480, 8, true);

        //Initialize some control variables.
        setAvatar = false;
        setName = false;

        //Initialize window used to display text.
        Utils.window.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.window.getWidth()/2,
                Utils.GAME_SCREEN_HEIGHT/2-Utils.window.getHeight());

        //Initialize Schmalz Sprite info.
        Utils.profSchmalz.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.profSchmalz.getWidth()/2,
                Utils.window.getY()+Utils.window.getHeight()+20);

        //Initialize avatar preview
        Utils.avatarChoices.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.avatarChoices.getWidth()/2+40,200);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        MainClass.batch.begin();
        if(setName){
            Utils.font.draw(MainClass.batch,Utils.nameSelect, 300,300);
            Utils.font.draw(MainClass.batch,Utils.nameLine,390,375);
            Utils.font.draw(MainClass.batch,Utils.nameAccept,460,150);
            Utils.menuIcon.draw(MainClass.batch);
            Utils.menuBorder.draw(MainClass.batch);
            Utils.font.draw(MainClass.batch,MainClass.hero.name,390,400);
            Utils.font_medsmall.draw(MainClass.batch,Utils.nameInstr,75,80);
        }
        else if(setAvatar){
            Utils.font.draw(MainClass.batch, Utils.avatarPrompt,300,400);
            Utils.avatarChoices.draw(MainClass.batch);
            Utils.menuIcon.draw(MainClass.batch);
        }
        else {
            Utils.window.draw(MainClass.batch);
            Utils.font_small.draw(MainClass.batch,
                    avatarLayout, Utils.window.getX() + 20, Utils.window.getY() + 185);
            Utils.profSchmalz.draw(MainClass.batch);
        }
        MainClass.batch.end();
    }

    @Override
    public void show() {
        MainClass.introLogic = new IntroLogic();
        Utils.avatarScreenMusic.play();
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
        Utils.avatarScreenMusic.stop();
    }

    @Override
    public void dispose() {

    }
}
