package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.ConnectionMsg;
import com.zagorskidev.webcheckers.client.enums.LobbyMsg;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;
import com.zagorskidev.webcheckers.client.model.domain.buttons.Button;
import com.zagorskidev.webcheckers.client.model.domain.buttons.CreateButton;
import com.zagorskidev.webcheckers.client.model.domain.buttons.ExitButton;
import com.zagorskidev.webcheckers.client.model.domain.buttons.JoinButton;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class LobbyModelImpl implements LobbyModel {
	
	private Button createButton;
	private Button joinButton;
	private Button exitButton;
	
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
		exitButton = new ExitButton();
	}
	
	@Override
	public void draw() {
		
		drawer.draw(Sprites.BACKGROUND, 0, 0);
		drawer.draw(Sprites.TITLE_PL, 0, (int)(Sizes.GAME_HEIGHT - Sizes.TITLE_HEIGHT));
		createButton.draw();
		joinButton.draw();
		exitButton.draw();
		
		if(lobbyMsg != null)
			drawer.draw(lobbyMsg.getSprite(), 0, (int)(Sizes.GAME_HEIGHT * 2.2 / 7));
		if(connectionMsg != null)
			drawer.draw(connectionMsg.getSprite(), 0, (int)(Sizes.GAME_HEIGHT * 0.3 / 7));
	}

	@Override
	public ButtonType recognizeClickedButton(int xClick, int yClick) {
		
		if(createButton.wasClicked(xClick, yClick))
			return ButtonType.CREATE;
		
		if(joinButton.wasClicked(xClick, yClick))
			return ButtonType.JOIN;

		if(exitButton.wasClicked(xClick, yClick))
			return ButtonType.EXIT;
		
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
