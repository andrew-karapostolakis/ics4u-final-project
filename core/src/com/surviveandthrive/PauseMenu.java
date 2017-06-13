/* Brandon Lit, Garrett Smith
 * 2017-06-12
 */
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PauseMenu implements Screen {

    Player testPlayer;
    Item[][] items;
    MainGame game;
    Stage stage;
    Skin skin;
    SurviveAndThrive world;

    public PauseMenu(Player player, Item[][] item, MainGame g, SurviveAndThrive w) {
        testPlayer = player;
        items = item;
        game = g;
        //creates a new skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //sets the input to be recieved from this stage
        Gdx.input.setInputProcessor(stage);

    }

    public void writeStats() {
        //gets the players health and food
        int health = testPlayer.getHealth();
        int food = testPlayer.getFood();
        //tries to write to the data file
        try {
            File stats = new File("..\\stats.txt");
            //creates the writer
            BufferedWriter writer = new BufferedWriter(new FileWriter(stats));
            //writes the information to the data file
            writer.write("Health: " + health);
            writer.write("Hunger: " + food);
            //loops through the items array
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    //if there is an item in the array
                    if (items[i][j] != null) {
                        //writes the information to the file
                        writer.write("Item: " + items[i][j].getName());
                        writer.write("Amount of item: " + items[i][j].getAmount());
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void show() {
        stage = new Stage();

        //creates a textbutton
        TextButton resume = new TextButton("Resume", skin);
        //sets the resumes buttons position
        resume.setX(300);
        resume.setY(400);
        //adds an input listener to the button
        resume.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //returns the user to the game world when pressed
                game.setScreen(world);
                //returns false to satusfy the inputlistener method
                return false;
            }
        });
        resume.pad(50);

        //creates a textbutton
        TextButton exit = new TextButton("Exit", skin);
        //sets the exit buttons position
        exit.setX(300);
        exit.setY(100);
        //adds an input listener to the button
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //writes the users information to a data file
                writeStats();
                //Closes the game
                Gdx.app.exit();
                //returns false to satusfy the inputlistener method
                return false;
            }
        });

        //adds the actors to the stage
        stage.addActor(resume);
        stage.addActor(exit);
    }

    @Override
    public void render(float f) {
        //sets the background colour to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        //draws the stage
        stage.draw();
    }

    //The next 4 methods do nothing but are required for implementing screen
    //pause, resume and hide are essentially for phone apps
    @Override
    public void resize(int i, int i1) {
        //resizes the stage according to the viewport
        stage.getViewport().update(i, i1, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
