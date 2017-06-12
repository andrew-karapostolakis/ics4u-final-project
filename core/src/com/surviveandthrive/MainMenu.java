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

    private SpriteBatch batch;
    private Texture startBtn;
    private Skin skin;

    private Stage stage;
    private TextureAtlas atlas;
    private Table table;
    private TextButton start, exit, instructions;
    private BitmapFont white, black;
    private Label heading;
    
    private MainGame game;

    public MainMenu(MainGame game) {
        this.game = game;
        
        stage = new Stage(new FitViewport(500,500));
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("UI/Button.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setFillParent(true);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        

        
        white = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFont.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFontBlack.fnt"), false);

        TextButtonStyle textButtonStyle = new TextButtonStyle();

        textButtonStyle.up = skin.getDrawable("button_up.9.png");
        textButtonStyle.down = skin.getDrawable("button_down.9.png");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        start = new TextButton("Start", textButtonStyle);
        start.setBounds(190,300,130,100);
        start.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                System.out.println("start game");
                game.setScreen(new SurviveAndThrive(game));
                
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                
            }
        });

        start.pad(20);

        exit = new TextButton("Exit", textButtonStyle);
        exit.setBounds(200, 125, 0, 0);
        exit.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                
            }
        });

        exit.pad(15);
        exit.debug();

        instructions = new TextButton("Instructions", textButtonStyle);
        instructions.setBounds(100, 180, 300, 100);
        instructions.debug();
        instructions.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                System.out.println("//TODO add instructions");
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                
            }
        });
        
        instructions.pad(35);

        LabelStyle labelStyle = new LabelStyle();

        labelStyle.font = white;

        heading = new Label("Survive and Thrive", labelStyle);

        table.add(heading);
        table.row();
        table.add(start);
        table.row();
        table.add(instructions);
        table.row();
        table.add(exit);

        table.debug();

        stage.addActor(table);

    }

    @Override
    public void render(float f) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, false);
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

}
