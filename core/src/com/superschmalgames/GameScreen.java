package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.text.DecimalFormat;

public class GameScreen implements Screen {
    //An instance of our actual game object.
    final MainClass game;

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    //Textures and audio used in the game screen.
    Music worldMusic;

    //The game map itself.
    TiledMap tiledmap;
    TiledMapTileLayer collision;
    TiledMapRenderer tiledmaprenderer;
    int[] background = new int[3];
    int[] foreground = new int[1];

    //Flags for handling character movement.
    boolean lWalk, rWalk, uWalk, dWalk;

    //Format for rounding doubles when printing them to the screen. The raw number should be close enough to make the
    //behind-the-scenes math stay accurate.
    DecimalFormat df1 = new DecimalFormat("0.0#");

    public GameScreen(final MainClass gam) {
        this.game = gam;

        lWalk = false;
        rWalk = false;
        uWalk = false;
        dWalk = false;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        //Initialize the music. Load an audio file from our assets into the Music object.
        worldMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/world_map_music.wav"));
        worldMusic.setLooping(true);

        //Initialize the map.
        setMap("Full_Map.tmx");
    }

    private void setMap(String str) {
        tiledmap = new TmxMapLoader().load("visuals/maps/" + str);
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

        //Update the camera that the game sees.
        camera.update();

        //NOTES
        tiledmaprenderer.setView(camera);
        tiledmaprenderer.render(background);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        game.batch.begin();

        ////////////////////////////////////////////////////////TEST PRINTS////////////////////////////////////////////////////////////////////
        game.testFont.draw(game.batch, "Player Coords: X: "+ camera.position.x +" Y: "+ camera.position.y, 0, game.GAME_SCREEN_HEIGHT-40);
        game.testFont.draw(game.batch, "GPA: "+df1.format(game.hero.gpa)+" InvSize: "+game.hero.inventory.size()+" RedBull Quant: "+game.hero.inventory.get(0).quantity, 0, game.GAME_SCREEN_HEIGHT-75);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //The following draw method is weird but allows us to make our hero smaller in order to look like he fits better proportional to objects in the world.
        //The second-to-last and third-to-last args are floats (from 0 to 1.0) that you can tweak to change the character's size.
        game.batch.draw(game.hero.heroAnim.currentFrame, game.hero.xPos, game.hero.yPos, 0, 0, game.hero.heroAnim.currentFrame.getRegionWidth(), game.hero.heroAnim.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
        game.batch.end();

        //NOTES
        tiledmaprenderer.render(foreground);

        //Keyboard input is taken in the InputHandler class, which updates the following walk variables to control
        //how character movement is rendered to the screen
        walk(delta);

    }

    public void walk(float delta){
            if(lWalk &&
               !collision.getCell((int)(camera.position.x-game.hero.width/2-5)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
               !collision.getCell((int)(camera.position.x-game.hero.width/2-5)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(-5f, 0f);
                game.hero.walkAnimation('L', delta);
                if(collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*game.MAP_RESOLUTION, y*game.MAP_RESOLUTION);
                }
            }
            else if(rWalk &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+5)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+5)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(5f,0f);
                game.hero.walkAnimation('R', delta);
                if(collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*game.MAP_RESOLUTION, y*game.MAP_RESOLUTION);
                }
            }
            else if(uWalk &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y+5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,5f);
                game.hero.walkAnimation('U', delta);
                if(collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String)collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x*game.MAP_RESOLUTION, y*game.MAP_RESOLUTION);
                }
            }
            else if(dWalk &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-5)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked"))
            {
                camera.translate(0f,-5f);
                game.hero.walkAnimation('D', delta);
                if (collision.getCell((int) camera.position.x / game.MAP_RESOLUTION, (int) camera.position.y / game.MAP_RESOLUTION).getTile().getProperties().containsKey("door")) {
                    int x = Integer.valueOf((String) collision.getCell((int) camera.position.x / game.MAP_RESOLUTION, (int) camera.position.y / game.MAP_RESOLUTION).getTile().getProperties().get("x"));
                    int y = Integer.valueOf((String) collision.getCell((int) camera.position.x / game.MAP_RESOLUTION, (int) camera.position.y / game.MAP_RESOLUTION).getTile().getProperties().get("y"));
                    camera.translate(x * game.MAP_RESOLUTION, y * game.MAP_RESOLUTION);
                }
            }
            else game.hero.standAnimation();
    }

    @Override
    public void show() {
        //worldMusic.play();
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
        worldMusic.dispose();
    }

}
