package com.superschmalgames.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;


public class OpenWorldScreen implements Screen {

    OrthographicCamera camera;
    private Viewport viewport;
    TiledMapRenderer tiledmaprenderer;
    TiledMapTileLayer collision;
    public  Boolean lwalk = false, rwalk = false, uwalk = false, dwalk = false;
    int location = 0;
    public Texture crosshair;

    public OpenWorldScreen() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);
        camera.position.set(2700f,830f,0f);
        tiledmaprenderer = new OrthogonalTiledMapRenderer(Utils.openworld);
        collision = (TiledMapTileLayer) Utils.openworld.getLayers().get(0);
        crosshair = new Texture("visuals/crosshair.png");

    }

    //Handles movement around the map, allows for sideways motion
    public void move() {
        if(uwalk && !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION + 1).getTile().getProperties().containsKey("blocked"))
            camera.translate(0f,5f);
        if(dwalk && !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION - 1).getTile().getProperties().containsKey("blocked"))
            camera.translate(0f,-5f);
        if(lwalk && !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION - 1, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            camera.translate(-5f,0f);
        if(rwalk && !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION + 1, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            camera.translate(5f,0f);
    }

    //Handles the moving from open world into a selected dungeon
    public int select() {
        if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("level")) {
            location = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("level"));
            switch(location) {
                //Dorms
                case 5 :
                    MainClass.gameScreen.setMap(Utils.dorm, Utils.dorm_x, Utils.dorm_y, 5);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    break;
                //Marston
                case 4 :
                    MainClass.gameScreen.setMap(Utils.marston, Utils.marston_x, Utils.marston_y, 4);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    break;
                //NEB
                case 3 :
                    MainClass.gameScreen.setMap(Utils.neb, Utils.neb_x, Utils.neb_y, 3);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    break;
                //CISE
                case 2 :
                    MainClass.gameScreen.setMap(Utils.cise, Utils.cise_x, Utils.cise_y, 2);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    break;
                //Turlington
                case 1 :
                    MainClass.gameScreen.setMap(Utils.turlington, Utils.turlington_x, Utils.turlington_y, 1);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.gameScreen);
                    break;
                default:
                    break;
            }
        }
        return(0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render();

        MainClass.batch.setProjectionMatrix(camera.combined);
        MainClass.batch.begin();
        MainClass.batch.draw(crosshair, camera.position.x, camera.position.y);
        Utils.testFont.draw(MainClass.batch, "Map data ©2016 Google Imagery ©2016, DigitalGlobe, U.S. Geological Survey", 0, Utils.GAME_SCREEN_HEIGHT - 10);
        MainClass.batch.end();

        move();
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

