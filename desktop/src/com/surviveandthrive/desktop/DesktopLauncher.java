package com.surviveandthrive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.surviveandthrive.SurviveAndThrive;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.title = "Survive And Thrive";
		new LwjglApplication(new SurviveAndThrive(), config);
	}
}
