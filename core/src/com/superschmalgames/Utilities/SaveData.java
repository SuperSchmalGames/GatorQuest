package com.superschmalgames.Utilities;

/**
 * Created by Cory on 3/25/2016.
 */
public class SaveData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    //hero info
    public int outfitNum;
    public String name;
    public double GPA;
    public int semester;
    public int gatorBucks;
    public int lvl;
    public int experience;
    public int expCap;
    public double Software;
    public double Hardware;
    public double Writing;
    public double Endurance;
    public double Social;
    public double Math;
    public double Focus;
    public double Software_buf;
    public double Hardware_buf;
    public double Writing_buf;
    public double Endurance_buf;
    public double Social_buf;
    public double Math_buf;
    public double Focus_buf;

    //hero inventory - only quantities needed
    public int[] quantities = new int[18];

    //enemies - only triggered flag (randoms excluded)
    public boolean[] neb = new boolean[69];
    public boolean[] cise = new boolean[41];
    public boolean[] turlington = new boolean[67];
    public boolean[] dorm = new boolean[11];
    public boolean[] marston = new boolean[14];
    public boolean[] bookstore = new boolean[14];
}
