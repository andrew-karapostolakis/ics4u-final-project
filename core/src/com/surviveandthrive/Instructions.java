/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surviveandthrive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 *
 * @author grumm
 */
public class Instructions implements Screen{
    private MainGame game;
    private LabelStyle style;
    private TextButton exit;
    private TextButtonStyle textButtonStyle; 
    Label instructions = new Label("Welcome to Survive and Thrive, an open world exploration game"
            + "\nwhere the player gathers resources to make tools and other useful things."
            + "\nUpon entering the world, you will find yourself in a desert. Use the arrow"
            + "\nkeys to move around into different biomes. You will find forests, meadows,"
            + "\nrivers, and so on. Keep in mind that you are incapable of swimming, and must"
            + "\ntherefore find a bridge to cross any rivers that you come to. Most of the"
            + "\nbiomes have something useful in them. For"
            + "\nexample; in forests you can get wood, in quarries you can get rocks, and in"
            + "\nmeadows you can get rabbits and flowers. Once you start collecting items"
            + "\nyou can press \"e\" to view your inventory. Once in your inventory you can"
            + "\nselect the crafting menu to make new and fancy items. Sometimes you require a"
            + "\nitem to collect. Rabbits, for example, require a sword to catch when. ", style);

    public Instructions(MainGame newGame, LabelStyle newStyle, TextButtonStyle newButtonStyle){
        game = newGame;
        style = newStyle;
        textButtonStyle = newButtonStyle;
        
    }
    @Override
    public void show() {
        exit = new TextButton("Exit", textButtonStyle);
        exit.setBounds(200, 125, 0, 0);
        exit.addListener(new InputListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                
            }
        });

        exit.pad(15);
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
