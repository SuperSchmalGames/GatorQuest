package com.superschmalgames.Screens;

//This class is for the Hero screen you see when you hit "H" from the game screen.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class HeroScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;
    private Viewport viewport;
    public String heroPanel;
    public int heroPage;
    public int heroRow;

    public HeroScreen() {

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);
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
            MainClass.batch.draw(Utils.hero_stats_tex, 0, 0);
            Utils.font.draw(MainClass.batch,"Software: "+MainClass.hero.Software_buf+"/"+MainClass.hero.Software, 50,515);
            Utils.font.draw(MainClass.batch,"Hardware: "+MainClass.hero.Hardware_buf+"/"+MainClass.hero.Hardware, 50,450);
            Utils.font.draw(MainClass.batch,"Read/Write: "+MainClass.hero.Writing_buf+"/"+MainClass.hero.Writing, 50, 385);
            Utils.font.draw(MainClass.batch,"Endurance: "+MainClass.hero.Endurance_buf+"/"+MainClass.hero.Endurance, 50,320);
            Utils.font.draw(MainClass.batch,"Social Skills: "+MainClass.hero.Social_buf+"/"+MainClass.hero.Social, 50,255);
            Utils.font.draw(MainClass.batch,"Math: "+MainClass.hero.Math_buf+"/"+MainClass.hero.Math, 50, 190);
            Utils.font.draw(MainClass.batch,"Focus: "+MainClass.hero.Focus_buf+"/"+MainClass.hero.Focus, 50, 125);

            if(MainClass.heroScreen.heroRow==0)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\n" +
                                                         "of moves like Java Function\n" +
                                                         "and Recursive Loop", 510,535);
            else if(MainClass.heroScreen.heroRow==1)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Nodal Analysis\nand Karnaugh Map", 510,535);
            else if(MainClass.heroScreen.heroRow==2)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Documentation\nand Commented Code", 510,535);
            else if(MainClass.heroScreen.heroRow==3)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Soldering Skills\nand Karnaugh Map", 510,535);
            else if(MainClass.heroScreen.heroRow==4)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Perf.\nPresentation", 510,535);
            else if(MainClass.heroScreen.heroRow==5)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Double\nIntegration and Set Equal\nto 0", 510,535);
            else if(MainClass.heroScreen.heroRow==6)
                Utils.font_medsmall.draw(MainClass.batch,"Increases the effectiveness\nof moves like Soldering Skills\nand Code Testing", 510,535);
        }
        else if("Moves".equals(heroPanel))
        {
            MainClass.batch.draw(Utils.hero_moves_tex, 0, 0);
            int temp = 0;
            int p2index = 0;
            int p3index = 0;
            if(MainClass.heroScreen.heroPage==0) {
                for (int i = 0; i < 18; i++) {
                    if(MainClass.hero.moves.attacks[i].obtained && temp < 8)
                    {
                        Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(),
                                50, 515 - 65 * temp);
                        temp +=1;
                    }
                }
            }
            else if(MainClass.heroScreen.heroPage==1) {
                for (int i = 0; i < 18; i++) {
                    if(MainClass.hero.moves.attacks[i].obtained && temp < 8)
                    {
                        if(temp == 7)
                        {
                            p2index = i+1;
                        }
                        temp +=1;
                    }
                }
                for(int i= p2index; i<18; i++)
                {
                    if(MainClass.hero.moves.attacks[i].obtained && temp < 16)
                    {
                        Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(),
                                50, 515 - 65 * (temp-8));
                        temp +=1;
                    }
                }
            }
            else if(MainClass.heroScreen.heroPage==2) {
                for (int i = 0; i < 18; i++) {
                    if(MainClass.hero.moves.attacks[i].obtained && temp < 16)
                    {
                        if(temp == 15)
                        {
                            p2index = i+1;
                        }
                        temp +=1;
                    }
                }
                for(int i= p2index; i<18; i++)
                {
                    if(MainClass.hero.moves.attacks[i].obtained && temp < 24)
                    {
                        Utils.font.draw(MainClass.batch, MainClass.hero.moves.attacks[i].getMoveName(),
                                50, 515 - 65 * (temp-16));
                        temp +=1;
                    }
                }
            }
        }

        MainClass.batch.draw(Utils.sel_item_tex, 35,480-65*heroRow);

        //If we currently have apparel equipped.
        if(MainClass.hero.heroApparel != null)
        {
            //Draw the sprite associated with our currently worn apparel item and print its description.
            MainClass.batch.draw(Utils.white_sq_tex, 505, 55, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroApparel.getTexture(), 520, 70, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroApparel.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroApparel.getStatBoosted()+" by "+
                    MainClass.hero.heroApparel.getBoostAmt(), 610,140);
        }

        //If we currently have an item equipped.
        if(MainClass.hero.heroEquipment != null)
        {
            //Draw the sprite associated with the equipped item and print its description.
            MainClass.batch.draw(Utils.white_sq_tex, 505, 240, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroEquipment.getTexture(), 520, 255, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroEquipment.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroEquipment.getStatBoosted()+" by "+
                    MainClass.hero.heroEquipment.getBoostAmt(), 610, 325);
        }

        if("Moves".equals(heroPanel))
        {
            MainClass.batch.draw(Utils.white_sq_tex, 495, 450, 94, 94);
            MainClass.batch.draw(MainClass.hero.moves.attacks[MainClass.hero.moves.getCurrentMove()].texture, 510, 465, 64, 64);
            Utils.font_medsmall.draw(MainClass.batch,
                    MainClass.hero.moves.attacks[MainClass.hero.moves.getCurrentMove()].description,
                    595, 530);
        }

        if("Degree Audit".equals(heroPanel))
        {
            MainClass.batch.draw(Utils.hero_degree_tex, 0, 0);
            Utils.font.draw(MainClass.batch,"Name:\n"+MainClass.hero.name, 50 ,505);
            Utils.font.draw(MainClass.batch,"GatorBucks:\n"+MainClass.hero.gatorBucks, 50 ,385);
            if(MainClass.hero.outfitNum == 7)
            {
                Utils.av1_sprite.setScale(2.5f);
                Utils.av1_sprite.draw(MainClass.batch);
                Utils.av1_sprite.setPosition(150,200);
            }
            else if(MainClass.hero.outfitNum == 11)
            {
                Utils.av2_sprite.setScale(2.5f);
                Utils.av2_sprite.draw(MainClass.batch);
                Utils.av2_sprite.setPosition(150,200);
            }
            else if(MainClass.hero.outfitNum == 10)
            {
                Utils.av3_sprite.setScale(2.5f);
                Utils.av3_sprite.draw(MainClass.batch);
                Utils.av3_sprite.setPosition(150,200);
            }
            else if(MainClass.hero.outfitNum == 9)
            {
                Utils.av4_sprite.setScale(2.5f);
                Utils.av4_sprite.draw(MainClass.batch);
                Utils.av4_sprite.setPosition(150,200);
            }

            Utils.font.draw(MainClass.batch,"Programming I: Dobbins", 420,505);
            Utils.font.draw(MainClass.batch,"Calculus II: Chui", 420,440);
            Utils.font.draw(MainClass.batch,"Programming II: Horton", 420, 385);
            Utils.font.draw(MainClass.batch,"Circuits I: Srivastava", 420,325);
            Utils.font.draw(MainClass.batch,"Sigs & Systems: Wong", 420,265);
            Utils.font.draw(MainClass.batch,"Digital Logic: Gugel", 420, 205);
            Utils.font.draw(MainClass.batch,"Operating Sys: Small", 420, 140);
            Utils.font.draw(MainClass.batch,"Senior Design: Schmalz", 420, 80);

            for(int i=0; i<MainClass.hero.semester; i++)
            {
                MainClass.batch.draw(Utils.checkbox2_tex, 905, 475-60*i, 50, 50);
            }
        }

        MainClass.batch.end();
    }

    @Override
    public void show() {

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

    }

    @Override
    public void dispose() {

    }
}
