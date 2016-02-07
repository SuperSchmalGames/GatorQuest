package com.superschmalgames;

//Utilities class that manages all of the audio and video assets for the game.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.text.DecimalFormat;

public class Utils {

    ////////////////////////////////////MAIN CLASS/////////////////////////////////////////////////////////////////////////////
    public static final int GAME_SCREEN_WIDTH = 1020;
    public static final int GAME_SCREEN_HEIGHT = 612;
    public static final int MAP_RESOLUTION = 64;
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
    public static final BitmapFont font_large = new BitmapFont(Gdx.files.internal("RosesAreFF0_large.fnt"));
    public static final BitmapFont font_small = new BitmapFont(Gdx.files.internal("RosesAreFF0_small.fnt"));
    public static final BitmapFont testFont = new BitmapFont();
    public static boolean isPaused;
    public static final FPSLogger logger = new FPSLogger();
    public static final DecimalFormat df1 = new DecimalFormat("0.0#");
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////TITLE SCREEN///////////////////////////////////////////////////////////////////////////
    public static final  Music titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Opening.ogg"));
    public static final Sound titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
    public static final String GatorQuest = "GatorQuest";
    public static final String PressSpace = "Press space to start.";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////AVATAR SCREEN/////////////////////////////////////////////////////////////////////////
    public static final Music avatarScreenMusic =Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Prelude.ogg"));
    public static final Sound avatarScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
    public static final Texture avatarTexture = new Texture("visuals/sprite_sheets/Sprite_Color_Sel.png");
    public static final String key_prompt_text = "Press the number key\ncorresponding to your\ndesired Avatar";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////GAME SCREEN////////////////////////////////////////////////////////////////////////////
    public static final Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Dungeon.ogg"));
    public static final TiledMap tiledmap = new TmxMapLoader().load("visuals/maps/Full_Map.tmx");
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////INVENTORY SCREEN///////////////////////////////////////////////////////////////////////
    public static final Sound inventoryScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/zipper.wav"));
    public static boolean invOpen;
    public static boolean heroOpen;

    //Offsets in the ArrayList of Items for the categories (Apparel, Consumables, Equipment)
    public static final int apparelStart = 0;
    public static final int apparelEnd = 4;
    public static final int consumableStart = 5;
    public static final int consumableEnd = 6;
    public static final int equipmentStart = 7;
    public static final int equipmentEnd = 15;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////INVENTORY ITEM TEXTURES///////////////////////////////////////////////////////////////
    //Consumable
    public static final Texture redbull_tex = new Texture("visuals/inv_items/consume/redbull.png");
    public static final Texture starbucks_tex = new Texture("visuals/inv_items/consume/starbucks.png");

    //Apparel
    public static final Texture business_casual_attire_tex = new Texture("visuals/inv_items/apparel/business_casual_attire.png");
    public static final Texture class_ring_tex = new Texture("visuals/inv_items/apparel/class_ring.png");
    public static final Texture gator_hat_tex = new Texture("visuals/inv_items/apparel/gator_hat.png");
    public static final Texture graduation_gown_tex = new Texture("visuals/inv_items/apparel/graduation_gown.png");
    public static final Texture suit_and_tie_tex = new Texture("visuals/inv_items/apparel/suit_and_tie.png");

    //Equipment
    public static final Texture avr_dragon_tex = new Texture("visuals/inv_items/equip/avr_dragon.png");
    public static final Texture cyclone_III_tex = new Texture("visuals/inv_items/equip/cyclone_III.png");
    public static final Texture dad_board_tex = new Texture("visuals/inv_items/equip/dad_board.png");
    public static final Texture macbook_pro_tex = new Texture("visuals/inv_items/equip/macbook_pro.png");
    public static final Texture nspire_tex = new Texture("visuals/inv_items/equip/nspire.png");
    public static final Texture soldering_iron_tex = new Texture("visuals/inv_items/equip/soldering_iron.png");
    public static final Texture ti_89_tex = new Texture("visuals/inv_items/equip/ti89.png");
    public static final Texture usb_blaster_tex = new Texture("visuals/inv_items/equip/usb_blaster.png");
    public static final Texture wire_kit_tex = new Texture("visuals/inv_items/equip/wire_kit.png");

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////INVENTORY ITEM SOUNDS///////////////////////////////////////////////////////////////
    static final Sound error = Gdx.audio.newSound(Gdx.files.internal("sound/effects/error_tone.wav"));
    static final Sound rustling = Gdx.audio.newSound(Gdx.files.internal("sound/effects/rustling.mp3"));
    static final Sound page = Gdx.audio.newSound(Gdx.files.internal("sound/effects/page.wav"));
    static final Sound oob_error = Gdx.audio.newSound(Gdx.files.internal("sound/effects/oob_error.wav"));

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Static method to initialize all required options for the variables fields used above.
    public static void initUtils(){
        ///////////////////////////////////MAIN CLASS//////////////////////////////////////////////////////////////////////////
        isPaused = false;
        invOpen = false;
        heroOpen = false;
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////TITLE SCREEN/////////////////////////////////////////////////////////////////////////
        //Set the music to loop.
        titleScreenMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////AVATAR SCREEN////////////////////////////////////////////////////////////////////////
        avatarScreenMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////GAME SCREEN///////////////////////////////////////////////////////////////////////////
        gameMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////INVENTORY SCREEN//////////////////////////////////////////////////////////////////////
        inventoryScreenSelectionSound.setVolume(1, 1);
        gameMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
