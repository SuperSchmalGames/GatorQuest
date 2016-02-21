package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;
    private Viewport viewport;
    int current_location;
    //The game map itself.
    TiledMapTileLayer collision;
    TiledMapRenderer tiledmaprenderer;
    //storage for the tile layers that are drawn under the character
    int[] background = new int[3];
    //storage for the tile layer drawn above the character
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
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize the map
        setMap(Utils.dorm, Utils.start_x, Utils.start_y, 5);

    }

    //made a separate method so that the map can be changed and starting coordinates
    //can be provided. This allows the gamescreen to handle all of the dungeons without
    //making seperate screens.
    public void setMap(TiledMap tiledmap, int x, int y, int location) {
        camera.position.set(x,y,0);
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledmap);
        collision = (TiledMapTileLayer) tiledmap.getLayers().get("Collision");
        //background layer
        background[0] = 0;
        //collision layer
        background[1] = 1;
        //mid layer
        background[2] = 2;
        //foreground layer
        foreground[0] = 3;
        current_location = location;
    }

    private void drawEnemies() {
        switch(current_location) {
            //Dorm
            case 5:
                break;
            //Marston
            case 4:
                break;
            //NEB
            case 3:
                for (NPC enemies : Utils.NEB_enemies) {
                    MainClass.batch.draw(enemies.walk.currentFrame, enemies.x_pos, enemies.y_pos,0,0,enemies.walk.currentFrame.getRegionWidth(), enemies.walk.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
                }
                break;
            //CISE
            case 2:
                break;
            //Turlington
            case 1:
                break;
            default :
                break;
        }
    }

    private void checkForEnemy(float delta) {
        if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("enemy")) {
            lWalk = false;
            rWalk = false;
            uWalk = false;
            dWalk = false;
            int temp = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("number"));
            switch(current_location) {
                //Dorm
                case 5:
                    break;
                //Marston
                case 4:
                    break;
                //NEB
                case 3:
                    if(!Utils.NEB_enemies[temp].getTriggered()) {
                        Utils.NEB_enemies[temp].move(delta);
                        MainClass.hero.setMove(false);
                    }
                    else {
                        MainClass.hero.setMove(true);
                    }
                    break;
                //CISE
                case 2:
                    break;
                //Turlington
                case 1:
                    break;
                default :
                    break;
            }

        }
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

        //sets the map to move with a stationary camera
        tiledmaprenderer.setView(camera);
        //draw all the layers that appear below the character
        tiledmaprenderer.render(background);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.setProjectionMatrix(camera.combined);
        MainClass.batch.begin();

        ////////////////////////////////////////////////////////TEST PRINTS////////////////////////////////////////////////////////////////////
        Utils.testFont.draw(MainClass.batch, "Player Coords: X: "+ camera.position.x +" Y: "+ camera.position.y , 0, Utils.GAME_SCREEN_HEIGHT-40);
        Utils.testFont.draw(MainClass.batch, "GPA: " + Utils.df1.format(MainClass.hero.GPA) + " RedBull Quant: " + MainClass.hero.inventory.items.get(5).getQuantity(), 0, Utils.GAME_SCREEN_HEIGHT - 75);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //The following draw method is weird but allows us to make our hero smaller in order to look like he fits better proportional to objects in the world.
        //The second-to-last and third-to-last args are floats (from 0 to 1.0) that you can tweak to change the character's size.
        MainClass.batch.draw(MainClass.hero.heroAnim.currentFrame, MainClass.hero.xPos, MainClass.hero.yPos, 0, 0, MainClass.hero.heroAnim.currentFrame.getRegionWidth(), MainClass.hero.heroAnim.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
        drawEnemies();
        MainClass.batch.end();

        //draw the layer that appears above the character
        tiledmaprenderer.render(foreground);

        //Keyboard input is taken in the InputHandler class, which updates the following walk variables to control
        //how character movement is rendered to the screen
        walk(delta);
        checkForEnemy(delta);
    }

    //checks if one of the walk booleans has been triggered, then checks if the blocks that the character would move into are considered blocked using the collision layer of the maps
    //if able, the "character" moves (actually the camera is moved, and the character is linked to the camera). A check is made to see if the character has entered a "door" to another floor.
    //After that, if the character has moved up or down an additional check is needed to determine if the character is leaving the dungeon to the open world
    public void walk(float delta){
            if(lWalk &&
               !collision.getCell((int)(camera.position.x-MainClass.hero.width/2-Utils.MOVE_DIST)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
               !collision.getCell((int)(camera.position.x-MainClass.hero.width/2-Utils.MOVE_DIST)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")) {
                camera.translate(Utils.N_MOVE_DIST, 0f);
                MainClass.hero.walkAnimation('L', delta);
                if(collision.getCell((int)(camera.position.x-MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("exit")) {
                    lWalk = false;
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.openWorldScreen);
                }
            }
            else if(rWalk &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2+Utils.MOVE_DIST)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2+Utils.MOVE_DIST)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(Utils.MOVE_DIST,0f);
                MainClass.hero.walkAnimation('R', delta);
                if(collision.getCell((int)(camera.position.x+MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("exit")) {
                    rWalk = false;
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.openWorldScreen);
                }
            }
            else if(uWalk &&
                    !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y+Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y+Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y+Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,Utils.MOVE_DIST);
                MainClass.hero.walkAnimation('U', delta);
                if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*Utils.MAP_RESOLUTION, y*Utils.MAP_RESOLUTION);
                }
                else if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("exit")) {
                    uWalk = false;
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.openWorldScreen);
                }
            }
            else if(dWalk &&
                    !collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+MainClass.hero.width/2)/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2-Utils.MOVE_DIST)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,Utils.N_MOVE_DIST);
                MainClass.hero.walkAnimation('D', delta);
                if (collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String) collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String) collision.getCell((int) camera.position.x / Utils.MAP_RESOLUTION, (int) camera.position.y / Utils.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x * Utils.MAP_RESOLUTION, y * Utils.MAP_RESOLUTION);
                }
                else if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int) (camera.position.y-MainClass.hero.height/2)/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("exit")) {
                    dWalk = false;
                    ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.openWorldScreen);
                }
            }
            else MainClass.hero.standAnimation();
            MainClass.hero.setPosition(camera.position.x, camera.position.y);

    }

    @Override
    public void show() {
        Utils.gameMusic.play();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
