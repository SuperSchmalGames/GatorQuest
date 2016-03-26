package com.superschmalgames.Utilities;

//This class handles the popup window that shows when there is a shop open

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ShopMenu{

    public final float WINDOW_X_OFFSET = MainClass.hero.xPos-480;
    public final float WINDOW_Y_OFFSET = MainClass.hero.yPos-274;
    public float DIAL_X_OFFSET = MainClass.hero.xPos+90;
    public float DIAL_Y_OFFSET = MainClass.hero.yPos+230;
    public float OKNO_X_OFFSET = MainClass.hero.xPos;
    public float OKNO_Y_OFFSET = MainClass.hero.yPos-134;
    public float ICON_X_OFFSET = MainClass.hero.xPos+60;
    public float ICON_Y_OFFSET = MainClass.hero.yPos+210;

    public int currIndex = 0;

    public GlyphLayout dialog;
    public GlyphLayout ok;
    public GlyphLayout quit;
    public GlyphLayout okNo;
    public GlyphLayout quitPrompt;
    public boolean proceed, decLock;
    public float decOffsetX, decOffsetY;

    public ShopMenu() {
        proceed = false;
        dialog = new GlyphLayout(Utils.font, "");
        quitPrompt = new GlyphLayout();
        quit = new GlyphLayout(Utils.font_small, "Press Q to quit menu", Color.BLUE, 300, 300, true);
        ok = new GlyphLayout(Utils.font, "ok!", Color.WHITE, 100, 8, true);
        okNo = new GlyphLayout(Utils.font, "ok!  no!", Color.WHITE, 200, 8, true);
        Utils.shop_window.setPosition(WINDOW_X_OFFSET, WINDOW_Y_OFFSET);
        currIndex =0;
    }

}
