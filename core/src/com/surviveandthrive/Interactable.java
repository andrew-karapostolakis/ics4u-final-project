/* Garrett Smith, A Karapostolakis
 * 2017-06-09
 * This class will handle all of the items that can be interacted with in the world */
package com.surviveandthrive;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class Interactable extends RectangleMapObject {
	//attribute declaration
	//private String name;
	//private Rectangle mapObject;

	private MapProperties properties;
	private int resourcesRemaining;

	/**
	 * Constructor for interactable items.
	 *
	 * @param obj The RectangleMapObject from which to construct the
	 * Interactable
	 */
	public Interactable(RectangleMapObject obj) {
		//name = nName;
		super();
		//each resource can be extracted 5 times
		resourcesRemaining = 5;
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
