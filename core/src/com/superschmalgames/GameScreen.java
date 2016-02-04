package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    //The game map itself.
    TiledMapTileLayer collision;
    TiledMapRenderer tiledmaprenderer;
    int[] background = new int[3];
    int[] foreground = new int[1];

    //Flags for handling character movement.
    boolean lWalk, rWalk, uWalk, dWalk;

    public GameScreen() {

        lWalk = false;
        rWalk = false;
        uWalk = false;
        dWalk = false;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize the map
        setMap(Utils.dorm);

    }

    private void setMap(TiledMap tiledmap) {
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledmap);
        collision = (TiledMapTileLayer) tiledmap.getLayers().get("Collision");
        background[0] = 0;
        background[1] = 1;
        background[2] = 2;
        foreground[0] = 3;
    }

    @Override
    public void render(float delta) {
        //Clear the screen once per refresh.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Log FPS in the console
        Utils.logger.log();

        //Update the camera that the game sees.
        camera.update();

        //NOTES
        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render(background);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();

        ////////////////////////////////////////////////////////TEST PRINTS////////////////////////////////////////////////////////////////////
        Utils.testFont.draw(MainClass.batch, "Player Coords: X: "+ camera.position.x +" Y: "+ camera.position.y, 0, Utils.GAME_SCREEN_HEIGHT-40);
        Utils.testFont.draw(MainClass.batch, "GPA: " + Utils.df1.format(MainClass.hero.gpa) + " InvSize: " + MainClass.hero.inventory.items.size() + " RedBull Quant: " + MainClass.hero.inventory.items.get(0).getQuantity(), 0, Utils.GAME_SCREEN_HEIGHT - 75);
        if(MainClass.hero.inventory.items.get(0).getQuantity() > 0){
            MainClass.batch.draw(MainClass.hero.inventory.items.get(0).getTexture(), MainClass.hero.xPos-40, MainClass.hero.yPos);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //The following draw method is weird but allows us to make our hero smaller in order to look like he fits better proportional to objects in the world.
        //The second-to-last and third-to-last args are floats (from 0 to 1.0) that you can tweak to change the character's size.
        MainClass.batch.draw(MainClass.hero.heroAnim.currentFrame, MainClass.hero.xPos, MainClass.hero.yPos, 0, 0, MainClass.hero.heroAnim.currentFrame.getRegionWidth(), MainClass.hero.heroAnim.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
        MainClass.batch.end();

        //NOTES
        tiledmaprenderer.render(foreground);

        //Keyboard input is taken in the InputHandler class, which updates the following walk variables to control
        //how character movement is rendered to the screen
        walk(delta);
    }

    public void walk(float delta){
            if(lWalk &&
               !collision.getCell((int)(camera.position.x-MainClass.hero.width/2-5)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
               !collision.getCell((int)(camera.position.x-MainClass.hero.width/2-5)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(-5f, 0f);
                MainClass.hero.walkAnimation('L', delta);
                if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*Utils.MAP_RESOLUTION, y*Utils.MAP_RESOLUTION);
                }
            }
            else if(rWalk &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2+5)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2+5)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(5f,0f);
                MainClass.hero.walkAnimation('R', delta);
                if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*Utils.MAP_RESOLUTION, y*Utils.MAP_RESOLUTION);
                }
            }
            else if(uWalk &&
                    !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y+5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y+5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y+5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,5f);
                MainClass.hero.walkAnimation('U', delta);
                if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*Utils.MAP_RESOLUTION, y*Utils.MAP_RESOLUTION);
                }
            }
            else if(dWalk &&
                    !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-5)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,-5f);
                MainClass.hero.walkAnimation('D', delta);
                if (collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String) collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String) collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x * Utils.MAP_RESOLUTION, y * Utils.MAP_RESOLUTION);
                }
            }
            else MainClass.hero.standAnimation();
    }

    @Override
    public void show() {
        Utils.gameMusic.play();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Utils.gameMusic.pause();
    }

    @Override
    public void resume() {
        Utils.gameMusic.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
