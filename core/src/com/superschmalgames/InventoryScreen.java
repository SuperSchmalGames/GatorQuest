package com.superschmalgames;

//This class is for the title screen you see when the game first loads up. Input is handled through the InputHandler
//class, which processes input events and takes care of everything.

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
import com.badlogic.gdx.utils.Align;

public class InventoryScreen implements Screen {
    //An instance of our actual game object.
    final MainClass game;

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    GlyphLayout layout1 = new GlyphLayout();
    GlyphLayout layout2 = new GlyphLayout();
    String GatorQuest = "Inventory";
    String PressSpace = "Press Esc to return to game";
    String invPanel;
    int invPage;
    int invRow;

    Texture invTex1, invTex2, invTex3, invTex4;

    public InventoryScreen(final MainClass gam) {
        this.game = gam;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        invTex1 = new Texture("visuals/Menus/Consumable Item Menu.png");
        invTex2 = new Texture("visuals/Menus/Equipment Item Menu.png");
        invTex3 = new Texture("visuals/Menus/Apparel Item Menu.png");
        invTex4 = new Texture("visuals/Menus/Selected Item Box.png");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        game.batch.begin();

        game.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(game.font, GatorQuest);
        layout2.setText(game.font, PressSpace);
        game.font.draw(game.batch,
                GatorQuest,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2+50
        );
        game.font.draw(game.batch,
                PressSpace,
                Gdx.graphics.getWidth()/2 - layout2.width/2,
                Gdx.graphics.getHeight()/2 + layout2.height/2-50
        );

        if(invPanel == "Consumable")
            game.batch.draw(invTex1, game.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);
        else if(invPanel == "Equipment")
            game.batch.draw(invTex2, game.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);
        else if(invPanel == "Apparel")
            game.batch.draw(invTex3, game.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);

        game.batch.draw(invTex4, game.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2+35,480-65*invRow);

        game.batch.end();
    }

    @Override
    public void show() {

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

    }
}
