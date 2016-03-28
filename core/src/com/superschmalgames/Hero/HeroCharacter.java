package com.superschmalgames.Hero;

//This class will represent our main playable character.

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.superschmalgames.Inventory.ApparelItem;
import com.superschmalgames.Inventory.EquipableItem;
import com.superschmalgames.NPC.ENEMY;
import com.superschmalgames.NPC.NPC;
import com.superschmalgames.Utilities.Animator;
import com.superschmalgames.Utilities.MainClass;

import java.util.Random;

public class HeroCharacter {

    public int outfitNum;
    public String name;     //Our hero character's name. Can be set at start of game, and used in dialogue with NPC's.
    public double GPA;       //Character's Grade Point Average. Acts as a "health meter." If reduced to zero, character loses fight.

    public float width;
    public float height;
    public float xPos;
    public float yPos;

    //The character's inventory of items, and which item is currently being worn (apparel) and held (equipment)
    public HeroInventory inventory;
    public HeroMoves moves;

    public ApparelItem heroApparel;
    public EquipableItem heroEquipment;

    //Represents numbers of semester completed. Each named professor beaten will increase this by 1.
    public int semester;
    public int gatorBucks;
    public int experience;
    public int expCap;
    public int lvl;

    //Character stats.
    public double Software;
    public double Hardware;
    public double Writing;
    public double Endurance;
    public double Social;
    public double Math;
    public double Focus;
    private final double STAT_CAP = 10.0;   //Cap all stats at 10.0 (but may exceed 10 through equipment buffs)

    //Character Stats including Apparel/Equipment
    public double Software_buf;
    public double Hardware_buf;
    public double Writing_buf;
    public double Endurance_buf;
    public double Social_buf;
    public double Math_buf;
    public double Focus_buf;

    //Character stats used while in combat in order to boost damage dealt or reduce damage received.
    public double Attack;
    public int attack_dur;
    public double Defense;
    public int defense_dur;

    //Animators that take care of walking in each direction.
    public Animator leftWalk, rightWalk, upWalk, downWalk;
    public char lastDir;
    public boolean canMove;

    //Tracks the NPC we just talked to, so we can reference him when in combat, etc.
    public NPC lastNPC;
    public ENEMY lastEnemy;

    //Sprite that's shown for the hero during combat.
    public Sprite combatSprite;

    //Main animator that handles switching between all different direction the character can walk.
    public Animator heroAnim;

    public HeroCharacter(){
        GPA = 4.0;
        Software = 1.0;
        Hardware = 1.0;
        Writing = 1.0;
        Endurance = 1.0;
        Social = 1.0;
        Math = 1.0;
        Focus = 1.0;

        Software_buf = 1.0;
        Hardware_buf = 1.0;
        Writing_buf = 1.0;
        Endurance_buf = 1.0;
        Social_buf = 1.0;
        Math_buf = 1.0;
        Focus_buf = 1.0;

        Attack = 1;
        Defense = 1;
        attack_dur = 0;
        defense_dur = 0;

        //Create and initialize hero character's inventory.
        inventory = new HeroInventory();
        //Create and initialize hero character's move list.
        moves = new HeroMoves();

        //#Semesters completed. Each named professor beaten increments this by 1.
        semester = 8;
        gatorBucks = 10000;
        experience = 0;
        lvl = 1;
        expCap = 10;

        canMove = true;
    }

    public void initAnimations(){

        //Initialize the skin that will be displayed on the character.
        leftWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_l"+outfitNum+".png", 0.17f);
        rightWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_r"+outfitNum+".png", 0.17f);
        upWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_u"+outfitNum+".png", 0.17f);
        downWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_d"+outfitNum+".png", 0.17f);

        //Grab the texture region for our hero facing right, so that we can use it to render him/her in combat.
        heroAnim = rightWalk;
        heroAnim.currentFrame = rightWalk.walkAnimation.getKeyFrame(rightWalk.stateTime, true);
        combatSprite = new Sprite(heroAnim.currentFrame);

        //Set up the initial frame that will be rendered to the screen before any input is given.
        heroAnim = downWalk;
        heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(downWalk.stateTime, true);
    }

    //Gets the current frame of the proper walking animation for the character
    public void walkAnimation(char direction, float deltaTime) {
        switch (direction) {
            case 'L':   leftWalk.stateTime += deltaTime;
                        heroAnim.currentFrame = leftWalk.walkAnimation.getKeyFrame(leftWalk.stateTime, true);
                        lastDir = direction;  //Track direction we last walked so we can use it in standAnimation().
                        break;
            case 'R':   rightWalk.stateTime += deltaTime;
                        heroAnim.currentFrame = rightWalk.walkAnimation.getKeyFrame(rightWalk.stateTime, true);
                        lastDir = direction;
                        break;
            case 'U':   upWalk.stateTime += deltaTime;
                        heroAnim.currentFrame = upWalk.walkAnimation.getKeyFrame(upWalk.stateTime, true);
                        lastDir = direction;
                        break;
            case 'D':   downWalk.stateTime += deltaTime;
                        heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(downWalk.stateTime, true);
                        lastDir = direction;
                        break;
        }

    }

    //Gets the "standing" frame of the direction our character last walked, so it looks like he is standing still
    //(and not mid-stride) when the player releases the button to make him walk.
    public void standAnimation(){
        switch(lastDir){
            case 'L':   heroAnim.currentFrame = leftWalk.walkAnimation.getKeyFrame(0f, true);
                break;
            case 'R':   heroAnim.currentFrame = rightWalk.walkAnimation.getKeyFrame(0f, true);
                break;
            case 'U':   heroAnim.currentFrame = upWalk.walkAnimation.getKeyFrame(0f, true);
                break;
            case 'D':   heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(0f, true);
                break;
        }
    }

    //sets position of character
    public void setPosition(float x, float y) {
        xPos = x-width/2;
        yPos = y-height/2;
    }

    public void setMove(boolean set) {
        canMove = set;
    }

    public boolean canMove() {
        return canMove;
    }

    public void fillGPA() {
        GPA = 4.0;
        System.out.print("GPA filled");
    }

    public void winCombat(int e, int money) {
        gatorBucks += money;
        experience += e;
        if (experience >= expCap)
            level();
    }

    private void mult_level() {
        if (experience >= expCap)
            level();
    }

    //needs to be finished
    public void level() {
        boolean temp = true;
        lvl++;
        experience -= expCap;
        Random rand;
        while(temp) {
            rand = new Random();
            switch (rand.nextInt(7)) {
                case 6:
                    if(Software < STAT_CAP) {
                        Software++;
                        Software_buf++;
                        temp = false;
                    }
                    break;
                case 5:
                    if(Hardware < STAT_CAP) {
                        Hardware++;
                        Hardware_buf++;
                        temp = false;
                    }
                    break;
                case 4:
                    if(Writing < STAT_CAP) {
                        Writing++;
                        Writing_buf++;
                        temp = false;
                    }
                    break;
                case 3:
                    if(Endurance < STAT_CAP) {
                        Endurance++;
                        Endurance_buf++;
                        temp = false;
                    }
                    break;
                case 2:
                    if(Social < STAT_CAP) {
                        Social++;
                        Social_buf++;
                        temp = false;
                    }
                    break;
                case 1:
                    if(Math < STAT_CAP) {
                        Math++;
                        Math_buf++;
                        temp = false;
                    }
                    break;
                case 0:
                    if(Focus < STAT_CAP) {
                        Focus++;
                        Focus_buf++;
                        temp = false;
                    }
                    break;
            }
        }
        mult_level();
    }

}
