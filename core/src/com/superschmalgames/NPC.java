package com.superschmalgames;

import com.superschmalgames.Utilities.Animator;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class NPC {

    public Animator walk;
    char direction;
    //used for movement
    public int x_pos,y_pos;
    //used to reset position
    int org_x, org_y;
    boolean triggered;
    String script;

    //NPCs will only move in one direction if any
    public NPC(char dir, String s, String sprite, int x, int y) {
        x_pos = x;
        org_x = x;
        y_pos = y;
        org_y = y;
        script = s;
        direction = dir;
        walk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_" + sprite + ".png", 0.17f);
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        triggered = false;

    }

    //move to players location.
    public void move(float delta, float x, float y) {
        boolean temp = false;
        switch(direction) {
            case 'l':
                if (x_pos > x + MainClass.hero.width/2) {
                    x_pos -= Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateCombat();
                break;
            case 'r':
                if (x_pos < x - 3*MainClass.hero.width/2) {
                    x_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateCombat();
                break;
            case 'u':
                if (y_pos < y - 3*MainClass.hero.height/2) {
                    y_pos += Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateCombat();
                break;
            case 'd':
                if (y_pos > y + MainClass.hero.height/2) {
                    y_pos -= Utils.MOVE_DIST;
                    temp = true;
                }
                else
                    initiateCombat();
                break;
        }
        if(temp) {
            walk.stateTime += delta;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        }
    }

    public void reset() {
        x_pos = org_x;
        y_pos = org_y;
    }

    public String getScript() {
        return script;
    }

    public boolean getTriggered() {
        return triggered;
    }

    public void setTriggered(boolean set) {
        triggered = set;
    }

    public void initiateCombat() {
        System.out.print(script);
        if (!triggered) {
            reset();
            triggered = true;
            walk.currentFrame = walk.walkAnimation.getKeyFrame(0f, true);
        }
    }
}
