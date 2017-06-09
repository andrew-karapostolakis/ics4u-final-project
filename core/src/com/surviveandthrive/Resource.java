/* Brandon Lit
 * 2017-05-27
 * This is the resource class, it extends the Item class*/
package com.surviveandthrive;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author Brandon
 */
public class Resource extends Item {
    //the recipe for the resource
    String recipe;
    
    /**
     * The constructor for the resource
     * @param n the name of the resource
     * @param a the amount of resources
     */
    public Resource(String n, int a) {
        super(n, a, false);
    }

    /**
     * Returns if the resource is in a recipe
     * @return the boolean in recipe
     */
    public boolean inRecipe(){
        //place holder value
        return true;
    }

    /**
     * returns the recipe for the resource
     * @return String recipe
     */
    public String getRecipe(){
        return recipe;
    }
    
}
