package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.Color;
import com.zagorskidev.webcheckers.client.enums.GameMsg;
import com.zagorskidev.webcheckers.client.enums.LobbyMsg;
import com.zagorskidev.webcheckers.client.enums.ModelType;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.util.GameID;
import com.zagorskidev.webcheckers.client.util.Position;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class CheckersModel implements Model {

	private static Model model;
	
	public static Model getInstance() {
		if(model == null)
			model = new CheckersModel();
		
		return model;
	}
	
	private Drawable view;
	
	private LobbyModel lobbyModel;
	private GameModel gameModel;
	private WaitingModel waitingModel;
	
	private ModelType modelType;
	
	private boolean inverted;
	private GameID gameID;
	private boolean gameOver;
	private boolean waitingForPlayer;
	
	private CheckersModel() {
		createLobby();
	}
	
	@Override
	public void createGame() {
		
		waitingForPlayer = false;
		waitingModel = new WaitingModelImpl();
		setModel(ModelType.WAITING);
	}
	
	@Override
	public void startGame(Color color) {
		
		inverted = color != Color.BLACK;
		gameModel = new GameModelImpl(inverted);
		setModel(ModelType.GAME);
	}
	
	@Override
	public void createLobby() {
		
		lobbyModel = new LobbyModelImpl();
		setModel(ModelType.LOBBY);
	}
	
	@Override
	public void setGameID(GameID gameID) {
		this.gameID = gameID;
	}
	
	@Override
	public GameID getGameID() {
		return gameID;
	}
	
	@Override 
	public boolean isInverted(){
		return inverted;
	}
	
	private void setModel(ModelType modelType) {

		gameOver = false;
		this.modelType = modelType;
		
		switch(modelType) {
		case GAME:
			view = gameModel;
			break;
		case LOBBY:
			view = lobbyModel;
			break;
		case WAITING:
			view = waitingModel;
			break;
		default:
			break;
		}
	}

	@Override
	public void addChecker(Position position, Checker checker, Promotion promotion) {
		
		gameModel.addChecker(position, checker, promotion);
	}

	@Override
	public void clear(Position position) {
		
		gameModel.clear(position);
	}

	@Override
	public void selectChecker(Position position) {
		
		gameModel.selectChecker(position);
	}

	@Override
	public void clearSelection() {
		
		gameModel.clearSelection();
	}

	@Override
	public void selectCheckerToKill(Position position) {
		
		gameModel.selectCheckerToKill(position);
	}
	
	@Override
	public void setLabel(GameMsg msg) {
		 gameModel.setLabel(msg);
	}
	
	@Override
	public void setLobbyMsg(LobbyMsg msg) {
		 lobbyModel.setLobbyMsg(msg);
	}
	
	@Override
	public void gameOver(GameMsg msg) {
		gameOver = true;
		setLabel(msg);
	}
	
	@Override
	public boolean isGameOver() {
		return gameOver;
	}
	
	@Override
	public boolean isDuringGame() {
		return modelType == ModelType.GAME;
	}
	
	@Override
	public boolean isInLobby() {
		return modelType == ModelType.LOBBY;
	}

	@Override
	public ButtonType recognizeClickedButton(int xClick, int yClick) {
		
		if(isInLobby())
			return lobbyModel.recognizeClickedButton(xClick, yClick);
		else if(isDuringGame())
			return gameModel.recognizeClickedButton(xClick,yClick);
		
		return null;
	}
	
	@Override
	public void playerDisconnected() {
		
		if(isDuringGame()) {
			gameOver(GameMsg.OPP_DISCONNECTED);
			return;
		}
		else if(!isInLobby())
			createLobby();
		
		lobbyModel.connected();
		lobbyModel.setLobbyMsg(null);
	}
	
	@Override
	public void serverDisconnected() {
		
		if(isDuringGame()) {
			gameOver(GameMsg.SERVER_DISCONNECTED);
			return;
		}
		else if(!isInLobby())
			createLobby();
		
		lobbyModel.disconnected();
		lobbyModel.setLobbyMsg(null);
	}
	
	@Override
	public void connected() {
		
		if(isInLobby())		
			lobbyModel.connected();
	}
	
	@Override
	public void disconnected() {
		
		if(isInLobby())		
			lobbyModel.disconnected();
	}
	
	public boolean isWaiting() {
		return waitingForPlayer;
	}
	
	@Override
	public void invert(){
		if(isDuringGame()) {
			inverted = !inverted;
			gameModel.invert(inverted);
		}
	}
	
	@Override
	public void invertRequest(){
		if(isDuringGame()) {
			gameModel.invertRequest();
		}
	}
	
	@Override
	public void draw() {
		
		if(view != null) {
			view.draw();
		}
	}

	@Override
	public void invert(boolean inverted){ }
}
