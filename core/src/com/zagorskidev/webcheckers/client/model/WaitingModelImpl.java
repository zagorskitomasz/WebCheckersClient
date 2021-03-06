package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class WaitingModelImpl implements WaitingModel{
	
	private Drawer drawer;
	
	public WaitingModelImpl() {
		drawer = Drawer.getInstance();
	}
	
	@Override
	public void draw() {
		drawer.draw(Sprites.BACKGROUND, 0, 0);
		drawer.draw(Sprites.TITLE_PL, 0, (int)(Sizes.GAME_HEIGHT - Sizes.TITLE_HEIGHT));
		drawer.draw(Sprites.WAITING, 0, (int)(Sizes.GAME_HEIGHT * 3 / 7));
	}
}
