package com.zagorskidev.webcheckers.client.controller;

import java.util.concurrent.Semaphore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.zagorskidev.webcheckers.client.enums.MsgCode;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.messages.Messager;
import com.zagorskidev.webcheckers.client.messages.MessagesProducer;
import com.zagorskidev.webcheckers.client.util.GameID;

public class GameRequester implements TextInputListener, MessagesProducer {

	private static final int MAX_PARALLEL_REQUESTS = 1;
	
	private Messager messager;
	private Semaphore semaphore;
	
	private String name;
	private String password;
	private MsgCode code;
	
	public GameRequester() {
		semaphore = new Semaphore(MAX_PARALLEL_REQUESTS);
	}

	@Override
	public void registerMessagesSender(Messager messager) {
		this.messager = messager;
	}
	
	public void executeMessage(MsgCode code) {
		
		if(semaphore.tryAcquire()) {
			init(code);
			addName();
		}
	}
	
	private void init(MsgCode code) {
		name = null;
		password = null;
		this.code = code;
	}
	
	private void addName() {
		Gdx.input.getTextInput(this, "Enter game name", "", "Enter name for game");
	}
	
	private void addPassword() {
		Gdx.input.getTextInput(this, "Enter password", "", "Enter password for game");
	}
	
	private boolean validate(String text) {
		
		if(text == null || "".equals(text.trim()))
			return false;
		
		return true;
	}
	
	@Override
	public void input(String text) {
		
		if(name == null) {
			if(validate(text)) {
				name = text;
				addPassword();
			}
		}
		else {
			if(validate(text)) { 
				password = text;
				processInput();
			}
		}
	}
		
	private void processInput() {
		
		GameID gameID = buildID();
		
		semaphore.release();
		
		Message message = new Message(code, gameID, (String[])null);
		messager.sendMessage(message);
	}
	
	private GameID buildID() {
		return new GameID(name, password);
	}

	@Override
	public void canceled() {
		
		name = null;
		password = null;
		code = null;
		
		semaphore.release();
	}
}
