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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
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
    private ItemSlot[][] itemGrid = new ItemSlot[4][7];
    private Item tempStorage = null;
    private Skin skin;
    private SpriteBatch batch;
    private Texture texture;
    private boolean food = false, tools = false;

    public Inventory(final Item[][] oldInv) {
        stage = new Stage();
        //atlas = new TextureAtlas("uiskin.json");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        texture = new Texture(Gdx.files.internal("Log.png"));
        batch = new SpriteBatch();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                itemGrid[x][y] = new ItemSlot("", skin, x, y);
                itemGrid[x][y].setPosition(35 + (y * 75), 250 + (x * 75));
                itemGrid[x][y].setSize(75, 75);

                itemGrid[x][y].addListener(new InventoryListener(x, y) {
                    @Override
                    public boolean touchDown(InputEvent event, float xPos, float yPos, int pointer, int button) {
                        //System.out.println(x + " : " + y);
                        //System.out.println(tempStorage);
                        if (itemGrid[x][y].getStoredItem() != null && tempStorage == null) {
                            tempStorage = itemGrid[x][y].getStoredItem();
                            //System.out.println("name of stored item: " + tempStorage.getName());
                            itemGrid[x][y].setStored(null);
                            itemGrid[x][y].setText("");

                        } else if (itemGrid[x][y].getStoredItem() == null && tempStorage != null) {
                            itemGrid[x][y].setStored(tempStorage);
                            itemGrid[x][y].setText(tempStorage.getAmount() + " " + tempStorage.getName().replaceAll("_", " "));
                            tempStorage = null;
                            //System.out.println("just stored: " + items[x][y].getName());
                            //System.out.println("placed: " + items[x][y].getItemName());

                        } else if (itemGrid[x][y].getStoredItem() != null && tempStorage != null) {
                            Item temp = tempStorage;
                            tempStorage = itemGrid[x][y].getStoredItem();
                            itemGrid[x][y].setStored(temp);
                            itemGrid[x][y].setText(temp.getAmount() + " " + temp.getName().replaceAll("_", " "));

                        }
                        return true;
                    }
                });

                if (oldInv[x][y] != null) {
                    if (oldInv[x][y].getAmount() > 0) {
                        itemGrid[x][y].setStored(oldInv[x][y]);
                        itemGrid[x][y].setText(oldInv[x][y].getAmount() + " " + oldInv[x][y].getName());
                    }
                }

                stage.addActor(itemGrid[x][y]);
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

        final Label craftItem1 = new Label("", skin);

        final Label craftItem2 = new Label("", skin);

        final Label craftItem3 = new Label("", skin);


        craftItem1.setPosition(80, 60);
        craftItem2.setPosition(80, 110);
        craftItem3.setPosition(80, 160);


        final TextButton Crafting1 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting1.setX(25);
        Crafting1.setY(50);
        Crafting1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //the variables that will hold the X and Y locations of the required items (if they exist)
                int req1X = 0, req1Y = 0, req2X = 0, req2Y = 0;
                //if the user is on the tools menu
                if (tools) {
                    //loops through the array conatining all items
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 8; j++) {
                            //if there is an item in the current index
                            if (oldInv[i][j] != null) {
                                //checks for the item called Wood
                                if (oldInv[i][j].getName().equals("Wood")) {
                                    //saves the X and Y coordinates
                                    req1X = i;
                                    req1Y = j;
                                } else if (oldInv[i][j].getName().equals("Stone")) {
                                    //saves the X and Y coordinates
                                    req2X = i;
                                    req2Y = j;
                                }
                            }
                        }
                    }
                    //checks to see if it is possible to subtract 2 from both of the items
                    if (oldInv[req1X][req1Y].getAmount() >= 2 && oldInv[req2X][req2Y].getAmount() >= 2) {
                        //removes 2 from both of the items
                        oldInv[req1X][req1Y].removeItem(2);
                        oldInv[req2X][req2Y].removeItem(2);
                        //runs through the item inventory again
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (oldInv[m][n] != null) {
                                    //finds the item that needs to be crafted
                                    if (oldInv[m][n].getName().equals("Axe")) {
                                        //adds one to the crafted item
                                        oldInv[m][n].addItem(1);
                                        //System.out.println(oldInv[m][n].getName() + " and there are " + oldInv[m][n].getAmount());
                                        //updates the inventory
                                        updateInventory(oldInv);
                                    }

                                }
                            }
                        }

                    }

                }
                //returns false for the touchdown method
                return false;
            }
        });

        final TextButton Crafting2 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting2.setX(25);
        Crafting2.setY(100);
        Crafting2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //the variables that will hold the X and Y locations of the required items (if they exist)
                int req1X = 0, req1Y = 0, req2X = 0, req2Y = 0;
                //if the user is on the tools menu
                if (tools) {
                    //loops through the array conatining all items
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 8; j++) {
                            //if there is an item in the current index
                            if (oldInv[i][j] != null) {
                                //checks for the item called Wood
                                if (oldInv[i][j].getName().equals("Wood")) {
                                    //saves the X and Y coordinates
                                    req1X = i;
                                    req1Y = j;
                                } else if (oldInv[i][j].getName().equals("Stone")) {
                                    //saves the X and Y coordinates
                                    req2X = i;
                                    req2Y = j;
                                }
                            }
                        }
                    }
                    //checks to see if it is possible to subtract 2 from both of the items
                    if (oldInv[req1X][req1Y].getAmount() >= 2 && oldInv[req2X][req2Y].getAmount() >= 3) {
                        //removes 2 from both of the items
                        oldInv[req1X][req1Y].removeItem(2);
                        oldInv[req2X][req2Y].removeItem(3);
                        //runs through the item inventory again
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (oldInv[m][n] != null) {
                                    //finds the item that needs to be crafted
                                    if (oldInv[m][n].getName().equals("Pickaxe")) {
                                        //adds one to the crafted item
                                        oldInv[m][n].addItem(1);
                                        //System.out.println(oldInv[m][n].getName() + " and there are " + oldInv[m][n].getAmount());
                                        //updates the inventory
                                        updateInventory(oldInv);
                                    }

                                }
                            }
                        }

                    }

                }else if (food) {
                    //loops through the array conatining all items
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 8; j++) {
                            //if there is an item in the current index
                            if (oldInv[i][j] != null) {
                                //checks for the item called Wood
                                if (oldInv[i][j].getName().equals("Rabbit")) {
                                    //saves the X and Y coordinates
                                    req1X = i;
                                    req1Y = j;
                                }
                            }
                        }
                    }
                    //checks to see if it is possible to subtract 2 from both of the items
                    if (oldInv[req1X][req1Y].getAmount() >= 1) {
                        //removes 2 from both of the items
                        oldInv[req1X][req1Y].removeItem(1);
                        //runs through the item inventory again
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (oldInv[m][n] != null) {
                                    //finds the item that needs to be crafted
                                    if (oldInv[m][n].getName().equals("Rabbit_Stew")) {
                                        //adds one to the crafted item
                                        oldInv[m][n].addItem(1);
                                        //System.out.println(oldInv[m][n].getName() + " and there are " + oldInv[m][n].getAmount());
                                        //updates the inventory
                                        updateInventory(oldInv);
                                    }

                                }
                            }
                        }

                    }

                }
                return false;
            }
        });

        final TextButton Crafting3 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        Crafting3.setX(25);
        Crafting3.setY(150);
        Crafting3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //the variables that will hold the X and Y locations of the required items (if they exist)
                int req1X = 0, req1Y = 0, req2X = 0, req2Y = 0;
                //if the user is on the tools menu
                if (tools) {
                    //loops through the array conatining all items
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 8; j++) {
                            //if there is an item in the current index
                            if (oldInv[i][j] != null) {
                                //checks for the item called Wood
                                if (oldInv[i][j].getName().equals("Wood")) {
                                    //saves the X and Y coordinates
                                    req1X = i;
                                    req1Y = j;
                                } else if (oldInv[i][j].getName().equals("Stone")) {
                                    //saves the X and Y coordinates
                                    req2X = i;
                                    req2Y = j;
                                }
                            }
                        }
                    }
                    //checks to see if it is possible to subtract 2 from both of the items
                    if (oldInv[req1X][req1Y].getAmount() >= 1 && oldInv[req2X][req2Y].getAmount() >= 5) {
                        //removes 2 from both of the items
                        oldInv[req1X][req1Y].removeItem(1);
                        oldInv[req2X][req2Y].removeItem(5);
                        //runs through the item inventory again
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (oldInv[m][n] != null) {
                                    //finds the item that needs to be crafted
                                    if (oldInv[m][n].getName().equals("Sword")) {
                                        //adds one to the crafted item
                                        oldInv[m][n].addItem(1);
                                        //System.out.println(oldInv[m][n].getName() + " and there are " + oldInv[m][n].getAmount());
                                        //updates the inventory
                                        updateInventory(oldInv);
                                    }

                                }
                            }
                        }

                    }

                }else if (food) {
                    //loops through the array conatining all items
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 8; j++) {
                            //if there is an item in the current index
                            if (oldInv[i][j] != null) {
                                //checks for the item called Wood
                                if (oldInv[i][j].getName().equals("Flower")) {
                                    //saves the X and Y coordinates
                                    req1X = i;
                                    req1Y = j;
                                }
                            }
                        }
                    }
                    //checks to see if it is possible to subtract 2 from both of the items
                    if (oldInv[req1X][req1Y].getAmount() >= 2) {
                        //removes 2 from both of the items
                        oldInv[req1X][req1Y].removeItem(2);
                        //runs through the item inventory again
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 8; n++) {
                                if (oldInv[m][n] != null) {
                                    //finds the item that needs to be crafted
                                    if (oldInv[m][n].getName().equals("Petal_Salad")) {
                                        //adds one to the crafted item
                                        oldInv[m][n].addItem(1);
                                        //System.out.println(oldInv[m][n].getName() + " and there are " + oldInv[m][n].getAmount());
                                        //updates the inventory
                                        updateInventory(oldInv);
                                    }

                                }
                            }
                        }

                    }

                }
                return false;
            }
        });

        Crafting1.setVisible(false);
        Crafting2.setVisible(false);
        Crafting3.setVisible(false);

        TextButton foodMenu = new TextButton("Food", skin.get("default", TextButtonStyle.class));
        foodMenu.setX(100);
        foodMenu.setY(200);
        foodMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                food = true;
                tools = false;
                Crafting1.setVisible(false);
                Crafting2.setVisible(true);
                Crafting3.setVisible(true);
                craftItem3.setText(oldInv[1][0].getName().replaceAll("_", " ") + "    requires:    " + ((Food) oldInv[1][0]).getRecipe().replaceAll("_", " "));
                craftItem2.setText(oldInv[1][1].getName().replaceAll("_", " ") + "    requires:    " + ((Food) oldInv[1][1]).getRecipe().replaceAll("_", " "));
                craftItem1.setText("");
                return false;
            }
        });

        TextButton toolMenu = new TextButton("Tools", skin.get("default", TextButtonStyle.class));
        toolMenu.setX(200);
        toolMenu.setY(200);
        toolMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                food = false;
                tools = true;
                Crafting1.setVisible(true);
                Crafting2.setVisible(true);
                Crafting3.setVisible(true);
                craftItem1.setText(oldInv[2][0].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][0]).getRecipe().replaceAll("_", " "));

                craftItem2.setText(oldInv[2][1].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][1]).getRecipe().replaceAll("_", " "));

                craftItem3.setText(oldInv[2][2].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][2]).getRecipe().replaceAll("_", " "));

                return false;
            }
        });

        stage.addActor(craftItem1);
        stage.addActor(craftItem2);
        stage.addActor(craftItem3);

        stage.addActor(foodMenu);
        stage.addActor(toolMenu);
        stage.addActor(Crafting1);
        stage.addActor(Crafting2);
        stage.addActor(Crafting3);
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

        Slider slider = new Slider(0, 100, 1, false, skin);
        slider.addListener(stopTouchDown); // Stops touchDown events from propagating to the FlickScrollPane.
        stage.addActor(slider);

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

    public void updateInventory(Item[][] itemLog) {
        boolean newItem;
        //k and l loop through the items array, which holds information for every type of item in the game
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 10; l++) {
                //checks to see if there is an item in the item storage
                if (itemLog[k][l] != null) {
                    //if there is at least 1 item in teh inventory
                    if (itemLog[k][l].getAmount() > 0) {
                        newItem = true;
                        System.out.println(itemLog[k][l].getName());
                        // s and t loop through the actual grid based inventory system
                        for (int s = 0; s < 4; s++) {
                            for (int t = 0; t < 7; t++) {
                                //rechecks to see if an item exists at the current searched location in the grid
                                if (itemGrid[s][t].getStoredItem() != null) {
                                    //checks to see if the item to update is stored in the current square that is being searched
                                    if (itemGrid[s][t].getStoredItem().getName().equals(itemLog[k][l].getName())) {
                                            //updates the text on the button
                                        //System.out.println(itemLog[k][l].getName());
                                        itemGrid[s][t].setText(itemLog[k][l].getAmount() + " " + itemLog[k][l].getName().replaceAll("_", " "));
                                        newItem = false;
                                    }
                                }
                            }
                        }
                        if (newItem) {
                            for (int a = 0; a < 4; a++) {
                                for (int b = 0; b < 7; b++) {
                                    if (itemGrid[a][b].getStoredItem() == null) {
                                        itemGrid[a][b].setStored(itemLog[k][l]);
                                        itemGrid[a][b].setText(itemLog[k][l].getAmount() + " " + itemLog[k][l].getName().replaceAll("_", " "));
                                        newItem = false;
                                        break;
                                    }
                                }
                                if(!newItem){
                                    break;
                                }
                            }
                        }
                    }else{
                        
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 7; j++) {
                                if(itemGrid[i][j].getStoredItem() != null){
                                    if(itemGrid[i][j].getStoredItem().getName().equals(itemLog[k][l].getName())){
                                        itemGrid[i][j].setStored(null);
                                        itemGrid[i][j].setText("");
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

}
