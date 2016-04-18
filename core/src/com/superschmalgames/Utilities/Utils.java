package com.superschmalgames.Utilities;

//Utilities class that manages all of the audio and visual assets for the game.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.superschmalgames.NPC.*;
import com.superschmalgames.Hero.*;
import java.text.DecimalFormat;

public class Utils {

    ////////////////////////////////////MAIN CLASS/////////////////////////////////////////////////////////////////////////////
    public static final int GAME_SCREEN_WIDTH = 1020;
    public static final int GAME_SCREEN_HEIGHT = 612;
    public static final int MAP_RESOLUTION = 64;
    public static final int MOVE_DIST = 5;
    public static final int N_MOVE_DIST = - MOVE_DIST;
    public static final BitmapFont font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
    //public static final BitmapFont font_large = new BitmapFont(Gdx.files.internal("RosesAreFF0_large.fnt"));
    public static final BitmapFont font_small = new BitmapFont(Gdx.files.internal("RosesAreFF0_small.fnt"));
    public static final BitmapFont font_medsmall = new BitmapFont(Gdx.files.internal("RosesAreFF0_medsmall.fnt"));
    public static final BitmapFont testFont = new BitmapFont();
    public static boolean isPaused;
    public static final FPSLogger logger = new FPSLogger();
    public static final DecimalFormat df1 = new DecimalFormat("0.00");
    public static final String savefile = "GatorQuest.save";
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
    public static final String menuOptions = "New Game\nLoad Game\nExit Game\nCredits";
    public static final String creditsText = "Thanks to our friends at freesound.org for \n" +
            "providing sound effects for the game!\n" +
            "atha89, EverHeat, nsstudios, distillerystudio\n\n" +
            "We also want to thank our friends over at \n" +
            "opengameart.org for providing sprite sheets \n" +
            "and map textures for our game!\n" +
            "Icons borrowed from:\n" +
            "http://piq.codeus.net/static/media/userpics/\n" +
            "piq_139831_400x400.png\n" +
            "http://www.clker.com/cliparts/2/k/n/l/C/Q/\n" +
            "transparent-green-checkmark-hi.png\n" +
            "http://emojipedia-us.s3.amazonaws.com/cache/\n" +
            "c5/1f/c51f0caacbf8fa9d8c5fa9d163657f62.png\n" +
            "\nMap/TileSets:\n" +
            "Map data copyright 2016 Google Imagery copyright\n" +
            "2016, DigitalGlobe, U.S. GeologicalSurvey\n" +
            "Rogulike Modern City pack and Roguelike Indoor \n" +
            "pack by Kenney Vleugels for Kenney (www.kenney.nl)";
    public static boolean menuReady;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////AVATAR SCREEN/////////////////////////////////////////////////////////////////////////
    public static final Music avatarScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Prelude.ogg"));
    public static final Sound avatarScreenSelectionSound = Gdx.audio.newSound(Gdx.files.internal("sound/effects/explosion.wav"));
    public static final Sprite profSchmalz = new Sprite(new Texture("visuals/intro_sequence/schmalz.png"));
    public static final Sprite avatarChoices = new Sprite(new Texture("visuals/sprite_sheets/Sprite_Color_Sel.png"));
    public static final String avatarPrompt = "Select your avatar!";
    public static final String nameInstr = "Press [ENTER] to select, or [BACKSPACE] to delete";
    public static final String introText1 = "Hello there! Allow me to introduce myself. I am Professor Schmalz, but most " +
            "people just call me the Gator Professor.";
    public static final String introText2 = "In the GatorQuest universe, it is your mission to face the challenges of " +
            "UF undergrad engineering studies in order to attain the rank of Gator Alum!";
    public static final String introText3 = "First, what is your name?";
    public static String introText4;
    public static final String introText5 = "Thanks, now it will be easier to put a name to the face.";
    public static final String introText6 = "This is your rival, he - wait, what? You don't have a rival? Oh, ok. The " +
            "last student I talked to insisted on having a rival. I thought it was sort of weird, too.";
    public static final String introText7 = "First off, remember to get out and explore a bit. No sense in sitting " +
            "around in your dorm room all day. Use the 'arrow' keys to stretch your legs and go for a walk. If " +
            "you ever do need to take a break, press 'F1' to make a note of what you were working on so you can pick it back later.";
    public static final String introText8 = "There are 8 professors whose classes you'll need to finish before you can " +
            "graduate. Make sure to talk to your advisor to find out which professor you should go see next. Press 'A' " +
            "to email Allison for advice.";
    public static final String introText9 = "You'll need to use many engineering tools during your studies. And don't " +
            "forget to bring a snack with you for those long study sessions! Press 'I' to keep track of everything in your bag.";
    public static final String introText10 = "It's always a treat to see how students learn and mature during their " +
            "time here at UF. Keep track of your progress at all times by pressing 'H'. Remember to keep track of how " +
            "many Gatorbucks you have so you'll be able to pay tuition!";
    public static final String introText11 = "Most students around campus are very nice, and just want to relax. But " +
            "beware! If you venture into any of the buildings containing classrooms, you'll surely run into TA's and " +
            "Professors looking to hand out assignments that can hurt your GPA.";
    public static final String introText12 = "These next few years will be challenging, but with hard work, I'm sure " +
            "you'll succeed. Remember, how do we eat an elephant? One bite at a time!";
    public static String introText13;
    public static final String nameSelect = "A B C D E F G H I\nJ K L M N O P Q R\nS T U V W X Y Z\n";
    public static final String nameLine = "--------";
    public static final String nameAccept = "OK!";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////COMBAT SCREEN/////////////////////////////////////////////////////////////////////////
    public static final Sprite combatBackground = new Sprite(new Texture("visuals/backgrounds/cise_battle.png"));
    public static final Music combatScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/soundtrack/Fight.ogg"));
    public static final Music victoryMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/battle_victory.mp3"));
    public static final Sound punchEffect1 = Gdx.audio.newSound(Gdx.files.internal("sound/effects/punch1.mp3"));
    public static final Sound punchEffect2 = Gdx.audio.newSound(Gdx.files.internal("sound/effects/punch2.mp3"));
    public static final Sprite combatBorder = new Sprite(new Texture("visuals/menus/combat_border.png"));
    public static final Sprite transBorder = new Sprite(new Texture("visuals/menus/combat_border_noline.png"));
    public static final Sprite hpBack = new Sprite(new Texture("visuals/menus/hp_back.png"));
    public static final Sprite hpBack2 = new Sprite(new Texture("visuals/menus/hp_back.png"));
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
    public static final TiledMap bookstore = new TmxMapLoader().load("visuals/maps/Bookstore.tmx");
    public static final int bookstore_x = 2090;
    public static final int bookstore_y = 3840;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    //public static final Texture checkbox_tex = new Texture("visuals/Menus/checkbox.png");
    public static final Texture checkbox2_tex = new Texture("visuals/Menus/checkbox2.png");

    public static final Texture hero_stats_tex = new Texture("visuals/Menus/hero_statistics_menu.png");
    public static final Texture hero_moves_tex = new Texture("visuals/Menus/hero_moves_menu.png");
    public static final Texture sel_item_tex = new Texture("visuals/Menus/Selected Item Box.png");
    public static final Texture white_sq_tex = new Texture("visuals/Menus/white_sq.png");
    public static final Texture hero_degree_tex = new Texture("visuals/Menus/hero_degree_menu.png");

    public static final Texture av1_tex = new Texture("visuals/sprite_sheets/sprite_walk_d7.png");
    public static final Texture av2_tex = new Texture("visuals/sprite_sheets/sprite_walk_d11.png");
    public static final Texture av3_tex = new Texture("visuals/sprite_sheets/sprite_walk_d10.png");
    public static final Texture av4_tex = new Texture("visuals/sprite_sheets/sprite_walk_d9.png");

    public static final Sprite av1_sprite = new Sprite(av1_tex);
    public static final Sprite av2_sprite = new Sprite(av2_tex);
    public static final Sprite av3_sprite = new Sprite(av3_tex);
    public static final Sprite av4_sprite = new Sprite(av4_tex);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////MOVES///////////////////////////////////////////////////////////////////////
    public static final H_Move Java_Function = new H_Move("Java Function", java_function_tex, "A move that benefits\nfrom improved Software \nskill.", false, 0, 0, 3,7,10,11, 3,7,10,11, 1,3,5,11,11);
    public static final H_Move Recursive_Loop = new H_Move("Recursive Loop",recursive_function_tex, "A move that benefits\nfrom improved Software \nskill.", false, 0, 0, 5,10,11,11, 5,10,11,11, 4,6,11,11,11);
    public static final H_Move Stack_Overflow = new H_Move("Stack Overflow",stack_overflow_tex, "A move that benefits\nfrom improved Software \nand Read/Write skills.", true, 0, 2, 3,5,8,11, 1,5,7,11, 2,4,6,11,11);
    public static final H_Move Commented_Code = new H_Move("Commented Code",commented_code_tex, "A move that benefits\nfrom improved Software\nand Read/Write skills.", true, 0, 2, 3,5,7,9, 2,4,6,8, 2,3,4,5,6);
    public static final H_Move Double_Integration = new H_Move("Double Integration",double_integration_tex, "A move that benefits\nfrom improved Math \nskills.", true, 5, 5, 2,4,10,11, 2,4,10,11, 2,4,5,11,11);
    public static final H_Move Set_Equal_to_0 = new H_Move("Set Equal to 0",set_equal_to_0_tex, "A move that benefits\nfrom improved Math \nskills.", true, 5, 5, 3,6,10,11, 3,6,10,11, 3,4,5,11,11);
    public static final H_Move Practice_Test = new H_Move("Practice Test",practice_test_tex, "A move that benefits\nfrom improved Focus \nskill.", true, 6, 6, 2,4,6,8, 2,4,6,8, 2,5,8,11,14);
    public static final H_Move Extra_Credit = new H_Move("Extra Credit",extra_credit_tex, "A move that benefits\nfrom improved Social\nand Focus skills", false, 4, 6, 3,5,7,9, 2,4,6,8, 1,3,5,7,9);
    public static final H_Move CPP_Skills = new H_Move("C++ Skills",cpp_skills_tex, "A move that benefits\nfrom improved Software\nskill.", false, 0, 0, 2,4,6,8, 2,4,6,8, 2,4,6,8,10);
    public static final H_Move Nodal_Analysis = new H_Move("Nodal Analysis",nodal_analysis_tex, "A move that benefits\nfrom improved Hardware\nskill.", false, 1, 1, 2,5,9,10, 2,5,9,10, 3,5,6,8,11);
    public static final H_Move F2_Solve = new H_Move("F2 Solve",f2_solve_tex, "A move that benefits\nfrom improved Math\nand Focus skills.", false, 5, 6, 3,10,10,10, 3,10,10,10, 4,5,5,5,5);
    public static final H_Move _5_Lines_Matlab_Code = new H_Move("5 Lines Matlab Code",f_lines_matlab_code_tex, "A move that benefits\nfrom improved Software\nand Math skills.", false, 0, 5, 4,6,7,10, 2,4,5,10, 4,5,6,7,11);
    public static final H_Move Karnaugh_Map = new H_Move("Karnaugh Map",karnaugh_map_tex, "A move that benefits\nfrom improved\nEndurance and\nHardware skills.", false, 1, 3, 2,4,10,10, 3,5,10,10, 4,6,8,11,11);
    public static final H_Move Soldering_Skills = new H_Move("Soldering Skills",soldering_skills_tex, "A move that benefits\nfrom improved\nEndurance and\nFocus skills.", false, 3, 6, 2,4,10,10, 2,4,10,10, 2,3,5,11,11);
    public static final H_Move Boolean_Logic = new H_Move("Boolean Logic",boolean_logic_tex, "A move that benefits\nfrom improved Math,\nFocus and Hardware\nskills.", false, 5, 1, 3,6,10,10, 3,6,10,10, 2,4,6,11,11);
    public static final H_Move Documentation = new H_Move("Documentation",documentation_tex, "A move that benefits\nfrom improved\nRead/Write skills.", false, 2, 2, 4,6,7,10, 4,6,7,10, 2,4,5,7,11);
    public static final H_Move Code_Testing = new H_Move("Code Testing",code_testing_tex, "A move that benefits\nfrom improved Software\nand Focus skills.", false, 0, 6, 4,6,7,10, 4,5,7,10, 3,4,6,7,11);
    public static final H_Move Perf_Presentation = new H_Move("Perf. Presentation",perfect_presentation_tex, "A move that benefits\nfrom improved Social\nskill.", false, 4, 4, 3,5,7,9, 3,5,7,9, 2,4,5,6,7);
    public static final E_Move New_Syntax = new E_Move("New Syntax", 0, 0, 3,7,10,10, 3,7,10,10, .4,.3,.2,.1,.1);
    public static final E_Move Uncompilable_Code = new E_Move("Uncompilable Code", 0, 3, 2,4,6,10, 2,4,6,10, .6,.5,.4,.2,.1);
    public static final E_Move Build_the_Circuit = new E_Move("Build the Circuit", 1, 6, 2,4,6,10, 1,3,5,10, .5,.4,.3,.1,.1);
    public static final E_Move Memory_Map_It = new E_Move("Memory Map It", 1, 5, 3,4,5,10, 1,2,3,10, .55,.5,.45,.3,.1);
    public static final E_Move Essay = new E_Move("essay", 2, 2, 3,7,10,10, 3,7,10,10, .6,.3,.2,.1,.1);
    public static final E_Move Ambiguous_Test_Question = new E_Move("Ambiguous Test Question", 2, 6, 3,5,8,10, 2,4,7,10, .75,.5,.25,0,0);
    public static final E_Move Insurmountable_Workload = new E_Move("Insurmountable Work Load", 3, 3, 2,4,6,8, 2,4,6,8, .8,.55,.4,.3,.2);
    public static final E_Move Simultaneous_Deadlines = new E_Move("Simultaneous Deadlines", 3, 3, 3,5,7,10, 3,5,7,10, .8,.7,.6,.5,.3);
    public static final E_Move Group_Project = new E_Move("Group Project", 4, 4, 2,4,6,8, 2,4,6,8, .8,.7,.55,.4,.2);
    public static final E_Move Partner_Up = new E_Move("Partner Up", 4, 4, 2,4,6,8, 2,4,6,8, .7,.6,.4,.3,.2);
    public static final E_Move Complex_Equation = new E_Move("Complex Equation", 5, 5, 2,4,6,8, 2,4,6,8, .7,.6,.5,.4,.3);
    public static final E_Move Find_the_Algorithm = new E_Move("Find the Algorithm", 5, 2, 2,4,10,10, 1,3,10,10, .8,.6,.4,.3,.1);
    public static final E_Move Off_by_One = new E_Move("Off By One", 5, 6, 4,6,10,10, 4,6,10,10, .8,.7,.6,.5,.4);
    public static final E_Move Debug_Error = new E_Move("Debug The Error", 0, 6, 3,5,7,10, 3,5,7,10, .8,.7,.5,.3,.2);
    public static final E_Move FFT_it = new E_Move("FFT It", 5, 6, 2,4,6,8, 3,5,7,9, .8,.5,.35,.25,.1);
    public static final E_Move Colloquialisms = new E_Move("Colloquialisms", 6, 6, 2,4,6,8, 2,4,6,8, .7,.6,.5,.4,.3);
    public static final E_Move Trig_sub_it = new E_Move("Trig Substitution", 5,5, 3,5,7,9, 3,5,7,9, .6,.5,.4,.3,.1);
    private static final E_Move[] Sriv_attacks = {Build_the_Circuit,Partner_Up,Complex_Equation,Find_the_Algorithm};
    private static final H_Move[] Sriv_weakness = {Nodal_Analysis,F2_Solve,Set_Equal_to_0};
    private static final E_Move[] Wong_attacks = {Ambiguous_Test_Question,Complex_Equation,New_Syntax,FFT_it};
    private static final H_Move[] Wong_weakness = {F2_Solve, Practice_Test, _5_Lines_Matlab_Code};
    private static final E_Move[] Gugel_attacks = {Insurmountable_Workload,Build_the_Circuit,Memory_Map_It,Off_by_One};
    private static final H_Move[] Gugel_weakness = {Karnaugh_Map,Soldering_Skills,Boolean_Logic};
    private static final E_Move[] Dobbins_attacks = {New_Syntax,Find_the_Algorithm,Uncompilable_Code,Debug_Error};
    private static final H_Move[] Dobbins_weakness = {Java_Function,Recursive_Loop,Stack_Overflow,Commented_Code};
    private static final E_Move[] Schmalz_attacks = {Essay,Group_Project,Colloquialisms,Uncompilable_Code};
    private static final H_Move[] Schmalz_weakness = {Code_Testing,Perf_Presentation};
    private static final E_Move[] Chui_attacks = {Complex_Equation,Find_the_Algorithm,Trig_sub_it,Off_by_One};
    private static final H_Move[] Chui_weakness = {Double_Integration, Set_Equal_to_0,Practice_Test,Extra_Credit};
    private static final E_Move[] Horton_attacks = {New_Syntax,Uncompilable_Code,Group_Project,Ambiguous_Test_Question};
    private static final H_Move[] Horton_weakness = {CPP_Skills,Stack_Overflow,Extra_Credit};
    private static final E_Move[] Small_attacks = {Uncompilable_Code,Insurmountable_Workload,Simultaneous_Deadlines,Debug_Error};
    private static final H_Move[] Small_weakness = {Documentation,Code_Testing};
    private static final E_Move[] Cise_A_attacks = {New_Syntax, Find_the_Algorithm};
    private static final E_Move[] Cise_B_attacks = {Find_the_Algorithm, Uncompilable_Code};
    private static final E_Move[] Cise_C_attacks = {Uncompilable_Code, Debug_Error};
    private static final E_Move[] Cise_D_attacks = {Essay, Group_Project};
    private static final E_Move[] Cise_E_attacks = {Group_Project,Colloquialisms};
    private static final E_Move[] Cise_F_attacks = {Colloquialisms,Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_1 = {New_Syntax};
    private static final E_Move[] Cise_random_attacks_2 = {Find_the_Algorithm};
    private static final E_Move[] Cise_random_attacks_3 = {Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_4 = {Debug_Error};
    private static final E_Move[] Cise_random_attacks_5 = {Essay};
    private static final E_Move[] Cise_random_attacks_6 = {Group_Project};
    private static final E_Move[] Cise_random_attacks_7 = {Colloquialisms};
    private static final E_Move[] Cise_random_attacks_8 = {Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_9 = {New_Syntax};
    private static final E_Move[] Cise_random_attacks_10 = {Find_the_Algorithm};
    private static final E_Move[] Cise_random_attacks_11 = {Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_12 = {Debug_Error};
    private static final E_Move[] Cise_random_attacks_13 = {Essay};
    private static final E_Move[] Cise_random_attacks_14 = {Group_Project};
    private static final E_Move[] Cise_random_attacks_15 = {Colloquialisms};
    private static final E_Move[] Cise_random_attacks_16 = {Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_17 = {New_Syntax};
    private static final E_Move[] Cise_random_attacks_18 = {Find_the_Algorithm};
    private static final E_Move[] Cise_random_attacks_19 = {Uncompilable_Code};
    private static final E_Move[] Cise_random_attacks_20 = {Debug_Error};
    private static final E_Move[] Cise_random_attacks_21 = {Essay};
    private static final E_Move[] Cise_random_attacks_22 = {Group_Project};
    private static final E_Move[] Cise_random_attacks_23 = {Colloquialisms};
    private static final E_Move[] Cise_random_attacks_24 = {New_Syntax};
    private static final E_Move[] Cise_random_attacks_25 = {Find_the_Algorithm};
    private static final E_Move[] Cise_random_attacks_26 = {Debug_Error};
    private static final E_Move[] Cise_random_attacks_27 = {Essay};
    private static final E_Move[] Cise_random_attacks_28 = {Group_Project};
    private static final E_Move[] Neb_A_attacks = {Build_the_Circuit,Partner_Up};
    private static final E_Move[] Neb_B_attacks = {Partner_Up,Complex_Equation};
    private static final E_Move[] Neb_C_attacks = {Complex_Equation,Find_the_Algorithm};
    private static final E_Move[] Neb_D_attacks = {Ambiguous_Test_Question,Complex_Equation};
    private static final E_Move[] Neb_E_attacks = {Complex_Equation,New_Syntax};
    private static final E_Move[] Neb_F_attacks = {New_Syntax,FFT_it};
    private static final E_Move[] Neb_G_attacks = {Insurmountable_Workload,Build_the_Circuit};
    private static final E_Move[] Neb_H_attacks = {Build_the_Circuit,Memory_Map_It};
    private static final E_Move[] Neb_I_attacks = {Memory_Map_It,Off_by_One};
    private static final E_Move[] Neb_random_attacks_1 = {Build_the_Circuit};
    private static final E_Move[] Neb_random_attacks_2 = {Partner_Up};
    private static final E_Move[] Neb_random_attacks_3 = {Complex_Equation};
    private static final E_Move[] Neb_random_attacks_4 = {Find_the_Algorithm};
    private static final E_Move[] Neb_random_attacks_5 = {Ambiguous_Test_Question};
    private static final E_Move[] Neb_random_attacks_6 = {New_Syntax};
    private static final E_Move[] Neb_random_attacks_7 = {FFT_it};
    private static final E_Move[] Neb_random_attacks_8 = {Insurmountable_Workload};
    private static final E_Move[] Neb_random_attacks_9 = {Memory_Map_It};
    private static final E_Move[] Neb_random_attacks_10 = {Off_by_One};
    private static final E_Move[] Neb_random_attacks_11 = {Build_the_Circuit};
    private static final E_Move[] Neb_random_attacks_12 = {Partner_Up};
    private static final E_Move[] Neb_random_attacks_13 = {Complex_Equation};
    private static final E_Move[] Neb_random_attacks_14 = {Find_the_Algorithm};
    private static final E_Move[] Neb_random_attacks_15 = {Ambiguous_Test_Question};
    private static final E_Move[] Neb_random_attacks_16 = {New_Syntax};
    private static final E_Move[] Neb_random_attacks_17 = {FFT_it};
    private static final E_Move[] Neb_random_attacks_18 = {Insurmountable_Workload};
    private static final E_Move[] Neb_random_attacks_19 = {Memory_Map_It};
    private static final E_Move[] Neb_random_attacks_20 = {Off_by_One};
    private static final E_Move[] Neb_random_attacks_21 = {Build_the_Circuit};
    private static final E_Move[] Neb_random_attacks_22 = {Partner_Up};
    private static final E_Move[] Neb_random_attacks_23 = {Complex_Equation};
    private static final E_Move[] Neb_random_attacks_24 = {Find_the_Algorithm};
    private static final E_Move[] Neb_random_attacks_25 = {Ambiguous_Test_Question};
    private static final E_Move[] Neb_random_attacks_26 = {New_Syntax};
    private static final E_Move[] Neb_random_attacks_27 = {FFT_it};
    private static final E_Move[] Neb_random_attacks_28 = {Insurmountable_Workload};
    private static final E_Move[] Turlington_A_attacks = {Complex_Equation, Find_the_Algorithm};
    private static final E_Move[] Turlington_B_attacks = {Find_the_Algorithm,Trig_sub_it};
    private static final E_Move[] Turlington_C_attacks = {Trig_sub_it, Off_by_One};
    private static final E_Move[] Turlington_D_attacks = {New_Syntax,Uncompilable_Code};
    private static final E_Move[] Turlington_E_attacks = {Uncompilable_Code,Group_Project};
    private static final E_Move[] Turlington_F_attacks = {Group_Project,Ambiguous_Test_Question};
    private static final E_Move[] Turlington_G_attacks = {Uncompilable_Code,Insurmountable_Workload};
    private static final E_Move[] Turlington_H_attacks = {Insurmountable_Workload,Simultaneous_Deadlines};
    private static final E_Move[] Turlington_I_attacks = {Simultaneous_Deadlines,Debug_Error};
    private static final E_Move[] Turlington_random_attacks_1 = {Complex_Equation};
    private static final E_Move[] Turlington_random_attacks_2 = {Find_the_Algorithm};
    private static final E_Move[] Turlington_random_attacks_3 = {Trig_sub_it};
    private static final E_Move[] Turlington_random_attacks_4 = {Off_by_One};
    private static final E_Move[] Turlington_random_attacks_5 = {New_Syntax};
    private static final E_Move[] Turlington_random_attacks_6 = {Uncompilable_Code};
    private static final E_Move[] Turlington_random_attacks_7 = {Group_Project};
    private static final E_Move[] Turlington_random_attacks_8 = {Ambiguous_Test_Question};
    private static final E_Move[] Turlington_random_attacks_9 = {Uncompilable_Code};
    private static final E_Move[] Turlington_random_attacks_10 = {Insurmountable_Workload};
    private static final E_Move[] Turlington_random_attacks_11 = {Simultaneous_Deadlines};
    private static final E_Move[] Turlington_random_attacks_12 = {Debug_Error};
    private static final E_Move[] Turlington_random_attacks_13 = {Complex_Equation};
    private static final E_Move[] Turlington_random_attacks_14 = {Find_the_Algorithm};
    private static final E_Move[] Turlington_random_attacks_15 = {Trig_sub_it};
    private static final E_Move[] Turlington_random_attacks_16 = {Off_by_One};
    private static final E_Move[] Turlington_random_attacks_17 = {Uncompilable_Code};
    private static final E_Move[] Turlington_random_attacks_18 = {Group_Project};
    private static final E_Move[] Turlington_random_attacks_19 = {Ambiguous_Test_Question};
    private static final E_Move[] Turlington_random_attacks_20 = {Insurmountable_Workload};
    private static final E_Move[] Turlington_random_attacks_21 = {Simultaneous_Deadlines};
    private static final E_Move[] Turlington_random_attacks_22 = {Debug_Error};
    private static final E_Move[] Turlington_random_attacks_23 = {Complex_Equation};
    private static final E_Move[] Turlington_random_attacks_24 = {Find_the_Algorithm};
    private static final E_Move[] Turlington_random_attacks_25 = {Trig_sub_it};
    private static final E_Move[] Turlington_random_attacks_26 = {Off_by_One};
    private static final E_Move[] Turlington_random_attacks_27 = {Group_Project};
    private static final E_Move[] Turlington_random_attacks_28 = {Ambiguous_Test_Question};
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////NPC DIALOGUE START///////////////////////////////////////////////////////////////////////////////////////
    public static final String dormA = "Open up your inventory with I. You can equip and remove items here using E and R respectively and view your consumables.";
    public static final String dormB = "Don\'t forget to look at your Degree Audit! Press h to see it in the Hero menu, and it will tell you the order of the classes you have to complete.";
    public static final String dormC = "Your Campus Map will tell you where all of the buildings on campus are.";
    public static final String dormD = "This dining hall food is giving me some serious stomach issues…*belch* Oops!";
    //public static final String dormEInitial = "Will you proofread my essay for me? I\'m nervous that I\'m going to get a bad grade on my first college paper…";
    //public static final String dormEYes = "Your Reading Comprehension and Writing Skill increased by 1.";
    //public static final String dormENo = "Okay fine.";
    //public static final String dormEUsed = "I got a B on that paper.";
    public static final String dormF = "It seems like there are so many classes I have to take! How am I going to fit all of this into 8 semesters???";
    //public static final String dormGInitial = "Is it just me, or does this dorm feel like a jail cell?";
    //public static final String dormGYes = "Your Social Skills increased by 1.";
    //public static final String dormGNo = "Y i k e s.";
    //public static final String dormGUsed = "This place is a jail!";

    public static final String worldH = "UF is a great place to go to school. We have so many great Alumni like GloZell, Ryan Lochte and even Dan Bilzerian!";
    public static final String worldI = "College is so easy. I was an IB Student in high school and that was WAY harder. Yes, I\'m a business major. Why do you ask? What does that have to do with this?";
    public static final String worldJ = "I\'m late I\'m late I\'m late I\'m late";
    public static final String worldK = "I just missed my RTS bus and the next one doesn\'t come for another 15 minutes.";
    public static final String worldL = "Jimmy Johns just brought me my sandwich in 3 and a half minutes. That really is freaky fast delivery.";
    public static final String worldM = "The Reitz construction is finally done! Bless. Up.";
    //public static final String worldNInitial = "I love math! It\'s the universal language, you know? Do you want me to teach you a trick I learned today in class?";
    //public static final String worldNYes = "Your Math Skills increased by 1.";
    //public static final String worldNNo = "I mean...Okay.";
    //public static final String worldNUsed = "I hope that trick comes in handy.";
    public static final String worldO = "I\'m reading Siddhartha and I really think it\'s opened my eyes. I feel so enlightened. Come here, I want you to be in my Snap Story.";
    public static final String worldP = "Well, I\'m technically a junior by credits. No, I mean, this is my first semester at college. If you factor in my AP classes, though...";
    public static final String worldQ = "You should never let your GPA drop to 0.00! When that happened to me, I woke up back in my down with a ton of Gator Bucks missing.";
    //public static final String worldRInitial = "I\'m having a really tough time finding my next class. It\'s in Turlington. Can you point me in the right direction?";
    //public static final String worldRYes = "Your Social Skills increased by 1.";
    //public static final String worldRNo = "K bye.";
    //public static final String worldRUsed = "I can do this!";
    //public static final String worldRRInitial = "I heard that some professors are suckers for particular types of moves. Do you have to take Calc 2?";
    //public static final String worldRRYes = "Ryan taught you how to utilize Extra Credit";
    //public static final String worldRRNo = "Ah, you're lucky.";
    //public static final String worldRRUsed = "Good luck with Calc 2.";
    public static final String worldS = "I heard that there are kids who will tutor you in Marston library, but some of them charge way too many Gator Bucks for me...";
    public static final String worldT = "TA\'s and professors will challenge you if you walk around in one of the class buildings!";
    //public static final String worldUInitial = "Do you have to take Dr. Wong?";
    //public static final String worldUYes = "Uke taught you how to use 5 Lines Matlab Code";
    //public static final String worldUNo = "Ohh, I understand.";
    //public static final String worldUUsed = "Have fun!";
    public static final String worldV = "College is hard, but I think it\'s worth it.";
    //public static final String worldWInitial = "Look at what I just learned how to do!?";
    //public static final String worldWYes = "Wanda taught you how to use F2 Solve";
    //public static final String worldWNo = "Fine. Rude.";
    //public static final String worldWUsed = "Isn\'t that a cool trick?";
    public static final String worldX = "I\'m overwhelmed with all of these assignments, and I don\'t think all of this coffee is helping my anxiety!";
    //public static final String worldYInitial = "Oh, you\'re an engineer? Maybe you would have some use for this? Would you like to buy my old TI-89? I\'ll sell it to you for 80 Gator Bucks.";
    //public static final String worldYYesInsfFunds = "You need more money.";
    //public static final String worldYYesSuffFunds = "Here you go. You receive a TI-89.";
    //public static final String worldYNo = "Fine, then.";
    //public static final String worldYUsed = "Hope it helps you.";
    //public static final String worldZInitial = "You\'re a gator fan too, huh? Want to buy a Gator Hat for 30 Gator bucks?";
    //public static final String worldZYesInsfFunds = "Get that money up, brother.";
    //public static final String worldZYesSuffFunds = "Alright, sweet. You receive a Gator Hat.";
    //public static final String worldZNo = "Nevermind, then.";
    //public static final String worldZUsed = "It will look good on you!";
    public static final String turlingtonAPre = "haven\'t you ever heard of trig sub?";
    public static final String turlingtonAWin = "You\'ll need to review your unit circle...";
    public static final String turlingtonALose = "It would seem that you\'ve gotten the hang of this...";
    public static final String turlingtonBPre = "You need to integrate math into your life. Get it?";
    public static final String turlingtonBWin = "Perhaps you should review those theorems.";
    public static final String turlingtonBLose = "Good job! I\'m proud of you.";
    public static final String turlingtonCPre = "It\'s called a cardioid because it looks like a heart. Can\'t you see?";
    public static final String turlingtonCWin = "You\'ll have to take another look at those homeworks...";
    public static final String turlingtonCLose = "It would appear that you get the picture.";
    public static final String turlingtonDPre = "There will be no texting in my class!";
    public static final String turlingtonDWin = "You should have been paying more attention.";
    public static final String turlingtonDLose = "I guess your focus paid off.";
    public static final String turlingtonEPre = "Make sure to keep track of your pointers.";
    public static final String turlingtonEWin = "You\'re having an error.";
    public static final String turlingtonELose = "Flawless code. Good work.";
    public static final String turlingtonFPre = "I love group projects!";
    public static final String turlingtonFWin = "You have to be a team player.";
    public static final String turlingtonFLose = "Collaboration is your strong-suit.";
    public static final String turlingtonGPre = "Why am I here?";
    public static final String turlingtonGWin = "I literally hate every college student.";
    public static final String turlingtonGLose = "I literally hate every college student.";
    public static final String turlingtonHPre = "What do you mean you\'ve never worked with a binary search tree?";
    public static final String turlingtonHWin = "Do you even know who Djikstra is?";
    public static final String turlingtonHLose = "You found the shortest path. I commend you.";
    public static final String turlingtonIPre = "Linked lists are your friend!";
    public static final String turlingtonIWin = "It seems like your links got broken...!";
    public static final String turlingtonILose = "Your chain of links held strong!";

    public static final String ciseJPre = "Coding is fun! I like working in Python.";
    public static final String ciseJWin = "Maybe you should start with some online tutorials...";
    public static final String ciseJLose = "I think you\'ve got the hang of this!";
    public static final String ciseKPre = "Have you ever heard of recursion?";
    public static final String ciseKWin = "Maybe you got lost in the branches?";
    public static final String ciseKLose = "All of your outputs looked right!";
    public static final String ciseLPre = "Int, Boolean, Double, Char...You need to know the difference!";
    public static final String ciseLWin = "Maybe a little more review was needed.";
    public static final String ciseLLose = "You seem to have everything in order.";
    public static final String ciseMPre = "My research is everything to me.";
    public static final String ciseMWin = "You forgot your bibliography!";
    public static final String ciseMLose = "This is a solid paper.";
    public static final String ciseNPre = "I love working with other engineers. Iron sharpens iron.";
    public static final String ciseNWin = "This collaboration just didn\'t work out.";
    public static final String ciseNLose = "You all clearly worked well together!";
    public static final String ciseOPre = "Show me the data and I\'ll believe it.";
    public static final String ciseOWin = "This methodology is flawed.";
    public static final String ciseOLose = "I think we should try to publish this!";

    public static final String nebPPre = "Make sure that your circuit is grounded.";
    public static final String nebPWin = "Your nodal analysis is off.";
    public static final String nebPLose = "Good work. This is the correct voltage.";
    public static final String nebQPre = "Kirchoff\'s law is an invaluable tool for you.";
    public static final String nebQWin = "This doesn\'t add up!";
    public static final String nebQLose = "Everything balances out.";
    public static final String nebRPre = "You should try using a wheatstone bridge.";
    public static final String nebRWin = "You need to review your electrical engineering history.";
    public static final String nebRLose = "Good work on finding the missing value.";
    public static final String nebSPre = "Signals are all around us!";
    public static final String nebSWin = "You should try to learn about your surroundings.";
    public static final String nebSLose = "We\'re on the same frequency, it would seem.";
    public static final String nebTPre = "Have you heard of a fourier transform?";
    public static final String nebTWin = "These calculations don\'t seem right.";
    public static final String nebTLose = "You got the right answer!";
    public static final String nebUPre = "Matlab is a valuable tool in our industry.";
    public static final String nebUWin = "You should have utilized the help option if you were confused.";
    public static final String nebULose = "All of your functions are correct.";
    public static final String nebVPre = "You have to be familiar with low-true logic.";
    public static final String nebVWin = "You\'re missing some inverters.";
    public static final String nebVLose = "Nice diagramming!";
    public static final String nebWPre = "If you did your lab then you should have no problem with this quiz.";
    public static final String nebWWin = "Are you sure you built this circuit yourself?";
    public static final String nebWLose = "Your stoplight works! Good work.";
    public static final String nebXPre = "Make sure to minimize the states in your UML.";
    public static final String nebXWin = "You\'re missing some signals in your diagram.";
    public static final String nebXLose = "You did it in the least amount of states possible!";

    public static final String marstonA = "I came here to study, but it seems like everyone else just came to socialize...Why are they at the library?";
    public static final String marstonB = "Sorority recruitment is such a nightmare. I can\'t believe they want us to wear heels the whole day!";
    public static final String marstonC = "I\'m here to meet up with people for a group project but I\'m the only one that showed up. They did mean 10:30AM...Right?";
    public static final String marstonD = "Welcome to the Marston Library, talk to the lady at the main desk to restore your GPA, or visit the Starbucks to get some supplies";
    public static final String marstonE = "There are so many books here, but I don\'t think I\'ve ever used any of them. I just go on my laptop when I\'m here.";
    public static final String marstonF = "I\'m calculating the lowest grade that I can get on this final that will still let me pass the class. It looks like I need a 99%...That can\'t be right.";
    public static final String marstonG = "I\'m studying for pre-calc right now. This is the hardest class ever! I aced it in high school, but it\'s just so different here...";
    public static final String marstonH = "The library always stresses me out because everyone else seems stressed out. I think it might be contagious...";
    public static final String marstonI = "I\'m tired of reading! I already read 45 pages about Ancient Civilizations today. Can I stop now?";
    public static final String marstonJ = "Don\'t bother me! I\'m studying.";
    //public static final String marstonKUnused = "Oh, you\'re a computer engineer? You can have this old thing. I switched my major to Business, so I won\'t be needing it… Avatar receives a Soldering Iron";
    //public static final String marstonKUsed = "Enjoy that soldering iron. Try not to burn yourself.";
    //public static final String marstonLUnused = "I\'m getting my PhD in mathematics at UF, so I can tutor you if you want. It\\'s $300 per session.";
    //public static final String marstonLYesInsfFunds = "Sorry, you don\'t have the money...";
    //public static final String marstonLNo = "Let me know if you change your mind.";
    //public static final String marstonLSuffFunds = "Alright, let's get started. Your Math Skills increase by 1 and you pay 300GB.";
    //public static final String marstonLUsed = "I hope that helped.";
    //public static final String marstonMUnused = "I need someone with a Software Skill of at least 4 to help me with this Java 1 project! Will you help me?";
    //public static final String marstonMYesInsfSkill = "I don't think you have the skills required...";
    //public static final String marstonMNo = "I'll have tp figure it out on my own.";
    //public static final String marstonMYesSuffSkill = "Thanks! It compiled this time. You eared 200 GB.";
    //public static final String marstonMUsed = "Thanks for the help!";
    //public static final String marstonNUnused = "Hi! Would you like a class ring to value your time here at UF forever? Each ring contains a beautiful gem and it will be a constant reminder of your amazing adventures here. Would you like to buy one? A class ring costs 300.";
    //public static final String marstonNYesInsfFunds = "Sorry, you'll need more money than that.";
    //public static final String marstonNNo = "Thanks! Here's your ring. Received Class Ring.";
    //public static final String marstonNSuffFunds = "Maybe next time?";
    //public static final String marstonNUsed = "Enjoy your ring!";
    //public static final String marstonOUnused = "Do you want to buy my USB Blaster? I\'m selling it for only 50GB. That\'s a steal!";
    //public static final String marstonOYesInsfFunds = "Erm, it costs 50GB.";
    //public static final String marstonONo = "Understandable.";
    //public static final String marstonOSuffFunds = "Here you go! You receive a USB Blaster.";
    //public static final String marstonOUsed = "Thanks for taking it off my hands.";
    public static final String neb1 = "I\'m in Digital Integrated Circuits right now and it\'s great.";
    public static final String neb2 = "I\'m loving this #HarrisRotunda. #Harris is such a good company. #Sponsored #PayMeHarris";
    public static final String neb3 = "Dr. Turner is just the best. Such a nice guy, and he actually likes teaching. What a concept, huh?";
    public static final String neb4 = "I hate it when a professor just blasts through 70 powerpoint slides and then calls it a lecture. How is that helpful? How is that teaching?";
    public static final String neb5 = "I survive on coffee.";
    public static final String neb6 = "Oh yea? Well I only got 2 hours of sleep last night. Top THAT.";
    public static final String neb7 = "I\'m pretty stoked about being an engineer. I love feeling employable.";
    public static final String neb8 = "I\'m in Digital Design right now and it\'s actually really cool. It\'s like Digital Logic but more code-y, and you get to do more stuff.";
    public static final String neb9 = "Wire wrapping is hell on earth.";
    public static final String neb10 = "Dr. Stitt is a champion. Nice, professional, and a good teacher. ";
    public static final String neb11 = "I lost my toolbox!";
    public static final String neb12 = "Yes! I just got an A on a test!";
    public static final String neb13 = "Engineering is all about beating the curve. It doesn\'t matter if you got a 65 if the average on the test was a 57. Just beat the curve, and you\'ll succeed here as a Gator Engineer.";
    public static final String neb14 = "I like that we get a lot of hands-on learning here at UF. The labs are tough, and they take up a lot of time, but I really feel like I\'m learning.";
    public static final String neb15 = "I\'m beginning to realize that I\'m going to graduate soon. I\'m not ready to be part of the real world!";
    public static final String neb16 = "My name is Julio Chavez. Nope, not like that Julio Chavez ;)";
    public static final String neb17 = "Have you heard about Scala? It\'s this great programming language. You\'re a total newb if you don\'t use it.";
    public static final String neb18 = "Yep, I\'m the TA for Senior Design. No, I haven\'t taken Senior Design.";
    public static final String neb19 = "These engineers stress me out. I mean, I get that these classes are hard but...Chill? A little? Please?";
    public static final String neb20 = "This is stressful.";
    public static final String neb21 = "I think that there\'s a real problem with the unattainable expectations we set for women in today\'s society.";
    public static final String neb22 = "I need to visit a dentist. I have a molar that hurts when I chew hot foods.";
    public static final String neb23 = "I accidently sat on my glasses yesterday and they snapped! I\'m wearing my contacts right now.";
    public static final String neb24 = "I got sunburned at the lake the other day. I put a little aloe vera yesterday and it\'s better today, but it still hurts a little.";
    public static final String neb25 = "I hate how cold they keep the inside of these class buildings! I go from sweating outside in shorts and a tank to shivering when I\'m in class.";
    public static final String neb26 = "I think I\'m getting sick. I woke up with a sore throat.";
    public static final String neb27 = "UF is always under construction. They\'re building a new chemistry building on the north side of campus, and they just finished a new dorm over by the business school.";
    public static final String neb28 = "I think I want to get my nose pierced.";
    public static final String neb29 = "Relish has the best burgers ever.";
    public static final String neb30 = "I can\'t wait for the summer! I have an internship lined up.";
    public static final String neb31 = "I\'m a senior, so I\'ll be out of here in no time. It\'s such a bittersweet feeling.";
    public static final String neb32 = "My phone is at 11% and it\'s only noon. This is insane! I need to carry a charger with me everywhere I go.";
    public static final String neb33 = "If the UF wifi knocks me off one more time I am actually going to scream.";
    public static final String neb34 = "I haven\'t worn my retainer in almost a month and I\'m worried that my teeth are shifting. I don\'t want to have to wear braces again!";
    public static final String neb35 = "I want to travel the world! Germany is at the top of my list.";
    public static final String neb36 = "I did yoga this morning and now I\'m feeling limber and energized.";
    public static final String neb37 = "Choosing my major is making me feel so lost. I don\'t know what I want for lunch. How am I supposed to decide what job I want for my whole life?";
    public static final String neb38 = "Facebook is pretty much dead. Instagram and Snapchat have taken over.";
    public static final String neb39 = "I hope my teacher doesn\'t give us any more projects. I have enough work to do as it is!";
    public static final String neb40 = "Online homework is the worst. I always leave it until the last minute and, since it\'s due at midnight, that means I\'m working late.";
    public static final String neb41 = "Being smart is nice, but being nice is more important.";
    public static final String neb42 = "The UF infirmary is a joke. The RN there wouldn\'t give me anything.";
    public static final String neb43 = "It\'s pretty tough to graduate in only 4 years with all of these classes they keep adding.";
    public static final String neb44 = "I\'m debating whether I should be an electrical engineer or a computer engineer. There aren\'t too many differences from what I can tell, but it seems like an important decision.";
    public static final String neb45 = "GPD has given me 4 parking tickets in the last year. This is absurd.";
    public static final String neb46 = "I\'m getting sick of these professors who want to give no notice for quizzes and tests. Have a syllabus and follow it.";
    public static final String neb47 = "I love the smoothies from Little Hall Express. They\'re so tasty!";
    public static final String neb48 = "I\'m learning to enjoy red wine now that I\'m finally 21. It seems like such a classy drink to me.";
    public static final String neb49 = "I was up all night studying for a test... Could really use some rest.";
    public static final String neb50 = "I'm so tired of having to repeat classes... It's getting to pricey.";
    public static final String neb51 = "Oh crap, I'm late turning in this assignment and my computer won't connect to the school's wifi.";
    public static final String neb52 = "I tried the sushi at the store here... It was a mistake.";
    public static final String neb53 = "Have you ever seen the senior design presentations done here?";
    public static final String neb54 = "Balancing school and work is getting to be too tiring.";
    public static final String neb55 = "Have you thought about joining any of the engineering societies?";
    public static final String cise1 = "No Southern Accent is actually a lot of fun. Our album is dropping on iTunes this week.";
    public static final String cise2 = "I\'m taking Object Oriented Programming with Dave Small because I enjoy feeling pain.";
    public static final String cise3 = "So many noobs at this school. Like, how do you not know how to write a simple Perl script? How can you not understand simple Javascript? I have a poor relationship with my father.";
    public static final String cise4 = "I\'m in Discrete Math and I like the logic based stuff.";
    public static final String cise5 = "I love Dr. Williamson. Linear Algebra is so easy and fun.";
    public static final String cise6 = "Programming 1 is in Java but Programming 2 is in C++. ";
    public static final String cise7 = "I\'m CS in the LAS college so I have to do a minor. Should I do a math minor or a history minor?";
    public static final String cise8 = "I don\'t really understand the choices some people make.";
    public static final String cise9 = "This Buzzfeed quiz told me I\'m destined to marry Bieber.";
    public static final String cise10 = "Should I vote for Bernie or Hillary? Trump is out since I\'m not a racist.";
    public static final String cise11 = "I\'m a crossfit pro.";
    public static final String cise12 = "Freshman girls should stay away from Pike.";
    public static final String cise13 = "Mac or PC? What about Linux?";
    public static final String cise14 = "My Smash Bros main is Cloud. Probably Zero Suit if I have to choose from non-DLC characters.";
    public static final String cise15 = "What\'s your favorite anime?";
    public static final String cise16 = "The Good Wife is the best show on television. You need to watch it as soon as possible.";
    public static final String cise17 = "I just chugged a monster and took an exam. Hopefully my writing was legible. My hand was shaking a little bit.";
    public static final String cise18 = "I have an OS test tomorrow and I couldn\'t be more nervous. This is too stressful.";
    public static final String cise19 = "You can get your Ethics requirement by taking Intro to ECE. Lifehack.";
    public static final String cise20 = "It\'s pretty hard to graduate in 4 years as an engineer these days. My major has 14 more required credits than my roommate\'s.";
    public static final String cise21 = "I love Gator football.";
    public static final String cise22 = "We send more kids to the Olympics than any other school.";
    public static final String cise23 = "I really like volleyball. It\'s all about that bump-set-spike.";
    public static final String cise24 = "I love seeing athletes out at bars. They can\'t hide because they\'re all like 6\'5”.";
    public static final String cise25 = "I\'m so nauseous. I think I drank too much last night.";
    public static final String cise26 = "You look like a friend I knew in high school.";
    public static final String cise27 = "Do you live in a dorm? I\'m in Lakeside which is kinda a dorm.";
    public static final String cise28 = "Did you rush? I thought about it, but eventually decided it wasn\'t for me.";
    public static final String cise29 = "I\'m a chemistry major, but I decided to explore campus a little today.";
    public static final String cise30 = "I think I want to work in academia down the road. I guess that means I need a PhD.";
    public static final String cise31 = "Job security is nice, but is this my real passion?";
    public static final String cise32 = "Have you ever been to Rockey\'s? I like singing along to all of the throwbacks.";
    public static final String cise33 = "The Gator Nation is everywhere. I visited a friend all the way in California and saw a Gator flag flying on some dude\'s house.";
    public static final String tur1 = "This is the most confusing building on our entire campus.";
    public static final String tur2 = "This is such an interesting building. It\'s in the heart of campus. Definitely a part of UF history.";
    public static final String tur3 = "Classes of all disciplines are held in this building. I think that\'s really neat.";
    public static final String tur4 = "I need to find my lecture hall, but none of them have a sign for it.";
    public static final String tur5 = "The bathrooms in this building are absolutely disgusting.";
    public static final String tur6 = "The artwork in the plaza looks like a potato.";
    public static final String tur7 = "There are always the most random people at the tables in Turlington Plaza. Don\'t these people have class? Or jobs? Ambitions?";
    public static final String tur8 = "I donated $1 to get a free donut in Turlington Plaza. The money is going to save puppies.";
    public static final String tur9 = "Managerial accounting is the worst.";
    public static final String tur10 = "Hello, my name is Patrick and I\'m the Director of UF CURBS which is the Center for Undergraduate Research. We are a very important organization which works with the department to mentor students on how they can obtain a research position at the University. We try to match potential students with ...where are you going?";
    public static final String tur11 = "This building is a nightmare.";
    public static final String tur12 = "I only have classes on Mondays and Fridays.";
    public static final String tur13 = "I\'m shooting a video to apply for a Buzzfeed internship. I want to be a director some day. I\'m going to make a movie about JFK that takes place in the early 2000s.";
    public static final String tur14 = "I\'m only here to take a picture for my sorority. Yea, they make us get points. I\'m Ariel. No, sorry, I\'m busy.";
    public static final String tur15 = "Hey ya I\'m Dustin. Haha yea.";
    public static final String tur16 = "I love that we have like 3 Chick-Fil-A\'s on Campus. That\'s wild.";
    public static final String tur17 = "I really like going to the UF Gymnastics matches.";
    public static final String tur18 = "I\'m in an Organic Chemistry class. I feel so fancy and educated.";
    public static final String tur19 = "Pre-Med isn\'t actually a major these days. You can be Pre-Med with any major as long as you take the required classes for the MCAT.";
    public static final String tur20 = "I\'m really into The Weeknd these days. What type of music do you like?";
    public static final String tur21 = "I like trying different types of food. Recently I tried Thai food for the first time, and it was actually really good! They use a lot of coconut and peanut flavors.";
    public static final String tur22 = "I\'m trying to get into shape this semester. I\'m monitoring my calorie intake and hitting the gym at least 3 times a week. I love how I feel!";
    public static final String tur23 = "I just got a haircut. I got 8 inches taken off! Do you think it looks good?";
    public static final String tur24 = "I like that one vending machine right outside of Library West with the healthy snacks. It\'s so nice to be able to snack on edamame instead of Doritos some of the time.";
    public static final String tur25 = "Have you every had a frozen lemonade from Chick-Fil-A? So good.";
    public static final String tur26 = "I\'m an art major. I love my classes!";
    public static final String tur27 = "I like listening to country music. It\'s so soothing to me! It\'s just a preference.";
    public static final String tur28 = "My favorite show is Breaking Bad. I just finished watching it. I\'m late, I know, but that ending…";
    public static final String tur29 = "Do you watch TV on your laptop or live? I just hate viewing the commercials.";
    public static final String tur30 = "I want to be tumblr famous.";
    public static final String tur31 = "My last insta is flopping. Only 49 likes. Will you follow me? I need to at least hit 50.";
    public static final String tur32 = "I feel like I can speak an entirely different language with my engineering friends. My other friends don\'t even know what we\'re saying. There are so many acronyms!";
    public static final String tur33 = "I\'m beginning to hit my afternoon crash period. I feel great in the morning after I drink my coffee, but later in the day I always wish I could take a nap.";
    public static final String tur34 = "I\'m turning 21 tomorrow! I\'m so excited.";
    public static final String tur35 = "Have you heard the new Ariana Grande song? It puts me in such a good mood.";
    public static final String tur36 = "Make sure you join at least one student organization at UF. It\'s important to stay involved for your resume, and it\'s also a great way to make friends.";
    public static final String tur37 = "It\'s not always just what you know. Who you know can sometimes matter just as much, if not more.";
    public static final String tur38 = "I just got summoned for jury duty. What a pain!";
    public static final String tur39 = "I need to get a haircut. It\'s getting so unruly.";
    public static final String tur40 = "I just cleaned my whole apartment. I mopped, swept and even dusted.";
    public static final String tur41 = "My friend bought me a flask for my birthday. How nice!";
    public static final String tur42 = "I\'ve been working out a lot lately. Today was leg day. I love seeing how much I can squat.";
    public static final String tur43 = "It seems like everyone is so smart here. I\'m proud to be a Florida Gator.";
    public static final String tur44 = "I\'m feeling a little nostalgic lately. I like having social media so that I can go back through all of my old pictures.";
    public static final String tur45 = "I like using those little Tide Pods. They look cool and they\'re easier to use than the detergent that you have to pour.";
    public static final String tur46 = "Amazon always has great deals. I got my latest tennis shoes just a few days ago and they were at half price.";
    public static final String tur47 = "I have so much laundry to do!";
    public static final String tur48 = "Getting all dressed up is so fun. Wearing a suit and tie really boosts my confidence.";
    public static final String tur49 = "I always bring my umbrella on the days that it doesn\'t end up raining, but I forget it on the days when it really pours.";
    public static final String tur50 = "Home Depot has a lot of good plants to choose from. I recently picked up a golden pothos and a snake plant. They\'re meant to clean the air in my room.";
    public static final String tur51 = "I love jamming out with my Bluetooth speaker.";
    public static final String tur52 = "I packed my lunch today! I\'ve been trying to do meal prep more consistently. Today, I have greek yogurt and a roast beef sandwich.";
    public static final String tur53 = "Flannels are so nice, but they\'re too warm to wear very often in Florida. I sometimes wish it could be a little cooler here.";
    public static final String tur54 = "Gainesville is so muggy! I\'m covered in sweat.";
    public static final String tur55 = "I prefer mechanical pencils over pens.";


    //////////////////////////////////////ADVISING//////////////////////////////////////////////////////////////////////
    public static final Texture advising_tex = new Texture("visuals/Menus/advising_tex.png");

    public static final String[] advText = {
            "Your first class will be Java 1 with\nDr. Dobbins. Go meet him in the\nNorthwest part of CISE basement",
            "Your next class will be Calc 2 with\nDr. Chui. Go meet her in the\nNorthwest part of Turlington 1F",
            "Your third class will be Programming\n2with Dr. Horton. Go meet\nhim in South part\nof 2F Turlington",
            "Your next class will be Circuits I with\nDr. Srivastava. Go meet him\nwest of the stairs\non 2F NEB",
            "Your next class will be Signals with\nDr. Wong. Go meet him\nSouthwest of the stairs\non 3F NEB",
            "Your next class will be Digital Logic with\nDr. Gugel. Go meet him\nin the Northeast corner\non 4F NEB",
            "Your next class will be Operating Systems with\nDr. Small. Go meet him on the\nNortheast part of 3F\nin Turlington",
            "Your next class will be Senior Design with\nDr. Schmalz. Go meet him in\n the Northwest part of 3F CISE",
            "You\'ve completed all your classes. \n\nCongrats, you\'re a Gator Alum!"
    };
    //////////////////////////////////////END ADVISING//////////////////////////////////////////////////////////////////////

    //////////////////////////////////////DUNGEON ENEMIES//////////////////////////////////////////////////////////////////////
    //when adding enemies to the various arrays, the proper tiles must be accounted for in the Tiled Map Editor
    //========================================NEB=============================================
    //89
    public static NPC[] NEB_enemies = {
            new NPC('l',"Welcome to the New Engineering Building", "visuals/sprite_sheets/sprite_walk_l7.png", 65*MAP_RESOLUTION,12*MAP_RESOLUTION), //0
            new NPC('r',"What time does class start?", "visuals/sprite_sheets/sprite_walk_r11.png", 79*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',neb1, "visuals/sprite_sheets/sprite_walk_r9.png", 51*MAP_RESOLUTION, 19*MAP_RESOLUTION),
            new NPC('d',neb2, "visuals/sprite_sheets/sprite_walk_d1.png", 59*MAP_RESOLUTION, 41*MAP_RESOLUTION),
            new NPC('l',neb3, "visuals/sprite_sheets/sprite_walk_l3.png", 46*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('r',neb4, "visuals/sprite_sheets/sprite_walk_r6.png", 44*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('l',neb5, "visuals/sprite_sheets/sprite_walk_l14.png", 48*MAP_RESOLUTION, 54*MAP_RESOLUTION),
            new NPC('u',neb6, "visuals/sprite_sheets/sprite_walk_u12.png", 47*MAP_RESOLUTION, 82*MAP_RESOLUTION),
            new NPC('d',neb7, "visuals/sprite_sheets/sprite_walk_d9.png", 22*MAP_RESOLUTION, 53*MAP_RESOLUTION),
            new NPC('l',neb8, "visuals/sprite_sheets/sprite_walk_l10.png", 45*MAP_RESOLUTION, 7*MAP_RESOLUTION),
            new NPC('d',neb9, "visuals/sprite_sheets/sprite_walk_d13.png", 41*MAP_RESOLUTION, 23*MAP_RESOLUTION),
            new NPC('l',neb10, "visuals/sprite_sheets/sprite_walk_l12.png", 49*MAP_RESOLUTION, 32*MAP_RESOLUTION),
            new NPC('d',neb11, "visuals/sprite_sheets/sprite_walk_d3.png", 30*MAP_RESOLUTION, 27*MAP_RESOLUTION),
            new NPC('l',neb12,"visuals/sprite_sheets/sprite_walk_l11.png", 24*MAP_RESOLUTION, 20*MAP_RESOLUTION),
            new NPC('r',neb13,"visuals/sprite_sheets/sprite_walk_r7.png", 28*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION),
            new NPC('d',neb14,"visuals/sprite_sheets/sprite_walk_d5.png", 16*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d',neb15,"visuals/sprite_sheets/sprite_walk_d4.png",3*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new NPC('u',neb16, "visuals/sprite_sheets/sprite_walk_u9.png",3*MAP_RESOLUTION, (89-49)*MAP_RESOLUTION),  //17
            new NPC('r',neb17, "visuals/sprite_sheets/sprite_walk_r1.png", 3*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', neb18, "visuals/sprite_sheets/sprite_walk_d14.png", 16*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', neb19, "visuals/sprite_sheets/sprite_walk_d2.png", 27*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('d', neb20, "visuals/sprite_sheets/sprite_walk_d6.png", 45*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('u', neb21, "visuals/sprite_sheets/sprite_walk_u8.png", 68*MAP_RESOLUTION, (89-6)*MAP_RESOLUTION),
            new NPC('r', neb22, "visuals/sprite_sheets/sprite_walk_r9.png", 96*MAP_RESOLUTION, (89-72)*MAP_RESOLUTION),
            new NPC('u', neb23, "visuals/sprite_sheets/sprite_walk_u3.png", 97*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('r', neb24, "visuals/sprite_sheets/sprite_walk_r1.png", 110*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', neb25, "visuals/sprite_sheets/sprite_walk_d11.png", 122*MAP_RESOLUTION, (89-62)*MAP_RESOLUTION),
            new NPC('r', neb26, "visuals/sprite_sheets/sprite_walk_r12.png", 121*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new ENEMY('l', nebPPre, nebPWin, nebPLose, "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 113*MAP_RESOLUTION, (89-38)*MAP_RESOLUTION, 16, Neb_A_attacks, Sriv_weakness, 75, 500),
            new NPC('l', neb27, "visuals/sprite_sheets/sprite_walk_l7.png", 125*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('r', neb28, "visuals/sprite_sheets/sprite_walk_r10.png", 118*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION), //30
            new NPC('d', neb29, "visuals/sprite_sheets/sprite_walk_d14.png", 133*MAP_RESOLUTION, (89-4)*MAP_RESOLUTION),
            new NPC('l', neb30, "visuals/sprite_sheets/sprite_walk_l9.png", 140*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('u', neb31, "visuals/sprite_sheets/sprite_walk_u8.png", 140*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('d', neb32, "visuals/sprite_sheets/sprite_walk_d7.png", 149*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('l', neb33, "visuals/sprite_sheets/sprite_walk_l6.png", 170*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('d', neb34, "visuals/sprite_sheets/sprite_walk_d5.png", 137*MAP_RESOLUTION, (89-80)*MAP_RESOLUTION),
            new NPC('u', neb35, "visuals/sprite_sheets/sprite_walk_u4.png", 143*MAP_RESOLUTION, (89-57)*MAP_RESOLUTION),
            new NPC('d', neb36, "visuals/sprite_sheets/sprite_walk_d3.png", 165*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('u', neb37, "visuals/sprite_sheets/sprite_walk_u2.png", 160*MAP_RESOLUTION, (89-83)*MAP_RESOLUTION), //39
            new NPC('r', neb38, "visuals/sprite_sheets/sprite_walk_r1.png", 187*MAP_RESOLUTION, (89-68)*MAP_RESOLUTION),
            new NPC('u', neb39, "visuals/sprite_sheets/sprite_walk_u14.png", 190*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('u', neb40, "visuals/sprite_sheets/sprite_walk_u13.png", 202*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', neb41, "visuals/sprite_sheets/sprite_walk_d12.png", 214*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new ENEMY('r', nebSPre, nebSWin, nebSLose, "visuals/sprite_sheets/sprite_walk_r11.png", "visuals/sprite_sheets/sprite_l11.png", 211*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION, 16, Neb_D_attacks, Wong_weakness, 75, 500),
            new NPC('u', neb42, "visuals/sprite_sheets/sprite_walk_u10.png", 229*MAP_RESOLUTION, (89-58)*MAP_RESOLUTION),
            new NPC('l', neb43, "visuals/sprite_sheets/sprite_walk_l9.png", 224*MAP_RESOLUTION, (89-52)*MAP_RESOLUTION), //46
            new NPC('r', neb44, "visuals/sprite_sheets/sprite_walk_r1.png", 187*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('l', neb45, "visuals/sprite_sheets/sprite_walk_l2.png", 205*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', neb46, "visuals/sprite_sheets/sprite_walk_d3.png", 212*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', neb47, "visuals/sprite_sheets/sprite_walk_d4.png", 229*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION), //50
            new NPC('r', neb48, "visuals/sprite_sheets/sprite_walk_r5.png", 246*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('u', neb49, "visuals/sprite_sheets/sprite_walk_u6.png", 258*MAP_RESOLUTION, (89-26)*MAP_RESOLUTION),
            new ENEMY('d', nebXPre, nebXWin, nebXLose, "visuals/sprite_sheets/sprite_walk_d7.png", "visuals/sprite_sheets/sprite_l7.png", 283*MAP_RESOLUTION, (89-16)*MAP_RESOLUTION, 16, Neb_I_attacks, Gugel_weakness, 75, 500),
            new ENEMY('d', nebVPre, nebVWin, nebVLose, "visuals/sprite_sheets/sprite_walk_d8.png", "visuals/sprite_sheets/sprite_l8.png", 284*MAP_RESOLUTION, (89-41)*MAP_RESOLUTION, 16, Neb_G_attacks, Gugel_weakness, 75, 500),
            new NPC('l', neb50, "visuals/sprite_sheets/sprite_walk_l9.png", 276*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d', neb51, "visuals/sprite_sheets/sprite_walk_d10.png", 287*MAP_RESOLUTION, (89-70)*MAP_RESOLUTION),
            new NPC('d', neb52, "visuals/sprite_sheets/sprite_walk_d11.png", 269*MAP_RESOLUTION, (89-61)*MAP_RESOLUTION),
            new NPC('u', neb53, "visuals/sprite_sheets/sprite_walk_u12.png", 262*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION),
            new NPC('d', neb54, "visuals/sprite_sheets/sprite_walk_d13.png", 262*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('r', neb55, "visuals/sprite_sheets/sprite_walk_r14.png", 248*MAP_RESOLUTION, (89-46)*MAP_RESOLUTION), //60
            new BOSS('d', "Srivastava", "Hello, I am Prof. Srivastava, and I teach Circuits I. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/srivastava/srivastava_d.png", "visuals/Professors/srivastava/srivastava_l.png", 112*MAP_RESOLUTION, (89-33)*MAP_RESOLUTION, 25, Sriv_attacks, Sriv_weakness, 150, 0, 3),
            new BOSS('d', "Wong", "Hello, I am Prof. Wong, and I teach Signals and Systems. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/wong/wong_d.png", "visuals/Professors/wong/wong_l.png", 211*MAP_RESOLUTION, (89-55)*MAP_RESOLUTION, 25, Wong_attacks, Wong_weakness, 150, 0, 4),
            new BOSS('d', "Gugel", "Hello, I am Prof. Gugel, and I teach Digital Logic. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/gugel/gugel_d.png", "visuals/Professors/gugel/gugel_l.png", 287*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION, 25, Gugel_attacks, Gugel_weakness, 150, 0, 5),
            new ENEMY('r', nebQPre, nebQWin, nebQLose, "visuals/sprite_sheets/sprite_walk_r1.png", "visuals/sprite_sheets/sprite_l1.png", 110*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION, 16, Neb_B_attacks, Sriv_weakness, 75, 500),
            new ENEMY('l', nebRPre, nebRWin, nebRLose, "visuals/sprite_sheets/sprite_walk_l2.png", "visuals/sprite_sheets/sprite_l2.png", 112*MAP_RESOLUTION, (89-44)*MAP_RESOLUTION, 16, Neb_C_attacks, Sriv_weakness, 75, 500),
            new ENEMY('l', nebTPre, nebTWin, nebTLose, "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 220*MAP_RESOLUTION, (89-48)*MAP_RESOLUTION, 16, Neb_E_attacks, Wong_weakness, 75, 500),
            new ENEMY('r', nebUPre, nebUWin, nebULose, "visuals/sprite_sheets/sprite_walk_r4.png", "visuals/sprite_sheets/sprite_l4.png", 229*MAP_RESOLUTION, (89-41)*MAP_RESOLUTION, 16, Neb_F_attacks, Wong_weakness, 75, 500),
            new ENEMY('l', nebWPre, nebWWin, nebWLose, "visuals/sprite_sheets/sprite_walk_l5.png", "visuals/sprite_sheets/sprite_l5.png", 281*MAP_RESOLUTION, (89-30)*MAP_RESOLUTION, 16, Neb_H_attacks, Gugel_weakness, 75, 500),
    };
    
    public static ENEMY[] NEB_randoms = {
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Neb_random_attacks_1, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Neb_random_attacks_2, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Neb_random_attacks_3, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Neb_random_attacks_4, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Neb_random_attacks_5, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Neb_random_attacks_6, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Neb_random_attacks_7, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Neb_random_attacks_8, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Neb_random_attacks_9, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Neb_random_attacks_10, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Neb_random_attacks_11, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Neb_random_attacks_12, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Neb_random_attacks_13, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Neb_random_attacks_14, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Neb_random_attacks_15, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Neb_random_attacks_16, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Neb_random_attacks_17, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Neb_random_attacks_18, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Neb_random_attacks_19, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Neb_random_attacks_20, Gugel_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Neb_random_attacks_21, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Neb_random_attacks_22, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Neb_random_attacks_23, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Neb_random_attacks_24, Sriv_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Neb_random_attacks_25, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Neb_random_attacks_26, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Neb_random_attacks_27, Wong_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Neb_random_attacks_28, Gugel_weakness, 25, 200),
    };
    //========================================================================================
    //========================================CISE============================================
    public static NPC[] CISE_enemies = {
            new NPC('r', cise1, "visuals/sprite_sheets/sprite_walk_r1.png", 5*MAP_RESOLUTION, (66-8)*MAP_RESOLUTION),  //0
            new NPC('d', cise2, "visuals/sprite_sheets/sprite_walk_d2.png", 21*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('u', cise3, "visuals/sprite_sheets/sprite_walk_u3.png", 3*MAP_RESOLUTION, (66-24)*MAP_RESOLUTION),
            new NPC('d', cise4, "visuals/sprite_sheets/sprite_walk_d4.png", 28*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('r', cise5, "visuals/sprite_sheets/sprite_walk_r5.png", /*1**/MAP_RESOLUTION, (66-32)*MAP_RESOLUTION),
            new NPC('l', cise6, "visuals/sprite_sheets/sprite_walk_l6.png", 48*MAP_RESOLUTION, (66-8)*MAP_RESOLUTION),
            new NPC('r', cise7, "visuals/sprite_sheets/sprite_walk_r5.png", 33*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('l', cise8, "visuals/sprite_sheets/sprite_walk_l6.png", 17*MAP_RESOLUTION, (66-41)*MAP_RESOLUTION),
            new NPC('r', cise9, "visuals/sprite_sheets/sprite_walk_r7.png", 18*MAP_RESOLUTION, (66-55)*MAP_RESOLUTION),
            new NPC('l', cise10, "visuals/sprite_sheets/sprite_walk_l8.png", 31*MAP_RESOLUTION, (66-44)*MAP_RESOLUTION),
            new NPC('u', cise11, "visuals/sprite_sheets/sprite_walk_u9.png", 44*MAP_RESOLUTION, (66-44)*MAP_RESOLUTION),  //10
            new NPC('l', cise12, "visuals/sprite_sheets/sprite_walk_l10.png", 88*MAP_RESOLUTION, (66-61)*MAP_RESOLUTION),
            new NPC('d', cise13, "visuals/sprite_sheets/sprite_walk_d11.png", 75*MAP_RESOLUTION, (66-39)*MAP_RESOLUTION),
            new NPC('u', cise14, "visuals/sprite_sheets/sprite_walk_u12.png", 77*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('d', cise15, "visuals/sprite_sheets/sprite_walk_d13.png", 72*MAP_RESOLUTION, (66-9)*MAP_RESOLUTION),
            new NPC('u', cise16, "visuals/sprite_sheets/sprite_walk_u14.png", 101*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('d', cise17, "visuals/sprite_sheets/sprite_walk_d1.png", 124*MAP_RESOLUTION, (66-6)*MAP_RESOLUTION),
            new NPC('r', cise18, "visuals/sprite_sheets/sprite_walk_r2.png", 130*MAP_RESOLUTION, (66-12)*MAP_RESOLUTION),
            new NPC('l', cise19, "visuals/sprite_sheets/sprite_walk_l3.png", 146*MAP_RESOLUTION, (66-6)*MAP_RESOLUTION),
            new NPC('d', cise20, "visuals/sprite_sheets/sprite_walk_d4.png", 109*MAP_RESOLUTION, (66-29)*MAP_RESOLUTION),
            new NPC('r', cise21, "visuals/sprite_sheets/sprite_walk_r5.png", 130*MAP_RESOLUTION, (66-28)*MAP_RESOLUTION), //20
            new NPC('r', cise22, "visuals/sprite_sheets/sprite_walk_r6.png", 101*MAP_RESOLUTION, (66-47)*MAP_RESOLUTION),
            new NPC('l', cise23, "visuals/sprite_sheets/sprite_walk_l7.png", 123*MAP_RESOLUTION, (66-55)*MAP_RESOLUTION),
            new NPC('d', cise24, "visuals/sprite_sheets/sprite_walk_d8.png", 130*MAP_RESOLUTION, (66-60)*MAP_RESOLUTION),
            new NPC('d', cise25, "visuals/sprite_sheets/sprite_walk_d9.png", 143*MAP_RESOLUTION, (66-43)*MAP_RESOLUTION),
            new ENEMY('d', ciseOPre,ciseOWin,ciseOLose,"visuals/sprite_sheets/sprite_walk_d10.png", "visuals/sprite_sheets/sprite_l10.png", 175*MAP_RESOLUTION, (66-7)*MAP_RESOLUTION, 16, Cise_D_attacks, Schmalz_weakness, 75, 500),
            new NPC('l', cise26, "visuals/sprite_sheets/sprite_walk_l11.png", 202*MAP_RESOLUTION, (66-12)*MAP_RESOLUTION),
            new NPC('d', cise27, "visuals/sprite_sheets/sprite_walk_d13.png", 213*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('r', cise28, "visuals/sprite_sheets/sprite_walk_r14.png", 168*MAP_RESOLUTION, (66-34)*MAP_RESOLUTION),
            new NPC('u', cise29, "visuals/sprite_sheets/sprite_walk_u1.png", 167*MAP_RESOLUTION, (66-47)*MAP_RESOLUTION),
            new NPC('u', cise30, "visuals/sprite_sheets/sprite_walk_u2.png", 202*MAP_RESOLUTION, (66-30)*MAP_RESOLUTION), //30
            new NPC('l', cise31, "visuals/sprite_sheets/sprite_walk_l3.png", 217*MAP_RESOLUTION, (66-32)*MAP_RESOLUTION),
            new NPC('l', cise32, "visuals/sprite_sheets/sprite_walk_l4.png", 213*MAP_RESOLUTION, (66-42)*MAP_RESOLUTION),
            new NPC('u', cise33, "visuals/sprite_sheets/sprite_walk_u5.png", 199*MAP_RESOLUTION, (66-58)*MAP_RESOLUTION),
            new BOSS('d', "Dobbins", "Hello, I am Prof. Dobbins, and I teach Programming I. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/dobbins/dobbins_d.png", "visuals/Professors/dobbins/dobbins_l.png", 2*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION, 25, Dobbins_attacks, Dobbins_weakness, 150, 0, 0),
            new BOSS('d', "Schmalz", "Hello, I am Prof. Schmalz, and I teach CS Senior Design. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/schmalz/schmalz_d.png", "visuals/Professors/schmalz/schmalz_l.png", 168*MAP_RESOLUTION, (66-4)*MAP_RESOLUTION, 25, Schmalz_attacks, Schmalz_weakness, 150, 0, 7),
            new ENEMY('d', ciseJPre,ciseJWin,ciseJLose,"visuals/sprite_sheets/sprite_walk_d6.png", "visuals/sprite_sheets/sprite_l6.png",33*MAP_RESOLUTION, (66-28)*MAP_RESOLUTION, 16, Cise_A_attacks, Dobbins_weakness, 75, 500),
            new ENEMY('l', ciseKPre,ciseKWin,ciseKLose,"visuals/sprite_sheets/sprite_walk_l7.png", "visuals/sprite_sheets/sprite_l7.png",19*MAP_RESOLUTION, (66-31)*MAP_RESOLUTION, 16, Cise_B_attacks, Dobbins_weakness, 75, 500),
            new ENEMY('r', ciseLPre,ciseLWin,ciseLLose,"visuals/sprite_sheets/sprite_walk_r8.png", "visuals/sprite_sheets/sprite_l8.png",8*MAP_RESOLUTION, (66-16)*MAP_RESOLUTION, 16, Cise_C_attacks, Dobbins_weakness, 75, 500),
            new ENEMY('r', ciseMPre,ciseMWin,ciseMLose,"visuals/sprite_sheets/sprite_walk_r9.png", "visuals/sprite_sheets/sprite_l9.png",177*MAP_RESOLUTION, (66-13)*MAP_RESOLUTION, 16, Cise_E_attacks, Schmalz_weakness, 75, 500),
            new ENEMY('l', ciseNPre,ciseNWin,ciseNLose,"visuals/sprite_sheets/sprite_walk_l10.png", "visuals/sprite_sheets/sprite_l10.png",182*MAP_RESOLUTION, (66-16)*MAP_RESOLUTION, 16, Cise_F_attacks, Schmalz_weakness, 57, 500), //40
    };

    public static ENEMY[] Cise_randoms = {
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Cise_random_attacks_1, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Cise_random_attacks_2, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Cise_random_attacks_3, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Cise_random_attacks_4, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Cise_random_attacks_5, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Cise_random_attacks_6, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Cise_random_attacks_7, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Cise_random_attacks_8, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Cise_random_attacks_9, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Cise_random_attacks_10, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Cise_random_attacks_11, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Cise_random_attacks_12, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Cise_random_attacks_13, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Cise_random_attacks_14, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Cise_random_attacks_15, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Cise_random_attacks_16, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Cise_random_attacks_17, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Cise_random_attacks_18, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Cise_random_attacks_19, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Cise_random_attacks_20, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Cise_random_attacks_21, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Cise_random_attacks_22, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Cise_random_attacks_23, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Cise_random_attacks_24, Schmalz_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Cise_random_attacks_25, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Cise_random_attacks_26, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Cise_random_attacks_27, Dobbins_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Cise_random_attacks_28, Dobbins_weakness, 25, 200),
    };
    //========================================================================================
    //========================================Turlington======================================
    public static NPC[] Turlington_enemies = {
            new NPC('u', tur1, "visuals/sprite_sheets/sprite_walk_u1.png", 17*MAP_RESOLUTION, (128-116)*MAP_RESOLUTION),  //0
            new NPC('u', tur2, "visuals/sprite_sheets/sprite_walk_u2.png", 32*MAP_RESOLUTION, (128-123)*MAP_RESOLUTION),
            new NPC('r', tur3, "visuals/sprite_sheets/sprite_walk_r3.png", 47*MAP_RESOLUTION, (128-109)*MAP_RESOLUTION),
            new NPC('d', tur4, "visuals/sprite_sheets/sprite_walk_d4.png", 82*MAP_RESOLUTION, (128-121)*MAP_RESOLUTION),
            new NPC('l', tur5, "visuals/sprite_sheets/sprite_walk_l5.png", 95*MAP_RESOLUTION, (128-98)*MAP_RESOLUTION),
            new NPC('l', tur6, "visuals/sprite_sheets/sprite_walk_l6.png", 111*MAP_RESOLUTION, (128-90)*MAP_RESOLUTION),
            new NPC('d', tur7, "visuals/sprite_sheets/sprite_walk_d7.png", 79*MAP_RESOLUTION, (128-85)*MAP_RESOLUTION),
            new NPC('u', tur8, "visuals/sprite_sheets/sprite_walk_u8.png", 32*MAP_RESOLUTION, (128-71)*MAP_RESOLUTION),
            new NPC('d', tur9, "visuals/sprite_sheets/sprite_walk_d9.png", 8*MAP_RESOLUTION, (128-80)*MAP_RESOLUTION),
            new NPC('l', tur10, "visuals/sprite_sheets/sprite_walk_l10.png", 46*MAP_RESOLUTION, (128-47)*MAP_RESOLUTION),
            new NPC('u', tur11, "visuals/sprite_sheets/sprite_walk_u11.png", 56*MAP_RESOLUTION, (128-50)*MAP_RESOLUTION), //10
            new NPC('l', tur12, "visuals/sprite_sheets/sprite_walk_l12.png", 48*MAP_RESOLUTION, (128-35)*MAP_RESOLUTION),
            new NPC('d', tur13, "visuals/sprite_sheets/sprite_walk_d13.png", 73*MAP_RESOLUTION, (128-24)*MAP_RESOLUTION),
            new NPC('r', tur14, "visuals/sprite_sheets/sprite_walk_r14.png", 86*MAP_RESOLUTION, (128-34)*MAP_RESOLUTION),
            new NPC('d', tur15, "visuals/sprite_sheets/sprite_walk_d1.png", 100*MAP_RESOLUTION, (128-29)*MAP_RESOLUTION),
            new NPC('d', tur16, "visuals/sprite_sheets/sprite_walk_d2.png", 107*MAP_RESOLUTION, (128-57)*MAP_RESOLUTION),
            new NPC('r', tur17, "visuals/sprite_sheets/sprite_walk_r3.png", 74*MAP_RESOLUTION, (128-10)*MAP_RESOLUTION),
            new NPC('u', tur18, "visuals/sprite_sheets/sprite_walk_u4.png", 89*MAP_RESOLUTION, (128-15)*MAP_RESOLUTION),
            new NPC('l', tur19, "visuals/sprite_sheets/sprite_walk_l5.png", 106*MAP_RESOLUTION, (128-13)*MAP_RESOLUTION),
            new NPC('r', tur20, "visuals/sprite_sheets/sprite_walk_r6.png", 132*MAP_RESOLUTION, (128-16)*MAP_RESOLUTION),
            new NPC('u', tur21, "visuals/sprite_sheets/sprite_walk_u7.png", 152*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION), //20
            new NPC('l', tur22, "visuals/sprite_sheets/sprite_walk_l8.png", 164*MAP_RESOLUTION, (128-28)*MAP_RESOLUTION),
            new NPC('u', tur23, "visuals/sprite_sheets/sprite_walk_u9.png", 139*MAP_RESOLUTION, (128-45)*MAP_RESOLUTION),
            new NPC('l', tur24, "visuals/sprite_sheets/sprite_walk_l10.png", 155*MAP_RESOLUTION, (128-42)*MAP_RESOLUTION),
            new NPC('d', tur25, "visuals/sprite_sheets/sprite_walk_d11.png", 174*MAP_RESOLUTION, (128-48)*MAP_RESOLUTION),
            new NPC('l', tur26, "visuals/sprite_sheets/sprite_walk_l12.png", 182*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION),
            new NPC('u', tur27, "visuals/sprite_sheets/sprite_walk_u13.png", 193*MAP_RESOLUTION, (128-31)*MAP_RESOLUTION),
            new NPC('u', tur28, "visuals/sprite_sheets/sprite_walk_u14.png", 211*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION),
            new NPC('d', tur29, "visuals/sprite_sheets/sprite_walk_d1.png", 225*MAP_RESOLUTION, (128-18)*MAP_RESOLUTION),
            new NPC('l', tur30, "visuals/sprite_sheets/sprite_walk_l2.png", 233*MAP_RESOLUTION, (128-38)*MAP_RESOLUTION),
            new NPC('r', tur31, "visuals/sprite_sheets/sprite_walk_r3.png", 136*MAP_RESOLUTION, (128-58)*MAP_RESOLUTION), //30
            new NPC('r', tur32, "visuals/sprite_sheets/sprite_walk_r4.png", 125*MAP_RESOLUTION, (128-77)*MAP_RESOLUTION),
            new NPC('r', tur33, "visuals/sprite_sheets/sprite_walk_r5.png", 135*MAP_RESOLUTION, (128-92)*MAP_RESOLUTION),
            new NPC('d', tur34, "visuals/sprite_sheets/sprite_walk_d6.png", 159*MAP_RESOLUTION, (128-65)*MAP_RESOLUTION),
            new NPC('u', tur35, "visuals/sprite_sheets/sprite_walk_u7.png", 146*MAP_RESOLUTION, (128-97)*MAP_RESOLUTION),
            new NPC('r', tur36, "visuals/sprite_sheets/sprite_walk_r8.png", 168*MAP_RESOLUTION, (128-102)*MAP_RESOLUTION),
            new NPC('d', tur37, "visuals/sprite_sheets/sprite_walk_d9.png", 151*MAP_RESOLUTION, (128-121)*MAP_RESOLUTION),
            new ENEMY('l', turlingtonFPre,turlingtonFWin,turlingtonFLose, "visuals/sprite_sheets/sprite_walk_l10.png", "visuals/sprite_sheets/sprite_l10.png", 169*MAP_RESOLUTION, (128-113)*MAP_RESOLUTION, 16, Turlington_F_attacks, Horton_weakness, 75, 500),
            new NPC('u', tur38, "visuals/sprite_sheets/sprite_walk_u11.png", 274*MAP_RESOLUTION, (128-125)*MAP_RESOLUTION),
            new NPC('l', tur39, "visuals/sprite_sheets/sprite_walk_l12.png", 292*MAP_RESOLUTION, (128-113)*MAP_RESOLUTION),
            new NPC('r', tur40, "visuals/sprite_sheets/sprite_walk_r13.png", 261*MAP_RESOLUTION, (128-92)*MAP_RESOLUTION), //40
            new NPC('u', tur41, "visuals/sprite_sheets/sprite_walk_u14.png", 271*MAP_RESOLUTION, (128-95)*MAP_RESOLUTION),
            new NPC('r', tur42, "visuals/sprite_sheets/sprite_walk_r1.png", 291*MAP_RESOLUTION, (128-91)*MAP_RESOLUTION),
            new NPC('r', tur43, "visuals/sprite_sheets/sprite_walk_r2.png", 259*MAP_RESOLUTION, (128-44)*MAP_RESOLUTION),
            new NPC('r', tur44, "visuals/sprite_sheets/sprite_walk_r3.png", 255*MAP_RESOLUTION, (128-60)*MAP_RESOLUTION),
            new NPC('u', tur45, "visuals/sprite_sheets/sprite_walk_u4.png", 270*MAP_RESOLUTION, (128-47)*MAP_RESOLUTION),
            new NPC('d', tur46, "visuals/sprite_sheets/sprite_walk_d5.png", 291*MAP_RESOLUTION, (128-50)*MAP_RESOLUTION),
            new NPC('l', tur47, "visuals/sprite_sheets/sprite_walk_l6.png", 274*MAP_RESOLUTION, (128-71)*MAP_RESOLUTION),
            new NPC('r', tur48, "visuals/sprite_sheets/sprite_walk_r7.png", 291*MAP_RESOLUTION, (128-65)*MAP_RESOLUTION),
            new NPC('r', tur49, "visuals/sprite_sheets/sprite_walk_r8.png", 249*MAP_RESOLUTION, (128-20)*MAP_RESOLUTION),
            new NPC('r', tur50, "visuals/sprite_sheets/sprite_walk_r9.png", 270*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION), //50
            new NPC('u', tur51, "visuals/sprite_sheets/sprite_walk_u10.png", 288*MAP_RESOLUTION, (128-24)*MAP_RESOLUTION),
            new NPC('l', tur52, "visuals/sprite_sheets/sprite_walk_l11.png", 301*MAP_RESOLUTION, (128-28)*MAP_RESOLUTION),
            new NPC('l', tur53, "visuals/sprite_sheets/sprite_walk_l12.png", 320*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION),
            new NPC('u', tur54, "visuals/sprite_sheets/sprite_walk_u13.png", 334*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION),
            new ENEMY('d', turlingtonIPre,turlingtonIWin,turlingtonILose, "visuals/sprite_sheets/sprite_walk_d14.png", "visuals/sprite_sheets/sprite_l14.png", 349*MAP_RESOLUTION, (128-19)*MAP_RESOLUTION, 16, Turlington_I_attacks, Small_weakness, 75, 500),
            new NPC('r', tur55, "visuals/sprite_sheets/sprite_walk_r1.png", 353*MAP_RESOLUTION, (128-30)*MAP_RESOLUTION), //56
            new BOSS('d', "Chui", "Hello, I am Prof. Chui, and I teach Calc II. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/chui/chui_d.png", "visuals/Professors/chui/chui_l.png", 46*MAP_RESOLUTION, (128-27)*MAP_RESOLUTION, 25, Chui_attacks, Chui_weakness, 150, 0, 1),
            new BOSS('d', "Horton", "Hello, I am Prof. Horton, and I teach Programming II. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/horton/horton_d.png", "visuals/Professors/horton/horton_l.png", 170*MAP_RESOLUTION, (128-122)*MAP_RESOLUTION, 25, Horton_attacks, Horton_weakness, 150, 0, 2),
            new BOSS('d', "Small", "Hello, I am Prof. Small, and I teach Operating Systems. It will cost $2000 to take this course.", "Guess I will see you next semester", "Congratulations on passing the course", "visuals/Professors/small/small_d.png", "visuals/Professors/small/small_l.png", 362*MAP_RESOLUTION, (128-16)*MAP_RESOLUTION, 25, Small_attacks, Small_weakness, 150, 0, 6),
            new ENEMY('d', turlingtonAPre,turlingtonAWin,turlingtonALose,"visuals/sprite_sheets/sprite_walk_d2.png", "visuals/sprite_sheets/sprite_l2.png", 63*MAP_RESOLUTION, (128-36)*MAP_RESOLUTION, 16, Turlington_A_attacks, Chui_weakness, 75, 500), //60
            new ENEMY('l', turlingtonBPre,turlingtonBWin,turlingtonBLose,"visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 49*MAP_RESOLUTION, (128-40)*MAP_RESOLUTION, 16, Turlington_B_attacks, Chui_weakness, 75, 500),
            new ENEMY('r', turlingtonCPre,turlingtonCWin,turlingtonCLose,"visuals/sprite_sheets/sprite_walk_r4.png", "visuals/sprite_sheets/sprite_l4.png", 43*MAP_RESOLUTION, (128-37)*MAP_RESOLUTION, 16, Turlington_C_attacks, Chui_weakness, 75, 500),
            new ENEMY('r', turlingtonDPre,turlingtonDWin,turlingtonDLose,"visuals/sprite_sheets/sprite_walk_r5.png", "visuals/sprite_sheets/sprite_l5.png", 173*MAP_RESOLUTION, (128-106)*MAP_RESOLUTION, 16, Turlington_D_attacks, Horton_weakness, 75, 500),
            new ENEMY('r', turlingtonEPre,turlingtonEWin,turlingtonELose,"visuals/sprite_sheets/sprite_walk_r6.png", "visuals/sprite_sheets/sprite_l6.png", 158*MAP_RESOLUTION, (128-110)*MAP_RESOLUTION, 16, Turlington_E_attacks, Horton_weakness, 75, 500),
            new ENEMY('d', turlingtonGPre,turlingtonGWin,turlingtonGLose,"visuals/sprite_sheets/sprite_walk_d7.png", "visuals/sprite_sheets/sprite_l7.png", 341*MAP_RESOLUTION, (128-15)*MAP_RESOLUTION, 16, Turlington_G_attacks, Small_weakness, 75, 500),
            new ENEMY('l', turlingtonHPre,turlingtonHWin,turlingtonHLose,"visuals/sprite_sheets/sprite_walk_l8.png", "visuals/sprite_sheets/sprite_l8.png", 347*MAP_RESOLUTION, (128-25)*MAP_RESOLUTION, 16, Turlington_H_attacks, Small_weakness, 75, 500),
    };

    public static ENEMY[] Turlington_randoms = {
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Turlington_random_attacks_1, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Turlington_random_attacks_2, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Turlington_random_attacks_3, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Turlington_random_attacks_4, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Turlington_random_attacks_5, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Turlington_random_attacks_6, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Turlington_random_attacks_7, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Turlington_random_attacks_8, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Turlington_random_attacks_9, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Turlington_random_attacks_10, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Turlington_random_attacks_11, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Turlington_random_attacks_12, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Turlington_random_attacks_13, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Turlington_random_attacks_14, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l1.png", 0, 0, 8, Turlington_random_attacks_15, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l2.png", 0, 0, 8, Turlington_random_attacks_16, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l3.png", 0, 0, 8, Turlington_random_attacks_17, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l4.png", 0, 0, 8, Turlington_random_attacks_18, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l5.png", 0, 0, 8, Turlington_random_attacks_19, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l6.png", 0, 0, 8, Turlington_random_attacks_20, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l7.png", 0, 0, 8, Turlington_random_attacks_21, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l8.png", 0, 0, 8, Turlington_random_attacks_22, Small_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l9.png", 0, 0, 8, Turlington_random_attacks_23, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l10.png", 0, 0, 8, Turlington_random_attacks_24, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l11.png", 0, 0, 8, Turlington_random_attacks_25, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l12.png", 0, 0, 8, Turlington_random_attacks_26, Chui_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l13.png", 0, 0, 8, Turlington_random_attacks_27, Horton_weakness, 25, 200),
            new RANDOM('l', "","","", "visuals/sprite_sheets/sprite_walk_l3.png", "visuals/sprite_sheets/sprite_l14.png", 0, 0, 8, Turlington_random_attacks_28, Horton_weakness, 25, 200),
    };
    //========================================================================================
    //========================================Dorm============================================
    public static NPC[] Dorm_enemies = {
            new NPC('u',"Welcome to UF, this is your dorm room. Return here whenever you want to restore your GPA.", "visuals/sprite_sheets/sprite_walk_u3.png",9*MAP_RESOLUTION,20*MAP_RESOLUTION),
            new NPC('d',"Press F1 while in the gamescreen to save!", "visuals/sprite_sheets/sprite_walk_d5.png", 17*MAP_RESOLUTION,24*MAP_RESOLUTION),
            new NPC('l',"Press A to contact your advisor and get information on what you should do next.","visuals/sprite_sheets/sprite_walk_l1.png", 25*MAP_RESOLUTION,12*MAP_RESOLUTION),
            new NPC('r',"When leaving a building you will bring up the world map. Just move the pin to the building you want to visit and hit enter","visuals/sprite_sheets/sprite_walk_r8.png", 10*MAP_RESOLUTION,3*MAP_RESOLUTION),
            new NPC('r',dormA,"visuals/sprite_sheets/sprite_walk_r13.png",17*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('l',dormB, "visuals/sprite_sheets/sprite_walk_l12.png", 21*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',worldO, "visuals/sprite_sheets/sprite_walk_r2.png", MAP_RESOLUTION,31*MAP_RESOLUTION),
            new NPC('u',dormC,"visuals/sprite_sheets/sprite_walk_u14.png", 20*MAP_RESOLUTION,35*MAP_RESOLUTION),
            new NPC('r', dormD, "visuals/sprite_sheets/sprite_walk_r6.png", 6*MAP_RESOLUTION,42*MAP_RESOLUTION),
            new NPC('d',worldX,"visuals/sprite_sheets/sprite_walk_d4.png", 24*MAP_RESOLUTION,41*MAP_RESOLUTION),
            new REST('d',"You've rested in bed.\nGPA has been restored.", "visuals/sprite_sheets/RestingNPC.png",2*MAP_RESOLUTION,(59-24)*MAP_RESOLUTION) //10
    };
    //========================================================================================
    //========================================Marston=========================================
    public static NPC[] Marston_enemies = {
            new SHOP('r', "Redbull $100\n\nStarbucks $300", "visuals/sprite_sheets/sprite_walk_r1.png", 45*MAP_RESOLUTION, (45-18)*MAP_RESOLUTION), //0
            new NPC('d', marstonA, "visuals/sprite_sheets/sprite_walk_d2.png", 48*MAP_RESOLUTION, (45-14)*MAP_RESOLUTION),
            new NPC('u', marstonC, "visuals/sprite_sheets/sprite_walk_u3.png", 45*MAP_RESOLUTION, (45-30)*MAP_RESOLUTION),
            new NPC('r', marstonB, "visuals/sprite_sheets/sprite_walk_r4.png", 40*MAP_RESOLUTION, (45-16)*MAP_RESOLUTION),
            new NPC('l', marstonD, "visuals/sprite_sheets/sprite_walk_l5.png", 37*MAP_RESOLUTION, (45-35)*MAP_RESOLUTION),
            new REST('u', "Thank you for studying at the Marston Library! Your GPA has been replenished", "visuals/sprite_sheets/sprite_walk_u6.png", 24*MAP_RESOLUTION, (45-26)*MAP_RESOLUTION),
            new NPC('l', marstonF, "visuals/sprite_sheets/sprite_walk_l7.png", 5*MAP_RESOLUTION, (45-26)*MAP_RESOLUTION),
            new NPC('r', marstonG, "visuals/sprite_sheets/sprite_walk_r8.png", 2*MAP_RESOLUTION, (45-15)*MAP_RESOLUTION),
            new NPC('r', marstonH, "visuals/sprite_sheets/sprite_walk_r9.png", 13*MAP_RESOLUTION, (45-21)*MAP_RESOLUTION),
            new NPC('l', marstonI, "visuals/sprite_sheets/sprite_walk_l10.png", 23*MAP_RESOLUTION, (45-16)*MAP_RESOLUTION),
            new NPC('d', marstonJ, "visuals/sprite_sheets/sprite_walk_d11.png", 20*MAP_RESOLUTION, (45-7)*MAP_RESOLUTION), //10
            new NPC('d', dormF, "visuals/sprite_sheets/sprite_walk_d12.png", 28*MAP_RESOLUTION, (45-10)*MAP_RESOLUTION),
            new NPC('l', marstonE, "visuals/sprite_sheets/sprite_walk_l13.png", 22*MAP_RESOLUTION, (45-3)*MAP_RESOLUTION),
            new NPC('u', worldP, "visuals/sprite_sheets/sprite_walk_u14.png", 32*MAP_RESOLUTION, (45-6)*MAP_RESOLUTION),
            //new NPC
    };
    //========================================================================================
    //========================================Bookstore=======================================
    public static NPC[] Bookstore_enemies = {
            new SHOP('d', "Biz Cas Attire $500\n\nClass Ring $1000\n\nGator Hat $400\n\nGrad Gown $1200\n\nSuit and Tie $2000", "visuals/sprite_sheets/sprite_walk_d1.png", 25*MAP_RESOLUTION, (64-3)*MAP_RESOLUTION), //0
            new SHOP('d', "AVR Dragon $500\n\nCyclone $700\n\nDAD Board $900\n\nNSpire $400\n\nSoldering Iron $300\n\nTI-89 $200\n\nUSB Blaster $500", "visuals/sprite_sheets/sprite_walk_d2.png", 22*MAP_RESOLUTION, (64-3)*MAP_RESOLUTION),
            new NPC('r', worldH, "visuals/sprite_sheets/sprite_walk_r3.png", 19*MAP_RESOLUTION, (64-5)*MAP_RESOLUTION),
            new NPC('u', worldI, "visuals/sprite_sheets/sprite_walk_u4.png", 5*MAP_RESOLUTION, (64-10)*MAP_RESOLUTION),
            new NPC('u', worldJ, "visuals/sprite_sheets/sprite_walk_u5.png", 24*MAP_RESOLUTION, (64-13)*MAP_RESOLUTION),
            new NPC('l', worldK, "visuals/sprite_sheets/sprite_walk_l6.png", 32*MAP_RESOLUTION, (64-17)*MAP_RESOLUTION),
            new NPC('r', worldL, "visuals/sprite_sheets/sprite_walk_r7.png", 28*MAP_RESOLUTION, (64-31)*MAP_RESOLUTION),
            new NPC('l', worldM, "visuals/sprite_sheets/sprite_walk_l8.png", 33*MAP_RESOLUTION, (64-38)*MAP_RESOLUTION),
            new NPC('u', "Can I help you?", "visuals/sprite_sheets/sprite_walk_u9.png", 24*MAP_RESOLUTION, (64-49)*MAP_RESOLUTION),
            new NPC('u', "What do you need?", "visuals/sprite_sheets/sprite_walk_u10.png", 18*MAP_RESOLUTION, (64-49)*MAP_RESOLUTION),
            new NPC('u', worldQ, "visuals/sprite_sheets/sprite_walk_u11.png", 16*MAP_RESOLUTION, (64-44)*MAP_RESOLUTION), //10
            new NPC('r', worldS, "visuals/sprite_sheets/sprite_walk_r12.png", 6*MAP_RESOLUTION, (64-33)*MAP_RESOLUTION),
            new NPC('d', worldT, "visuals/sprite_sheets/sprite_walk_d13.png", 14*MAP_RESOLUTION, (64-30)*MAP_RESOLUTION),
            new NPC('d', worldV, "visuals/sprite_sheets/sprite_walk_d14.png", 9*MAP_RESOLUTION, (64-21)*MAP_RESOLUTION),
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
    public static final Texture chegg_tex = new Texture("visuals/inv_items/consume/chegg.png");
    public static final Texture tutoringzone_tex = new Texture("visuals/inv_items/consume/tutoringzone.png");
    public static final Texture quiz_drop_tex = new Texture("visuals/inv_items/consume/quiz_drop.png");
    public static final Texture test_drop_tex = new Texture("visuals/inv_items/consume/test_drop.png");
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
        CHEGG (7),
        TUTORINGZONE (8),
        QUIZ_DROP(9),
        TEST_DROP(10),
        DRAGON (11),
        CYCLONE (12),
        DAD (13),
        MACBOOK (14),
        NSPIRE (15),
        SOLDER (16),
        TI89 (17),
        USB_BLASTER (18),
        WIRE_KIT (19);

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
    public static final Sound rustling = Gdx.audio.newSound(Gdx.files.internal("sound/effects/rustling.mp3"));
    public static final Sound page = Gdx.audio.newSound(Gdx.files.internal("sound/effects/page.wav"));
    public static final Sound oob_error = Gdx.audio.newSound(Gdx.files.internal("sound/effects/oob_error.wav"));

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////CHARACTER DIALOGUE/////////////////////////////////////////////////////////////
    public static Sprite window = new Sprite(new Texture("visuals/Menus/dialogue_window.png"));
    public static Sprite shop_window = new Sprite(new Texture("visuals/Menus/shop_window.png"));
    public static final Sound kaching = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kaching.mp3"));
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        introText4 = "Oh, so your name is " + MainClass.hero.name + "? Such a nice name. Say, my eyes aren't quite " +
                "what they used to be. Step over here so I can see what you look like.";
        introText13 = "Get ready, " + MainClass.hero.name + "! Your GatorQuest adventure is about to begin!";
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////COMBAT SCREEN////////////////////////////////////////////////////////////////////////
        combatScreenMusic.setLooping(true);
        victoryMusic.setLooping(false);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////GAME SCREEN///////////////////////////////////////////////////////////////////////////
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.4f);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////INVENTORY SCREEN//////////////////////////////////////////////////////////////////////
        inventoryScreenSelectionSound.setVolume(1, 1);
        gameMusic.setLooping(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////DIALOGUE WINDOW//////////////////////////////////////////////////////////////////////

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
