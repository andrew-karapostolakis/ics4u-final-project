package com.surviveandthrive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.Set;

public class SurviveAndThrive extends Game {

    Stage mainMenu;
    Table menu;
    Skin start;

    public SpriteBatch batch;
    Texture startBtn;
    Texture car;
    Rectangle test = new Rectangle();
    Sprite sprite;

    @Override
    public void create() {

    }

    @Override
    public void render() {
        this.setScreen(new MainMenu(this));
        
        
    }

    @Override
    public void dispose() {

    }
}
