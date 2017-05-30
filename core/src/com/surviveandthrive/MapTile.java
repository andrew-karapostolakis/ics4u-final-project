/*
Garrett Smith
May 29 2017
Map tile class for the game. Will store all info for a given coordinate
 */
package com.surviveandthrive;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author grumm
 */
public class MapTile {
    //attribute declaration
    private String biome;
    private Texture tileImg;
    /**
     * constructor for MapTile class
     * @param nBiome takes the name of the biome
     */
    public MapTile(String nBiome){
        biome = nBiome;
        selectImage();
    }
    /**
     * returns the image of for the biome that you are in
     * @return 
     */
    public Texture getImage(){
        return tileImg;
    }
    /**
     * draw the tile on the screen
     */
    public void draw(){
        
    }
    /**
     * pick what image to use for the tile, based on the biome type
     */
    private void selectImage(){
        //check to see which biome the tile is, and set the picture accordingly
        if(biome.equals("plane")){
            
        }else if(biome.equals("forest")){
            
        }else if(biome.equals("hill")){
            
        }else if(biome.equals("mountain")){
            
        }else if(biome.equals("lake")){
            
        }else{
            
        }
    }
}
