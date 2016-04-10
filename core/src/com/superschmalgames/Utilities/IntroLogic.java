package com.superschmalgames.Utilities;

//This class handles the logic during the game introduction, including name input, avatar selection, etc.

import com.badlogic.gdx.graphics.Color;

public class IntroLogic {

    public int sentence, nameIndex;

    public IntroLogic(){
        sentence = 1;
        nameIndex = 0;
        Utils.menuIcon.setColor(Color.BLUE);
        Utils.menuIcon.setScale(1.5f);
        MainClass.hero.name = "";
    }

    public void nextSentence(){
        switch (sentence){
            case 1: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText2, Color.BLUE, 480, 8, true);
                    MainClass.inputHandler.menuIndex = 26;
                    sentence++;
                    break;
            case 2: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText3, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 3: if(!MainClass.avatarScreen.setName){
                        Utils.menuIcon.setPosition(275,278);
                        Utils.menuIcon.setScale(2f);
                        Utils.menuIcon.setColor(Color.WHITE);
                        Utils.menuBorder.setPosition(290,65);
                        Utils.menuBorder.setScale(1.50f,0.4f);
                        MainClass.avatarScreen.setName = true;
                        break;
                    }
                    else{
                        setCharacterName();
                        MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText4, Color.BLUE, 480, 8, true);
                        Utils.menuIcon.setColor(Color.BLUE);
                        Utils.menuIcon.setScale(1.5f);
                        MainClass.avatarScreen.setName = false;
                        sentence++;
                        break;
                    }
            case 4: if(!MainClass.avatarScreen.setAvatar){
                        Utils.menuIcon.setPosition(260,260);
                        Utils.menuIcon.setScale(3f);
                        Utils.menuIcon.setColor(Color.WHITE);
                        nameIndex = 1;
                        MainClass.avatarScreen.setAvatar = true;
                        break;
                    }
                    else {
                        MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText5, Color.BLUE, 480, 8, true);
                        Utils.menuIcon.setColor(Color.BLUE);
                        Utils.menuIcon.setScale(1.5f);
                        MainClass.avatarScreen.setAvatar = false;
                        sentence++;
                        break;
                    }
            case 5: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText6, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 6: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText7, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 7: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText8, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 8: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText9, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 9: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText10, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 10: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText11, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 11: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText12, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            case 12: MainClass.avatarScreen.avatarLayout.setText(Utils.font_small, Utils.introText13, Color.BLUE, 480, 8, true);
                    sentence++;
                    break;
            default: break;
        }
    }

    public void concatName(int index){
        if(nameIndex < 52 && MainClass.hero.name.length() < 8){
            MainClass.hero.name += Utils.nameSelect.charAt(index);
        }
    }

    public void setCharacterName(){
        Utils.introText4 = "Oh, so your name is " + MainClass.hero.name + "? Such a nice name. Say, my eyes aren't quite " +
                "what they used to be. Step over here so I can see what you look like.";
        Utils.introText13 = "Get ready, " + MainClass.hero.name + "! Your GatorQuest adventure is about to begin!";
    }

    public void setCharacterAvatar(){
        switch (nameIndex){
            case 1: MainClass.hero.outfitNum = 7;
                break;
            case 2: MainClass.hero.outfitNum = 11;
                break;
            case 3: MainClass.hero.outfitNum = 10;
                break;
            case 4: MainClass.hero.outfitNum = 9;
                break;
            default: break;
        }
        MainClass.hero.initAnimations();
    }

    public void removeLetter(){
        if(!MainClass.hero.name.isEmpty()) {
            MainClass.hero.name = MainClass.hero.name.substring(0, MainClass.hero.name.length() - 1);
        }
    }
}
