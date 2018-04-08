package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class JoinButton extends Button {
	
	public JoinButton () {
		
		super();
		initialize(Sizes.GAME_HEIGHT - (int)(Sizes.GAME_HEIGHT * 5.3 / 7 * Sizes.RESIZE_FACTOR));
	}
	
	@Override
	public void draw() {
		drawer.draw(Sprites.BTN_JOIN_PL, 0, yPos);
	}
}
