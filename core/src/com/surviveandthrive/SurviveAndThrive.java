package com.surviveandthrive;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class SurviveAndThrive extends Game implements InputProcessor, ApplicationListener {

	int[] backgroundLayers = {0};
	int[] foregroundLayers = {1};
	SpriteBatch batch;
	Sprite sprite;
	Player testPlayer;
	Texture img;
	TiledMap map;
	OrthographicCamera cam;
	TiledMapRenderer mapRenderer;
	MapObjects objects;

	@Override
	public void create() {
		batch = new SpriteBatch();
		testPlayer = new Player("Jeff");
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		//set up the map and camera
		cam = new OrthographicCamera();
		cam.setToOrtho(false, w, h);
		map = new TmxMapLoader().load("takethree.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map, 4 / 1f);
		Gdx.input.setInputProcessor(this);
		objects = map.getLayers().get("Object Layer 1").getObjects();
		Rectangle tree = ((RectangleMapObject)objects.get("DeadTree1")).getRectangle();
		System.out.println("Tree X: " + tree.getX());
		System.out.println("Tree Y: " + tree.getY());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		mapRenderer.setView(cam);
		mapRenderer.render();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		testPlayer.draw(batch);
		batch.end();
		//these if statements check to see if the arrow keys are being pressed

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (true) {
				cam.translate(-4, 0);
				testPlayer.translateX(-4);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(4, 0);
			testPlayer.translateX(4);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -4);
			testPlayer.translateY(-4);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 4);
			testPlayer.translateY(4);
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		/*if(keycode == Input.Keys.LEFT)
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
            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());*/
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
