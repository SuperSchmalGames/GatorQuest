package com.superschmalgames;

//This class is for the main screen we see when moving through the game world.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

        //Set initial info for the main character.
        game.hero.name = "Matt";
        game.hero.width = 50;
        game.hero.height = 87;
        game.hero.xPos = game.GAME_SCREEN_WIDTH/2 - game.hero.width/2;
        game.hero.yPos = game.GAME_SCREEN_HEIGHT/2 - game.hero.height/2;
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
        game.batch.draw(game.hero.heroAnim.currentFrame, game.hero.xPos, game.hero.yPos);
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
            game.hero.leftWalk.stateTime += Gdx.graphics.getDeltaTime();
            game.hero.heroAnim.currentFrame = game.hero.leftWalk.walkAnimation.getKeyFrame(game.hero.leftWalk.stateTime, true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(3f,0f);
            game.hero.rightWalk.stateTime += Gdx.graphics.getDeltaTime();
            game.hero.heroAnim.currentFrame = game.hero.rightWalk.walkAnimation.getKeyFrame(game.hero.rightWalk.stateTime, true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP) &&
                !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y+33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(0f,3f);
            game.hero.upWalk.stateTime += Gdx.graphics.getDeltaTime();
            game.hero.heroAnim.currentFrame = game.hero.upWalk.walkAnimation.getKeyFrame(game.hero.upWalk.stateTime, true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
                !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y-33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                ){
            camera.translate(0f,-3f);
            game.hero.downWalk.stateTime += Gdx.graphics.getDeltaTime();
            game.hero.heroAnim.currentFrame = game.hero.downWalk.walkAnimation.getKeyFrame(game.hero.downWalk.stateTime, true);
        }

        //Mouse/touchpad control for character movement.
        else if(Gdx.input.isTouched()){
            if(Gdx.input.getX() < Gdx.graphics.getWidth()/2 &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2-3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(-3f,0f);
                game.hero.leftWalk.stateTime += Gdx.graphics.getDeltaTime();
                game.hero.heroAnim.currentFrame = game.hero.leftWalk.walkAnimation.getKeyFrame(game.hero.leftWalk.stateTime, true);
            }
            if(Gdx.input.getX() > Gdx.graphics.getWidth()/2 &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) camera.position.y/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2+3)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(3f,0f);
                game.hero.rightWalk.stateTime += Gdx.graphics.getDeltaTime();
                game.hero.heroAnim.currentFrame = game.hero.rightWalk.walkAnimation.getKeyFrame(game.hero.rightWalk.stateTime, true);
            }
            if(Gdx.input.getY() < Gdx.graphics.getHeight()/2 &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y+33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y+game.hero.height/2+3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(0f,3f);
                game.hero.upWalk.stateTime += Gdx.graphics.getDeltaTime();
                game.hero.heroAnim.currentFrame = game.hero.upWalk.walkAnimation.getKeyFrame(game.hero.upWalk.stateTime, true);
            }
            if(Gdx.input.getY() > Gdx.graphics.getHeight()/2 &&
                    !collision.getCell((int)camera.position.x/game.MAP_RESOLUTION, (int) (camera.position.y-33)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x-game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked") &&
                    !collision.getCell((int)(camera.position.x+game.hero.width/2)/game.MAP_RESOLUTION, (int) (camera.position.y-game.hero.height/2-3)/game.MAP_RESOLUTION).getTile().getProperties().containsKey("blocked")
                    ){
                camera.translate(0f,-3f);
                game.hero.downWalk.stateTime += Gdx.graphics.getDeltaTime();
                game.hero.heroAnim.currentFrame = game.hero.downWalk.walkAnimation.getKeyFrame(game.hero.downWalk.stateTime, true);
            }
        }
        //Include guards that keep the character on the screen.
        //if(game.hero.xPos < 0) game.hero.xPos = 0;
        //if(game.hero.yPos < 0) game.hero.yPos = 0;
        //if(game.hero.xPos > game.GAME_SCREEN_WIDTH - game.hero.width) game.hero.xPos = game.GAME_SCREEN_WIDTH - game.hero.width;
        //if(game.hero.yPos > game.GAME_SCREEN_HEIGHT - game.hero.height) game.hero.yPos = game.GAME_SCREEN_HEIGHT - game.hero.height;
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
