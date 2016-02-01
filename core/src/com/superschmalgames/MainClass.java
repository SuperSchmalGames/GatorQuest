package com.superschmalgames;

//The main game class that contains much of the "guts" of everything.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends Game {

	public static SpriteBatch batch;
	public static HeroCharacter hero;
	public static TitleScreen titleScreen;
	public static AvatarColorSel avatarScreen;
	public static GameScreen gameScreen;
	public static InputHandler inputHandler;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		inputHandler = new InputHandler();
		Gdx.input.setInputProcessor(inputHandler);

		//Create and initialize the main character object.
		hero = new HeroCharacter();
		hero.name = "Matt";
		hero.width = 55;
		hero.height = 64;
		hero.xPos = Utils.GAME_SCREEN_WIDTH/2 - hero.width/2;
		hero.yPos = Utils.GAME_SCREEN_HEIGHT/2 - hero.height/2;

		//Initialize all utility values.
		Utils.initUtils();

		//Set the screen as the title screen.
		titleScreen = new TitleScreen();
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
		titleScreen.dispose();
		avatarScreen.dispose();
		gameScreen.dispose();
	}

}
