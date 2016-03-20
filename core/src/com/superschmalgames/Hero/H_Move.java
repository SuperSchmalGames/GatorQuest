package com.superschmalgames.Hero;

import com.badlogic.gdx.graphics.Texture;

public class H_Move extends Move {

    private int first_stat, second_stat;
    private int first_1,first_2,first_3,first_4;
    private int second_1,second_2,second_3,second_4;
    private int damage_1,damage_2,damage_3,damage_4,damage_5;

    public H_Move(String name, Texture tex, String des, boolean isObtained, int f, int s, int f1, int f2, int f3, int f4, int s1, int s2, int s3, int s4, int d1, int d2, int d3, int d4, int d5) {
        super(name, tex, des, isObtained);
        first_stat = f;
        second_stat = s;
        first_1 = f1;
        first_2 = f2;
        first_3 = f3;
        first_4 = f4;
        second_1 = s1;
        second_2 = s2;
        second_3 = s3;
        second_4 = s4;
        damage_1 = d1;
        damage_2 = d2;
        damage_3 = d3;
        damage_4 = d4;
        damage_5 = d5;
    }

    //must pass in an array of the stats: order = [software, hardware, writing, endurance, social, math, focus]
    //use({hero.Software_buf,hero.Hardware_buf,hero.Writing_buf,hero.Endurance_buf,hero.Social_buf,hero.Math_buf,hero.Focus_buf});
    public int use(double[] stats) {
        if(stats[first_stat] <= first_1 || stats[second_stat] <= second_1) {
            return damage_1;
        }
        else if(stats[first_stat] <= first_2 || stats[second_stat] <= second_2) {
            return damage_2;
        }
        else if(stats[first_stat] <= first_3 || stats[second_stat] <= second_3) {
            return damage_3;
        }
        else if(stats[first_stat] <= first_4 || stats[second_stat] <= second_4) {
            return damage_4;
        }
        else {
            return damage_5;
        }

    }
}
