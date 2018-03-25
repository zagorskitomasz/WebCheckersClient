package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.Color;
import com.zagorskidev.webcheckers.client.enums.GameMsg;
import com.zagorskidev.webcheckers.client.util.GameID;

/**
 * Facade for specialized models which can be transparently switched by controllers.
 * @author tomek
 *
 */
public interface Model extends LobbyModel, GameModel, WaitingModel{

	public void createLobby();
	public void createGame();
	public void startGame(Color color);
	public void setGameID(GameID gameID);
	public GameID getGameID();
	public boolean isGameOver();
	public boolean isDuringGame();
	public boolean isInLobby();
	public boolean isWaiting();
	public boolean isInverted();
	public void playerDisconnected();
	public void serverDisconnected();
	public void gameOver(GameMsg msg);
	public void invert();
}
