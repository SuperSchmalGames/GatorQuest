package com.superschmalgames.Utilities;

//This class handles the popup window that shows when there is dialogue between characters

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class CharacterDialogue{

    public GlyphLayout dialog;

    public CharacterDialogue() {
        dialog = new GlyphLayout(Utils.font_small, "");
        Utils.window.setPosition(0,0);
    }

}



//Need Sprite to act as the window our text is in   (Plain blue with white order?)
//Need GlyphLayout to hold each sentence that gets displayed to the screen
//Need to reuse menuIcon Sprite
//Need a separate GlyphLayout/string to hold either "ok" or "ok  cancel" selection options at bottom right of window
//Need some sort of border to go around where the npc portraits will be displayed