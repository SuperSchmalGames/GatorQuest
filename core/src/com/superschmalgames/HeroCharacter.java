package com.superschmalgames;

//This class will represent our main playable character.

public class HeroCharacter {

    public String name;     //Our hero character's name. Can be set at start of game, and used in dialogue with NPC's.
    public float gpa;       //Character's Grade Point Average. Acts as a "health meter." If reduced to zero, character loses fight.

    public float width;     //Width of character object. Used to handle collision detection and how player interacts with environment
    public float height;    //Height of character object.
    public float xPos;      //Cartesian x-coordinate of character. Used for player movement tracking.
    public float yPos;      //Cartesian y-coordinate of character.

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
        gpa = 4.0f;
        software = 1.0f;
        hardware = 1.0f;
        readWrite = 1.0f;
        endurance = 1.0f;
        social = 1.0f;
        math = 1.0f;
        detail = 1.0f;

        initAnimations();
    }

    public void initAnimations(){
        //Initialize all of the different animations.
        leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l.png");
        rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r.png");
        upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u.png");
        downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d.png");

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
}
