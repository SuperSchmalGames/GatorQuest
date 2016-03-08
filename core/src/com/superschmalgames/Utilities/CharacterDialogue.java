package com.superschmalgames.Utilities;

//This class handles the popup window that shows when there is dialogue between characters

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class CharacterDialogue{

    public final float WINDOW_X_OFFSET = MainClass.hero.xPos-480;
    public final float WINDOW_Y_OFFSET = MainClass.hero.yPos-272;
    public float DIAL_X_OFFSET = MainClass.hero.xPos-458;
    public float DIAL_Y_OFFSET = MainClass.hero.yPos-89;
    public float OK_X_OFFSET = MainClass.hero.xPos-17;
    public float OK_Y_OFFSET = MainClass.hero.yPos-234;
    public float OKNO_X_OFFSET = MainClass.hero.xPos-75;
    public float OKNO_Y_OFFSET = MainClass.hero.yPos-234;
    public float ICON_X_OFFSET = MainClass.hero.xPos-37;
    public float ICON_Y_OFFSET = MainClass.hero.yPos-254;

    public GlyphLayout dialog;
    public GlyphLayout ok;
    public GlyphLayout okNo;
    public GlyphLayout decision;
    public boolean proceed, decLock;
    public float decOffsetX, decOffsetY;

    public CharacterDialogue() {
        proceed = false;
        dialog = new GlyphLayout(Utils.font_small, "");
        decision = new GlyphLayout();
        ok = new GlyphLayout(Utils.font_small, "ok!", Color.BLUE, 100, 8, true);
        okNo = new GlyphLayout(Utils.font_small, "ok!  no!", Color.BLUE, 200, 8, true);
        Utils.window.setPosition(WINDOW_X_OFFSET, WINDOW_Y_OFFSET);
    }

}

//Need some sort of border to go around where the npc portraits will be displayed