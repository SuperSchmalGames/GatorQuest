package com.superschmalgames;

//This class is for the title screen you see when the game first loads up. Input is handled through the InputHandler
//class, which processes input events and takes care of everything.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class InventoryScreen implements Screen {

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

    public InventoryScreen() {

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

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
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();

        //Utils.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(Utils.font, GatorQuest);
        layout2.setText(Utils.font, PressSpace);
        Utils.font.draw(MainClass.batch,
                GatorQuest,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2+50
        );
        Utils.font.draw(MainClass.batch,
                PressSpace,
                Gdx.graphics.getWidth()/2 - layout2.width/2,
                Gdx.graphics.getHeight()/2 + layout2.height/2-50
        );

        if(invPanel.equals("Consumable"))
            MainClass.batch.draw(invTex1, Utils.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);
        else if(invPanel == "Equipment")
            MainClass.batch.draw(invTex2, Utils.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);
        else if(invPanel == "Apparel")
            MainClass.batch.draw(invTex3, Utils.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2,0);

        MainClass.batch.draw(invTex4, Utils.GAME_SCREEN_WIDTH/2-invTex1.getWidth()/2+35,480-65*invRow);

        MainClass.batch.end();
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