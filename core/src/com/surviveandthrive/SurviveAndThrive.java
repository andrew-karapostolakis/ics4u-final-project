package com.surviveandthrive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SurviveAndThrive extends Game {

    SpriteBatch batch;
    Texture img;
    Texture car;
    Rectangle test = new Rectangle();
    Sprite sprite;
    String itemType, itemName, recipe;
    int foodValue;
    Item[][] items;
    int readInIndex = 0;

    @Override
    public void create() {
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

        /*
         Gdx.gl.glClearColor(1, 0, 0, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         batch.begin();
         sprite.draw(batch);
         //batch.draw(img, 0, 0);
         //draws the car at the tests x and y locations
         batch.draw(car, test.x, test.y);
         batch.end();

         //these if statements check to see if the arrow keys are being pressed
         if(Gdx.input.isKeyPressed(Keys.LEFT)) test.x -= 15;
         if(Gdx.input.isKeyPressed(Keys.RIGHT)) test.x += 15;
         if(Gdx.input.isKeyPressed(Keys.DOWN)) test.y -= 15;
         if(Gdx.input.isKeyPressed(Keys.UP)) test.y += 15;
        
         }

         @Override
         public void dispose() {
         batch.dispose();
         car.dispose();
         img.dispose();
         */
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

}
