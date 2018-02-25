package com.zagorskidev.webcheckers.client.controller;

import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.MsgCode;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.model.CheckersModel;
import com.zagorskidev.webcheckers.client.model.Model;
import com.zagorskidev.webcheckers.client.util.GameID;
import com.zagorskidev.webcheckers.client.util.Position;

/**
 * Straight implementation of Controller.
 * @author tomek
 *
 */
public class CheckersController implements Controller{

	private Model model;
	private GameRequester requester;
	
	public CheckersController(GameRequester requester) {
		model = CheckersModel.getInstance();
		this.requester = requester;
	}

	@Override
	public Message actionToMessage(int xClick, int yClick) {
		
		if(model.isGameOver()) {
			model.createLobby();
			return null;
		}
		
		Position clickedField = recognizeField(xClick, yClick);
		
		if(clickedField != null) {
			if(isValidField(clickedField))
				return createFieldMessage(clickedField);
			else
				return null;
		}
		
		ButtonType clickedButton = recognizeButton(xClick, yClick);
		
		if(clickedButton != null)
			executeButtonMessage(clickedButton);
		
		return null;
	}
	
	private Position recognizeField(int xClick, int yClick) {
		
		if(!model.isDuringGame())
			return null;
		
		if(!model.isInverted()) 
			return regularField(xClick, yClick);
		else
			return invertedField(xClick, yClick);
	}
	
	private Position regularField(int xClick, int yClick) {
		
		Position position = calculatePosition(xClick, yClick);
		
		return validatePosition(position);
	}
	
	private Position invertedField(int xClick, int yClick) {
		
		Position position = calculatePosition(xClick, yClick);
		position = invertPosition(position);
		
		return validatePosition(position);
	}
	
	private Position calculatePosition(int xClick, int yClick) {
		
		int x = (xClick - Sizes.BOARD_OFFSET) / Sizes.FIELD_SIZE;
		int y = (yClick - Sizes.BOARD_OFFSET) / Sizes.FIELD_SIZE;
		
		if(xClick < Sizes.BOARD_OFFSET)
			x = -1;
		
		if(yClick < Sizes.BOARD_OFFSET)
			y = -1;
		
		return new Position(x, y);
	}
	
	private Position validatePosition(Position position) {
		
		if (position.X >= 0 && position.X < Sizes.FIELD_NUMBER 
				&& position.Y >= 0 && position.Y < Sizes.FIELD_NUMBER)
			return position;
		else
			return Position.NOWHERE;
	}
	
	private Position invertPosition(Position position) {
		
		int maxFieldIndex = Sizes.FIELD_NUMBER - 1;
		
		int invertedX = maxFieldIndex - position.X;
		int invertedY = maxFieldIndex - position.Y;
		
		return new Position(invertedX, invertedY);
	}
	
	private boolean isValidField(Position position) {
		
		return position != Position.NOWHERE;
	}
	
	private Message createFieldMessage(Position clickedField) {
		
		GameID gameID = model.getGameID();
		Message message = new Message(MsgCode.CLICKED_FIELD, gameID, clickedField.toString());
		
		return message;
	}
	
	private ButtonType recognizeButton(int xClick, int yClick) {
		
		return model.recognizeClickedButton(xClick, yClick);
	}
	
	private Message executeButtonMessage(ButtonType button) {
		
		switch(button) {
		case CREATE:
			requester.executeMessage(MsgCode.CREATE_GAME);
		case JOIN:
			requester.executeMessage(MsgCode.JOIN_GAME);
		default:
			return null;
		}
	}

	@Override
	public void executeMessageAction(Message message) {
		// TODO Auto-generated method stub
		
	}
}
