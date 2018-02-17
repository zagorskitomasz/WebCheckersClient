package com.zagorskidev.webcheckers.client.model.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class Background implements Drawable {

	private int size;
	private int msgPanel;
	
	public Background() {
		msgPanel = Sizes.BOARD_MSG.getValue();
		size = Sizes.FIELD_NUMBER.getValue() * Sizes.FIELD_SIZE.getValue() + Sizes.BOARD_OFFSET.getValue() * 2;
	}
	
	@Override
	public void draw(ShapeRenderer renderer) {
		
		drawLayer1(renderer);
		drawLayer2(renderer);
	}

	private void drawLayer1(ShapeRenderer renderer) {
		renderer.setColor(Color.TAN);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, size, size + msgPanel);
		renderer.end();
	}

	private void drawLayer2(ShapeRenderer renderer) {
		renderer.setColor(Color.BLACK);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, msgPanel, size, size);
		renderer.end();
	}
}
