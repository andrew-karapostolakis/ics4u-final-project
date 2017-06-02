package com.surviveandthrive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class SurviveAndThrive extends ApplicationAdapter implements InputProcessor{
    Texture img;
    TiledMap map;
    OrthographicCamera cam;
    TiledMapRenderer mapRenderer;
    SpriteBatch batch;
    Texture car;
    Rectangle test = new Rectangle();
    Sprite sprite;
    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        car = new Texture("car.png");
        sprite = new Sprite(car);
        sprite.setOrigin(0,0);
        sprite.setPosition(0,0);
        test.height = 80;
        test.width = 60;
        test.x = 100;
        test.y = 60;
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //set up the map and camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false,w,h);
        map = new TmxMapLoader().load("takethree.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 4/1f);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*batch.begin();
        sprite.draw(batch);
        //batch.draw(img, 0, 0);
        //draws the car at the tests x and y locations
        batch.draw(car, test.x, test.y);
        batch.end();*/
        cam.update();
        mapRenderer.setView(cam);
        mapRenderer.render();
        //these if statements check to see if the arrow keys are being pressed
        /*if(Gdx.input.isKeyPressed(Keys.LEFT)) test.x -= 15;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) test.x += 15;
        if(Gdx.input.isKeyPressed(Keys.DOWN)) test.y -= 15;
        if(Gdx.input.isKeyPressed(Keys.UP)) test.y += 15;*/
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        car.dispose();
        img.dispose();
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            cam.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            cam.translate(32,0);
        if(keycode == Input.Keys.UP)
            cam.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            cam.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            map.getLayers().get(0).setVisible(!map.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());
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

