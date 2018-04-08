package com.zagorskidev.webcheckers.client.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.czyzby.websocket.CommonWebSockets;
import com.zagorskidev.webcheckers.client.WebCheckers;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		CommonWebSockets.initiate();
		createApplication();
	}

	private static Application createApplication() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Web Checkers";
		config.useGL30 = true;
		config.height = 700;
		config.width = 400;
		
		return new LwjglApplication(new WebCheckers(), config);
	}
}
