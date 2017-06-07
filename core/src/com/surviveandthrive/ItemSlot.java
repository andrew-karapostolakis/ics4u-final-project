/* Brandon Lit
 * 2017-06-06
 * This is a class to manage the inventory*/
package com.surviveandthrive;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ItemSlot extends TextButton{
    
    Item storedItem;
    int x, y;

    public ItemSlot(String s, TextButtonStyle st, int xPos, int yPos){
        super(s, st);
        x = xPos;
        y = yPos;
        
    }
    
    public Skin getItemImage(){
        return storedItem.getImage();
    }
    
    public String getItemName(){
        return storedItem.getName();
    }
    
    public int getXButton(){
        return x;
    }
    
    public int getYButton(){
        return y;
    }
    
    
}
