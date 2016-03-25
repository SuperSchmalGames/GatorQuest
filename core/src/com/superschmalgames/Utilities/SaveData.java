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
    int[] quantities;

    //enemies - only triggered flag (randoms excluded)
    boolean[] neb;
    boolean[] cise;
    boolean[] turlington;
    boolean[] dorm;
    boolean[] marston;
    boolean[] bookstore;
}
