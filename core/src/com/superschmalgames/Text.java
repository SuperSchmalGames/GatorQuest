package com.superschmalgames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by blake_000 on 1/19/2016.
 */
public class Text extends ApplicationAdapter{
    SpriteBatch batch;
    BitmapFont font;
    String myText;
    GlyphLayout layout = new GlyphLayout();

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        myText = "Hello world.\nHello world.\nHello Work";
        layout.setText(font, myText);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch,
                myText,
                Gdx.graphics.getWidth()/2 - layout.width/2
                , Gdx.graphics.getHeight()/2 + layout.height/2);
        batch.end();
    }
}
