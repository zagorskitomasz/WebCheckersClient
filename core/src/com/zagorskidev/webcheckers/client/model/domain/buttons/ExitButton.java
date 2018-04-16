package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class ExitButton extends Button {
	
	public ExitButton () {
		
		super();
		initialize((int)(Sizes.GAME_HEIGHT * 1.1 / 7));
	}
	
	@Override
	public void draw() {
		drawer.draw(Sprites.BTN_EXIT_PL, 0, yPos);
	}
}
