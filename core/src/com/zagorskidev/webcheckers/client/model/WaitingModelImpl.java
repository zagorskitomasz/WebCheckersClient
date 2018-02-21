package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class WaitingModelImpl implements WaitingModel{
	private Stage stage;
	
	public WaitingModelImpl(Stage stage) {
		
		this.stage = stage;
		stage.clear();
		
		createWaitingLabel();
	}
	
	private void createWaitingLabel() {
		
		createLabel("Waiting for \nsecond player...", Color.GREEN, 0, Sizes.GAME_HEIGHT - 250, 3);
	}
	
	private void createLabel(String text, Color color, float moveLeft, float y, float fontSize) {
		
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
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
