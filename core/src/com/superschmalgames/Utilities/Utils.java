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
import com.oracle.xmlns.internal.webservices.jaxws_databinding.JavaWsdlMappingType;
import com.superschmalgames.BOSS;
import com.superschmalgames.NPC;
import com.superschmalgames.SHOP;
import com.superschmalgames.Hero.Move;
import java.text.DecimalFormat;
import java.util.jar.JarEntry;

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
    public static final TiledMap bookstore = new TmxMapLoader().load("visuals/maps/Bookstore.tmx");
    public static final int bookstore_x = 2090;
    public static final int bookstore_y = 3840;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////MOVES///////////////////////////////////////////////////////////////////////
    public static final Move Java_Function = new Move("Java Function", Utils.java_function_tex, "A move that benefits\nfrom improved Software \nskill.", true);
    public static final Move Recursive_Loop = new Move("Recursive Loop",Utils.recursive_function_tex, "A move that benefits\nfrom improved Software \nskill.", true);
    public static final Move Stack_Overflow = new Move("Stack Overflow",Utils.stack_overflow_tex, "A move that benefits\nfrom improved Software \nand Read/Write skills.", true);
    public static final Move Commented_Code = new Move("Commented Code",Utils.commented_code_tex, "A move that benefits\nfrom improved Software\nand Read/Write skills.", true);
    public static final Move Double_Integration = new Move("Double Integration",Utils.double_integration_tex, "A move that benefits\nfrom improved Math \nskills.", true);
    public static final Move Set_Equal_to_0 = new Move("Set Equal to 0",Utils.set_equal_to_0_tex, "A move that benefits\nfrom improved Math \nskills.", true);
    public static final Move Practice_Test = new Move("Practice Test",Utils.practice_test_tex, "A move that benefits\nfrom improved Focus \nskill.", true);
    public static final Move Extra_Credit = new Move("Extra Credit",Utils.extra_credit_tex, "A move that does not\nbenefit from your\nstats. Pure luck.", true);
    public static final Move CPP_Skills = new Move("C++ Skills",Utils.cpp_skills_tex, "A move that benefits\nfrom improved Software\nskill.", true);
    public static final Move Nodal_Analysis = new Move("Nodal Analysis",Utils.nodal_analysis_tex, "A move that benefits\nfrom improved Hardware\nskill.", true);
    public static final Move F2_Solve = new Move("F2 Solve",Utils.f2_solve_tex, "A move that benefits\nfrom improved Math\nand Focus skills.", true);
    public static final Move _5_Lines_Matlab_Code = new Move("5 Lines Matlab Code",Utils.f_lines_matlab_code_tex, "A move that benefits\nfrom improved Software\nand Math skills.", true);
    public static final Move Karnaugh_Map = new Move("Karnaugh Map",Utils.karnaugh_map_tex, "A move that benefits\nfrom improved\nEndurance and\nHardware skills.", true);
    public static final Move Soldering_Skills = new Move("Soldering Skills",Utils.soldering_skills_tex, "A move that benefits\nfrom improved\nEndurance and\nFocus skills.", true);
    public static final Move Boolean_Logic = new Move("Boolean Logic",Utils.boolean_logic_tex, "A move that benefits\nfrom improved Math,\nFocus and Hardware\nskills.", true);
    public static final Move Documentation = new Move("Documentation",Utils.documentation_tex, "A move that benefits\nfrom improved\nRead/Write skills.", true);
    public static final Move Code_Testing = new Move("Code Testing",Utils.code_testing_tex, "A move that benefits\nfrom improved Software\nand Focus skills.", true);
    public static final Move Perf_Presentation = new Move("Perf. Presentation",Utils.perfect_presentation_tex, "A move that benefits\nfrom improved Social\nskill.", true);
    private static final Move[] Sriv_attacks = {Java_Function};
    private static final Move[] Wong_attacks = {Java_Function};
    private static final Move[] Gugel_attacks = {Java_Function};
    private static final Move[] Dobbins_attacks = {Java_Function};
    private static final Move[] Schmalz_attacks = {Java_Function};
    private static final Move[] Chui_attacks = {Java_Function};
    private static final Move[] Horton_attacks = {Java_Function};
    private static final Move[] Small_attacks = {Java_Function};
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////NPC DIALOGUE START///////////////////////////////////////////////////////////////////////////////////////
    public static final String dormA = "Hi! I’m a freshman too. Are you in The Good Life too? I’ve heard it’s really hard!";
    public static final String dormB = "Don’t forget to look at your Degree Audit! Press h to see it in the Hero menu, and it will tell you the order of the classes you have to complete.";
    public static final String dormC = "Your Campus Map will tell you where all of the buildings on campus are.";
    public static final String dormD = "This dining hall food is giving me some serious stomach issues…*belch* Oops!";
    public static final String dormEInitial = "Will you proofread my essay for me? I’m nervous that I’m going to get a bad grade on my first college paper…";
    public static final String dormEYes = "Your Reading Comprehension and Writing Skill increased by 1.";
    public static final String dormENo = "Okay fine.";
    public static final String dormEUsed = "I got a B on that paper.";
    public static final String dormF = "It seems like there are so many classes I have to take! How am I going to fit all of this into 8 semesters???";
    public static final String dormGInitial = "Is it just me, or does this dorm feel like a jail cell?";
    public static final String dormGYes = "Your Social Skills increased by 1.";
    public static final String dormGNo = "Y i k e s.";
    public static final String dormGUsed = "This place is a jail!";

    public static final String worldH = "UF is a great place to go to school. We have so many great Alumni like GloZell, Ryan Lochte and even Dan Bilzerian!";
    public static final String worldI = "College is so easy. I was an IB Student in high school and that was WAY harder. Yes, I’m a business major. Why do you ask? What does that have to do with this?";
    public static final String worldJ = "I’m late I’m late I’m late I’m late";
    public static final String worldK = "I just missed my RTS bus and the next one doesn’t come for another 15 minutes.";
    public static final String worldL = "Jimmy Johns just brought me my sandwich in 3 and a half minutes. That really is freaky fast delivery.";
    public static final String worldM = "The Reitz construction is finally done! Bless. Up.";
    public static final String worldNInitial = "I love math! It’s the universal language, you know? Do you want me to teach you a trick I learned today in class?";
    public static final String worldNYes = "Your Math Skills increased by 1.";
    public static final String worldNNo = "I mean...Okay.";
    public static final String worldNUsed = "I hope that trick comes in handy.";
    public static final String worldO = "I’m reading Siddhartha and I really think it’s opened my eyes. I feel so enlightened. Come here, I want you to be in my Snap Story.";
    public static final String worldP = "Well, I’m technically a junior by credits. No, I mean, this is my first semester at college. If you factor in my AP classes, though...";
    public static final String worldQ = "You should never let your GPA drop to 0.00! When that happened to me, I woke up back in my down with a ton of Gator Bucks missing.";
    public static final String worldRInitial = "I’m having a really tough time finding my next class. It’s in Turlington. Can you point me in the right direction?";
    public static final String worldRYes = "Your Social Skills increased by 1.";
    public static final String worldRNo = "K bye.";
    public static final String worldRUsed = "I can do this!";
    public static final String worldRRInitial = "I heard that some professors are suckers for particular types of moves. Do you have to take Calc 2?";
    public static final String worldRRYes = "Ryan taught you how to utilize Extra Credit";
    public static final String worldRRNo = "Ah, you're lucky.";
    public static final String worldRRUsed = "Good luck with Calc 2.";
    public static final String worldS = "I heard that there are kids who will tutor you in Marston library, but some of them charge way too many Gator Bucks for me...";
    public static final String worldT = "TA’s and professors will challenge you if you walk around in one of the class buildings!";
    public static final String worldUInitial = "Do you have to take Dr. Wong?";
    public static final String worldUYes = "Uke taught you how to use 5 Lines Matlab Code";
    public static final String worldUNo = "Ohh, I understand.";
    public static final String worldUUsed = "Have fun!";
    public static final String worldV = "College is hard, but I think it’s worth it.";
    public static final String worldWInitial = "Look at what I just learned how to do!?";
    public static final String worldWYes = "Wanda taught you how to use F2 Solve";
    public static final String worldWNo = "Fine. Rude.";
    public static final String worldWUsed = "Isn't that a cool trick?";
    public static final String worldX = "I’m overwhelmed with all of these assignments, and I don’t think all of this coffee is helping my anxiety!";
    public static final String worldYInitial = "Oh, you’re an engineer? Maybe you would have some use for this? Would you like to buy my old TI-89? I’ll sell it to you for 80 Gator Bucks.";
    public static final String worldYYesInsfFunds = "You need more money.";
    public static final String worldYYesSuffFunds = "Here you go. You receive a TI-89.";
    public static final String worldYNo = "Fine, then.";
    public static final String worldYUsed = "Hope it helps you.";
    public static final String worldZInitial = "You’re a gator fan too, huh? Want to buy a Gator Hat for 30 Gator bucks?";
    public static final String worldZYesInsfFunds = "Get that money up, brother.";
    public static final String worldZYesSuffFunds = "Alright, sweet. You receive a Gator Hat.";
    public static final String worldZNo = "Nevermind, then.";
    public static final String worldZUsed = "It will look good on you!";

    public static final String turlingtonAPre = "Haven’t you ever heard of trig sub?";
    public static final String turlingtonAWin = "You’ll need to review your unit circle...";
    public static final String turlingtonALose = "It would seem that you’ve gotten the hang of this...";
    public static final String turlingtonBPre = "You need to integrate math into your life. Get it?";
    public static final String turlingtonBWin = "Perhaps you should review those theorems.";
    public static final String turlingtonBLose = "Good job! I’m proud of you.";
    public static final String turlingtonCPre = "It’s called a cardioid because it looks like a heart. Can’t you see?";
    public static final String turlingtonCWin = "You’ll have to take another look at those homeworks...";
    public static final String turlingtonCLose = "It would appear that you get the picture.";
    public static final String turlingtonDPre = "There will be no texting in my class!";
    public static final String turlingtonDWin = "You should have been paying more attention.";
    public static final String turlingtonDLose = "I guess your focus paid off.";
    public static final String turlingtonEPre = "Make sure to keep track of your pointers.";
    public static final String turlingtonEWin = "You’re having an error.";
    public static final String turlingtonELose = "Flawless code. Good work.";
    public static final String turlingtonFPre = "I love group projects!";
    public static final String turlingtonFWin = "You have to be a team player.";
    public static final String turlingtonFLose = "Collaboration is your strong-suit.";
    public static final String turlingtonGPre = "Why am I here?";
    public static final String turlingtonGWin = "I literally hate every college student.";
    public static final String turlingtonGLose = "I literally hate every college student.";
    public static final String turlingtonHPre = "What do you mean you’ve never worked with a binary search tree?";
    public static final String turlingtonHWin = "Do you even know who Djikstra is?";
    public static final String turlingtonHLose = "You found the shortest path. I commend you.";
    public static final String turlingtonIPre = "Linked lists are your friend!";
    public static final String turlingtonIWin = "It seems like your links got broken...!";
    public static final String turlingtonILose = "Your chain of links held strong!";

    public static final String ciseJPre = "Coding is fun! I like working in Python.";
    public static final String ciseJWin = "Maybe you should start with some online tutorials...";
    public static final String ciseJLose = "I think you’ve got the hang of this!";
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
    public static final String ciseNWin = "This collaboration just didn’t work out.";
    public static final String ciseNLose = "You all clearly worked well together!";
    public static final String ciseOPre = "Show me the data and I’ll believe it.";
    public static final String ciseOWin = "This methodology is flawed.";
    public static final String ciseOLose = "I think we should try to publish this!";

    public static final String cisePPre = "Make sure that your circuit is grounded.";
    public static final String cisePWin = "Your nodal analysis is off.";
    public static final String cisePLose = "Good work. This is the correct voltage.";
    public static final String ciseQPre = "Kirchoff’s law is an invaluable tool for you.";
    public static final String ciseQWin = "This doesn’t add up!";
    public static final String ciseQLose = "Everything balances out.";
    public static final String ciseRPre = "You should try using a wheatstone bridge.";
    public static final String ciseRWin = "You need to review your electrical engineering history.";
    public static final String ciseRLose = "Good work on finding the missing value.";
    public static final String ciseSPre = "Signals are all around us!";
    public static final String ciseSWin = "You should try to learn about your surroundings.";
    public static final String ciseSLose = "We’re on the same frequency, it would seem.";
    public static final String ciseTPre = "Have you heard of a fourier transform?";
    public static final String ciseTWin = "These calculations don’t seem right.";
    public static final String ciseTLose = "You got the right answer!";
    public static final String ciseUPre = "Matlab is a valuable tool in our industry.";
    public static final String ciseUWin = "You should have utilized the help option if you were confused.";
    public static final String ciseULose = "All of your functions are correct.";
    public static final String ciseVPre = "You have to be familiar with low-true logic.";
    public static final String ciseVWin = "You’re missing some inverters.";
    public static final String ciseVLose = "Nice diagramming!";
    public static final String ciseWPre = "If you did your lab then you should have no problem with this quiz.";
    public static final String ciseWWin = "Are you sure you built this circuit yourself?";
    public static final String ciseWLose = "Your stoplight works! Good work.";
    public static final String ciseXPre = "Make sure to minimize the states in your UML.";
    public static final String ciseXWin = "You’re missing some signals in your diagram.";
    public static final String ciseXLose = "You did it in the least amount of states possible!";

    public static final String marstonA = "I came here to study, but it seems like everyone else just came to socialize...Why are they at the library?";
    public static final String marstonB = "Sorority recruitment is such a nightmare. I can’t believe they want us to wear heels the whole day!";
    public static final String marstonC = "I’m here to meet up with people for a group project but I’m the only one that showed up. They did mean 10:30AM...Right?";
    public static final String marstonD = "I hate coming to the library, but I never get stuff done at home. I guess I’m stuck here...";
    public static final String marstonE = "There are so many books here, but I don’t think I’ve ever used any of them. I just go on my laptop when I’m here.";
    public static final String marstonF = "I’m calculating the lowest grade that I can get on this final that will still let me pass the class. It looks like I need a 99%...That can’t be right.";
    public static final String marstonG = "I’m studying for pre-calc right now. This is the hardest class ever! I aced it in high school, but it’s just so different here...";
    public static final String marstonH = "The library always stresses me out because everyone else seems stressed out. I think it might be contagious...";
    public static final String marstonI = "I’m tired of reading! I already read 45 pages about Ancient Civilizations today. Can I stop now?";
    public static final String marstonJ = "Don’t bother me! I’m studying.";
    public static final String marstonKUnused = "Oh, you’re a computer engineer? You can have this old thing. I switched my major to Business, so I won’t be needing it… Avatar receives a Soldering Iron";
    public static final String marstonKUsed = "Enjoy that soldering iron. Try not to burn yourself.";
    public static final String marstonLUnused = "I’m getting my PhD in mathematics at UF, so I can tutor you if you want. It’s $300 per session.";
    public static final String marstonLYesInsfFunds = "Sorry, you don't have the money...";
    public static final String marstonLNo = "Let me know if you change your mind.";
    public static final String marstonLSuffFunds = "Alright, let's get started. Your Math Skills increase by 1 and you pay 300GB.";
    public static final String marstonLUsed = "I hope that helped.";
    public static final String marstonMUnused = "I need someone with a Software Skill of at least 4 to help me with this Java 1 project! Will you help me?";
    public static final String marstonMYesInsfSkill = "I don't think you have the skills required...";
    public static final String marstonMNo = "I'll have tp figure it out on my own.";
    public static final String marstonMYesSuffSkill = "Thanks! It compiled this time. You eared 200 GB.";
    public static final String marstonMUsed = "Thanks for the help!";
    public static final String marstonNUnused = "Hi! Would you like a class ring to value your time here at UF forever? Each ring contains a beautiful gem and it will be a constant reminder of your amazing adventures here. Would you like to buy one? A class ring costs 300.";
    public static final String marstonNYesInsfFunds = "Sorry, you'll need more money than that.";
    public static final String marstonNNo = "Thanks! Here's your ring. Received Class Ring.";
    public static final String marstonNSuffFunds = "Maybe next time?";
    public static final String marstonNUsed = "Enjoy your ring!";
    public static final String marstonOUnused = "Do you want to buy my USB Blaster? I’m selling it for only 50GB. That’s a steal!";
    public static final String marstonOYesInsfFunds = "Erm, it costs 50GB.";
    public static final String marstonONo = "Understandable.";
    public static final String marstonOSuffFunds = "Here you go! You receive a USB Blaster.";
    public static final String marstonOUsed = "Thanks for taking it off my hands.";
    //////////////////////////////////////DUNGEON ENEMIES//////////////////////////////////////////////////////////////////////
    //when adding enemies to the various arrays, the proper tiles must be accounted for in the Tiled Map Editor
    //========================================NEB=============================================
    //89
    public static NPC[] NEB_enemies = {
            new NPC('l',"Welcome to the New Engineering Building", "visuals/sprite_sheets/sprite_walk_l7.png", 65*MAP_RESOLUTION,12*MAP_RESOLUTION), //0
            new NPC('r',"What time does class start?", "visuals/sprite_sheets/sprite_walk_r11.png", 79*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',"Another wild Zubat appeared", "visuals/sprite_sheets/sprite_walk_r9.png", 51*MAP_RESOLUTION, 19*MAP_RESOLUTION),
            new NPC('d',"as;dfjkdslaf;", "visuals/sprite_sheets/sprite_walk_d1.png", 59*MAP_RESOLUTION, 41*MAP_RESOLUTION),
            new NPC('l',"as;dfjkdslaf;", "visuals/sprite_sheets/sprite_walk_l3.png", 46*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('r',"as;dfjkdslaf;", "visuals/sprite_sheets/sprite_walk_r6.png", 44*MAP_RESOLUTION, 48*MAP_RESOLUTION),
            new NPC('l',"abdfsefdscsd", "visuals/sprite_sheets/sprite_walk_l14.png", 48*MAP_RESOLUTION, 54*MAP_RESOLUTION),
            new NPC('u',"LEAVE ME ALONE", "visuals/sprite_sheets/sprite_walk_u12.png", 47*MAP_RESOLUTION, 82*MAP_RESOLUTION),
            new NPC('d',"Go away please", "visuals/sprite_sheets/sprite_walk_d9.png", 22*MAP_RESOLUTION, 53*MAP_RESOLUTION),
            new NPC('l',"adsfds", "visuals/sprite_sheets/sprite_walk_l10.png", 45*MAP_RESOLUTION, 7*MAP_RESOLUTION),
            new NPC('d',"asdf", "visuals/sprite_sheets/sprite_walk_d13.png", 41*MAP_RESOLUTION, 23*MAP_RESOLUTION),
            new NPC('l',"asdfsdf", "visuals/sprite_sheets/sprite_walk_l12.png", 49*MAP_RESOLUTION, 32*MAP_RESOLUTION),
            new NPC('d',"asdfsd", "visuals/sprite_sheets/sprite_walk_d3.png", 30*MAP_RESOLUTION, 27*MAP_RESOLUTION),
            new NPC('l',"asdfsadf","visuals/sprite_sheets/sprite_walk_l11.png", 24*MAP_RESOLUTION, 20*MAP_RESOLUTION),
            new NPC('r',"sadfdsf","visuals/sprite_sheets/sprite_walk_r7.png", 28*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION),
            new NPC('d',"gasdefds","visuals/sprite_sheets/sprite_walk_d5.png", 16*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d',"asfddsf","visuals/sprite_sheets/sprite_walk_d4.png",3*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new NPC('u',"asddfs", "visuals/sprite_sheets/sprite_walk_u9.png",3*MAP_RESOLUTION, (89-49)*MAP_RESOLUTION),  //17
            new NPC('r',"baddsf", "visuals/sprite_sheets/sprite_walk_r1.png", 3*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d14.png", 16*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d2.png", 27*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d6.png", 45*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u8.png", 68*MAP_RESOLUTION, (89-6)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r9.png", 96*MAP_RESOLUTION, (89-72)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u3.png", 97*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 110*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d11.png", 122*MAP_RESOLUTION, (89-62)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r12.png", 121*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l3.png", 113*MAP_RESOLUTION, (89-38)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l7.png", 125*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r10.png", 118*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION), //30
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d14.png", 133*MAP_RESOLUTION, (89-4)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l9.png", 140*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u8.png", 140*MAP_RESOLUTION, (89-35)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d7.png", 149*MAP_RESOLUTION, (89-19)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l6.png", 170*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d5.png", 137*MAP_RESOLUTION, (89-80)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u4.png", 143*MAP_RESOLUTION, (89-57)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d3.png", 165*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u2.png", 160*MAP_RESOLUTION, (89-83)*MAP_RESOLUTION), //39
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 187*MAP_RESOLUTION, (89-68)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u14.png", 190*MAP_RESOLUTION, (89-50)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u13.png", 202*MAP_RESOLUTION, (89-69)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d12.png", 214*MAP_RESOLUTION, (89-66)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r11.png", 211*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u10.png", 229*MAP_RESOLUTION, (89-58)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l9.png", 224*MAP_RESOLUTION, (89-52)*MAP_RESOLUTION), //46
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 187*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l2.png", 205*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d3.png", 212*MAP_RESOLUTION, (89-24)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d4.png", 229*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION), //50
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r5.png", 246*MAP_RESOLUTION, (89-22)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u6.png", 258*MAP_RESOLUTION, (89-26)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d7.png", 283*MAP_RESOLUTION, (89-16)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d8.png", 284*MAP_RESOLUTION, (89-41)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l9.png", 276*MAP_RESOLUTION, (89-51)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d10.png", 287*MAP_RESOLUTION, (89-70)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d11.png", 269*MAP_RESOLUTION, (89-61)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u12.png", 262*MAP_RESOLUTION, (89-54)*MAP_RESOLUTION), //58
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d13.png", 262*MAP_RESOLUTION, (89-37)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r14.png", 248*MAP_RESOLUTION, (89-46)*MAP_RESOLUTION),
            new BOSS('d', "I am Srivastava", "Congratulations on passing the course", "visuals/Professors/srivastava/srivastava_d.png", 112*MAP_RESOLUTION, (89-33)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Sriv_attacks),
            new BOSS('d', "I am Wong", "Congratulations on passing the course", "visuals/Professors/wong/wong_d.png", 211*MAP_RESOLUTION, (89-55)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Wong_attacks),
            new BOSS('d', "I am Gugel", "Congratulations on passing the course", "visuals/Professors/gugel/gugel_d.png", 287*MAP_RESOLUTION, (89-15)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Gugel_attacks),
    };
    //========================================================================================
    //========================================CISE============================================
    public static NPC[] CISE_enemies = {
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 5*MAP_RESOLUTION, (66-8)*MAP_RESOLUTION),  //0
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d2.png", 21*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u3.png", 3*MAP_RESOLUTION, (66-24)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d4.png", 28*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r5.png", /*1**/MAP_RESOLUTION, (66-32)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l6.png", 48*MAP_RESOLUTION, (66-8)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r5.png", 33*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l6.png", 17*MAP_RESOLUTION, (66-41)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r7.png", 18*MAP_RESOLUTION, (66-55)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l8.png", 31*MAP_RESOLUTION, (66-44)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u9.png", 44*MAP_RESOLUTION, (66-44)*MAP_RESOLUTION),  //10
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l10.png", 88*MAP_RESOLUTION, (66-61)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d11.png", 75*MAP_RESOLUTION, (66-39)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u12.png", 77*MAP_RESOLUTION, (66-22)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d13.png", 72*MAP_RESOLUTION, (66-9)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u14.png", 101*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d1.png", 124*MAP_RESOLUTION, (66-6)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r2.png", 130*MAP_RESOLUTION, (66-12)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l3.png", 146*MAP_RESOLUTION, (66-6)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d4.png", 109*MAP_RESOLUTION, (66-29)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r5.png", 130*MAP_RESOLUTION, (66-28)*MAP_RESOLUTION), //20
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r6.png", 101*MAP_RESOLUTION, (66-47)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l7.png", 123*MAP_RESOLUTION, (66-55)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d8.png", 130*MAP_RESOLUTION, (66-60)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d9.png", 143*MAP_RESOLUTION, (66-43)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d10.png", 175*MAP_RESOLUTION, (66-7)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l11.png", 202*MAP_RESOLUTION, (66-12)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d13.png", 213*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r14.png", 168*MAP_RESOLUTION, (66-34)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u1.png", 167*MAP_RESOLUTION, (66-47)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u2.png", 202*MAP_RESOLUTION, (66-30)*MAP_RESOLUTION), //30
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l3.png", 217*MAP_RESOLUTION, (66-32)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l4.png", 213*MAP_RESOLUTION, (66-42)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u5.png", 199*MAP_RESOLUTION, (66-58)*MAP_RESOLUTION),
            new BOSS('d', "I am Dobbins", "Congratulations on passing the course", "visuals/Professors/dobbins/dobbins_d.png", 2*MAP_RESOLUTION, (66-3)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Dobbins_attacks),
            new BOSS('d', "I am Schmalz", "Congratulations on passing the course", "visuals/Professors/schmalz/schmalz_d.png", 168*MAP_RESOLUTION, (66-4)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Schmalz_attacks)

    };
    //========================================================================================
    //========================================Turlington======================================
    public static NPC[] Turlington_enemies = {
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u1.png", 17*MAP_RESOLUTION, (128-116)*MAP_RESOLUTION),  //0
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u2.png", 32*MAP_RESOLUTION, (128-123)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r3.png", 47*MAP_RESOLUTION, (128-109)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d4.png", 82*MAP_RESOLUTION, (128-121)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l5.png", 95*MAP_RESOLUTION, (128-98)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l6.png", 111*MAP_RESOLUTION, (128-90)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d7.png", 79*MAP_RESOLUTION, (128-85)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u8.png", 32*MAP_RESOLUTION, (128-71)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d9.png", 8*MAP_RESOLUTION, (128-80)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l10.png", 46*MAP_RESOLUTION, (128-47)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u11.png", 56*MAP_RESOLUTION, (128-50)*MAP_RESOLUTION), //10
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l12.png", 48*MAP_RESOLUTION, (128-35)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d13.png", 73*MAP_RESOLUTION, (128-24)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r14.png", 86*MAP_RESOLUTION, (128-34)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d1.png", 100*MAP_RESOLUTION, (128-29)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d2.png", 107*MAP_RESOLUTION, (128-57)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r3.png", 74*MAP_RESOLUTION, (128-10)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u4.png", 89*MAP_RESOLUTION, (128-15)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l5.png", 106*MAP_RESOLUTION, (128-13)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r6.png", 132*MAP_RESOLUTION, (128-16)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u7.png", 152*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION), //20
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l8.png", 164*MAP_RESOLUTION, (128-28)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u9.png", 139*MAP_RESOLUTION, (128-45)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l10.png", 155*MAP_RESOLUTION, (128-42)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d11.png", 174*MAP_RESOLUTION, (128-48)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l12.png", 182*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u13.png", 193*MAP_RESOLUTION, (128-31)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u14.png", 211*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d1.png", 225*MAP_RESOLUTION, (128-18)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l2.png", 233*MAP_RESOLUTION, (128-38)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r3.png", 136*MAP_RESOLUTION, (128-58)*MAP_RESOLUTION), //30
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r4.png", 125*MAP_RESOLUTION, (128-77)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r5.png", 135*MAP_RESOLUTION, (128-92)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d6.png", 159*MAP_RESOLUTION, (128-65)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u7.png", 146*MAP_RESOLUTION, (128-97)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r8.png", 168*MAP_RESOLUTION, (128-102)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d9.png", 151*MAP_RESOLUTION, (128-121)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l10.png", 169*MAP_RESOLUTION, (128-113)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u11.png", 274*MAP_RESOLUTION, (128-125)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l12.png", 292*MAP_RESOLUTION, (128-113)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r13.png", 261*MAP_RESOLUTION, (128-92)*MAP_RESOLUTION), //40
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u14.png", 271*MAP_RESOLUTION, (128-95)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 291*MAP_RESOLUTION, (128-91)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r2.png", 259*MAP_RESOLUTION, (128-44)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r3.png", 255*MAP_RESOLUTION, (128-60)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u4.png", 270*MAP_RESOLUTION, (128-47)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d5.png", 291*MAP_RESOLUTION, (128-50)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l6.png", 274*MAP_RESOLUTION, (128-71)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r7.png", 291*MAP_RESOLUTION, (128-65)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r8.png", 249*MAP_RESOLUTION, (128-20)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r9.png", 270*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION), //50
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u10.png", 288*MAP_RESOLUTION, (128-24)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l11.png", 301*MAP_RESOLUTION, (128-28)*MAP_RESOLUTION),
            new NPC('l', "aassdf", "visuals/sprite_sheets/sprite_walk_l12.png", 320*MAP_RESOLUTION, (128-22)*MAP_RESOLUTION),
            new NPC('u', "aassdf", "visuals/sprite_sheets/sprite_walk_u13.png", 334*MAP_RESOLUTION, (128-26)*MAP_RESOLUTION),
            new NPC('d', "aassdf", "visuals/sprite_sheets/sprite_walk_d14.png", 349*MAP_RESOLUTION, (128-19)*MAP_RESOLUTION),
            new NPC('r', "aassdf", "visuals/sprite_sheets/sprite_walk_r1.png", 353*MAP_RESOLUTION, (128-30)*MAP_RESOLUTION), //56
            new BOSS('d', "I am Chui", "Congratulations on passing the course", "visuals/Professors/chui/chui_d.png", 46*MAP_RESOLUTION, (128-27)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Chui_attacks),
            new BOSS('d', "I am Horton", "Congratulations on passing the course", "visuals/Professors/horton/horton_d.png", 170*MAP_RESOLUTION, (128-122)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Horton_attacks),
            new BOSS('d', "I am Small", "Congratulations on passing the course", "visuals/Professors/small/small_d.png", 362*MAP_RESOLUTION, (128-16)*MAP_RESOLUTION, 1,1,1,1,1,1,1, Small_attacks),
    };
    //========================================================================================
    //========================================Dorm============================================
    public static NPC[] Dorm_enemies = {
            new NPC('u',"Welcome to UF, this is your dorm room.", "visuals/sprite_sheets/sprite_walk_u3.png",9*MAP_RESOLUTION,20*MAP_RESOLUTION),
            new NPC('d',"You better not steal anything", "visuals/sprite_sheets/sprite_walk_d5.png", 17*MAP_RESOLUTION,24*MAP_RESOLUTION),
            new NPC('l',"I have no idea how to play the piano","visuals/sprite_sheets/sprite_walk_l1.png", 25*MAP_RESOLUTION,12*MAP_RESOLUTION),
            new NPC('r',"If you ever feel tired just return here","visuals/sprite_sheets/sprite_walk_r8.png", 10*MAP_RESOLUTION,3*MAP_RESOLUTION),
            new NPC('r',dormA,"visuals/sprite_sheets/sprite_walk_r13.png",17*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('l',dormB, "visuals/sprite_sheets/sprite_walk_l12.png", 21*MAP_RESOLUTION,9*MAP_RESOLUTION),
            new NPC('r',worldO, "visuals/sprite_sheets/sprite_walk_r2.png", MAP_RESOLUTION,31*MAP_RESOLUTION),
            new NPC('u',dormC,"visuals/sprite_sheets/sprite_walk_u14.png", 20*MAP_RESOLUTION,35*MAP_RESOLUTION),
            new NPC('r', dormD, "visuals/sprite_sheets/sprite_walk_r6.png", 6*MAP_RESOLUTION,42*MAP_RESOLUTION),
            new NPC('d',worldX,"visuals/sprite_sheets/sprite_walk_d4.png", 24*MAP_RESOLUTION,41*MAP_RESOLUTION)
    };
    //========================================================================================
    //========================================Marston=========================================
    public static NPC[] Marston_enemies = {
            new SHOP('r', "visuals/sprite_sheets/sprite_walk_r1.png", 45*MAP_RESOLUTION, (45-18)*MAP_RESOLUTION), //0
            new NPC('d', marstonA, "visuals/sprite_sheets/sprite_walk_d2.png", 48*MAP_RESOLUTION, (45-14)*MAP_RESOLUTION),
            new NPC('u', marstonC, "visuals/sprite_sheets/sprite_walk_u3.png", 45*MAP_RESOLUTION, (45-30)*MAP_RESOLUTION),
            new NPC('r', marstonB, "visuals/sprite_sheets/sprite_walk_r4.png", 40*MAP_RESOLUTION, (45-16)*MAP_RESOLUTION),
            new NPC('l', marstonD, "visuals/sprite_sheets/sprite_walk_l5.png", 37*MAP_RESOLUTION, (45-35)*MAP_RESOLUTION),
            new NPC('u', "Do you need something from me?", "visuals/sprite_sheets/sprite_walk_u6.png", 24*MAP_RESOLUTION, (45-26)*MAP_RESOLUTION),
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
            new SHOP('d', "visuals/sprite_sheets/sprite_walk_d1.png", 25*MAP_RESOLUTION, (64-3)*MAP_RESOLUTION), //0
            new SHOP('d', "visuals/sprite_sheets/sprite_walk_d2.png", 22*MAP_RESOLUTION, (64-3)*MAP_RESOLUTION),
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
