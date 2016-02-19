package com.superschmalgames.Hero;

//Class to represent all of the possible moves of the character

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Move {

    public String moveName;        //Name of the move.
    public Texture texture;        //Texture used to render the move in the game.
    public String description;     //A sentence that describes the move
    public boolean obtained;       //True if the character has obtained the move in the game

    public Move() {
        moveName= "";
        description = "";
        obtained = false;
    }

    public Move(String name, String texPath, String des, boolean isObtained){
        moveName = name;
        texture = new Texture(texPath);
        description = des;
        obtained = isObtained;
    }

    public Move(String name, Texture tex, String des, boolean isObtained){
        moveName = name;
        texture = tex;
        description = des;
        obtained = isObtained;
    }

    public void addMove() {
        this.obtained = true;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        moveName = moveName;
    }

    public void setDescription(String moveDescription) {
        this.description = moveDescription;
    }

    public String getDescription(){ return description; }

    public Texture getTexture(){
        return texture;
    }

    public void setTexture(Texture texture) {this.texture = texture; }

}
