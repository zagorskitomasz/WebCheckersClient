package com.zagorskidev.webcheckers.client.model.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class Background implements Drawable {

	private Stage stage;
	private ShapeRenderer renderer;
	
	private int size;
	private int msgPanel;
	
	private Label label;
	
	public Background(Stage stage, ShapeRenderer renderer) {
		
		this.stage = stage;
		this.renderer = renderer;
		
		createInfoLabel();
		
		msgPanel = Sizes.BOARD_MSG.getValue();
		size = Sizes.FIELD_NUMBER.getValue() * Sizes.FIELD_SIZE.getValue() + Sizes.BOARD_OFFSET.getValue() * 2;
	}
	
	private void createInfoLabel() {
		
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();

		label = new Label("",labelStyle);
		label.setFontScale(2,2);
		label.setColor(Color.TAN);
		label.setPosition(Sizes.GAME_WIDTH.getValue() / 2 - 2 * label.getWidth() / 2, 50);
		stage.addActor(label);
	}
	
	public void setLabel(String text, Color color) {
		
		label.setText(text);
		label.setColor(color);
	}
	
	@Override
	public void draw() {
		
		drawLayer1();
		drawLayer2();
		
		stage.act();
		stage.draw();
	}

	private void drawLayer1() {
		
		renderer.setColor(Color.TAN);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, size, size + msgPanel);
		renderer.end();
	}

	private void drawLayer2() {
		
		renderer.setColor(Color.BLACK);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, msgPanel, size, size);
		renderer.end();
	}
}
