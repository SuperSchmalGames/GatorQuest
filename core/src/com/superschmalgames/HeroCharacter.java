package com.superschmalgames;

//This class will represent our main playable character.

public class HeroCharacter {

    public String name;     //Our hero character's name. Can be set at start of game, and used in dialogue with NPC's.
    public int level;       //Character's level. Goes up as enemies are defeated. (MAY NOT END UP USING)
    public float gpa;       //Character's Grade Point Average. Acts as a "health meter." If reduced to zero, character loses fight.

    public float width;     //Width of character object. Used to handle collision detection and how player interacts with environment
    public float height;    //Height of character object.
    public float xPos;      //Cartesian x-coordinate of character. Used for player movement tracking.
    public float yPos;      //Cartesian y-coordinate of character.

    //Animators that take care of walking in each direction.
    Animator leftWalk;
    Animator rightWalk;
    Animator upWalk;
    Animator downWalk;

    //Main animator that handles switching between all different direction the character can walk.
    Animator heroAnim;

    public HeroCharacter(){
        level = 1;
        gpa = 4;

        setAnimations();
    }

    public void setAnimations(){
        //Initialize all of the different animations.

        leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l.png");
        rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r.png");
        upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u.png");
        downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d.png");

        //Set up the initial frame that will be rendered to the screen before any input is given.
        heroAnim = downWalk;
        heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(downWalk.stateTime, true);
    }
}
