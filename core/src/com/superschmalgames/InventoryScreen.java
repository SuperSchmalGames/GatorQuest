package com.superschmalgames;

//This class is for the inventory screen you see when you hit "I" from the game screen.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class InventoryScreen implements Screen {

    //The camera through which we "see" the game world.
    OrthographicCamera camera;

    GlyphLayout layout1 = new GlyphLayout();
    GlyphLayout layout2 = new GlyphLayout();
    String GatorQuest = "Inventory";
    String PressSpace = "Press I to return to game";
    String invPanel;
    int invPage;
    int invRow;

    public InventoryScreen() {

        //Initialize the camera. Set the camera dimensions equal to our game screen height and width.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Utils.GAME_SCREEN_WIDTH, Utils.GAME_SCREEN_HEIGHT);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the camera once per refresh.
        camera.update();
        MainClass.batch.setProjectionMatrix(camera.combined);

        //Draw to our batch each refresh. The batch is then rendered to the screen.
        MainClass.batch.begin();

        MainClass.inputHandler.currentItemIndex=MainClass.hero.inventory.getCurrentItemIndex();

        //Utils.font = new BitmapFont(Gdx.files.internal("RosesAreFF0.fnt"));
        layout1.setText(Utils.font, GatorQuest);
        layout2.setText(Utils.font, PressSpace);
        Utils.font.draw(MainClass.batch,
                GatorQuest,
                Gdx.graphics.getWidth()/2 - layout1.width/2,
                Gdx.graphics.getHeight()/2 + layout1.height/2+50
        );
        Utils.font.draw(MainClass.batch,
                PressSpace,
                Gdx.graphics.getWidth()/2 - layout2.width/2,
                Gdx.graphics.getHeight()/2 + layout2.height/2-50
        );

        if(invPanel.equals("Consumable")) {
            MainClass.batch.draw(Utils.inv_consumable_tex, 0, 0);
            int temp = 0;
            if(invPage == 0) {
                for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null &&
                            MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                            MainClass.hero.inventory.items.get(i).getItemType() == 'c') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                        50, 515 - 65 * temp);
                        temp += 1;
                    }
                }
            }
            else if(invPage > 0)
            {
                int indexRelPopEquip = 0;
                int indexRelAllItems = 0;
                while(indexRelPopEquip < invPage*8) {
                    if (MainClass.hero.inventory.items.get(indexRelAllItems).getQuantity() > 0 && MainClass.hero.inventory.items.get(indexRelAllItems).getItemType() == 'c')
                    {
                        indexRelPopEquip += 1;
                    }
                    indexRelAllItems+=1;
                }

                for(int i=indexRelAllItems; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null
                            && MainClass.hero.inventory.items.get(i).getQuantity() > 0
                            && MainClass.hero.inventory.items.get(i).getItemType() == 'c') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(), 50, 515 - 65 * invRow);
                    }
                }
            }

            int populatedConsumables = 0;
            int invIndex = 0;
            if(invRow == 0 && invPage == 0 && MainClass.hero.inventory.getNumC() > 0)
            {
                MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);

                while (MainClass.hero.inventory.items.get(invIndex).getItemType() != 'c' ||
                        MainClass.hero.inventory.items.get(invIndex).getQuantity() == 0) {
                    invIndex += 1;
                }
                MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                        "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                        MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
            }
            else if(invRow == 0 && invPage == 0)
            {
            }
            else {
                while (populatedConsumables < (invRow + 8 * invPage)) {
                    if (MainClass.hero.inventory.items.get(invIndex).getQuantity() > 0 && MainClass.hero.inventory.items.get(invIndex).getItemType() == 'c')
                        populatedConsumables += 1;
                    invIndex += 1;
                }
                if (MainClass.hero.inventory.getNumC() > 0) {
                    MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);
                    MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                    Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                            "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                            MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
                }
            }
        }
        else if("Equipment".equals(invPanel)) {
            MainClass.batch.draw(Utils.inv_equip_tex, 0, 0);
            int temp = 0;
            if(invPage == 0) {
                for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null &&
                            MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                            MainClass.hero.inventory.items.get(i).getItemType() == 'e') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                50, 515 - 65 * temp);
                        temp += 1;
                    }
                }
            }
            else if(invPage > 0)
            {
                int indexRelPopEquip = 0;
                int indexRelAllItems = 0;
                while(indexRelPopEquip < invPage*8) {
                        if (MainClass.hero.inventory.items.get(indexRelAllItems).getQuantity() > 0 && MainClass.hero.inventory.items.get(indexRelAllItems).getItemType() == 'e')
                        {
                            indexRelPopEquip += 1;
                        }
                        indexRelAllItems+=1;
                }

                for(int i=indexRelAllItems; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null
                            && MainClass.hero.inventory.items.get(i).getQuantity() > 0
                            && MainClass.hero.inventory.items.get(i).getItemType() == 'e') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                               50, 515 - 65 * invRow);
                    }
                }
            }

            int populatedEquipments = 0;
            int invIndex = 0;
            if(invRow == 0 && invPage == 0 && MainClass.hero.inventory.getNumE() > 0)
            {
                MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);

                while (MainClass.hero.inventory.items.get(invIndex).getItemType() != 'e' ||
                        MainClass.hero.inventory.items.get(invIndex).getQuantity() == 0) {
                    invIndex += 1;
                }
                MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                        "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                        MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
            }
            else if(invRow == 0 && invPage == 0)
            {
            }
            else {
                while (populatedEquipments < (invRow + 8 * invPage)) {
                    if (MainClass.hero.inventory.items.get(invIndex).getQuantity() > 0 && MainClass.hero.inventory.items.get(invIndex).getItemType() == 'e')
                        populatedEquipments += 1;
                    invIndex += 1;
                }
                if (MainClass.hero.inventory.getNumE() > 0) {
                    MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);
                    MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                    Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                            "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                            MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
                }
            }
        }
        else if("Apparel".equals(invPanel)) {
            MainClass.batch.draw(Utils.inv_apparel_tex, 0, 0);
            int temp = 0;
            if(invPage == 0) {
                for (int i = 0; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null &&
                            MainClass.hero.inventory.items.get(i).getQuantity() > 0 &&
                            MainClass.hero.inventory.items.get(i).getItemType() == 'a') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                50, 515 - 65 * temp);
                        temp += 1;
                    }
                }
            }
            else if(invPage > 0)
            {
                int indexRelPopEquip = 0;
                int indexRelAllItems = 0;
                while(indexRelPopEquip < invPage*8) {
                    if (MainClass.hero.inventory.items.get(indexRelAllItems).getQuantity() > 0 && MainClass.hero.inventory.items.get(indexRelAllItems).getItemType() == 'a')
                    {
                        indexRelPopEquip += 1;
                    }
                    indexRelAllItems+=1;
                }

                for(int i=indexRelAllItems; i < MainClass.hero.inventory.items.size(); i++) {
                    if (MainClass.hero.inventory.items.get(i) != null
                            && MainClass.hero.inventory.items.get(i).getQuantity() > 0
                            && MainClass.hero.inventory.items.get(i).getItemType() == 'a') {
                        Utils.font.draw(MainClass.batch, (MainClass.hero.inventory.items.get(i)).getItemName()
                                        + " x" + MainClass.hero.inventory.items.get(i).getQuantity(),
                                50, 515 - 65 * invRow);
                    }
                }
            }

            int populatedApparels = 0;
            int invIndex = 0;
            if(invRow == 0 && invPage == 0 && MainClass.hero.inventory.getNumA() > 0)
            {
                MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);

                while (MainClass.hero.inventory.items.get(invIndex).getItemType() != 'a' ||
                        MainClass.hero.inventory.items.get(invIndex).getQuantity() == 0) {
                    invIndex += 1;
                }
                MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                        "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                        MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
            }
            else if(invRow == 0 && invPage == 0)
            {
            }
            else {
                while (populatedApparels < (invRow + 8 * invPage)) {
                    if (MainClass.hero.inventory.items.get(invIndex).getQuantity() > 0 && MainClass.hero.inventory.items.get(invIndex).getItemType() == 'a')
                        populatedApparels += 1;
                    invIndex += 1;
                }
                if (MainClass.hero.inventory.getNumA() > 0) {
                    MainClass.batch.draw(Utils.white_sq_tex, 505, 435, 94, 94);
                    MainClass.batch.draw(MainClass.hero.inventory.items.get(invIndex).getTexture(), 520, 450, 64, 64);
                    Utils.font_small.draw(MainClass.batch, MainClass.hero.inventory.items.get(invIndex).getItemName() +
                            "\nBoosts "+ MainClass.hero.inventory.items.get(invIndex).getStatBoosted()+" by "+
                            MainClass.hero.inventory.items.get(invIndex).getBoostAmt(), 610,520);
                }
            }
        }

        MainClass.batch.draw(Utils.sel_item_tex, 35,480-65*invRow);

        if (invPanel.equals("Consumable") && MainClass.hero.inventory.getNumC() == 0)
            Utils.font.draw(MainClass.batch, "No items of this type!", 50,515);
        else if (invPanel.equals("Consumable") && MainClass.hero.inventory.getNumC() == 0)
            Utils.font.draw(MainClass.batch, "No items of this type!", 50,515);
        else if (invPanel.equals("Consumable") && MainClass.hero.inventory.getNumC() == 0)
            Utils.font.draw(MainClass.batch, "No items of this type!", 50,515);

        if(!"".equals(MainClass.hero.heroApparel.getItemName()))
        {
            MainClass.batch.draw(Utils.white_sq_tex, 505, 55, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroApparel.getTexture(), 520, 70, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroApparel.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroApparel.getStatBoosted()+" by "+
                    MainClass.hero.heroApparel.getBoostAmt(), 610,140);
        }

        if(!"".equals(MainClass.hero.heroEquipment.getItemName()))
        {
            MainClass.batch.draw(Utils.white_sq_tex, 505, 240, 94, 94);
            MainClass.batch.draw(MainClass.hero.heroEquipment.getTexture(), 520, 255, 64, 64);
            Utils.font_small.draw(MainClass.batch, MainClass.hero.heroEquipment.getItemName() +
                    "\nBoosts "+ MainClass.hero.heroEquipment.getStatBoosted()+" by "+
                    MainClass.hero.heroEquipment.getBoostAmt(), 610, 325);
        }

        MainClass.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
