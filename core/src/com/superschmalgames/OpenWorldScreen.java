package com.superschmalgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OpenWorldScreen implements Screen {

    OrthographicCamera camera;
    TiledMap tiledmap;
    TiledMapRenderer tiledmaprenderer;
    int movement =  0;

    public OpenWorldScreen() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        tiledmap = new TmxMapLoader().load("visuals/maps/UF_Full_Map.tmx");
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledmap);


    }

    public void move() {
        switch(movement){
            //UP
            case 4 :
                camera.translate(0f,5f);
                break;
            //DOWN
            case 3 :
                camera.translate(0f,-5f);
                break;
            //LEFT
            case 2 :
                camera.translate(-5f,0f);
                break;
            //RIGHT
            case 1 :
                camera.translate(5f,0f);
                break;
            //STATIONARY
            case 0 :
                break;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render();

        move();
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

