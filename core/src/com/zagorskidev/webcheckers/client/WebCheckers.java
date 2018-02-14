package com.zagorskidev.webcheckers.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.zagorskidev.webcheckers.client.manager.CheckersManager;
import com.zagorskidev.webcheckers.client.manager.GameManager;
import com.zagorskidev.webcheckers.client.messages.CheckersMessager;
import com.zagorskidev.webcheckers.client.messages.Messager;

public class WebCheckers extends ApplicationAdapter {
	
	private GameManager game;
	
	@Override
	public void create () {
		initializeGame();
		initializeMessagesThread();
	}
	
	private void initializeGame() {
		game = new CheckersManager();
	}

	private void initializeMessagesThread() {
		
		Messager messagesDispatcher = prepareMessager();
		
		Thread messagesDispatcherThread = new Thread(messagesDispatcher);
		messagesDispatcherThread.start();
	}

	private Messager prepareMessager() {
		
		Messager messager = new CheckersMessager();
		messager.registerMessagesConsumer(game);
		game.registerMessagesSender(messager);
		
		return messager;
	}

	@Override
	public void render () {
		game.draw();
	}
}
