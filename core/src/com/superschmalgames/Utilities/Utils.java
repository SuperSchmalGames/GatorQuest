package com.superschmalgames.Utilities;

//Utilities class that manages all of the audio and video assets for the game.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.superschmalgames.NPC;
import java.text.DecimalFormat;

public class Utils {

    ////////////////////////////////////MAIN CLASS/////////////////////////////////////////////////////////////////////////////
    public static final int GAME_SCREEN_WIDTH = 1020;
    public static final int GAME_SCREEN_HEIGHT = 612;
    public static final int MAP_RESOLUTION = 64;
    public static final int MOVE_DIST = 5;
    public static final int N_MOVE_DIST = - MOVE_DIST;
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
    public static final BitmapFont font_large = new BitmapFont(Gdx.files.internal("RosesAreFF0_large.fnt"));
    public static final BitmapFont font_small = new BitmapFont(Gdx.files.internal("RosesAreFF0_small.fnt"));
    public static final BitmapFont font_medsmall = new BitmapFont(Gdx.files.internal("RosesAreFF0_medsmall.fnt"));
    public static final BitmapFont testFont = new BitmapFont();
    public static boolean isPaused;
    public static final FPSLogger logger = new FPSLogger();
    public static final DecimalFormat df1 = new DecimalFormat("0.0#");
    //Enum for holding state info for fade-in/fade-out animation
    public enum fader{
        FADING_IN,
        FINISHED_FADE_IN,
        FADING_OUT,
        FINISHED_FADE_OUT
    }
    //The variables needed for proper fading transitions.
    public static float alpha;
    public static fader fadeStatus;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////TITLE SCREEN///////////////////////////////////////////////////////////////////////////
    public static final Music titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Opening.ogg"));  //disposed
    public static final Sound titleScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/sword_sound.wav")); //disposed
    public static final Sound orangeBlue = Gdx.audio.newSound(Gdx.files.internal("sound/effects/orange_blue_chant.wav"));  //disposed
    public static final Sound menuOptionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/click_6.mp3"));
    public static final Sound errTone = Gdx.audio.newSound(Gdx.files.internal("sound/effects/error_tone.wav"));
    public static final Sprite gatorLogo = new Sprite(new Texture("visuals/sprites/gator_logo.png"));
    public static final Sprite titleLogo = new Sprite(new Texture("visuals/title_screen/final_title_test.png"));
    public static final Sprite menuBorder = new Sprite(new Texture("visuals/title_screen/white_sq.png"));
    public static final Sprite menuIcon = new Sprite(new Texture("visuals/title_screen/white_tr.png"));
    public static final String superSchmal = "SUPER SCHMAL GAMES";
    public static final String presents = "presents";
    public static final String menuOptions = "New Game\nLoad Game\nExit Game";
    public static boolean menuReady;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////AVATAR SCREEN/////////////////////////////////////////////////////////////////////////
    public static final Music avatarScreenMusic =Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Prelude.ogg"));
    public static final Sound avatarScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
    public static final Texture avatarTexture = new Texture("visuals/sprite_sheets/Sprite_Color_Sel.png");
    public static final String key_prompt_text = "Press the number key\ncorresponding to your\ndesired Avatar";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////GAME SCREEN////////////////////////////////////////////////////////////////////////////
    public static final Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/world_map_music.wav"));
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////TILED MAPS////////////////////////////////////////////////////////////////////////////
    public static final int start_x = 157;
    public static final int start_y = 1572;
    public static final TiledMap cise = new TmxMapLoader().load("visuals/maps/CISE_Dungeon.tmx");
    public static final int cise_x = 4351;
    public static final int cise_y = 4031;
    public static final TiledMap neb = new TmxMapLoader().load("visuals/maps/NEB_Dungeon.tmx");
    public static final int neb_x = 3745;
    public static final int neb_y = 292;
    public static final TiledMap dorm = new TmxMapLoader().load("visuals/maps/Dorm.tmx");
    public static final int dorm_x = 865;
    public static final int dorm_y = 227;
    public static final TiledMap marston = new TmxMapLoader().load("visuals/maps/Marston.tmx");
    public static final int marston_x = 2170;
    public static final int marston_y = 257;
    public static final TiledMap openworld = new TmxMapLoader().load("visuals/maps/UF_Full_Map.tmx");
    public static final TiledMap turlington = new TmxMapLoader().load("visuals/maps/Turlington_Dungeon.tmx");
    public static final int turlington_x = 7264;
    public static final int turlington_y = 3560;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////DUNGEON ENEMIES//////////////////////////////////////////////////////////////////////
    //when adding enemies to the various arrays, the proper tiles must be accounted for in the Tiled Map Editor
    //========================================NEB=============================================
    //89
    public static NPC[] NEB_enemies = {
            new NPC('l',"Welcome to the New Engineering Building", "l7", 65*MAP_RESOLUTION,12*MAP_RESOLUTION), //0
            new NPC('r',"What time does class start?", "r11", 79*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',"Another wild Zubat appeared", "r9", 51*MAP_RESOLUTION, 19*MAP_RESOLUTION),
            new NPC('d',"as;dfjkdslaf;", "d9", 59*MAP_RESOLUTION, 41*MAP_RESOLUTION),
            new NPC('l',"as;dfjkdslaf;", "l10", 46*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('r',"as;dfjkdslaf;", "r11", 44*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('l',"abdfsefdscsd", "l7", 48*MAP_RESOLUTION, 54*MAP_RESOLUTION),
            new NPC('u',"LEAVE ME ALONE", "u11", 47*MAP_RESOLUTION, 82*MAP_RESOLUTION),
            new NPC('d',"Go away please", "d10", 22*MAP_RESOLUTION, 53*MAP_RESOLUTION),
            new NPC('l',"adsfds", "l10", 45*MAP_RESOLUTION, 7*MAP_RESOLUTION),
            new NPC('d',"asdf", "d10", 41*MAP_RESOLUTION, 23*MAP_RESOLUTION),
            new NPC('l',"asdfsdf", "l9", 49*MAP_RESOLUTION, 32*MAP_RESOLUTION),
            new NPC('d',"asdfsd", "d10", 30*MAP_RESOLUTION, 27*MAP_RESOLUTION),
            new NPC('l',"asdfsadf","l11", 24*MAP_RESOLUTION, 20*MAP_RESOLUTION),
            new NPC('r',"sadfdsf","r7", 28*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION),
            new NPC('d',"gasdefds","d7", 16*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d',"asfddsf","d11",3*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new NPC('u',"asddfs", "u10",3*MAP_RESOLUTION, (89-49)*MAP_RESOLUTION),  //17
            new NPC('r',"baddsf", "r7", 3*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 16*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d11", 27*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d10", 45*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 68*MAP_RESOLUTION, (89-6)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 96*MAP_RESOLUTION, (89-72)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 97*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 110*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 122*MAP_RESOLUTION, (89-62)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 121*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 112*MAP_RESOLUTION, (89-33)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 125*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 118*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION), //30
            new NPC('d', "aassdf", "d9", 133*MAP_RESOLUTION, (89-4)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 140*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 140*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 149*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 170*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 137*MAP_RESOLUTION, (89-80)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 143*MAP_RESOLUTION, (89-57)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 165*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 160*MAP_RESOLUTION, (89-83)*MAP_RESOLUTION), //39
            new NPC('r', "aassdf", "r9", 187*MAP_RESOLUTION, (89-68)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 190*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 202*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 214*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 211*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 229*MAP_RESOLUTION, (89-58)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 224*MAP_RESOLUTION, (89-52)*MAP_RESOLUTION), //46
            new NPC('r', "aassdf", "r9", 187*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 205*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 212*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 229*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION), //50
            new NPC('r', "aassdf", "r9", 246*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 258*MAP_RESOLUTION, (89-26)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 283*MAP_RESOLUTION, (89-16)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 284*MAP_RESOLUTION, (89-41)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "l9", 276*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 287*MAP_RESOLUTION, (89-70)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "d9", 269*MAP_RESOLUTION, (89-61)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "u9", 262*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION), //58
            new NPC('d', "aassdf", "d9", 262*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "r9", 248*MAP_RESOLUTION, (89-46)*MAP_RESOLUTION),
    };
    //========================================================================================
    //========================================CISE============================================
    public static NPC[] CISE_enemies = {
            //new NPC
    };
    //========================================================================================
    //========================================Turlington======================================
    public static NPC[] Turlington_enemies = {
            //new NPC
    };
    //========================================================================================
    //========================================Dorm============================================
    public static NPC[] Dorm_enemies = {
            new NPC('u',"Welcome to UF, this is your dorm room.", "u10",9*MAP_RESOLUTION,20*MAP_RESOLUTION),
            new NPC('d',"You better not steal anything", "d9", 17*MAP_RESOLUTION,24*MAP_RESOLUTION),
            new NPC('l',"I have no idea how to play the piano","l11", 25*MAP_RESOLUTION,12*MAP_RESOLUTION),
            new NPC('r',"If you ever feel tired just return here","r7", 10*MAP_RESOLUTION,3*MAP_RESOLUTION),
            new NPC('r',"I'm just a filler","r9",17*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('l',"Also a filler", "l10", 21*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',"This is my room", "r7", MAP_RESOLUTION,31*MAP_RESOLUTION),
            new NPC('u',"Hello","u11", 20*MAP_RESOLUTION,35*MAP_RESOLUTION),
            new NPC('r',"Filler", "r11", 6*MAP_RESOLUTION,42*MAP_RESOLUTION),
            new NPC('d',"I'm tired","d9", 24*MAP_RESOLUTION,41*MAP_RESOLUTION)
    };
    //========================================================================================
    //========================================Marston=========================================
    public static NPC[] Marston_enemies = {
            //new NPC
    };
    //========================================================================================
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////INVENTORY SCREEN///////////////////////////////////////////////////////////////////////
    public static final Sound inventoryScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/zipper.wav"));
    public static boolean invOpen;
    public static boolean heroOpen;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////INVENTORY ITEM TEXTURES///////////////////////////////////////////////////////////////
    public static final Texture inv_consumable_tex = new Texture("visuals/Menus/Consumable Item Menu.png");
    public static final Texture inv_equip_tex = new Texture("visuals/Menus/Equipment Item Menu.png");
    public static final Texture inv_apparel_tex = new Texture("visuals/Menus/Apparel Item Menu.png");

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

    //Enum of all items player can carry.
    public enum INV_ITEMS{
        BIZ_CAS_ATTIRE (0),
        CLASS_RING (1),
        GATOR_HAT (2),
        GRAD_GOWN (3),
        SUIT_TIE (4),
        RED_BULL (5),
        STARBUCKS (6),
        DRAGON (7),
        CYCLONE (8),
        DAD (9),
        MACBOOK (10),
        NSPIRE (11),
        SOLDER (12),
        TI89 (13),
        USB_BLASTER (14),
        WIRE_KIT (15);

        private int numVal;

        INV_ITEMS(int numVal){
            this.numVal = numVal;
        }

        public int getNumVal(){
            return numVal;
        }

        public static INV_ITEMS getItem(int itemIndex) {
            for (INV_ITEMS i : INV_ITEMS.values()) {
                if (i.numVal == itemIndex) return i;
            }
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////INVENTORY ITEM SOUNDS///////////////////////////////////////////////////////////////
    static final Sound rustling = Gdx.audio.newSound(Gdx.files.internal("sound/effects/rustling.mp3"));
    static final Sound page = Gdx.audio.newSound(Gdx.files.internal("sound/effects/page.wav"));
    static final Sound oob_error = Gdx.audio.newSound(Gdx.files.internal("sound/effects/oob_error.wav"));

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////HERO MOVE TEXTURES///////////////////////////////////////////////////////////////
    public static final Texture boolean_logic_tex = new Texture("visuals/hero_moves/boolean_logic.png");
    public static final Texture code_testing_tex = new Texture("visuals/hero_moves/code_testing.png");
    public static final Texture commented_code_tex = new Texture("visuals/hero_moves/commented_code.png");
    public static final Texture cpp_skills_tex = new Texture("visuals/hero_moves/cpp_skills.png");
    public static final Texture documentation_tex = new Texture("visuals/hero_moves/documentation.png");
    public static final Texture double_integration_tex = new Texture("visuals/hero_moves/double_integration.png");
    public static final Texture extra_credit_tex = new Texture("visuals/hero_moves/extra_credit.png");
    public static final Texture f2_solve_tex = new Texture("visuals/hero_moves/f2_solve.png");
    public static final Texture f_lines_matlab_code_tex = new Texture("visuals/hero_moves/f_lines_matlab_code.png");
    public static final Texture java_function_tex = new Texture("visuals/hero_moves/java_function.png");
    public static final Texture karnaugh_map_tex = new Texture("visuals/hero_moves/karnaugh_map.png");
    public static final Texture nodal_analysis_tex = new Texture("visuals/hero_moves/nodal_analysis.png");
    public static final Texture perfect_presentation_tex = new Texture("visuals/hero_moves/perfect_presentation.png");
    public static final Texture practice_test_tex = new Texture("visuals/hero_moves/practice_test.png");
    public static final Texture recursive_function_tex = new Texture("visuals/hero_moves/recursive_function.png");
    public static final Texture set_equal_to_0_tex = new Texture("visuals/hero_moves/set_equal_to_0.png");
    public static final Texture soldering_skills_tex = new Texture("visuals/hero_moves/soldering_skills.png");
    public static final Texture stack_overflow_tex = new Texture("visuals/hero_moves/stack_overflow.png");

    //ADDITIONAL HERO SCREEN TEXTURES
    public static final Texture checkbox_tex = new Texture("visuals/Menus/checkbox.png");
    public static final Texture checkbox2_tex = new Texture("visuals/Menus/checkbox2.png");

    public static final Texture hero_stats_tex = new Texture("visuals/Menus/hero_statistics_menu.png");
    public static final Texture hero_moves_tex = new Texture("visuals/Menus/hero_moves_menu.png");
    public static final Texture sel_item_tex = new Texture("visuals/Menus/Selected Item Box.png");
    public static final Texture white_sq_tex = new Texture("visuals/Menus/white_sq.png");
    public static final Texture hero_degree_tex = new Texture("visuals/Menus/hero_degree_menu.png");

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
        gatorLogo.setAlpha(0);
        titleLogo.setAlpha(0);
        menuReady = false;
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////AVATAR SCREEN////////////////////////////////////////////////////////////////////////
        avatarScreenMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////GAME SCREEN///////////////////////////////////////////////////////////////////////////
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.4f);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////INVENTORY SCREEN//////////////////////////////////////////////////////////////////////
        inventoryScreenSelectionSound.setVolume(1, 1);
        gameMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public static boolean fadeSprite(float delta, Sprite sprite, float timeToFade){
        if(fadeStatus == fader.FADING_OUT){
            alpha -= (delta / timeToFade);
            if (alpha < 0) {
                alpha = 0;
            }
            sprite.setAlpha(alpha);
            if (alpha <= 0) {
                fadeStatus = fader.FINISHED_FADE_OUT;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_OUT) {
            fadeStatus = fader.FADING_IN;
            return true;
        }
        else if (fadeStatus == fader.FADING_IN) {
            alpha += (delta / timeToFade);
            if (alpha > 1) {
                alpha = 1;
            }
            sprite.setAlpha(alpha);
            if (alpha >= 1) {
                fadeStatus = fader.FINISHED_FADE_IN;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_IN) {
            alpha = 1;
            fadeStatus = fader.FADING_OUT;
            return false;
        }
        return false;
    }

    public static boolean fadeFont(float delta, BitmapFont font, float timeToFade){
        if(fadeStatus == fader.FADING_OUT){
            alpha -= (delta / timeToFade);
            if (alpha < 0) {
                alpha = 0;
            }
            font.setColor(1,1,1,alpha);
            if (alpha <= 0) {
                fadeStatus = fader.FINISHED_FADE_OUT;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_OUT) {
            fadeStatus = fader.FADING_IN;
            return true;
        }
        else if (fadeStatus == fader.FADING_IN) {
            alpha += (delta / timeToFade);
            if (alpha > 1) {
                alpha = 1;
            }
            font.setColor(1,1,1,alpha);
            if (alpha >= 1) {
                fadeStatus = fader.FINISHED_FADE_IN;
            }
            return false;
        }
        else if (fadeStatus == fader.FINISHED_FADE_IN) {
            alpha = 1;
            fadeStatus = fader.FADING_OUT;
            return false;
        }
        return false;
    }

}
