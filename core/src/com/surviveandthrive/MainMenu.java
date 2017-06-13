/* Brandon Lit
 * 2017-05-29
 * This is the main menu window class*/
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu implements Screen {
    //declares all the required aspects of the menu
    private Skin skin;
    private Stage stage;
    private TextureAtlas atlas;
    private Table table;
    private TextButton start, exit, instructions;
    private BitmapFont white, black;
    private Label heading;
    private MainMenu instructionsMenu;
    
    private MainGame game;

    public MainMenu(MainGame game) {
        //sets the game to the passed game
        this.game = game;
        //sets the stage to a viewport
        stage = new Stage(new FitViewport(500,500));
        //sets teh input processor to the current stage
        Gdx.input.setInputProcessor(stage);
        //gets the UI atlas
        atlas = new TextureAtlas("UI/Button.atlas");
        //creates the skin (for any actor)
        skin = new Skin(atlas);
        //creates the table with the skin
        table = new Table(skin);
        //this essentially allows the table to scale
        table.setFillParent(true);
        //sets the bounds of the table
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //sets this main menu to the one that will be passed to the instruction manual
        instructionsMenu = this;
    }

    @Override
    public void show() {
        
        //creates a black and white font
        white = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFont.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFontBlack.fnt"), false);

        //creates a new textbutton style
        TextButtonStyle textButtonStyle = new TextButtonStyle();

        //sets up the text button style
        textButtonStyle.up = skin.getDrawable("button_up.9.png");
        textButtonStyle.down = skin.getDrawable("button_down.9.png");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;
        
        
        //creates a new textbutton with the created stye
        start = new TextButton("Start", textButtonStyle);
        //sets teh bound of teh start button
        start.setBounds(190,300,130,100);
        //adds an input listener to teh button
        start.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                //which the button is pressed switch the screen to the game screen
                game.setScreen(new SurviveAndThrive(game));
                //returns true to satisfy the overridden method 
                return true;
            }
        });
        //padds the start button
        start.pad(20);

        //creates an exit button
        exit = new TextButton("Exit", textButtonStyle);
        //sets teh bound of the exit button
        exit.setBounds(200, 125, 0, 0);
        //adds an event listener
        exit.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                //exits the program when the button is pressed
                Gdx.app.exit();
                return true;
            }
        });
        //pads the exit button
        exit.pad(15);

                
        //creates a label style
        LabelStyle labelStyle = new LabelStyle();
        //sets the font to the white text
        labelStyle.font = white;
        //creates a new label with the label style
        heading = new Label("Survive and Thrive", labelStyle);
        
        
        //creates the instruction button, sets the bounds and adds a listener
        instructions = new TextButton("Instructions", textButtonStyle);
        instructions.setBounds(100, 180, 300, 100);
        instructions.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                //switches screens to the instruction screen
                game.setScreen(new Instructions(game, labelStyle, textButtonStyle));
                return true;
            }
        });
       //pads the instructions
        instructions.pad(35);

        //adds everything to the table
        table.add(heading);
        table.row();//creates a new row
        table.add(start);
        table.row();
        table.add(instructions);
        table.row();
        table.add(exit);

        table.debug();
        //adds the table to the stage
        stage.addActor(table);

    }

    /**
     * renders the screen
     * @param f the time in seconds since the last render
     */
    @Override
    public void render(float f) {
        //sets teh background colour to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //draws the stage
        stage.draw();

    }
    /**
     * disposes the stage when the screen is closed
     */
    @Override
    public void dispose() {
        //disposes of teh sateg and its actors
        stage.dispose();
    }

    /**
     * is called when the screen resizes
     * @param i the new width
     * @param i1 the new height
     */
    @Override
    public void resize(int i, int i1) {
        //updates the screen
        stage.getViewport().update(i, i1, false);
    }

    
    
    //The next three methods do nothing but are required for implementing screen
    //pause, resume and hide are essentially for phone apps
    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
