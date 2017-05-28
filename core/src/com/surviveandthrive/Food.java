/* Brandon Lit
 * 2017-05-27
 * This is the food class, it extends the Item class*/
package com.surviveandthrive;

/**
 *
 * @author Brandon
 */
public class Food extends Item {
    //food value represents the amount of hunger the bar will replace
    private int foodValue;
    
    /**
     * The constructor for food
     * @param n the name of the food
     * @param a the amount of food
     * @param isE is the food edible
     * @param f the food value of the food
     */
    public Food(String n, int a, boolean isE, int f) {
        super(n, a, isE);
        foodValue = f;
    }

    /**
     * returns the food value
     * @return integer food value
     */
    public int getFoodValue(){
        return foodValue;
    }

}
