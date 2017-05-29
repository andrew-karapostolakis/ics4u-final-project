/*
 * Garrett Smith
May 29 2017
This class will handle all of the items that can be interacted with in the world
 */
package com.surviveandthrive;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author grumm
 */
public class Interactables {
    //attribute declaration
    private String name;
    private Texture tileImg;
    /**
     * constructor for interactable items
     * @param nName name of the item
     */
    public Interactables(String nName){
        name = nName;
    }
}
