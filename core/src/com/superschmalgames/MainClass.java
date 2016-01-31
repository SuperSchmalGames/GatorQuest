package com.superschmalgames;

//The main game class that contains much of the "guts" of everything.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends Game {

	final int GAME_SCREEN_WIDTH = 1020;
	final int GAME_SCREEN_HEIGHT = 612	;
	final int MAP_RESOLUTION = 64;

	SpriteBatch batch;
	BitmapFont font, testFont;
	HeroCharacter hero;

	TitleScreen titleScreen;
	AvatarColorSel avatarScreen;
	GameScreen gameScreen;

	InputHandler inputHandler;

	public boolean isPaused;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
		testFont = new BitmapFont();

		inputHandler = new InputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);

		//Create and initialize the main character object.
		hero = new HeroCharacter();
		hero.name = "Matt";
		hero.width = 55;
		hero.height = 64;
		hero.xPos = GAME_SCREEN_WIDTH/2 - hero.width/2;
		hero.yPos = GAME_SCREEN_HEIGHT/2 - hero.height/2;

		isPaused = false;

		//Set the screen as the title screen.
		titleScreen = new TitleScreen(this);
		this.setScreen(titleScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		testFont.dispose();
		titleScreen.dispose();
		avatarScreen.dispose();
		gameScreen.dispose();
	}
}
