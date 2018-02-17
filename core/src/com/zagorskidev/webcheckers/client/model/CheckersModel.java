package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zagorskidev.webcheckers.client.draw.Drawable;
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
	
	private LobbyModel lobbyModel;
	private GameModel gameModel;
	
	private boolean inverted;
	private GameID gameID;
	
	private CheckersModel() {
		lobbyModel = new LobbyModelImpl();
		
		view = lobbyModel;
	}
	
	@Override
	public void createGame(Color color) {
		
		inverted = color == Color.WHITE ? false : true;
		gameModel = new GameModelImpl(inverted);
		setModel(ModelType.GAME);
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
	
	@Override
	public void setModel(ModelType modelType) {
		
		switch(modelType) {
		case GAME:
			view = gameModel;
			break;
		case LOBBY:
			view = lobbyModel;
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
	public void unselectChecker(Position position) {
		
		gameModel.unselectChecker(position);
	}

	@Override
	public void selectCheckerToKill(Position position) {
		
		gameModel.selectCheckerToKill(position);
	}
	
	@Override
	public void draw(ShapeRenderer renderer) {
		
		if(view != null)
			view.draw(renderer);
	}
}
