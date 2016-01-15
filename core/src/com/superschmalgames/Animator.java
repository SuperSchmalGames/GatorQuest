package com.superschmalgames;

//Generic animator class for creating character animations from sprite sheets.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {

    private final int NUM_COLS;     //The width (in frames) of the sprite sheet.
    private final int NUM_ROWS;     //The height (in frames) of the sprite sheet.

    private final String texPath;   //The path to the sprite sheet we're animating.

    Animation walkAnimation;        //The actual animation we end up with.
    Texture walkSheet;              //The sprite sheet used for animation.
    TextureRegion[] walkFrames;     //The array of individual frames of our animation.
    TextureRegion currentFrame;     //The current frame of our animation.

    float stateTime;                //Number of seconds elapsed since the start of the animation.

    public Animator(int cols, int rows, String path) {
        //Initialize rows, columns, and file path for sprite sheet.
        NUM_COLS = cols;
        NUM_ROWS = rows;
        texPath = path;

        //Load the specified sprite sheet into the texture.
        walkSheet = new Texture(Gdx.files.internal(texPath));

        //Split the sprite sheet into individual frames and use it to populate an array or frames.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/NUM_COLS, walkSheet.getHeight()/NUM_ROWS);
        walkFrames = new TextureRegion[NUM_COLS * NUM_ROWS];
        int index = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        //Initialize our animation.
        walkAnimation = new Animation(0.075f, walkFrames);  //Might need to tweak this value to make animation smoother.

        //Set our time to zero. It will re-zero every time render() is called.
        stateTime = 0f;
    }
}
