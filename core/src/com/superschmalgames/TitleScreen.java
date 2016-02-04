package com.superschmalgames;

//This class is for the title screen you see when the game first loads up. Input is handled through the InputHandler
//class, which processes input events and takes care of everything.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TitleScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    //The text layout for the text we're printing to the screen.
    public GlyphLayout titleLayout1;
    public GlyphLayout titleLayout2;
    public GlyphLayout titleLayout3;

    //The variables needed for proper fading transitions.
    public float alpha;
    public fader fadeStatus;
    public boolean doneFadingSprite;
    public boolean doneFadingFont;
    public boolean doneOrange;
    public float transDelay;

    //Enum for holding state info for fade-in/fade-out animation
    public enum fader{
        FADING_IN,
        FINISHED_FADE_IN,
        FADING_OUT,
        FINISHED_FADE_OUT
    }

    public final float TIME_TO_FADE = 0.85f;

    public TitleScreen() {
        //Initialize fader info.
        alpha = 0;
        doneFadingSprite = false;
        doneFadingFont = false;
        doneOrange = false;
        fadeStatus = fader.FADING_IN;
        transDelay = 0.0f;

        //Initialize info for the gator logo sprite and title sprite.
        Utils.gatorLogo.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.gatorLogo.getWidth()/2,
                Utils.GAME_SCREEN_HEIGHT/2-Utils.gatorLogo.getHeight()/2);
        Utils.gatorLogo.setScale(3.5f);
        Utils.titleLogo.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.titleLogo.getWidth()/2,
                Utils.GAME_SCREEN_HEIGHT-Utils.titleLogo.getHeight()-15);
        Utils.menuBorder.setScale(0.85f,0.4f);
        Utils.menuBorder.setPosition(Utils.GAME_SCREEN_WIDTH/2-Utils.menuBorder.getWidth()/2+10,
                -50);
        Utils.menuIcon.setPosition(Utils.GAME_SCREEN_WIDTH/2-105,
                171);
        Utils.menuIcon.setScale(3);

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

        //Initialize the text that will print to the screen.
        titleLayout1 = new GlyphLayout();
        titleLayout2 = new GlyphLayout();
        titleLayout3 = new GlyphLayout();
        titleLayout1.setText(Utils.font, Utils.superSchmal);
        titleLayout2.setText(Utils.font, Utils.presents);
        titleLayout3.setText(Utils.font, Utils.menuOptions);
        Utils.font.setColor(1,1,1,0);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Fade the Gator logo sprite in and then out
        if(!doneFadingSprite) {
            doneFadingSprite = fadeSprite(delta, Utils.gatorLogo, TIME_TO_FADE);
            if(!doneOrange){
                doneOrange = true;
                Utils.orangeBlue.play(0.9f);   //Play the orange and blue chant audio.
            }
        }
        //After Gator logo fades out, fade the company text in then out.
        else if(!doneFadingFont){
            doneFadingFont = fadeFont(delta, Utils.font, TIME_TO_FADE);
        }
        //After company text fades out, wait 1 second then display title text.
        else if(transDelay < 1.0) {
            transDelay += delta;
            if(transDelay >= 1.0) {
                Utils.menuReady = true;
                Utils.titleLogo.setAlpha(1);
                Utils.font.setColor(1,1,1,1);
                Utils.titleScreenMusic.play();
            }
        }
        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();

        Utils.gatorLogo.draw(MainClass.batch);
        Utils.titleLogo.draw(MainClass.batch);
        if(!Utils.menuReady) {
            Utils.font.draw(MainClass.batch,
                    Utils.superSchmal,
                    Utils.GAME_SCREEN_WIDTH / 2 - titleLayout1.width / 2,
                    Utils.GAME_SCREEN_HEIGHT / 2 + titleLayout1.height / 2 + 20
            );
            Utils.font.draw(MainClass.batch,
                    Utils.presents,
                    Utils.GAME_SCREEN_WIDTH / 2 - titleLayout2.width / 2,
                    Utils.GAME_SCREEN_HEIGHT / 2 + titleLayout2.height / 2 - 20
            );
        }
        else{
            Utils.font.draw(MainClass.batch,
                    Utils.menuOptions,
                    Utils.GAME_SCREEN_WIDTH / 2 - titleLayout3.width / 2+40,
                    Utils.GAME_SCREEN_HEIGHT / 2 - titleLayout3.height-20
            );
            Utils.menuBorder.draw(MainClass.batch);
            Utils.menuIcon.draw(MainClass.batch);
        }

        MainClass.batch.end();
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
        Utils.titleScreenMusic.stop();
    }

    @Override
    public void dispose() {
        
    }

    public boolean fadeSprite(float delta, Sprite sprite, float timeToFade){

        if(fadeStatus == fader.FADING_OUT){
            alpha -= (delta / timeToFade);
            if (alpha < 0) {
                alpha = 0;
            }
            sprite.setAlpha(alpha);
            if (alpha <= 0) {
                fadeStatus = fader.FINISHED_FADE_OUT;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_OUT) {
            fadeStatus = fader.FADING_IN;
            return true;
        }
        else if (fadeStatus == fader.FADING_IN) {
            alpha += (delta / timeToFade);
            if (alpha > 1) {
                alpha = 1;
            }
            sprite.setAlpha(alpha);
            if (alpha >= 1) {
                fadeStatus = fader.FINISHED_FADE_IN;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_IN) {
            alpha = 1;
            fadeStatus = fader.FADING_OUT;
            return false;
        }
        return false;
    }

    public boolean fadeFont(float delta, BitmapFont font, float timeToFade){

        if(fadeStatus == fader.FADING_OUT){
            alpha -= (delta / timeToFade);
            if (alpha < 0) {
                alpha = 0;
            }
            font.setColor(1,1,1,alpha);
            if (alpha <= 0) {
                fadeStatus = fader.FINISHED_FADE_OUT;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_OUT) {
            fadeStatus = fader.FADING_IN;
            return true;
        }
        else if (fadeStatus == fader.FADING_IN) {
            alpha += (delta / timeToFade);
            if (alpha > 1) {
                alpha = 1;
            }
            font.setColor(1,1,1,alpha);
            if (alpha >= 1) {
                fadeStatus = fader.FINISHED_FADE_IN;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_IN) {
            alpha = 1;
            fadeStatus = fader.FADING_OUT;
            return false;
        }
        return false;
    }
}
