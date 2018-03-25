package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.graphics.Drawer;

public abstract class Button implements Drawable{

	protected Drawer drawer;
	
	protected int yPos;
	protected int xFrom;
	protected int xTo;
	protected int yFrom;
	protected int yTo;
	
	private boolean highlighted;
	
	protected Button() {
	
		drawer = Drawer.getInstance();
		
		highlighted = false;
	}
	
	protected void initialize(int yPos) {
		
		this.yPos = yPos;
		
		xFrom = (Sizes.GAME_WIDTH - Sizes.BUTTON_SIZE_X) / 2;
		xTo = xFrom + Sizes.BUTTON_SIZE_X;
		yFrom = yPos + (Sizes.BUTTON_PANEL_SIZE_Y - Sizes.BUTTON_SIZE_Y) /2;
		yTo = yFrom + Sizes.BUTTON_SIZE_Y;
	}
	
	public boolean wasClicked(int xClick, int yClick) {
		
		return xClick >= xFrom && 
				xClick <= xTo &&
				yClick <= Sizes.GAME_HEIGHT - yFrom && 
				yClick >= Sizes.GAME_HEIGHT - yTo;
	}

	public void highlight(boolean highlight) {
		highlighted = highlight;
	}
	
	public boolean isHighlighted() {
		return highlighted;
	}
	
	@Override
	public abstract void draw();
}
