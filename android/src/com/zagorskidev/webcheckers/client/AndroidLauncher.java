package com.zagorskidev.webcheckers.client;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.czyzby.websocket.CommonWebSockets;
import com.zagorskidev.webcheckers.client.WebCheckers;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CommonWebSockets.initiate();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new WebCheckers(), config);
	}
}
