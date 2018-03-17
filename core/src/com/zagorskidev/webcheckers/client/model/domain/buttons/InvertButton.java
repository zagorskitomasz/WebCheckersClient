package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class InvertButton extends Button {
	
	
	
	public InvertButton (Stage stage, ShapeRenderer renderer) {
		
		super(stage, renderer);
		
		initialize(15, 15);
		createLabel("", Color.BLACK, 0, Sizes.GAME_HEIGHT - 255, 2);
	}
	
	@Override
	public void draw() {
		
		drawButton();
		if(isHighlighted())
			drawHighlight();
	}

	private void drawButton() {
		
		renderer.setColor(Color.BLUE);
		renderer.begin(ShapeType.Filled);
		renderer.rect(xPos, yPos, Sizes.SM_BUTTON_SIZE_X, Sizes.SM_BUTTON_SIZE_Y);
		renderer.end();
	}

	private void drawHighlight() {
		
		renderer.setColor(Color.YELLOW);
		renderer.begin(ShapeType.Filled);
		renderer.rect(xPos + 15, yPos + 15, Sizes.SM_BUTTON_SIZE_X - 30, Sizes.SM_BUTTON_SIZE_Y - 30);
		renderer.end();
	}
	
	public boolean wasClicked(int xClick, int yClick) {
		
		return xClick >= xPos && 
				xClick <= xPos + Sizes.SM_BUTTON_SIZE_X &&
				yClick <= Sizes.GAME_HEIGHT - yPos && 
				yClick >= Sizes.GAME_HEIGHT - (yPos + Sizes.SM_BUTTON_SIZE_Y);
	}
}
