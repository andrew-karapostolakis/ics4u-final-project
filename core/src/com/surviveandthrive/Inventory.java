/* Brandon Lit
 * 2017-06-03
 * This is the inventory class which will create eth screen that appears when the user wishes to access their inventory*/
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Set;

/**
 *
 * @author Brandon
 */
public class Inventory implements Screen {

    private Stage stage;
    private Table container;
    private TextureAtlas atlas;
    private ItemSlot[][] items = new ItemSlot[10][10];
    private Item tempStorage;

    public void show() {
        stage = new Stage();
        //atlas = new TextureAtlas("uiskin.json");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Gdx.graphics.setVSync(false);
        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);

        Table table = new Table();
        // table.debug();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 8; y++) {
                items[x][y] = new ItemSlot("" + x, skin.get("toggle", TextButtonStyle.class), x, y);
                items[x][y].setPosition(20 + (y * 50), 300 + (x * 50));
                items[x][y].setSize(50, 50);
                items[x][y].addListener(new InventoryListener(x, y) {
                    @Override
                    public boolean touchDown(InputEvent event, float xPos, float yPos, int pointer, int button) {
                        if (items[x][y] == null && tempStorage == null) {
                            tempStorage = items[x][y].storedItem;
                        }else if (items[x][y] == null && tempStorage != null){
                            items[x][y].storedItem = tempStorage;
                        }else if(items[x][y] != null && tempStorage != null){
                            Item temp = tempStorage;
                            tempStorage = items[x][y].storedItem;
                            items[x][y].storedItem = temp;
                        }
                        return true;
                    }
                });
                stage.addActor(items[x][y]);
            }
        }

        final ScrollPane scroll = new ScrollPane(table, skin);

        InputListener stopTouchDown = new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                event.stop();
                return false;
            }
        };

        table.pad(10).defaults().expandX().space(4);

        table.row();
        //table.add(new Label("Items", skin)).expandX().fillX();

        Label screenTitle = new Label("Items", skin);

        screenTitle.setPosition(10, 570);
        //Container title = new Container(screenTitle);

        //title.top();
        stage.addActor(screenTitle);

        //Slider slider = new Slider(0, 100, 1, false, skin);
        // slider.addListener(stopTouchDown); // Stops touchDown events from propagating to the FlickScrollPane.
        //table.add(slider);
        //table.add(new Label("tres long0 long1 long2 long3 long4 long5 long6 long7 long8 long9 long10 long11 long12", skin));
        final TextButton flickButton = new TextButton("Flick Scroll", skin.get("toggle", TextButtonStyle.class));
        flickButton.setChecked(true);
        flickButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                scroll.setFlickScroll(flickButton.isChecked());
            }
        });

        final TextButton smoothButton = new TextButton("Smooth Scrolling", skin.get("toggle", TextButtonStyle.class));
        smoothButton.setChecked(true);
        smoothButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("start game");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(smoothButton);

    }

    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        // Gdx.gl.glViewport(100, 100, width - 200, height - 200);
        // stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
    }

    public void dispose() {
        stage.dispose();
    }

    public boolean needsGL20() {
        return false;
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
