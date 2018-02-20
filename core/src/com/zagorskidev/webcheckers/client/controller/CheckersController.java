package com.zagorskidev.webcheckers.client.controller;

import com.zagorskidev.webcheckers.client.enums.Button;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.model.CheckersModel;
import com.zagorskidev.webcheckers.client.model.LobbyModelImpl;
import com.zagorskidev.webcheckers.client.model.Model;
import com.zagorskidev.webcheckers.client.util.Position;

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
		
		if(model.isGameOver()) {
			model.createLobby();
			return null;
		}
		
		Position clickedField = recognizeField(xClick, yClick);
		
		if(clickedField != null)
			return createFieldMessage(clickedField);
		
		Button clickedButton = recognizeButton(xClick, yClick);
		
		if(clickedButton != null)
			createButtonMessage(clickedButton);
		
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
		
		int x = (xClick - Sizes.BOARD_OFFSET.getValue()) / Sizes.FIELD_SIZE.getValue();
		int y = (yClick - Sizes.BOARD_OFFSET.getValue()) / Sizes.FIELD_SIZE.getValue();
		
		if(xClick < Sizes.BOARD_OFFSET.getValue())
			x = -1;
		
		if(yClick < Sizes.BOARD_OFFSET.getValue())
			y = -1;
		
		return new Position(x, y);
	}
	
	private Position validatePosition(Position position) {
		
		if (position.X >= 0 && position.X < Sizes.FIELD_NUMBER.getValue() 
				&& position.Y >= 0 && position.Y < Sizes.FIELD_NUMBER.getValue())
			return position;
		else
			return Position.NOWHERE;
	}
	
	private Position invertPosition(Position position) {
		
		int maxFieldIndex = Sizes.FIELD_NUMBER.getValue() - 1;
		
		int invertedX = maxFieldIndex - position.X;
		int invertedY = maxFieldIndex - position.Y;
		
		return new Position(invertedX, invertedY);
	}
	
	private Message createFieldMessage(Position clickedField) {
		System.out.println(clickedField);
		return null;
		//TODO
	}
	
	private Button recognizeButton(int xClick, int yClick) {
		
		if(!model.isInLobby())
			return null;
		
		if(xClick >= LobbyModelImpl.btnXPos && xClick <= LobbyModelImpl.btnXPos + LobbyModelImpl.btnXSize
				&& yClick <= Sizes.GAME_HEIGHT.getValue() - LobbyModelImpl.btnCreateYPos && yClick >= Sizes.GAME_HEIGHT.getValue() - (LobbyModelImpl.btnCreateYPos + LobbyModelImpl.btnYSize))
			return Button.CREATE;
		
		if(xClick >= LobbyModelImpl.btnXPos && xClick <= LobbyModelImpl.btnXPos + LobbyModelImpl.btnXSize
				&& yClick <= Sizes.GAME_HEIGHT.getValue() - LobbyModelImpl.btnJoinYPos && yClick >= Sizes.GAME_HEIGHT.getValue() - (LobbyModelImpl.btnJoinYPos + LobbyModelImpl.btnYSize))
			return Button.JOIN;
		
		return null;
	}
	
	private Message createButtonMessage(Button button) {
		System.out.println(button);
		return null;
		//TODO
	}

	@Override
	public void executeMessageAction(Message message) {
		// TODO Auto-generated method stub
		
	}
}
