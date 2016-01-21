package com.superschmalgames;

//This class will represent our main playable character.

public class HeroCharacter {

    public int outfitNum;
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

        if(outfitNum == 1) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d.png");
        }
        else if(outfitNum == 2) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l2.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r2.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u2.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d2.png");
        }
        else if(outfitNum == 3) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l3.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r3.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u3.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d3.png");
        }
        else if(outfitNum == 4) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l4.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r4.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u4.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d4.png");
        }
        else if(outfitNum == 5) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l5.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r5.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u5.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d5.png");
        }
        else if(outfitNum == 6) {
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l6.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r6.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u6.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d6.png");
        }
        else{
            leftWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_l.png");
            rightWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_r.png");
            upWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_u.png");
            downWalk = new Animator(8, 1, "visuals/sprite_sheets/sprite_walk_d.png");
        }

        //Set up the initial frame that will be rendered to the screen before any input is given.
        heroAnim = downWalk;
        heroAnim.currentFrame = downWalk.walkAnimation.getKeyFrame(downWalk.stateTime, true);
    }
}
