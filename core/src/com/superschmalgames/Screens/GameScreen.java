package com.superschmalgames.Screens;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.superschmalgames.Utilities.CharacterDialogue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.superschmalgames.NPC;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.ShopMenu;
import com.superschmalgames.Utilities.Utils;

public class GameScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;
    private Viewport viewport;
    NPC[] enemies;
    //The game map itself.
    public TiledMapTileLayer collision;
    public TiledMapRenderer tiledmaprenderer;
    //storage for the tile layers that are drawn under the character
    public int[] background = new int[3];
    //storage for the tile layer drawn above the character
    public int[] foreground = new int[1];

    //Flags for handling character movement.
    public boolean lWalk, rWalk, uWalk, dWalk;
    public boolean dial, newDial, store;

    //Declare window for dialogue popups
    public CharacterDialogue window;
    public ShopMenu shop_window;

    public GameScreen() {
        lWalk = false;
        rWalk = false;
        uWalk = false;
        dWalk = false;

        dial = false;
        newDial = false;
        store = false;

        //Reset info for the menuIcon to use for dialogue windows
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(1.5f);

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        viewport = new FitViewport(Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT, camera);
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize the map
        setMap(Utils.dorm, Utils.start_x, Utils.start_y, 5);
    }

    //made a separate method so that the map can be changed and starting coordinates
    //can be provided. This allows the gamescreen to handle all of the dungeons without

    //making separate screens.
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
        switch(location) {
            //Bookstore
            case 6:
                enemies = Utils.Bookstore_enemies;
                break;
            //Dorm
            case 5:
                enemies = Utils.Dorm_enemies;
                break;
            //Marston
            case 4:
                enemies = Utils.Marston_enemies;
                break;
            //NEB
            case 3:
                enemies = Utils.NEB_enemies;
                break;
            //CISE
            case 2:
                enemies = Utils.CISE_enemies;
                break;
            //Turlington
            case 1:
                enemies = Utils.Turlington_enemies;
                break;
        }
    }

    //Draws NPCs contained in the currently loaded location-specific array of NPC objects.
    private void drawEnemies() {
        for (NPC enemy : enemies) {
            MainClass.batch.draw(enemy.walk.currentFrame, enemy.x_pos, enemy.y_pos,0,0,enemy.walk.currentFrame.getRegionWidth(), enemy.walk.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
        }
    }

    //Check for line of sight, pauses the player as an npc approaches
    private void checkForEnemy(float delta) {
        if(collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().containsKey("enemy")) {
            int temp = Integer.valueOf((String)collision.getCell((int)camera.position.x/Utils.MAP_RESOLUTION, (int)camera.position.y/Utils.MAP_RESOLUTION).getTile().getProperties().get("number"));
            MainClass.hero.lastInteracted = enemies[temp];
            if(!enemies[temp].getTriggered()) {
                lWalk = false;
                rWalk = false;
                uWalk = false;
                dWalk = false;
                enemies[temp].move(delta, camera.position.x, camera.position.y);
                MainClass.hero.setMove(false);
            }
            else {
                MainClass.hero.setMove(true);
            }

        }
    }

    //Will handle events triggered by interaction between the player and things they are investigating
    public void interact() {
        int x = 0;
        int y = 0;
        switch(MainClass.hero.lastDir) {
            case 'L' :
                x = (int)((camera.position.x - MainClass.hero.width)/Utils.MAP_RESOLUTION);
                y = (int)(camera.position.y/Utils.MAP_RESOLUTION);
                break;
            case 'R' :
                x = (int)((camera.position.x + MainClass.hero.width)/Utils.MAP_RESOLUTION);
                y = (int)(camera.position.y/Utils.MAP_RESOLUTION);
                break;
            case 'U' :
                x = (int)(camera.position.x/Utils.MAP_RESOLUTION);
                y = (int)((camera.position.y + MainClass.hero.height)/ Utils.MAP_RESOLUTION);
                break;
            case 'D' :
                x = (int)(camera.position.x/Utils.MAP_RESOLUTION);
                y = (int)((camera.position.y - MainClass.hero.height)/ Utils.MAP_RESOLUTION);
                break;
        }
        if(collision.getCell(x,y).getTile().getProperties().containsKey("event")) {
            int event = Integer.valueOf((String) collision.getCell(x,y).getTile().getProperties().get("event"));
            MainClass.hero.lastInteracted = enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))];
            if(event == 1)
            {
                if (enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))].x_pos == 45*Utils.MAP_RESOLUTION)
                {
                    MainClass.shopInputHandler.shop = 'a';
                }
                else if(enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))].x_pos == 25*Utils.MAP_RESOLUTION)
                {
                    MainClass.shopInputHandler.shop = 'b';
                }
                else if(enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))].x_pos == 22*Utils.MAP_RESOLUTION)
                {
                    MainClass.shopInputHandler.shop = 'c';
                }
                enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))].initiateShop();
            }
            else {
                enemies[Integer.valueOf((String) collision.getCell(x, y).getTile().getProperties().get("number"))].initiateDialogue(event);
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
        Utils.testFont.draw(MainClass.batch, "Player Coords: X: "+ camera.position.x +" Y: "+ camera.position.y , camera.position.x-Utils.GAME_SCREEN_WIDTH/2, camera.position.y+Utils.GAME_SCREEN_HEIGHT/2-10);
        //Utils.testFont.draw(MainClass.batch, "GPA: " + Utils.df1.format(MainClass.hero.GPA) + " RedBull Quant: " + MainClass.hero.inventory.items.get(5).getQuantity(), 0, Utils.GAME_SCREEN_HEIGHT - 75);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //The following draw method is weird but allows us to make our hero smaller in order to look like he fits better proportional to objects in the world.
        //The second-to-last and third-to-last args are floats (from 0 to 1.0) that you can tweak to change the character's size.
        drawEnemies();
        MainClass.batch.draw(MainClass.hero.heroAnim.currentFrame, MainClass.hero.xPos, MainClass.hero.yPos, 0, 0, MainClass.hero.heroAnim.currentFrame.getRegionWidth(), MainClass.hero.heroAnim.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);

        MainClass.batch.end();

        //Draw the layer that appears above the character
        tiledmaprenderer.render(foreground);

        //Show our little dialogue popup if dial is true.
        if(dial) {
            MainClass.batch.begin();
            Utils.window.draw(MainClass.batch);
            Utils.font_small.draw(MainClass.batch, window.dialog, window.DIAL_X_OFFSET, window.DIAL_Y_OFFSET);
            Utils.font_small.draw(MainClass.batch, window.decision, window.decOffsetX, window.decOffsetY);
            Utils.menuIcon.draw(MainClass.batch);
            MainClass.batch.end();
        }

        if(store){//Change this section to render the actual stuff for the store
            MainClass.batch.begin();
            Utils.menuIcon.setColor(Color.WHITE);
            Utils.shop_window.draw(MainClass.batch);
            Utils.font_small.draw(MainClass.batch, "Reminder: Press Q to quit menu" , camera.position.x-Utils.GAME_SCREEN_WIDTH/2+580, camera.position.y+Utils.GAME_SCREEN_HEIGHT/2-565);
            Utils.font.draw(MainClass.batch, shop_window.dialog, shop_window.DIAL_X_OFFSET, shop_window.DIAL_Y_OFFSET);
            Utils.menuIcon.draw(MainClass.batch);
            MainClass.batch.end();
        }

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
