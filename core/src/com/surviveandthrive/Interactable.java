/* Garrett Smith, A Karapostolakis
 * 2017-06-13
 * This class will handle all of the items that can be interacted with in the world */
package com.surviveandthrive;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class Interactable extends RectangleMapObject {
	//attribute declaration
	//private String name;
	private Rectangle mapObject;

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
		mapObject = obj.getRectangle();
		//each resource can be extracted 5 times
		resourcesRemaining = 5;
		properties = obj.getProperties();
	}
	
	/**
	 * Interacts with the Interactable.
	 * 
	 * @return A boolean indicating the success/fail of the interaction
	 */
	public boolean interact() {
		if (resourcesRemaining > 0) {
			resourcesRemaining--;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public MapProperties getProperties() {
		return properties;
	}
	
	@Override
	public Rectangle getRectangle() {
		return mapObject;
	}
	
	/*public float getX() {
		return mapObject.getX();
	}
	
	public float getY() {
		return mapObject.getY();
	}
	
	public float getHeight() {
		return mapObject.getHeight();
	}
	
	public float getWidth() {
		return mapObject.getWidth();
	}*/
	/**
	 * get the image for the interactable
	 *
	 * @return the texture/picture
	 */
	/*public Texture getImage(){
        return tileImg;
    }*/
}
