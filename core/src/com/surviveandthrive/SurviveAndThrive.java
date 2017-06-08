package com.surviveandthrive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.HashSet;
import java.util.Set;

public class SurviveAndThrive extends Game {

    SpriteBatch batch;
    Texture img;
    Texture car;
    Rectangle test = new Rectangle();
    Sprite sprite;

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

        Resource test = new Resource("Wood", 5, "none");
        Resource test2 = new Resource("Rock", 3, "none");
        Resource test3 = new Resource("Sand", 10, "none");
        Resource test4 = new Resource("Grass", 6, "none");
        Resource test5 = new Resource("Sticks", 20, "none");
        Resource test6 = new Resource("Flower", 1, "none");
        
        Item[][] testInv = new Item[5][8];
        
        testInv[2][4] = test;
        testInv[4][2] = test2;
        testInv[0][0] = test3;
        testInv[1][3] = test4;
        testInv[2][6] = test5;
        testInv[3][7] = test6;
        
        this.setScreen(new Inventory(testInv));
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
}
