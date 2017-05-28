/* Brandon Lit
 * 2017-05-27
 * This is the resource class, it extends the Item class*/
package com.surviveandthrive;

/**
 *
 * @author Brandon
 */
public class Resource extends Item {

    String recipe;
    
    public Resource(String n, int a, String r) {
        super(n, a, false);
        recipe = r;
    }

    public boolean inRecipe(){
        //place holder value
        return true;
    }

    public String getRecipe(){
        return recipe;
    }
    
}
