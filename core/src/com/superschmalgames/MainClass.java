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
	public static InventoryScreen inventoryScreen;
	public static HeroScreen heroScreen;
	public static InputHandler inputHandler;
	public static OpenWorldScreen openWorldScreen;
	
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

		//Initialize screens (after testing creating and deleting
		//screens when needed to minimize memory, discovered that
		//there was a noticeable speed delay in loading screens)
		openWorldScreen = new OpenWorldScreen();
		titleScreen = new TitleScreen();

		//Set the screen as the title screen.
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
		if(batch != null) batch.dispose();
		if(titleScreen != null) titleScreen.dispose();
		if(avatarScreen != null) avatarScreen.dispose();
		if(gameScreen != null) gameScreen.dispose();
		if(openWorldScreen != null) openWorldScreen.dispose();
	}

}
