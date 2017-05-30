/* Brandon Lit
 * 2017-05-29
 * This is the main menu window class*/
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class MainMenu implements Screen {
    private SpriteBatch batch;
    private Texture startBtn;
 
    private static final int startButtonWidth = 250;
    private static final int startButtonHeight = 200;
    
    SurviveAndThrive game;
    
    public MainMenu(SurviveAndThrive game) {
        super();
        this.game = game;
        batch = new SpriteBatch();
        startBtn = new Texture("StartButton.png");
    }
 
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(startBtn, 100, 100, startButtonWidth, startButtonHeight);
        batch.end();
        
    }
 
    @Override
    public void hide() { }
 
    @Override
    public void pause() { }
 
    @Override
    public void resume() { }
 
    @Override
    public void show() { }
 
    @Override
    public void resize(int width, int height) { }
 
    @Override
    public void dispose() {
        startBtn.dispose();
        batch.dispose();
    }
}
