/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surviveandthrive;

import com.badlogic.gdx.Screen;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author grumm
 */
public class PauseMenu implements Screen{
    Player testPlayer;
    Item[][] items;
    public PauseMenu(Player player, Item[][] item){
        testPlayer = player;
        items = item;
    }
    public void writeStats(){
        int health = testPlayer.getHealth();
        int food = testPlayer.getFood();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("stats.txt"));
            writer.write("Health: " + health);
            writer.write("Hunger: " + food);
            for(int i = 0; i < 4; i ++){
                for(int j = 0; j < 7; j++){
                    if(items[i][j] != null){
                        writer.write("Item: " + items[i][j].getName());
                        writer.write("Amount of item: " + items[i][j].getAmount());
                    }
                }
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
