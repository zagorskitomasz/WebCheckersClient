package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ButtonType;

/**
 * Game menu for creating, joining, typing name/password.
 * @author tomek
 *
 */
public interface LobbyModel extends Drawable{

	public ButtonType recognizeClickedButton(int xClick, int yClick);
}
