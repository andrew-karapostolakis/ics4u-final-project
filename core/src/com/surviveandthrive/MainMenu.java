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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu implements Screen {

    private SpriteBatch batch;
    private Texture startBtn;
    private Skin skin;

    private Stage stage;
    private TextureAtlas atlas;
    private Table table;
    private TextButton start, exit;
    private BitmapFont white, black;
    private Label heading;
    
    private SurviveAndThrive game;
    
    public MainMenu(SurviveAndThrive game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        atlas = new TextureAtlas("ui/Button.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFont.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("Fonts/MainMenuFontBlack.fnt"), false);

        TextButtonStyle textButtonStyle = new TextButtonStyle();

        textButtonStyle.up = skin.getDrawable("button_up.9.png");
        textButtonStyle.down = skin.getDrawable("button_down.9.png");
        //textButtonStyle.pressedOffsetX = 1;
        //textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        start = new TextButton("Start", textButtonStyle);
        start.pad(20);
        start.setPosition(100,100);
        start.setWidth(250);
        start.setHeight(250);
        
        table.add(start);
        //table.debug();
        stage.addActor(table);
        stage.draw();
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        stage.act(Gdx.graphics.getDeltaTime());
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

    }

}
