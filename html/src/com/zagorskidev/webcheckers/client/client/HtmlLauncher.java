package com.zagorskidev.webcheckers.client.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.github.czyzby.websocket.GwtWebSockets;
import com.zagorskidev.webcheckers.client.WebCheckers;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class HtmlLauncher extends GwtApplication {

	@Override
	public GwtApplicationConfiguration getConfig() {
		return new GwtApplicationConfiguration(Sizes.GAME_WIDTH, Sizes.GAME_HEIGHT);
	}

	@Override
	public ApplicationListener createApplicationListener() {

		GwtWebSockets.initiate();
		return new WebCheckers();
	}
}