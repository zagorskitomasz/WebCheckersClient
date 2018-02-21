package com.zagorskidev.webcheckers.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zagorskidev.webcheckers.client.WebCheckers;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Web Checkers";
		config.useGL30 = true;
		config.height = Sizes.GAME_HEIGHT;
		config.width = Sizes.GAME_WIDTH;
		
		new LwjglApplication(new WebCheckers(), config);
	}
}
