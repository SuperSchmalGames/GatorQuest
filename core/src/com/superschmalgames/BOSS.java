package com.superschmalgames;


import com.superschmalgames.Utilities.Animator;

public class BOSS extends NPC{
    public BOSS(char dir, String s, String sprite, int x, int y) {
        super(dir,s,"l1",x,y);
        walk = new Animator(1, 1, "visuals/Professors/" + sprite + "/" + sprite + "_" + dir +".png", 0.17f);
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
    }
}
