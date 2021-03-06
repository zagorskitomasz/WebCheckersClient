package com.zagorskidev.webcheckers.client.controller;

import com.badlogic.gdx.Gdx;
import com.zagorskidev.webcheckers.client.WebCheckers;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.Color;
import com.zagorskidev.webcheckers.client.enums.GameMsg;
import com.zagorskidev.webcheckers.client.enums.LobbyMsg;
import com.zagorskidev.webcheckers.client.enums.MsgCode;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.model.CheckersModel;
import com.zagorskidev.webcheckers.client.model.Model;
import com.zagorskidev.webcheckers.client.model.domain.SimpleChecker;
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
	
	private boolean active;
	
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
		
		if(model.isWaiting()) {
			waitForSecondPlayer();
		}
		
		Position clickedField = recognizeField(xClick, yClick);
		
		if(clickedField != null && active && isValidField(clickedField))
			return createFieldMessage(clickedField);
		
		ButtonType clickedButton = recognizeButton(xClick, yClick);
		
		if(clickedButton != null)
			return executeButtonMessage(clickedButton);
		
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
		
		int x = (xClick - Sizes.BOARD_OFFSET) * Sizes.FIELD_NUMBER / (Sizes.BOARD_WIDTH - 2 * Sizes.BOARD_OFFSET);
		int y = (yClick - (int)(Sizes.GAME_HEIGHT - Sizes.BOARD_HEIGHT - (Sizes.GAME_HEIGHT * 1 / 7)) - Sizes.BOARD_OFFSET) / Sizes.FIELD_HEIGHT;

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
	
	private Message createInvertMessage() {
		
		GameID gameID = model.getGameID();
		Message message = new Message(MsgCode.INVERT, gameID, (String[])null);
		
		return message;
	}
	
	private ButtonType recognizeButton(int xClick, int yClick) {
		
		return model.recognizeClickedButton(xClick, yClick);
	}
	
	private Message executeButtonMessage(ButtonType button) {
		
		switch(button) {
		case CREATE:
			if(model.isInLobby())
				requester.executeMessage(MsgCode.CREATE_GAME);
			return null;
		case JOIN:
			if(model.isInLobby())
				requester.executeMessage(MsgCode.JOIN_GAME);
			return null;
		case EXIT:
			if(model.isInLobby())
				WebCheckers.exit();
			return null;
		case INVERT:
			if(model.isDuringGame())
				return createInvertMessage();
			return null;
		default:
			return null;
		}
	}

	@Override
	public void executeMessageAction(Message message) {
		
		try {
			Gdx.app.postRunnable(new Runnable() {
				public void run() {
					execute(message);
				}
			});
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void execute(Message message) {
		
		switch(message.CODE) {
		case GAME_CREATED:
			waitForSecondPlayer();
			break;
		case GAME_STARTED:
			startGame(message.gameID, message.ARGS[0]);
			break;
		case GAME_NOT_EXIST:
			notExist();
			break;
		case GAME_EXISTS:
			gameExists();
			break;
		case GAME_FULL:
			gameFull();
			break;
		case YOUR_MOVE:
			setActive(true);
			break;
		case CHECKER_OFF_FIELD:
			clearFields(message.ARGS);
			break;
		case CHECKER_ON_FIELD:
			addCheckers(message.ARGS);
			break;
		case CHECKER_SELECTED:
			selectChecker(message.ARGS[0]);
			break;
		case CHECKER_TO_KILL:
			selectToKill(message.ARGS);
			break;
		case INVALID_MOVE:
			break;
		case YOU_WON:
			won();
			break;
		case YOU_LOST:
			lost();
			break;
		case DRAW:
			draw();
			break;
		case TIME_OUT:
			connectionTimedOut();
			break;
		case DISCONNECTED:
			disconnected();
			break;
		case PLAYER_DISCONNECTED:
			playerDisconnected(message.ARGS[0]);
			break;
		case CONNECTED:
			connected();
			break;
		case INVERTED:
			invert();
			break;
		case INVERT_REQUEST:
			invertRequest();
			break;
		case DEACTIVATE:
			setActive(false);
			break;
		default:
			break;
		}
	}
	
	private void waitForSecondPlayer() {
		
		model.createGame();
	}
	
	private void startGame(GameID gameID, String colorString) {
		
		model.setGameID(gameID);
		model.createGame();
		
		Color color = "w".equals(colorString) ? Color.WHITE : Color.BLACK;
		
		model.startGame(color);
	}
	
	private void notExist() {
		model.setLobbyMsg(LobbyMsg.GAME_NOT_EXIST);
	}
	
	private void gameExists() {
		model.setLobbyMsg(LobbyMsg.GAME_EXISTS);
	}
	
	private void gameFull() {
		model.setLobbyMsg(LobbyMsg.GAME_FULL);
	}
	
	private void setActive(boolean active) {
		this.active = active;
		if(active)
			model.setLabel(GameMsg.YOUR_MOVE);
		else
			model.setLabel(null);
	}
	
	private void clearFields(String[] fields) {
		
		for(String field : fields) {
			model.clear(Position.parse(field));
		}
	}
	
	private void addCheckers(String[] checkers) {
		
		for(String field : checkers) {
			SimpleChecker checker = SimpleChecker.parse(field);
			model.addChecker(checker.position, checker.type, checker.promotion);
		}
		model.setLabel(null);
	}
	
	private void selectChecker(String field) {
		model.clearSelection();
		model.selectChecker(Position.parse(field));
	}
	
	private void selectToKill(String[] fields) {
		for(String field : fields)
			model.selectCheckerToKill(Position.parse(field));
	}
	
	private void won() {
		model.gameOver(GameMsg.WON);
	}
	
	private void lost() {
		model.gameOver(GameMsg.LOST);
	}
	
	private void draw() {
		model.gameOver(GameMsg.DRAW);
	}
	
	private void connectionTimedOut() {
		model.serverDisconnected();
	}
	
	private void disconnected() {
		model.serverDisconnected();
	}
	
	private void playerDisconnected(String color) {
		model.playerDisconnected();
	}
	
	private void connected() {
		model.connected();
	}
	
	private void invert() {
		model.invert();
	}
	
	private void invertRequest() {
		model.invertRequest();
	}
}
