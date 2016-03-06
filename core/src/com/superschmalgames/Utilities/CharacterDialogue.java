package com.superschmalgames.Utilities;

//This class handles the popup window that shows when there is dialogue between characters

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class CharacterDialogue{

    public GlyphLayout dialog;
    public GlyphLayout ok;
    public GlyphLayout okNo;
    public boolean proceed;

    public CharacterDialogue() {
        proceed = false;
        dialog = new GlyphLayout(Utils.font_small, "");
        ok = new GlyphLayout(Utils.font_small, "ok!", Color.BLUE, 100, 8, true);
        okNo = new GlyphLayout(Utils.font_small, "ok!  no!", Color.BLUE, 200, 8, true);
        Utils.window.setPosition(0,0);
    }

}




//Need to reuse menuIcon Sprite
//Need some sort of border to go around where the npc portraits will be displayed