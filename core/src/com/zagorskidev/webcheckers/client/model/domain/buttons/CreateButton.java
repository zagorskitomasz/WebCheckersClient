package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class CreateButton extends Button {
	
	public CreateButton () {
		
		super();
		initialize((int)(Sizes.GAME_HEIGHT * 3.7 / 7));
	}
	
	@Override
	public void draw() {
		drawer.draw(Sprites.BTN_CREATE_PL, 0, yPos);
	}
}
