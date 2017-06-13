/* A Karapostolakis, B Lit, G Smith
 * 2017-06-13
 * A basic survival RPG */
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SurviveAndThrive implements InputProcessor, Screen {

    int[] backgroundLayers = {0};
    int[] foregroundLayers = {1};
    SpriteBatch batch;
    Sprite sprite;
    Player testPlayer;
    Texture img;
    TiledMap map;
    OrthographicCamera cam;
    TiledMapRenderer mapRenderer;
    MapObjects objects;
    List<Interactable> treeObjects;
    List<Interactable> rockObjects;
    List<Interactable> mapObjects;
    String itemType, itemName, recipe;
    int foodValue;
    Item[][] items;
    int readInIndex = 0;
    TiledMapTileLayer trees;
    //player coordinates, tile size, and player location within the tile
    int playerX, playerY, tileWidth, tileHeight, tilePosX, tilePosY;
    TiledMapTileLayer layer;
    //arrays to hold the tiles surrounding the player
    Cell[][] cell = new Cell[3][3];
    TiledMapTile[][] tile = new TiledMapTile[3][3];
    MainGame game;
    boolean hasSword;

    Inventory inv;

    public SurviveAndThrive(MainGame g) {
        game = g;

        batch = new SpriteBatch();
        testPlayer = new Player();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //set up the map and camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);
        map = new TmxMapLoader().load("takethree.tmx");
        //scale the map
        mapRenderer = new OrthogonalTiledMapRenderer(map, 4 / 1f);
        Gdx.input.setInputProcessor(this);
        //set player size and position
        testPlayer.setSize(48, 70);
        testPlayer.setPosition(5276, 5256);
        cam.translate(5000, 5000);
        //get some map properties for later
        layer = (TiledMapTileLayer) map.getLayers().get("Tile Layer 1");
        tileWidth = (int) layer.getTileWidth();
        tileHeight = (int) layer.getTileHeight();

        //read item data from file
        readInItems();

        //load object layer from tilemap
        objects = map.getLayers().get("Object Layer 1").getObjects();
        //store all trees and rocks in lists
        treeObjects = new ArrayList<>();
        rockObjects = new ArrayList<>();
        mapObjects = new ArrayList<>();
        //loop through all mapobjects, add to list
        for (int i = 0; i < objects.getCount(); i++) {
            Interactable obj = new Interactable((RectangleMapObject) objects.get(i));
            obj.setName(obj.getProperties().get("name", String.class));
            mapObjects.add(obj);
        }
    }

    /**
     * Renders the canvas.
     *
     * @param f
     */
    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //update the camera
        cam.update();
        mapRenderer.setView(cam);
        batch.setProjectionMatrix(cam.combined);
        //draw the background
        mapRenderer.render(backgroundLayers);

        batch.begin();
        batch.enableBlending();
        //draw the player
        testPlayer.draw(batch);

        batch.end();
        //draw the foreground
        mapRenderer.render(foregroundLayers);
        //get player location, both on the map, and within the specific tile
        playerX = (int) testPlayer.getX() / tileWidth / 4;
        playerY = (int) testPlayer.getY() / tileHeight / 4;
        tilePosX = (int) testPlayer.getX() % 60 / 4;
        tilePosY = (int) testPlayer.getY() % 60 / 4;
        //cell the player is on
        cell[1][1] = layer.getCell(playerX, playerY);
        tile[1][1] = cell[1][1].getTile();
        //above
        cell[1][0] = layer.getCell(playerX, playerY + 1);
        tile[1][0] = cell[1][0].getTile();
        //below
        cell[1][2] = layer.getCell(playerX, playerY - 1);
        tile[1][2] = cell[1][2].getTile();
        //left
        cell[0][1] = layer.getCell(playerX - 1, playerY);
        tile[0][1] = cell[0][1].getTile();
        //right
        cell[2][1] = layer.getCell(playerX + 1, playerY);
        tile[2][1] = cell[2][1].getTile();
        //top left
        cell[0][0] = layer.getCell(playerX - 1, playerY + 1);
        tile[0][0] = cell[0][0].getTile();
        //top right
        cell[2][0] = layer.getCell(playerX + 1, playerY + 1);
        tile[2][0] = cell[2][0].getTile();
        //bottom left
        cell[0][2] = layer.getCell(playerX - 1, playerY - 1);
        tile[0][2] = cell[0][2].getTile();
        //bottom right
        cell[2][2] = layer.getCell(playerX + 1, playerY - 1);
        tile[2][2] = cell[2][2].getTile();
        //if statements get key input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //make sure the player isnt running into any water tiles
            if (!(tile[0][1].getProperties().containsKey("water") && tilePosX <= 1)) {
                //move player and camera
                cam.translate(-4, 0);
                testPlayer.translateX(-4);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //make sure the player isnt running into any water tiles
            if (!(tile[2][1].getProperties().containsKey("water") && (tilePosX + 11) <= 15)) {
                //move player and camera
                cam.translate(4, 0);
                testPlayer.translateX(4);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //make sure the player isnt running into any water tiles
            if (!(tile[1][2].getProperties().containsKey("water") && tilePosY <= 1)) {
                //check the tile next to the player as well so that they dont appear to be walking on water
                if (!((tilePosX > 4 && tilePosY <= 1) && tile[2][2].getProperties().containsKey("water"))) {
                    //move player and camera
                    cam.translate(0, -4);
                    testPlayer.translateY(-4);
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //make sure the player isnt running into any water tiles
            if (!(tile[1][0].getProperties().containsKey("water") && tilePosY <= 12)) {
                //check the tile next to the player as well so that they dont appear to be walking on water
                if (!((tilePosX > 4 && tilePosY <= 12) && tile[2][0].getProperties().containsKey("water"))) {
                    //move player and camera
                    cam.translate(0, 4);
                    testPlayer.translateY(4);
                }
            }

        }

        //if the users pressed E
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            inv = new Inventory(items, game, this);
            inv.updateInventory(items);
            //switches to the inventory screen
            game.setScreen(inv);

        }
        //if the user pressed ESC
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //switches to the pause menu
            game.setScreen(new PauseMenu(testPlayer, items, game, this));
        }
        

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //interact with closest object
            interact();
            //check if the user has a sword
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    if (items[i][j] != null && items[i][j].getName().equals("Sword")) {
                        //if they do, set hasSword to true, if not make sure it is false
                        
                        if(items[i][j].getAmount() > 0){
                            System.out.println("test");
                            hasSword = true;
                            break;
                        }
                    } else {
                        hasSword = false;
                    }
                }
                if(hasSword){
                    break;
                }
            }
            System.out.println(hasSword);
            //check if the player is in a meadow
            if (tile[1][1].getProperties().containsKey("meadow") && hasSword) {
                //if they are look for the rest of the rabbits in their inventory, and add another one
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (items[i][j] != null && items[i][j].getName().equals("Rabbit")) {
                            items[i][j].addItem(1);
                            System.out.println("got rabbit");
                        }
                    }
                }
                
            }
        }
    }

    /**
     * Causes the player to interact with the nearest object.
     *
     * @return Whether the interaction succeeded
     */
    public boolean interact() {
        System.out.println("pressed space");
        //check each object in the map
        for (int i = 0; i < mapObjects.size(); i++) {
            //check whether distance is less than 100 pixels in either direction
            if (distance(testPlayer, mapObjects.get(i).getRectangle()) <= 100) {
                //check if object has resources left
                if (mapObjects.get(i).interact()) {
					//trigger object interaction
                    //check if item is already present in inventory
                    System.out.println("interaction triggered");
                    for (int j = 0; j < items.length; j++) {
                        for (int k = 0; k < items[j].length; k++) {
                            //check if current item matches original
                            if (items[j][k] != null && mapObjects.get(i).getName().equals(items[j][k].getName())) {
                                System.out.println(mapObjects.get(i).getName() + " found at index [" + j + "][" + k + "]");
                                //add item to matching inventory slot
                                items[j][k].addItem(1);
                                return true;
                            }
                        }
                    }
					//item not present in inventory, place in first empty slot
					/*for (int j = 0; j < items.length; j++) {
                     for (int k = 0; k < items[j].length; k++) {
                     if (items[j][k] == null) {
                     //place in slot
                     items[j][k] = new Resource(mapObjects.get(i).getName(), 1);
                     }
                     }
                     }*/
                } else {
                    //display message
                    JOptionPane.showMessageDialog(null, "This resource has been exhausted.");
                }
            }
        }
        return false;
    }

    /**
     * Calculates the distance between a player and a rectangle.
     *
     * @param player The Player to check distance for
     * @param obj The Rectangle to check distance for
     * @return The largest orthogonal distance between the Player and the
     * Rectangle
     */
    public double distance(Player player, Rectangle obj) {
        //get centres of objects
        double playerX = player.getX() + (player.getWidth() / 2.0);
        double playerY = player.getY() + (player.getHeight() / 2.0);
        double objX = (obj.getX() + (obj.getWidth() / 2.0)) * 4.0;
        double objY = (obj.getY() + (obj.getHeight() / 2.0)) * 4.0;
        //get distances in each direction
        double xDist = Math.abs(playerX - objX);
        double yDist = Math.abs(playerY - objY);
        //return larger distance
        return Math.max(xDist, yDist);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     * Reads in item data from a the file ItemRecipes.txt.
     */
    public void readInItems() {
        int ResourceIndex = 0;
        int FoodIndex = 0;
        int ToolIndex = 0;
        FileHandle itemsFile = Gdx.files.internal("ItemRecipes.txt");
        String test = itemsFile.readString();
        String itemData[] = test.split("\n");

        //Format for new Items:
        //Type of Item
        //Name
        //Special attributes to the item
        //Item 1 to craft
        //amount of Item 1
        //Item 2 to craft
        //amount of Item 2
        //etc. will continue until program reads in:
        //End
        //When the program reads DONE it means that there are no more items to read in
        items = new Item[5][10];

        for (int i = 0; i < 10; i++) {
            itemType = itemData[readInIndex].trim();

            readInIndex++;
            if (itemType.equals("DONE")) {
                break;
            }
            if (itemType.equals("Resource")) {
                itemName = itemData[readInIndex].trim();

                readInIndex++;
                items[0][ResourceIndex] = new Resource(itemName, 0);
                ResourceIndex++;
            } else if (itemType.equals("Food")) {
                itemName = itemData[readInIndex].trim();

                readInIndex++;
                foodValue = Integer.parseInt(itemData[readInIndex].trim());
                readInIndex++;
                recipe = itemData[readInIndex].trim();
                readInIndex++;
                items[1][FoodIndex] = new Food(itemName, 0, true, foodValue, recipe);
                FoodIndex++;
            } else if (itemType.equals("Tool")) {
                itemName = itemData[readInIndex].trim();

                readInIndex++;
                recipe = itemData[readInIndex].trim();
                readInIndex++;
                items[2][ToolIndex] = new Tools(itemName, 0, recipe);
                ToolIndex++;
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
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

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }
}
