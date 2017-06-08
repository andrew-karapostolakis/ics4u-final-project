/* Garrett Smith, A Karapostolakis
 * May 29 2017
 * This class will handle all of the items that can be interacted with in the world */
package com.surviveandthrive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class Interactable extends RectangleMapObject {
	//attribute declaration
	//private String name;
	//private Rectangle mapObject;

	private MapProperties properties;

	/**
	 * Constructor for interactable items.
	 *
	 * @param obj The RectangleMapObject from which to construct the
	 * Interactable
	 */
	public Interactable(RectangleMapObject obj) {
		//name = nName;
		super();
		properties = obj.getProperties();
	}
	/**
	 * get the image for the interactable
	 *
	 * @return the texture/picture
	 */
	/*public Texture getImage(){
        return tileImg;
    }*/
}
