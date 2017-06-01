package com.surviveandthrive;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SurviveAndThrive implements ApplicationListener {

	SpriteBatch batch;
	Texture img;
	Texture car;
	Rectangle test = new Rectangle();
	Sprite sprite;
	Player testPlayer;
	OrthographicCamera cam;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		car = new Texture("car.png");
		testPlayer = new Player("Jeff");
		sprite = new Sprite(car);
		sprite.setOrigin(0, 0);
		sprite.setPosition(0, 0);
		test.height = 80;
		test.width = 60;
		test.x = 100;
		test.y = 60;
		cam = new OrthographicCamera(Gdx.graphics.getWidth()/* / 2f*/, Gdx.graphics.getHeight()/* / 2f*/);
	}

	@Override
	public void render() {
		//these if statements check to see if the arrow keys are being pressed
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			testPlayer.translateX(-1);
			cam.translate(-1, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			testPlayer.translateX(1);
			cam.translate(1, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			testPlayer.translateY(-1);
			cam.translate(0, -1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			testPlayer.translateY(1);
			cam.translate(0, 1);
		}
		cam.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//sprite.draw(batch);
		//batch.draw(img, 0, 0);
		//draws the car at the tests x and y locations
		//batch.draw(car, test.x, test.y);
		batch.draw(car, 0, 0);
		testPlayer.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		car.dispose();
		img.dispose();
	}
	
	@Override
	public void resume() {
	}

	@Override
	public void resize(int i, int i1) {
		cam.viewportWidth = i;
		cam.viewportHeight = i1;
		cam.update();
	}

	@Override
	public void pause() {
	}
}
