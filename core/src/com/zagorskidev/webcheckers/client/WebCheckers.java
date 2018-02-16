package com.zagorskidev.webcheckers.client;

import com.badlogic.gdx.ApplicationAdapter;
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
	
	@Override
	public void create () {
		initializeGame();
		initializeMessagesThread();
	}
	
	private void initializeGame() {
		gameManager = new CheckersManager();
		gameModel = CheckersModel.getInstance();
	}

	private void initializeMessagesThread() {
		
		Messager messagesDispatcher = prepareMessager();
		
		Thread messagesDispatcherThread = new Thread(messagesDispatcher);
		messagesDispatcherThread.start();
	}

	private Messager prepareMessager() {
		
		Messager messager = new CheckersMessager();
		messager.registerMessagesConsumer(gameManager);
		gameManager.registerMessagesSender(messager);
		
		return messager;
	}

	@Override
	public void render () {
		gameModel.draw();
	}
}
