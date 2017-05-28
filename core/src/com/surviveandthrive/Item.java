/* Brandon Lit
 * 2017-05-27
 * This is the Item class*/
package com.surviveandthrive;

/**
 *
 * @author Brandon
 */
public class Item {

    private String name;
    private int amount;
    protected boolean isEdible;

    public Item(String n, int a, boolean isE) {
        name = n;
        amount = a;
        isEdible = isE;
    }

    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int newAmount){
        amount = newAmount;
    }

    public void addItem(int a){
        amount += a;
    }

    public void removeItem(int a){
        amount -= a;
    }

}
