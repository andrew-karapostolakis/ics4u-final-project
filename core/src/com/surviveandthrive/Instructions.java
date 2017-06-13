/* A Karapostolakis, Brandon Lit, Garrett Smith
 * 2017-06-13
 * This is the instructions screen*/
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Instructions implements Screen {

    private MainGame game;
    private LabelStyle style;
    private TextButton start;
    private TextButtonStyle textButtonStyle;
    private Stage stage;
    private Label instructions;
    Skin skin;

    public Instructions(MainGame newGame, LabelStyle newStyle, TextButtonStyle newButtonStyle) {
        game = newGame;
        style = newStyle;
        textButtonStyle = newButtonStyle;
        //creates a new skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        //creates the instructions paragraph
        instructions = new Label("Welcome to Survive and Thrive, an open world exploration game"
            + "\nwhere the player gathers resources to make tools and other useful things."
            + "\nUpon entering the world, you will find yourself in a desert. Use the arrow"
            + "\nkeys to move around into different biomes. You will find forests, meadows,"
            + "\nrivers, and so on. Keep in mind that you are incapable of swimming, and must"
            + "\ntherefore find a bridge to cross any rivers that you come to. Most of the"
            + "\nbiomes have something useful in them. For"
            + "\nexample; in forests you can get wood, in quarries you can get rocks, and in"
            + "\nmeadows you can get rabbits and flowers. Once you start collecting items"
            + "\nyou can press \"e\" to view your inventory. Once in your inventory you can"
            + "\nselect the crafting menu to make new and fancy items. Sometimes you require a"
            + "\nitem to collect resources. Rabbits, for example, require a sword to catch them."
			+ "\n\nGame programmed by Andrew Karapostolakis, Brandon Lit, and Garrett Smith", skin);
        instructions.setY(250);
    }

    @Override
    public void show() {
        //creates the new stage
        stage = new Stage();
        //sets the input processor to the stage
        Gdx.input.setInputProcessor(stage);

        //creates the start button
        start = new TextButton("Start Game", skin);
        start.setX(250);
        start.setY(125);
        //adds an input listener
        start.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //sets the screen to the survive and thrive game
                game.setScreen(new SurviveAndThrive(game));

                return true;
            }
        });
        
        //adds the label and start button
        stage.addActor(instructions);
        stage.addActor(start);
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
    public void hide() {
    }

    @Override
    public void dispose() {
        //disposes of the stage and it's actors
        stage.dispose();
    }

}
