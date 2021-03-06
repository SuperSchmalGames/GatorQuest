package com.superschmalgames.Utilities;

//Generic animator class for creating character animations from sprite sheets.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {

    public Animation walkAnimation;        //The actual animation we end up with.
    public Texture walkSheet;              //The sprite sheet used for animation.
    public TextureRegion[] walkFrames;     //The array of individual frames of our animation.
    public TextureRegion currentFrame;     //The current frame of our animation.

    public float stateTime;                //Number of seconds elapsed since the start of the animation.

    public Animator(int cols, int rows, String path, float dur) {

        //Load the specified sprite sheet into the texture.
        walkSheet = new Texture(Gdx.files.internal(path));

        //Split the sprite sheet into individual frames and use it to populate an array or frames.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/cols, walkSheet.getHeight()/rows);
        walkFrames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        //Initialize our animation. The dur variable can be tweaked to make animation look smoother.
        walkAnimation = new Animation(dur, walkFrames);

        //Set our time to zero. It will re-zero every time render() is called.
        stateTime = 0f;
    }
}
