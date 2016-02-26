package com.superschmalgames.Utilities;

//This class handles the popup window that shows when there is dialogue between characters

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CharacterDialogue extends Dialog{

    public CharacterDialogue(String title, Skin skin) {
        super(title, skin);
        text("This test will be for a test for dialogue stuff\ninvolving longer strings which should hopefully\nboth require and allow for some sort of scrolling or text wrapping");
        button("K: Ok!");
    }

    //This method is called when we hit "ok" in our dialogue window. It hides the window and removes it from the Stage.
    @Override
    protected void result(Object object) {
        //Clear flag so window disappears
        MainClass.gameScreen.dial = false;
        //Give control back to InputHandler
        Gdx.input.setInputProcessor(MainClass.inputHandler);
    }
}
