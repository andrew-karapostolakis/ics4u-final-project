/* Brandon Lit
 * 2017-05-27
 * This is the resource class, it extends the Item class*/
package com.surviveandthrive;

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
     * @param r the recipe for the resource
     */
    public Resource(String n, int a, String r) {
        super(n, a, false);
        recipe = r;
    }

    /**
     * Returns if the resource is in a recipe
     * @return 
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
