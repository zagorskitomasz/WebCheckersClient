package com.zagorskidev.webcheckers.client.enums;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zagorskidev.webcheckers.client.graphics.MediaContainer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public enum GameMsg {

	DRAW(Sprites.DRAW),
	LOST(Sprites.LOST),
	OPP_DISCONNECTED(Sprites.OPP_DISCONNECTED),
	SERVER_DISCONNECTED(Sprites.SERVER_DISCONNECTED),
	WON(Sprites.WON),
	YOUR_MOVE(Sprites.YOUR_MOVE);
	
	private MediaContainer<Sprite> sprite;
	
	private GameMsg(MediaContainer<Sprite> sprite) {
		this.sprite = sprite;
	}
	
	public MediaContainer<Sprite> getSprite() {
		return sprite;
	}
}
