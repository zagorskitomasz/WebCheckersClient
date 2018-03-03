package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.Color;
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
	private Stage stage;
	private ShapeRenderer renderer;
	
	private LobbyModel lobbyModel;
	private GameModel gameModel;
	private WaitingModel waitingModel;
	
	private ModelType modelType;
	
	private boolean inverted;
	private GameID gameID;
	private boolean gameOver;
	
	private CheckersModel() {
		
		Gdx.gl.glLineWidth(4);
		renderer = new ShapeRenderer();
		stage = new Stage(new ScreenViewport());
		
		createLobby();
	}
	
	@Override
	public void createGame() {
		
		waitingModel = new WaitingModelImpl(stage);
		setModel(ModelType.WAITING);
	}
	
	@Override
	public void startGame(Color color) {
		
		inverted = color == Color.BLACK ? false : true;
		gameModel = new GameModelImpl(stage, renderer, inverted);
		setModel(ModelType.GAME);
	}
	
	@Override
	public void createLobby() {
		
		lobbyModel = new LobbyModelImpl(stage, renderer);
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
	public void setLabel(String text, com.badlogic.gdx.graphics.Color color) {
		 gameModel.setLabel(text, color);
	}
	

	
	@Override
	public void setLobbyLabel(String text, com.badlogic.gdx.graphics.Color color) {
		 lobbyModel.setLobbyLabel(text, color);
	}
	
	@Override
	public void gameOver(String message, com.badlogic.gdx.graphics.Color color) {
		gameOver = true;
		setLabel(message, color);
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
		
		if(!isInLobby())
			return null;
		
		return lobbyModel.recognizeClickedButton(xClick, yClick);
	}
	
	@Override
	public void draw() {
		if(view != null)
			view.draw();
	}
	
	@Override
	public void dispose() {
		if(view != null)
			view.dispose();
	}
}
