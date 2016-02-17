package com.superschmalgames;

//This class is for the Hero screen you see when you hit "H" from the game screen.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class HeroScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    String heroPanel;
    int heroPage;
    int heroRow;

    Texture heroTex1, heroTex2, heroTex4, heroTex5;

    public HeroScreen() {

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        heroTex1 = new Texture("visuals/Menus/hero_statistics_menu.png");
        heroTex2 = new Texture("visuals/Menus/hero_moves_menu.png");
        heroTex4 = new Texture("visuals/Menus/Selected Item Box.png");
        heroTex5 = new Texture("visuals/Menus/white_sq.png");
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

        if("Statistics".equals(heroPanel)) {
            //Render the list of our character's stats.
            MainClass.batch.draw(heroTex1, Utils.GAME_SCREEN_WIDTH / 2 - heroTex1.getWidth() / 2, 0);
            Utils.font.draw(MainClass.batch,"Software: "+MainClass.hero.Software_buf+"/"+MainClass.hero.Software, 50,515);
            Utils.font.draw(MainClass.batch,"Hardware: "+MainClass.hero.Hardware_buf+"/"+MainClass.hero.Hardware, 50,450);
            Utils.font.draw(MainClass.batch,"Read/Write: "+MainClass.hero.Writing_buf+"/"+MainClass.hero.Writing, 50, 385);
            Utils.font.draw(MainClass.batch,"Endurance: "+MainClass.hero.Endurance_buf+"/"+MainClass.hero.Endurance, 50,320);
            Utils.font.draw(MainClass.batch,"Social Skills: "+MainClass.hero.Social_buf+"/"+MainClass.hero.Social, 50,255);
            Utils.font.draw(MainClass.batch,"Math: "+MainClass.hero.Math_buf+"/"+MainClass.hero.Math, 50, 190);
            Utils.font.draw(MainClass.batch,"Focus: "+MainClass.hero.Focus_buf+"/"+MainClass.hero.Focus, 50, 125);
        }
        else if("Moves".equals(heroPanel))
        {
            MainClass.batch.draw(heroTex2, Utils.GAME_SCREEN_WIDTH / 2 - heroTex1.getWidth() / 2, 0);
        }

        MainClass.batch.draw(heroTex4, 35,480-65*heroRow);

        //If we currently have apparel equipped.
        if(MainClass.hero.heroApparel != null)
        {
            //Draw the sprite associated with our currently worn apparel item and print its description.
            MainClass.batch.draw(heroTex5, 505, 55, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroApparel.getTexture(), 520, 70, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroApparel.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroApparel.getStatBoosted()+" by "+
                    MainClass.hero.heroApparel.getBoostAmt(), 610,140);
        }

        //If we currently have an item equipped.
        if(MainClass.hero.heroEquipment != null)
        {
            //Draw the sprite associated with the equipped item and print its description.
            MainClass.batch.draw(heroTex5, 505, 240, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroEquipment.getTexture(), 520, 255, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroEquipment.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroEquipment.getStatBoosted()+" by "+
                    MainClass.hero.heroEquipment.getBoostAmt(), 610, 325);
        }

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
