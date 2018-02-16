package com.zagorskidev.webcheckers.client.controller;

import com.zagorskidev.webcheckers.client.enums.ModelType;
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
	private ModelType modelType;
	
	public CheckersController() {
		model = CheckersModel.getInstance();
	}
	
	@Override
	public boolean setModelType(ModelType modelType) {
		
		if(this.modelType == modelType)
			return false;
		
		this.modelType = modelType;
		model.setModel(modelType);
		return true;
	}
	
	@Override
	public ModelType getModelType() {
		return modelType;
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
