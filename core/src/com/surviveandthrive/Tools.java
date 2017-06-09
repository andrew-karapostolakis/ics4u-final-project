/* Brandon Lit
 * 2017-05-27
 * This is the tool class, it extends the Item class*/
package com.surviveandthrive;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author Brandon
 */
public class Tools extends Item{
    
    private String recipe;
    
    /**
     * The constructor for a tool
     * @param n the name of the tool
     * @param a the amount of the tools 
     */
    public Tools(String n, int a, String r){
        super(n, a, false);
        recipe = r;
    }
    
}
