package com.zagorskidev.webcheckers.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.TimeUtils;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.manager.CheckersManager;
import com.zagorskidev.webcheckers.client.manager.GameManager;
import com.zagorskidev.webcheckers.client.messages.CheckersMessager;
import com.zagorskidev.webcheckers.client.messages.Messager;
import com.zagorskidev.webcheckers.client.model.CheckersModel;

/**
 * Libgdx core application.
 * @author tomek
 *
 */
public class WebCheckers extends ApplicationAdapter {
	
	private GameManager gameManager;
	private Drawable gameModel;
	private Messager messagesDispatcher;
	
	private long lastConnectionCheck;
		
	@Override
	public void create () {

		initializeGame();
		initializeMessagesThread();
		
		lastConnectionCheck = TimeUtils.millis() - 5000;
	}
	
	private void initializeGame() {
		gameManager = new CheckersManager();
		gameModel = CheckersModel.getInstance();
	}

	private void initializeMessagesThread() {
		
		messagesDispatcher = prepareMessager();
		Gdx.app.postRunnable(messagesDispatcher);
	}

	private Messager prepareMessager() {
		
		Messager messager = new CheckersMessager();
		messager.registerMessagesConsumer(gameManager);
		gameManager.registerMessagesSender(messager);
		
		return messager;
	}

	@Override
	public void render () {
		handleConnection();
		clear();
		gameModel.draw();
	}
	
	private void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
		gameModel.dispose();
	}
	
	private void handleConnection() {
		
		long currentTime = TimeUtils.millis();
		
		if(lastConnectionCheck + 5000 < currentTime) {
			lastConnectionCheck = currentTime;
			messagesDispatcher.tryConnect();
		}
	}
}
