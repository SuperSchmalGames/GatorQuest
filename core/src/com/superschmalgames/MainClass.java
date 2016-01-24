package com.superschmalgames;

//The main game class that contains much of the "guts" of everything.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends Game {

	final int GAME_SCREEN_WIDTH = 800;
	final int GAME_SCREEN_HEIGHT = 480;
	final int MAP_RESOLUTION = 64;

	SpriteBatch batch;
	BitmapFont font;
	HeroCharacter hero;

	TitleScreen titleScreen;
	AvatarColorSel avatarScreen;
	GameScreen gameScreen;

	InputHandler inputHandler;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		inputHandler = new InputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);

		//Create and initialize the main character object.
		hero = new HeroCharacter();
		hero.name = "Matt";
		hero.width = 50;
		hero.height = 87;
		hero.xPos = GAME_SCREEN_WIDTH/2 - hero.width/2;
		hero.yPos = GAME_SCREEN_HEIGHT/2 - hero.height/2;

		//Set the screen as the title screen.
		titleScreen = new TitleScreen(this);
		this.setScreen(titleScreen);
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
