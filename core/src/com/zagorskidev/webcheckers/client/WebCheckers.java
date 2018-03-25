package com.zagorskidev.webcheckers.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
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
	
	private Drawer drawer;
		
	@Override
	public void create () {

		initializeGame();
		initializeMessager();
	}
	
	private void initializeGame() {
		gameManager = new CheckersManager();
		gameModel = CheckersModel.getInstance();
		drawer = Drawer.getInstance();
	}

	private void initializeMessager() {
		
		messagesDispatcher = new CheckersMessager();
		
		messagesDispatcher.registerMessagesConsumer(gameManager);
		gameManager.registerMessagesSender(messagesDispatcher);
		
		Gdx.app.postRunnable(() -> messagesDispatcher.startWriteReadThreads());
	}

	@Override
	public void render () {
		
		messagesDispatcher.checkConnection();
		
		clear();
		
		drawer.beginFrame();
		gameModel.draw();
		drawer.endFrame();
	}
	
	private void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
		Drawer.getInstance().dispose();
	}
}
