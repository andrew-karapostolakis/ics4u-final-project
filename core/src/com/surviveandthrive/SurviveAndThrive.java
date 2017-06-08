package com.surviveandthrive;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class SurviveAndThrive extends Game implements InputProcessor, ApplicationListener{
    int[] backgroundLayers = {0};
    int[] foregroundLayers = {1};
    SpriteBatch batch;
    Sprite sprite;
    Player testPlayer;
    Texture img;
    TiledMap map;
    OrthographicCamera cam;
    TiledMapRenderer mapRenderer;
    TiledMapTileLayer trees;
    int playerX, playerY, tileWidth, tileHeight;
    TiledMapTileLayer layer;
    Cell cell, otherCell;
    TiledMapTile tile, otherTile;
    @Override
    public void create() {
        batch = new SpriteBatch();
	testPlayer = new Player("Jeff");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //set up the map and camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false,w,h);
        map = new TmxMapLoader().load("takethree.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 4/1f);
        Gdx.input.setInputProcessor(this);
        testPlayer.setSize(48,88);
        testPlayer.setPosition(5276,5256);
        cam.translate(5000,5000);
        layer = (TiledMapTileLayer)map.getLayers().get("Tile Layer 1");
        tileWidth = (int)layer.getTileWidth();
        tileHeight = (int)layer.getTileHeight();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        mapRenderer.setView(cam);
        batch.setProjectionMatrix(cam.combined);
        //draw the background
        mapRenderer.render(backgroundLayers);
	batch.begin();
        //draw the player
        testPlayer.draw(batch);
        
	batch.end();
        //draw the foreground
        mapRenderer.render(foregroundLayers);
        //these if statements check to see if the arrow keys are being pressed
        playerX = (int)testPlayer.getX()/tileWidth/4;
        playerY = (int)testPlayer.getY()/tileHeight/4;
        //System.out.println(playerX + ", " + playerY);
        cell = layer.getCell(playerX, playerY);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            otherCell = layer.getCell(playerX - 1, playerY);
            
            if(cell != null){
                otherTile = otherCell.getTile();
                tile = cell.getTile();
                if(!tile.getProperties().containsKey("water") || !otherTile.getProperties().containsKey("water")){
                    cam.translate(-4,0);
                    testPlayer.translateX(-4);
                }
            }
            
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            otherCell = layer.getCell(playerX + 1, playerY);
            if(cell != null){
                otherTile = otherCell.getTile();
                tile = cell.getTile();
                if(!tile.getProperties().containsKey("water") || !otherTile.getProperties().containsKey("water")){
                    cam.translate(4,0);
                    testPlayer.translateX(4);
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            otherCell = layer.getCell(playerX, playerY - 1);
            if(cell != null){
                otherTile = otherCell.getTile();
                tile = cell.getTile();
                if(!tile.getProperties().containsKey("water") || !otherTile.getProperties().containsKey("water")){
                    cam.translate(0,-4);
                    testPlayer.translateY(-4);
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            otherCell = layer.getCell(playerX, playerY + 1);
            if(cell != null){
                otherTile = otherCell.getTile();
                tile = cell.getTile();
                if(!tile.getProperties().containsKey("water") || !otherTile.getProperties().containsKey("water")){
                    cam.translate(0,4);
                    testPlayer.translateY(4);
                }
            }
        }
        
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    @Override
    public boolean keyDown(int keycode) {
        
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*if(keycode == Input.Keys.LEFT)
            cam.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            cam.translate(32,0);
        if(keycode == Input.Keys.UP)
            cam.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            cam.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            map.getLayers().get(0).setVisible(!map.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());*/
        return false;
    }
     @Override
    public boolean keyTyped(char character) {
        
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}

