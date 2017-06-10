/* Brandon Lit
 * 2017-06-03
 * This is the inventory class which will create the screen that appears when the user wishes to access their inventory*/
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Brandon
 */
public class Inventory implements Screen {

    private Stage stage;
    private Table container;
    private TextureAtlas atlas;
    private ItemSlot[][] items = new ItemSlot[5][8];
    private Item tempStorage = null;
    private Skin skin;
    private SpriteBatch batch;
    private Texture texture;

    public Inventory(Item[][] oldInv) {
        stage = new Stage();
        //atlas = new TextureAtlas("uiskin.json");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        texture = new Texture(Gdx.files.internal("Log.png"));
        batch = new SpriteBatch();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 8; y++) {
                items[x][y] = new ItemSlot("", skin, x, y);
                items[x][y].setPosition(20 + (y * 50), 300 + (x * 50));
                items[x][y].setSize(50, 50);

                items[x][y].addListener(new InventoryListener(x, y) {
                    @Override
                    public boolean touchDown(InputEvent event, float xPos, float yPos, int pointer, int button) {
                        //System.out.println(x + " : " + y);
                        //System.out.println(tempStorage);
                        if (items[x][y].getStoredItem() != null && tempStorage == null) {
                            tempStorage = items[x][y].getStoredItem();
                            //System.out.println("name of stored item: " + tempStorage.getName());
                            items[x][y].setStored(null);
                            items[x][y].setText("");

                        } else if (items[x][y].getStoredItem() == null && tempStorage != null) {
                            items[x][y].setStored(tempStorage);
                            items[x][y].setText(tempStorage.getName());
                            tempStorage = null;
                            //System.out.println("just stored: " + items[x][y].getName());
                            //System.out.println("placed: " + items[x][y].getItemName());

                        } else if (items[x][y].getStoredItem() != null && tempStorage != null) {
                            Item temp = tempStorage;
                            tempStorage = items[x][y].getStoredItem();
                            items[x][y].setStored(temp);
                            items[x][y].setText(temp.getName());

                        }
                        return true;
                    }
                });

                //if (oldInv[x][y] != null) {
                    //if (oldInv[x][y].getAmount() > 0) {
                      //  items[x][y].setStored(oldInv[x][y]);
                     //   items[x][y].setText(oldInv[x][y].getName());
                   // }
                //}

                stage.addActor(items[x][y]);
            }
        }

        TextButton exit = new TextButton("Exit", skin.get("default", TextButtonStyle.class));
        exit.setX(550);
        exit.setY(50);
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        TextButton Crafting1 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting1.setX(25);
        Crafting1.setY(200);
        Crafting1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        TextButton Crafting2 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting2.setX(25);
        Crafting2.setY(150);
        Crafting2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        TextButton Crafting3 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting3.setX(25);
        Crafting3.setY(100);
        Crafting3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        TextButton Crafting4 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting4.setX(25);
        Crafting4.setY(50);
        Crafting4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        stage.addActor(Crafting1);
        stage.addActor(Crafting2);
        stage.addActor(Crafting3);
        stage.addActor(Crafting4);
        stage.addActor(exit);
    }

    public void show() {

        // Gdx.graphics.setVSync(false);
        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);

        Table table = new Table();
        // table.debug();

        final ScrollPane scroll = new ScrollPane(table, skin);

        InputListener stopTouchDown = new InputListener() {
            @Override
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
        smoothButton.addListener(new ClickListener() {

            public boolean clicked(ClickListener event, float x, float y) {
                System.out.println("start game");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(smoothButton);
        stage.act();

    }

    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, 300, 300);
        batch.end();

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
