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

    Texture heroTex1, heroTex2, heroTex3, heroTex4, heroTex5;

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
        MainClass.hero.inventory.calc_stats();
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();

        if("Statistics".equals(heroPanel)) {
            MainClass.batch.draw(heroTex1, Utils.GAME_SCREEN_WIDTH / 2 - heroTex1.getWidth() / 2, 0);
            Utils.font.draw(MainClass.batch,"Software: "+MainClass.hero.software_buf+"/"+MainClass.hero.software, 50,515);
            Utils.font.draw(MainClass.batch,"Hardware: "+MainClass.hero.hardware_buf+"/"+MainClass.hero.hardware, 50,450);
            Utils.font.draw(MainClass.batch,"Read/Write: "+MainClass.hero.readWrite_buf+"/"+MainClass.hero.readWrite, 50, 385);
            Utils.font.draw(MainClass.batch,"Endurance: "+MainClass.hero.endurance_buf+"/"+MainClass.hero.endurance, 50,320);
            Utils.font.draw(MainClass.batch,"Social Skills: "+MainClass.hero.social_buf+"/"+MainClass.hero.social, 50,255);
            Utils.font.draw(MainClass.batch,"Math: "+MainClass.hero.math_buf+"/"+MainClass.hero.math, 50, 190);
            Utils.font.draw(MainClass.batch,"Focus: "+MainClass.hero.focus_buf+"/"+MainClass.hero.focus, 50, 125);
        }
        else if("Moves".equals(heroPanel))
        //else if(heroPanel == "Moves")
        {
            MainClass.batch.draw(heroTex2, Utils.GAME_SCREEN_WIDTH / 2 - heroTex1.getWidth() / 2, 0);
        }

        MainClass.batch.draw(heroTex4, 35,480-65*heroRow);

        if(!"".equals(MainClass.hero.heroApparel.getItemName()))
        //if(MainClass.hero.heroApparel.getItemName() != "")
        {
            MainClass.batch.draw(heroTex5, 505, 55, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroApparel.getTexture(), 520, 70, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroApparel.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroApparel.getStatBoosted()+" by "+
                    MainClass.hero.heroApparel.getBoostAmt(), 610,140);
        }

        if(!"".equals(MainClass.hero.heroEquipment.getItemName()))
        //if(MainClass.hero.heroEquipment.getItemName() != "")
        {
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
