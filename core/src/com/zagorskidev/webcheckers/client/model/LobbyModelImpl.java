package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.ConnectionMsg;
import com.zagorskidev.webcheckers.client.enums.LobbyMsg;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;
import com.zagorskidev.webcheckers.client.model.domain.buttons.Button;
import com.zagorskidev.webcheckers.client.model.domain.buttons.CreateButton;
import com.zagorskidev.webcheckers.client.model.domain.buttons.JoinButton;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class LobbyModelImpl implements LobbyModel {
	
	private Button createButton;
	private Button joinButton;
	
	private LobbyMsg lobbyMsg;
	private ConnectionMsg connectionMsg;
	
	private Drawer drawer;
	
	public LobbyModelImpl() {
		
		drawer = Drawer.getInstance();
		createButtons();
	}
	
	private void createButtons() {
		
		createButton = new CreateButton();
		joinButton = new JoinButton();
	}
	
	@Override
	public void draw() {
		
		drawer.draw(Sprites.BACKGROUND, 0, 0);
		drawer.draw(Sprites.TITLE_PL, 0, Sizes.GAME_HEIGHT - 320);
		createButton.draw();
		joinButton.draw();
		
		if(lobbyMsg != null)
			drawer.draw(lobbyMsg.getSprite(), 0, 80);
		if(connectionMsg != null)
			drawer.draw(connectionMsg.getSprite(), 0, 10);
	}

	@Override
	public ButtonType recognizeClickedButton(int xClick, int yClick) {
		
		if(createButton.wasClicked(xClick, yClick))
			return ButtonType.CREATE;
		
		if(joinButton.wasClicked(xClick, yClick))
			return ButtonType.JOIN;
		
		return null;
	}

	@Override
	public void setLobbyMsg(LobbyMsg msg) {
		
		lobbyMsg = msg;
	}

	@Override
	public void disconnected() {
		
		connectionMsg = ConnectionMsg.CONNECTION_NOT;
	}

	@Override
	public void connected() {
		
		connectionMsg = ConnectionMsg.CONNECTION_OK;
	}
}
