package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.LobbyMsg;

/**
 * Game menu for creating, joining, typing name/password.
 * @author tomek
 *
 */
public interface LobbyModel extends Drawable{

	public ButtonType recognizeClickedButton(int xClick, int yClick);
	public void setLobbyMsg(LobbyMsg msg);
	public void disconnected();
	public void connected();
}
