package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ModelType;

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
	
	private CheckersModel() {
		lobbyModel = new LobbyModelImpl();
		gameModel = new GameModelImpl();
	}
	
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
	public void draw() {
		view.draw();
	}
}
