package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.Color;
import com.zagorskidev.webcheckers.client.enums.ModelType;
import com.zagorskidev.webcheckers.client.util.GameID;

/**
 * Facade for specialized models which can be transparently switched by controllers.
 * @author tomek
 *
 */
public interface Model extends LobbyModel, GameModel{

	public void setModel(ModelType modelType);
	public void createGame(Color color);
	public boolean isInverted();
	public void setGameID(GameID gameID);
	public GameID getGameID();
}
