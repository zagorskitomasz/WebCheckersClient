package com.zagorskidev.webcheckers.client.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.zagorskidev.webcheckers.client.controller.CheckersController;
import com.zagorskidev.webcheckers.client.controller.Controller;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.messages.Messager;

/**
 * Straight implementation of GameManager.
 * @author tomek
 *
 */
public class CheckersManager extends InputAdapter implements GameManager {

	private Controller controller;
	private Messager messager;
	
	public CheckersManager() {
		
		controller = new CheckersController();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void registerMessagesSender(Messager messager) {
		this.messager = messager;
	}

	@Override
	public void consume(Message message) {
		controller.executeMessageAction(message);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		Message message = controller.actionToMessage(screenX, screenY);
		if(message == null)
			return false;
		
		messager.sendMessage(message);
		return true;
	}
}
