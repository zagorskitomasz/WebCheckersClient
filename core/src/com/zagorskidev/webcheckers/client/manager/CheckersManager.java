package com.zagorskidev.webcheckers.client.manager;

import com.badlogic.gdx.InputAdapter;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.messages.Messager;

public class CheckersManager extends InputAdapter implements GameManager {

	//changeable drawable game interfaces
	//msg sender
	//controllers - consumer invokes their methods, modifies drawable domain objects
	//constructor registering this into Gdx.input
	// handling input -> uses senders
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerMessagesSender(Messager messager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void consume(Message message) {
		// TODO Auto-generated method stub

	}

}
