package com.superschmalgames;

//Class to represent the character's inventory.

import java.util.ArrayList;
import java.util.List;

public class HeroMoves {

    //Character inventory.
    Move[] attacks;

    public HeroMoves(){
        attacks = new Move[18];
        attacks[0] = new Move("Java Function",Utils.java_function_tex, "A move that benefits\nfrom improved Software \nskill.", true);
        attacks[1] = new Move("Recursive Loop",Utils.recursive_function_tex, "A move that benefits\nfrom improved Software \nskill.", true);
        attacks[2] = new Move("Stack Overflow",Utils.stack_overflow_tex, "A move that benefits\nfrom improved Software \nand Read/Write skills.", true);
        attacks[3] = new Move("Commented Code",Utils.commented_code_tex, "A move that benefits\nfrom improved Software\nand Read/Write skills.", true);
        attacks[4] = new Move("Double Integration",Utils.double_integration_tex, "A move that benefits\nfrom improved Math \nskills.", true);
        attacks[5] = new Move("Set Equal to 0",Utils.set_equal_to_0_tex, "A move that benefits\nfrom improved Math \nskills.", true);
        attacks[6] = new Move("Practice Test",Utils.practice_test_tex, "A move that benefits\nfrom improved Focus \nskill.", true);
        attacks[7] = new Move("Extra Credit",Utils.extra_credit_tex, "A move that does not\nbenefit from your\nstats. Pure luck.", true);
        attacks[8] = new Move("C++ Skills",Utils.cpp_skills_tex, "A move that benefits\nfrom improved Software\nskill.", true);
        attacks[9] = new Move("Nodal Analysis",Utils.nodal_analysis_tex, "A move that benefits\nfrom improved Hardware\nskill.", true);
        attacks[10] = new Move("F2 Solve",Utils.f2_solve_tex, "A move that benefits\nfrom improved Math\nand Focus skills.", true);
        attacks[11] = new Move("5 Lines Matlab Code",Utils.f_lines_matlab_code_tex, "A move that benefits\nfrom improved Software\nand Math skills.", true);
        attacks[12] = new Move("Karnaugh Map",Utils.karnaugh_map_tex, "A move that benefits\nfrom improved\nEndurance and\nHardware skills.", true);
        attacks[13] = new Move("Soldering Skills",Utils.soldering_skills_tex, "A move that benefits\nfrom improved\nEndurance and\nFocus skills.", true);
        attacks[14] = new Move("Boolean Logic",Utils.boolean_logic_tex, "A move that benefits\nfrom improved Math,\nFocus and Hardware\nskills.", true);
        attacks[15] = new Move("Documentation",Utils.documentation_tex, "A move that benefits\nfrom improved\nRead/Write skills.", true);
        attacks[16] = new Move("Code Testing",Utils.code_testing_tex, "A move that benefits\nfrom improved Software\nand Focus skills.", true);
        attacks[17] = new Move("Perf. Presentation",Utils.perfect_presentation_tex, "A move that benefits\nfrom improved Social\nskill.", true);
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

        while(relIndex < (MainClass.heroScreen.heroRow + MainClass.heroScreen.heroPage*8))
        {
            if(MainClass.hero.moves.attacks[overallIndex].obtained)
            {
                relIndex+=1;
            }

            overallIndex+=1;
        }

        return overallIndex;
    }

}
