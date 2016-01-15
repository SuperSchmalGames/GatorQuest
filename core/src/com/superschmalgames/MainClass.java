package com.superschmalgames;

//The main game class that contains much of the "guts" of everything.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends Game {

	final int GAME_SCREEN_WIDTH = 800;
	final int GAME_SCREEN_HEIGHT = 480;
	final int MAP_RESOLUTION = 64;

	SpriteBatch batch;
	BitmapFont font;
	HeroCharacter hero;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		//Create and initialize the main character object.
		hero = new HeroCharacter();

		//Set the screen as the title screen.
		this.setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
