package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.Color;
import com.zagorskidev.webcheckers.client.util.GameID;

/**
 * Facade for specialized models which can be transparently switched by controllers.
 * @author tomek
 *
 */
public interface Model extends LobbyModel, GameModel{

	public void createGame(Color color);
	public void createLobby();
	public boolean isInverted();
	public void setGameID(GameID gameID);
	public GameID getGameID();
	public void gameOver(String message, com.badlogic.gdx.graphics.Color color);
	public boolean isGameOver();
}
