package com.superschmalgames;

public class NPC {

    Animator walk;
    char direction;
    //used for movement
    int x_pos,y_pos;
    //used to reset position
    int org_x, org_y;
    boolean triggered;
    String script;

    //NPCs will only move in one direction if any
    public NPC(char dir,String s, int x, int y) {
        x_pos = x;
        org_x = x;
        y_pos = y;
        org_y = y;
        script = s;
        direction = dir;
        walk = new Animator(4, 1, "visuals/sprite_sheets/sprite_walk_" + direction +"9.png", 0.17f);
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        triggered = false;

    }

    //move to players location.
    public void move(float delta) {
        walk.stateTime += delta;
        walk.currentFrame = walk.walkAnimation.getKeyFrame(walk.stateTime, true);
        switch(direction) {
            case 'l':
                x_pos -= Utils.MOVE_DIST;
                break;
            case 'r':
                x_pos += Utils.MOVE_DIST;
                break;
            case 'u':
                y_pos += Utils.MOVE_DIST;
                break;
            case 'd':
                y_pos -= Utils.MOVE_DIST;
                break;
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
}
