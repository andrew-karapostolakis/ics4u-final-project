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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.files.FileHandle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SurviveAndThrive extends Game implements InputProcessor, ApplicationListener{
    int[] backgroundLayers = {0};
    int[] foregroundLayers = {1};
    SpriteBatch batch;
    Sprite sprite;
    String itemType, itemName, recipe;
    int foodValue;
    Item[][] items;
    int readInIndex = 0;
    Player testPlayer;
    Texture img;
    TiledMap map;
    OrthographicCamera cam;
    TiledMapRenderer mapRenderer;
    TiledMapTileLayer trees;
    //player coordinates, tile size, and player location within the tile
    int playerX, playerY, tileWidth, tileHeight, tilePosX, tilePosY;
    TiledMapTileLayer layer;
    //arrays to hold the tiles surrounding the player
    Cell[][] cell = new Cell[3][3];
    TiledMapTile[][] tile = new TiledMapTile[3][3];
    
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
        //scale the map
        mapRenderer = new OrthogonalTiledMapRenderer(map, 4/1f);
        Gdx.input.setInputProcessor(this);
        //set player size and position
        testPlayer.setSize(48,70);
        testPlayer.setPosition(5276,5256);
        cam.translate(5000,5000);
        //get some map properties for later
        layer = (TiledMapTileLayer)map.getLayers().get("Tile Layer 1");
        tileWidth = (int)layer.getTileWidth();
        tileHeight = (int)layer.getTileHeight();
    
        /*batch = new SpriteBatch();
         img = new Texture("badlogic.jpg");
         car = new Texture("car.png");
         sprite = new Sprite(car);
         sprite.setOrigin(0,0);
         sprite.setPosition(0,0);
         test.height = 80;
         test.width = 60;
         test.x = 100;
         test.y = 60;
         */
        /*
         Resource test = new Resource("Wood", 5);
         Resource test2 = new Resource("Rock", 3);
         Resource test3 = new Resource("Sand", 10);
         Resource test4 = new Resource("Grass", 6);
         Resource test5 = new Resource("Sticks", 20);
         Resource test6 = new Resource("Flower", 1);

         Item[][] testInv = new Item[5][8];

         testInv[2][4] = test;
         testInv[4][2] = test2;
         testInv[0][0] = test3;
         testInv[1][3] = test4;
         testInv[2][6] = test5;
         testInv[3][7] = test6;
         */
        readInItems();
        
        items[0][0].addItem(6);
        items[0][1].addItem(6);
        items[0][3].addItem(2);
        items[0][4].addItem(2);
        
        this.setScreen(new Inventory(items));
    }

    @Override
    public void render() {
        super.render();

        
         Gdx.gl.glClearColor(1, 0, 0, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         //sprite.draw(batch);
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
        playerX = (int)testPlayer.getX()/tileWidth/4;
        playerY = (int)testPlayer.getY()/tileHeight/4;
        tilePosX = (int)testPlayer.getX() % 60 / 4;
        tilePosY = (int)testPlayer.getY() % 60 / 4;
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
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){   
            //make sure the player isnt running into any water tiles
            if(!(tile[0][1].getProperties().containsKey("water") && tilePosX <= 1)){
                cam.translate(-4,0);
                testPlayer.translateX(-4);
            }
            
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //make sure the player isnt running into any water tiles
            if(!(tile[2][1].getProperties().containsKey("water") && (tilePosX + 11) <= 15)){
                cam.translate(4,0);
                testPlayer.translateX(4);
            }
            
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //make sure the player isnt running into any water tiles
            if(!(tile[1][2].getProperties().containsKey("water") && tilePosY <= 1)){
                //check the tile next to the player as well so that they dont appear to be walking on water
                if(!((tilePosX > 4 && tilePosY <= 1) && tile[2][2].getProperties().containsKey("water"))){
                    cam.translate(0,-4);
                    testPlayer.translateY(-4);
                }
            }
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            //make sure the player isnt running into any water tiles
            if(!(tile[1][0].getProperties().containsKey("water") && tilePosY <= 12)){
                //check the tile next to the player as well so that they dont appear to be walking on water
                if(!((tilePosX > 4 && tilePosY <= 12) && tile[2][0].getProperties().containsKey("water"))){
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

    public void readInItems() {
        int ResourceIndex = 0;
        int FoodIndex = 0;
        int ToolIndex = 0;
        //try {
        //FileReader fr = new FileReader("ItemRecipe.txt");
        //BufferedReader br = new BufferedReader(fr);
        FileHandle itemsFile = Gdx.files.internal("ItemRecipes.txt");
        String test = itemsFile.readString();
        String itemData[] = test.split("\n");

        //Format for new Items:
        //Type  of Item
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
           
            readInIndex ++;
            if (itemType.equals("DONE")) {
                break;
            }
            if (itemType.equals("Resource")) {
                itemName = itemData[readInIndex].trim();;
                
                readInIndex ++;
                items[0][ResourceIndex] = new Resource(itemName, 0);
                ResourceIndex ++;
            } else if (itemType.equals("Food")) {
                itemName = itemData[readInIndex].trim();;
                
                readInIndex ++;
                foodValue = Integer.parseInt(itemData[readInIndex].trim());
                readInIndex ++;
                recipe = itemData[readInIndex].trim();
                readInIndex ++;
                items[1][FoodIndex] = new Food(itemName, 0, true, foodValue, recipe);
                FoodIndex ++;
            } else if (itemType.equals("Tool")) {
                itemName = itemData[readInIndex].trim();;
               
                readInIndex ++;
                recipe = itemData[readInIndex].trim();;
                readInIndex ++;
                items[2][ToolIndex] = new Tools(itemName, 0, recipe);
                ToolIndex ++;
            }
        }

        //} catch (IOException e) {
        //     System.out.println(e);
        //}

        //this.setScreen(new MainMenu(this));
        

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

}

