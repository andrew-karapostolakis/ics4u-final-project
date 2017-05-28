/* Brandon Lit
 * 2017-05-27
 * This is the abstract Item class*/
package com.surviveandthrive;

/**
 *
 * @author Brandon
 */
public abstract class Item {

    private String name;
    private int amount;
    protected boolean isEdible;

    /**
     * The constructor for all items
     * @param n the name of the item
     * @param a the amount of that item
     * @param isE is Edible or not
     */
    public Item(String n, int a, boolean isE) {
        name = n;
        amount = a;
        isEdible = isE;
    }

    /**
     * will get the name of the item
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * will return the amount of items
     * @return int amount
     */
    public int getAmount() {
        return amount;
    }
    /**
     * sets the amount of items to a new number
     * @param newAmount the new integer to replace the old amount
     */
    public void setAmount(int newAmount) {
        amount = newAmount;
    }
    /**
     * adds X number of items to the current total
     * @param x the amount of items to be added
     */
    public void addItem(int x) {
        amount += x;
    }
    /**
     * subtracts X number of items from the current total
     * @param x the amount of items to be subtracted
     */
    public void removeItem(int x) {
        amount -= x;
    }

}
