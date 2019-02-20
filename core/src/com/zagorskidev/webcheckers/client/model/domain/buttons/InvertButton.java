package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class InvertButton extends Button {
	
	public InvertButton () {
		
		super();
		initialize((int)(20 * Sizes.WIDTH_FACTOR));
	}
	
	@Override
	protected void initialize(int yPos) {
		
		super.initialize(yPos);
		
		xFrom = (int)(20 * Sizes.WIDTH_FACTOR) + (Sizes.SM_BUTTON_PANEL_SIZE - Sizes.SM_BUTTON_SIZE) / 2;
		xTo = xFrom + Sizes.SM_BUTTON_SIZE;
		yFrom = yPos + (Sizes.SM_BUTTON_PANEL_SIZE - Sizes.SM_BUTTON_SIZE) /2;
		yTo = yFrom + Sizes.SM_BUTTON_SIZE;
	}
	
	@Override
	public void draw() {
		
		if(isHighlighted())
			drawHighlight();
		else
			drawButton();
	}

	private void drawButton() {
		
		drawer.draw(Sprites.BTN_INVERT_OFF, (int)(20 * Sizes.WIDTH_FACTOR), yPos);
	}

	private void drawHighlight() {
		
		drawer.draw(Sprites.BTN_INVERT_ON, (int)(20 * Sizes.WIDTH_FACTOR), yPos);
	}
}
