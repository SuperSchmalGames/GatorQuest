package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

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
    int[] background = new int[2];
    int[] foreground = new int[1];

    public GameScreen(final MainClass gam) {
        this.game = gam;

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_SCREEN_WIDTH, game.GAME_SCREEN_HEIGHT);

        //Initialize the music. Load an audio file from our assets into the Music object.
        worldMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/world_map_music.wav"));
        worldMusic.setLooping(true);

        //Initialize the map.
        tiledmap = new TmxMapLoader().load("visuals/maps/gatorquesttest.tmx");
        tiledmaprenderer = new OrthogonalTiledMapRenderer(tiledmap);
        collision = (TiledMapTileLayer) tiledmap.getLayers().get("Collision");
        background[0] = 0;
        background[1] = 1;
        foreground[0] = 2;
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
        game.font.draw(game.batch, "Player Coords: X: "+ camera.position.x +" Y: "+ camera.position.y, 0, game.GAME_SCREEN_HEIGHT-40);
        //The following draw method is weird but allows us to make our hero smaller in order to look like he fits better proportional to objects in the world.
        //The second-to-last and third-to-last args are floats (from 0 to 1.0) that you can tweak to change the character's size. //0.8f for the sprite we've been using, 2.0 for #1, 0.9 for #2
        game.batch.draw(game.hero.heroAnim.currentFrame, game.hero.xPos, game.hero.yPos, 0, 0, game.hero.heroAnim.currentFrame.getRegionWidth(), game.hero.heroAnim.currentFrame.getRegionHeight(), 2.0f, 2.0f, 0f);
        game.batch.end();

        //NOTES
        tiledmaprenderer.render(foreground);

        //Take keyboard input from user for character movement. Character actually stays centered on screen, and the
        //camera is translated about the map to give illusion of character movement.
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(-3f,0f);
            game.hero.walkAnimation('L', Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(3f,0f);
            game.hero.walkAnimation('R', Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP) &&
                !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y+33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(0f,3f);
            game.hero.walkAnimation('U', Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
                !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y-33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(0f,-3f);
            game.hero.walkAnimation('D', Gdx.graphics.getDeltaTime());
        }

        //Mouse/touchpad control for character movement. It appears the call to getY() may use the inverted y-axis used by openGL.
        else if(Gdx.input.isTouched()){
            if((Gdx.input.getX() < game.GAME_SCREEN_WIDTH/2 && (Gdx.input.getY() < game.GAME_SCREEN_HEIGHT/2+game.GAME_SCREEN_HEIGHT/4 && Gdx.input.getY() > game.GAME_SCREEN_HEIGHT/2-game.GAME_SCREEN_HEIGHT/4)) &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(-3f,0f);
                game.hero.walkAnimation('L', Gdx.graphics.getDeltaTime());
            }
            else if((Gdx.input.getX() > game.GAME_SCREEN_WIDTH/2 && (Gdx.input.getY() < game.GAME_SCREEN_HEIGHT/2+game.GAME_SCREEN_HEIGHT/4 && Gdx.input.getY() > game.GAME_SCREEN_HEIGHT/2-game.GAME_SCREEN_HEIGHT/4)) &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(3f,0f);
                game.hero.walkAnimation('R', Gdx.graphics.getDeltaTime());
            }
            else if(Gdx.input.getY() < game.GAME_SCREEN_HEIGHT/2 /*&& (Gdx.input.getX() < game.GAME_SCREEN_WIDTH/2+game.GAME_SCREEN_WIDTH/3 && Gdx.input.getX() > game.GAME_SCREEN_WIDTH/2-game.GAME_SCREEN_WIDTH/3))*/ &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y+33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(0f,3f);
                game.hero.walkAnimation('U', Gdx.graphics.getDeltaTime());
            }
            else if(Gdx.input.getY() > game.GAME_SCREEN_HEIGHT/2 /*&& (Gdx.input.getX() < game.GAME_SCREEN_WIDTH/2+game.GAME_SCREEN_WIDTH/3 && Gdx.input.getX() > game.GAME_SCREEN_WIDTH/2-game.GAME_SCREEN_WIDTH/3))*/ &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y-33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(0f,-3f);
                game.hero.walkAnimation('D', Gdx.graphics.getDeltaTime());
            }
            else game.hero.standAnimation();
        }
        else game.hero.standAnimation();

        //Include guards that keep the character on the screen. Likely a temporary implementation, since this should
        //be achievable using Tiled.
        if(camera.position.x < 20) camera.position.x = 20;
        if(camera.position.y < 50) camera.position.y = 50;
        if(camera.position.x > 6370) camera.position.x = 6370;
        if(camera.position.y > 6350) camera.position.y = 6350;
    }

    @Override
    public void show() {
        worldMusic.play();
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
