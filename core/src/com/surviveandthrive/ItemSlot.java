/* Brandon Lit
 * 2017-06-06
 * This is a class to manage the inventory*/
package com.surviveandthrive;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ItemSlot extends ImageTextButton {

    private Item storedItem = null;
    private int x, y;

    public ItemSlot(String s, Skin sk, int xPos, int yPos) {
        super(s, sk);
        x = xPos;
        y = yPos;

    }

    public Skin getItemImage() {
        return storedItem.getImage();
    }

    public String getItemName() {
        return storedItem.getName();
    }

    public int getXButton() {
        return x;
    }

    public int getYButton() {
        return y;
    }

    public void setStored(Item i) {
        storedItem = i;
    }

    public Item getStoredItem() {
        return storedItem;
    }
}
