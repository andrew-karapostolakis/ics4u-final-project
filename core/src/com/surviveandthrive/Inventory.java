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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Brandon
 */
public class Inventory implements Screen {

    private Stage stage;
    private ItemSlot[][] itemGrid = new ItemSlot[4][7];
    private Item tempStorage = null;
    private Skin skin;
    private boolean food = false, tools = false;
    private MainGame game;
    private SurviveAndThrive world;

    public Inventory(final Item[][] oldInv, MainGame g, SurviveAndThrive w) {
        stage = new Stage();
        game = g;
        world = w;
        //atlas = new TextureAtlas("uiskin.json");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        //loops through the array  of item slots.
        //Each slot is represented by a button in the grid on the inventory screen
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 7; y++) {
                //creates a new "Item slot"
                itemGrid[x][y] = new ItemSlot("", skin, x, y);
                //sets teh position of the new item slot
                itemGrid[x][y].setPosition(35 + (y * 75), 250 + (x * 75));
                //sets the default size of the button
                itemGrid[x][y].setSize(75, 75);
                //adds an event listener to the slot
                itemGrid[x][y].addListener(new InventoryListener(x, y) {
                    /**
                     * When the button is clicked
                     *
                     * @param event
                     * @param xPos The x coordinate, origin is in the upper left
                     * corner
                     * @param yPos The y coordinate, origin is in the upper left
                     * corner
                     * @param pointer the pointer for the event.
                     * @param button the button
                     */
                    @Override
                    public boolean touchDown(InputEvent event, float xPos, float yPos, int pointer, int button) {
                        //checks to see if there is an item at the current button index
                        if (itemGrid[x][y].getStoredItem() != null && tempStorage == null) {
                            //if there is an item and the user is not "holding" an item
                            //adds the item in the item slot to a temporary holding variable
                            tempStorage = itemGrid[x][y].getStoredItem();
                            //removes the item from the item slot
                            itemGrid[x][y].setStored(null);
                            //clears the text in the item slot
                            itemGrid[x][y].setText("");

                            //if there is no item in the item slot but the user is holding an item
                        } else if (itemGrid[x][y].getStoredItem() == null && tempStorage != null) {
                            //stores the held item in the item slot
                            itemGrid[x][y].setStored(tempStorage);
                            //sets up the text in the item slot
                            itemGrid[x][y].setText(tempStorage.getAmount() + " " + tempStorage.getName().replaceAll("_", " "));
                            //removes the item in the item slot
                            tempStorage = null;

                            //if there is both a held item and an item in the item slot
                        } else if (itemGrid[x][y].getStoredItem() != null && tempStorage != null) {
                            //creates a second temporary storage variable and stores the held item in it
                            Item temp = tempStorage;
                            //sets the temperary storage variable to the stored item
                            tempStorage = itemGrid[x][y].getStoredItem();
                            //sets teh stored item to the second temp storage
                            itemGrid[x][y].setStored(temp);
                            //sets up the text with the new item
                            itemGrid[x][y].setText(temp.getAmount() + " " + temp.getName().replaceAll("_", " "));

                        }
                        //resturing true satisfies the buttun pressed override method
                        return true;
                    }
                });

                //when generating the item slots the program checks to see if
                //there is an item stored from the passed inventory
                //then it checks to see if there is are at least 1 of that stored item
                if (oldInv[x][y] != null) {
                    //if there atucally is an item.
                    //not just a refrence to the item
                    if (oldInv[x][y].getAmount() > 0) {
                        //sets the stored item to the previous item
                        itemGrid[x][y].setStored(oldInv[x][y]);
                        //sets the text on the button
                        itemGrid[x][y].setText(oldInv[x][y].getAmount() + " " + oldInv[x][y].getName());
                    }
                }
                //adds the button to the stage
                stage.addActor(itemGrid[x][y]);
            }
        }
        //declares the exit textbutton
        final TextButton exit = new TextButton("Back to Game", skin.get("default", TextButtonStyle.class));
        //sets the X and Y position of the textbutton and an event listener
        exit.setX(450);
        exit.setY(50);
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //returns the user to the game world when pressed
                game.setScreen(world);
                //returns false to satusfy the inputlistener method
                return false;
            }
        });
        //creates three labels for the crafting panel
        final Label craftItem1 = new Label("", skin);

        final Label craftItem2 = new Label("", skin);

        final Label craftItem3 = new Label("", skin);

        //sets the position of the three labels
        craftItem1.setPosition(80, 60);
        craftItem2.setPosition(80, 110);
        craftItem3.setPosition(80, 160);

        //creates a textbutton for crafting
        final TextButton Crafting1 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        //sets the X and Y of the button
        Crafting1.setX(25);
        Crafting1.setY(50);
        //adds an input listener to the button
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

        //decalres the second crafting button
        final TextButton Crafting2 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        //sets teh X and Y of the crafting button
        Crafting2.setX(25);
        Crafting2.setY(100);
        //adds an input listener to the button
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

                } else if (food) {
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
                //returns false to satisfy the overriden method requirement
                return false;
            }
        });

        //declares the third crafting button
        final TextButton Crafting3 = new TextButton("Craft", skin.get("default", TextButtonStyle.class));
        //sets the X and Y of the crafting button
        Crafting3.setX(25);
        Crafting3.setY(150);
        //adds an input listener to the button
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

                } else if (food) {
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
                //returns false to satisfy the overriden method requirement
                return false;
            }
        });
        //hides all three buttons
        Crafting1.setVisible(false);
        Crafting2.setVisible(false);
        Crafting3.setVisible(false);

        //creates teh button to reveal the "food" menu
        TextButton foodMenu = new TextButton("Food", skin.get("default", TextButtonStyle.class));
        //sets the X and Y of the button
        foodMenu.setX(100);
        foodMenu.setY(200);
        //adds an input listener to the button
        foodMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //sets food to true
                food = true;
                //sets tools to false
                tools = false;
                //makes the required buttons visible and hides the uneeded buttons
                Crafting1.setVisible(false);
                Crafting2.setVisible(true);
                Crafting3.setVisible(true);
                //sets the text of the labels
                craftItem3.setText(oldInv[1][0].getName().replaceAll("_", " ") + "    requires:    " + ((Food) oldInv[1][0]).getRecipe().replaceAll("_", " "));
                craftItem2.setText(oldInv[1][1].getName().replaceAll("_", " ") + "    requires:    " + ((Food) oldInv[1][1]).getRecipe().replaceAll("_", " "));
                craftItem1.setText("");
                //returns false to satisfy the overriden method requirement
                return false;
            }
        });

        //creates a button for the tool crafting menu
        TextButton toolMenu = new TextButton("Tools", skin.get("default", TextButtonStyle.class));
        //sets teh X and Y of the button
        toolMenu.setX(200);
        toolMenu.setY(200);
        //adds an event listener
        toolMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //sets food to false
                food = false;
                //sets tools to true
                tools = true;
                //sets teh reuired buttons to visible
                Crafting1.setVisible(true);
                Crafting2.setVisible(true);
                Crafting3.setVisible(true);
                //sets the text of the variables
                craftItem1.setText(oldInv[2][0].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][0]).getRecipe().replaceAll("_", " "));

                craftItem2.setText(oldInv[2][1].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][1]).getRecipe().replaceAll("_", " "));

                craftItem3.setText(oldInv[2][2].getName().replaceAll("_", " ") + "    requires:    " + ((Tools) oldInv[2][2]).getRecipe().replaceAll("_", " "));
                //returns false to satisfy the overriden method requirement
                return false;
            }
        });
        //adds all the actors to the stage
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
        //creates a small title label
        final Label screenTitle = new Label("Items", skin);
        //sets the position of the label
        screenTitle.setPosition(10, 570);
        //adds the label
        stage.addActor(screenTitle);

    }

    public void render(float f) {
        //sets the background colour to blue
        Gdx.gl.glClearColor(65f / 255f, 138f / 255f, 234f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //causes the stages actors to "act"
        //runs through any events the actors must perform
        stage.act(Gdx.graphics.getDeltaTime());
        //draws the stage and all it's actors
        stage.draw();
    }

    public void resize(int width, int height) {
        //resizes teh stage according to the viewport
        stage.getViewport().update(width, height, true);

        // Gdx.gl.glViewport(100, 100, width - 200, height - 200);
        // stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
    }

    public void dispose() {
        //disposes of the stage and all of it's actors to prevent memory leakage
        stage.dispose();
    }

    public boolean needsGL20() {
        return false;
    }

    //The next three methods do nothing but are required for implementing screen
    //pause, resume and hide are essentially for phone apps
    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    /**
     * Updates the text of the item grid when called
     * @param itemLog the "log" of all items in the game
     */
    public void updateInventory(Item[][] itemLog) {
        boolean newItem;
        //k and l loop through the items array, which holds information for every type of item in the game
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 10; l++) {
                //checks to see if there is an item in the item storage
                if (itemLog[k][l] != null) {
                    //if there is at least 1 of that item to be stored
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
                                        //sets new item to false
                                        newItem = false;
                                    }
                                }
                            }
                        }
                        //if new item is still true, that means that the item is not 
                        //in the inventory
                        if (newItem) {
                            //loops through the item grid
                            for (int a = 0; a < 4; a++) {
                                for (int b = 0; b < 7; b++) {
                                    //finds the first empty space
                                    if (itemGrid[a][b].getStoredItem() == null) {
                                        //puts the new item there
                                        itemGrid[a][b].setStored(itemLog[k][l]);
                                        itemGrid[a][b].setText(itemLog[k][l].getAmount() + " " + itemLog[k][l].getName().replaceAll("_", " "));
                                        //sets new item to false
                                        newItem = false;
                                        //breaks the first loop
                                        break;
                                    }
                                }
                                //if new item is false
                                if (!newItem) {
                                    //breaks the second loop
                                    break;
                                }
                            }
                        }
                        //if there are 0 of that item
                    } else {
                        //loops through the item grid
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 7; j++) {
                                //finds the stored item
                                if (itemGrid[i][j].getStoredItem() != null) {
                                    if (itemGrid[i][j].getStoredItem().getName().equals(itemLog[k][l].getName())) {
                                        //removes the item from the inventory
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
