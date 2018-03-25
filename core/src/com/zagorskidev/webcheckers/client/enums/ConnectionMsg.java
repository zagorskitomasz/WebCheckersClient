package com.zagorskidev.webcheckers.client.enums;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zagorskidev.webcheckers.client.graphics.MediaContainer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public enum ConnectionMsg {

	CONNECTION_OK(Sprites.CONNECTION_OK),
	CONNECTION_NOT(Sprites.CONNECTION_NOT);
	
	private MediaContainer<Sprite> sprite;
	
	private ConnectionMsg(MediaContainer<Sprite> sprite) {
		this.sprite = sprite;
	}
	
	public MediaContainer<Sprite> getSprite() {
		return sprite;
	}
}
