package com.superschmalgames;

//This class will represent our main playable character.

import java.util.ArrayList;

public class HeroCharacter {

    public int outfitNum;
    public String name;     //Our hero character's name. Can be set at start of game, and used in dialogue with NPC's.
    public double gpa;       //Character's Grade Point Average. Acts as a "health meter." If reduced to zero, character loses fight.

    public float width;
    public float height;
    public float xPos;
    public float yPos;

    //Character inventory.
    ArrayList<InventoryItem> inventory;

    //Character stats.
    public float software;
    public float hardware;
    public float readWrite;
    public float endurance;
    public float social;
    public float math;
    public float detail;
    private final float STAT_CAP = 10.0f;   //Cap all stats at 10.0

    //Animators that take care of walking in each direction.
    Animator leftWalk, rightWalk, upWalk, downWalk;
    char lastDir;

    //Main animator that handles switching between all different direction the character can walk.
    Animator heroAnim;

    public HeroCharacter(){
        gpa = 4.0;
        software = 1.0f;
        hardware = 1.0f;
        readWrite = 1.0f;
        endurance = 1.0f;
        social = 1.0f;
        math = 1.0f;
        detail = 1.0f;

        initInventory();
    }

    public void initAnimations(){

        //Initialize the skin that will be displayed on the character.
        leftWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_l"+outfitNum+".png", 0.17f);
        rightWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_r"+outfitNum+".png", 0.17f);
        upWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_u"+outfitNum+".png", 0.17f);
        downWalk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_d"+outfitNum+".png", 0.17f);

        //Set up the initial frame that will be rendered to the screen before any input is given.
        heroAnim = downWalk;
        heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(downWalk.stateTime, true);
    }

    public void initInventory(){
        inventory = new ArrayList<InventoryItem>();
        InventoryItem tmp;
        tmp = new InventoryItem("Red Bull","visuals/sprites/hero.png","GPA",false,1.2f,3,0);
        tmp.addInvItem(this);
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

}
