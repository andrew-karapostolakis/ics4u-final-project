/* Brandon Lit
 * 2017-06-07
 * This is a custom click listener class for the inventory*/
package com.surviveandthrive;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InventoryListener extends InputListener{
    
    int x;
    int y;
    
    public InventoryListener(int xPos, int yPos){
        super();
        x = xPos;
        y = yPos;
    }
    
    @Override
            public boolean touchDown (InputEvent event, float xPos, float yPos, int pointer, int button)
            {                     
                System.out.println(x + " : " + y);
                return true;
            }
    
}
