package com.zagorskidev.webcheckers.client.enums;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zagorskidev.webcheckers.client.graphics.MediaContainer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public enum LobbyMsg {

	GAME_EXISTS(Sprites.GAME_EXISTS),
	GAME_FULL(Sprites.GAME_FULL),
	GAME_NOT_EXIST(Sprites.GAME_NOT_EXIST);
	
	private MediaContainer<Sprite> sprite;
	
	private LobbyMsg(MediaContainer<Sprite> sprite) {
		this.sprite = sprite;
	}
	
	public MediaContainer<Sprite> getSprite() {
		return sprite;
	}
}
