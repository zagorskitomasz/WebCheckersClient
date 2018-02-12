package com.zagorskidev.webcheckers.client;

import com.badlogic.gdx.ApplicationAdapter;

public class WebCheckers extends ApplicationAdapter {
	
	private Drawable game;
	
	@Override
	public void create () {
		initializeGame();
		initializeMessagesThread();
	}
	
	private void initializeGame() {
		game = new GameManager();
	}

	private void initializeMessagesThread() {
		
		Runnable messagesDispatcher = prepareMessagesDispatcher();
		
		Thread messagesDispatcherThread = new Thread(messagesDispatcher);
		messagesDispatcherThread.start();
	}

	private Runnable prepareMessagesDispatcher() {
		
		Runnable messagesDispatcher;
		TasksDispatcher tasksDispatcher = (TasksDispatcher)game;
		
		return new MessagesDispatcher(tasksDispatcher);
	}

	@Override
	public void render () {
		game.draw();
	}
}
