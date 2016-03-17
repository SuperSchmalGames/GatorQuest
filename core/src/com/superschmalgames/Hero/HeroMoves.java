package com.superschmalgames.Hero;

//Class to represent the character's inventory.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.superschmalgames.Utilities.MainClass;
import com.superschmalgames.Utilities.Utils;

public class HeroMoves {

    //Character inventory.
    public Move[] attacks;

    public HeroMoves(){
        attacks = new Move[18];
        attacks[0] = Utils.Java_Function;
        attacks[1] = Utils.Recursive_Loop;
        attacks[2] = Utils.Stack_Overflow;
        attacks[3] = Utils.Commented_Code;
        attacks[4] = Utils.Double_Integration;
        attacks[5] = Utils.Set_Equal_to_0;
        attacks[6] = Utils.Practice_Test;
        attacks[7] = Utils.Extra_Credit;
        attacks[8] = Utils.CPP_Skills;
        attacks[9] = Utils.Nodal_Analysis;
        attacks[10] = Utils.F2_Solve;
        attacks[11] = Utils._5_Lines_Matlab_Code;
        attacks[12] = Utils.Karnaugh_Map;
        attacks[13] = Utils.Soldering_Skills;
        attacks[14] = Utils.Boolean_Logic;
        attacks[15] = Utils.Documentation;
        attacks[16] = Utils.Code_Testing;
        attacks[17] = Utils.Perf_Presentation;
    }

    public int calc_dmg(){
        return 1;
    }

    public void generateMoves(Move[] moves)
    {

    }

    public int getCurrentMove()
    {
        int overallIndex = 0;
        int relIndex = 0;

        //If we're in the combat screen, use movePane and index to find current move.
        if (((Game) Gdx.app.getApplicationListener()).getScreen() == MainClass.combatScreen){
            while (relIndex < MainClass.combatInputHandler.index){
                if (MainClass.hero.moves.attacks[overallIndex].obtained) {
                    relIndex += 1;
                }

                overallIndex += 1;
            }
        }
        //If not in combat, use heroRow and heroPage to find current move.
        else {
            while (relIndex < (MainClass.heroScreen.heroRow + MainClass.heroScreen.heroPage * 8)) {
                if (MainClass.hero.moves.attacks[overallIndex].obtained) {
                    relIndex += 1;
                }

                overallIndex += 1;
            }
        }

        return overallIndex;
    }

    //Return the total number of moves the Hero has in his arsenal.
    public int getNum(){
        int temp = 0;
        for(int i = 0; i < attacks.length; i++){
            if(attacks[i].obtained){
                temp++;
            }
        }
        return temp;
    }

}
