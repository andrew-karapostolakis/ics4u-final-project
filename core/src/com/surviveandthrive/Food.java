/* Brandon Lit
 * 2017-05-27
 * This is the food class, it extends the Item class*/
package com.surviveandthrive;

/**
 *
 * @author Brandon
 */
public class Food extends Item {

    private int foodValue;
    
    public Food(String n, int a, boolean isE, int f) {
        super(n, a, isE);
        foodValue = f;
    }

    public int getFoodValue(){
        return foodValue;
    }

}
