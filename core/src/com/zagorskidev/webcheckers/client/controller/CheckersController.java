package com.zagorskidev.webcheckers.client.controller;

import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.model.CheckersModel;
import com.zagorskidev.webcheckers.client.model.Model;

/**
 * Straight implementation of Controller.
 * @author tomek
 *
 */
public class CheckersController implements Controller{

	private Model model;
	
	public CheckersController() {
		model = CheckersModel.getInstance();
	}

	@Override
	public Message actionToMessage(int xClick, int yClick) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeMessageAction(Message message) {
		// TODO Auto-generated method stub
		
	}
}
