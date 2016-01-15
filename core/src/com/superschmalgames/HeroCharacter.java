package com.superschmalgames;

//This class will represent our main playable character.

public class HeroCharacter {

    public String name;
    public int level;
    public float width;
    public float height;
    public float xPos;
    public float yPos;
    public float gpa;

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
