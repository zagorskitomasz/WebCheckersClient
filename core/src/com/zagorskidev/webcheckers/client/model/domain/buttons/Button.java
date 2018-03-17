package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public abstract class Button implements Drawable{

	protected int xPos;
	protected int yPos;
	
	protected Stage stage;
	protected ShapeRenderer renderer;
	
	protected Button(Stage stage, ShapeRenderer renderer) {
	
		this.stage = stage;
		this.renderer = renderer;
	}
	
	protected void initialize(int xPos, int yPos) {
		
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	protected void createLabel(String text, Color color, float moveLeft, float y, float fontSize) {
		
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();

		Label label;
		label = new Label(text,labelStyle);
		label.setFontScale(fontSize, fontSize);
		label.setColor(color);
		label.setPosition(Sizes.GAME_WIDTH / 2 - fontSize * label.getWidth() / 2 - moveLeft, y);
		
		stage.addActor(label);
	}
	
	@Override
	public void draw() {
		
		renderer.setColor(Color.YELLOW);
		renderer.begin(ShapeType.Filled);
		renderer.rect(xPos, yPos, Sizes.BUTTON_SIZE_X, Sizes.BUTTON_SIZE_Y);
		renderer.end();
	}
	
	public boolean wasClicked(int xClick, int yClick) {
		
		return xClick >= xPos && 
				xClick <= xPos + Sizes.BUTTON_SIZE_X &&
				yClick <= Sizes.GAME_HEIGHT - yPos && 
				yClick >= Sizes.GAME_HEIGHT - (yPos + Sizes.BUTTON_SIZE_Y);
	}
}
