package com.zagorskidev.webcheckers.client.model.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Field;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.enums.field.Selection;

public class BoardField implements Drawable{
	
	private final int X;
	private final int Y;
	
	private Field field;
	private Checker checker;
	private Selection selection;
	private Promotion promotion;
	
	public BoardField(int x, int y, Field field) {
		
		X = x * Sizes.FIELD_SIZE.getValue() + Sizes.BOARD_OFFSET.getValue();
		Y = Sizes.GAME_HEIGHT.getValue() - ((y + 1) * Sizes.FIELD_SIZE.getValue() + Sizes.BOARD_OFFSET.getValue());
		
		this.field = field;
		checker = Checker.NONE;
		selection = Selection.NONE;
		promotion = Promotion.NO;
	}
	
	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	public void setPromotion(Promotion promotion) {
		
		if(checker != Checker.NONE)
			this.promotion = promotion;
	}
	
	public void setSelection(Selection selection) {
		
		if(checker != Checker.NONE)
			this.selection = selection;
	}
	
	public void clearField() {
		
		checker = Checker.NONE;
		promotion = Promotion.NO;
		selection = Selection.NONE;
	}
	
	public void draw(ShapeRenderer renderer) {
		
		drawField(renderer);
		drawChecker(renderer);
		drawSelection(renderer);
		drawPromotion(renderer);
	}
	
	private void drawField(ShapeRenderer renderer) {
		
		switch(field) {
		case BRIGHT:
			drawField(renderer, Color.TAN);
			break;
		case DARK:
			drawField(renderer, Color.BROWN);
			break;
		default:
			break;
		}
	}
	
	private void drawChecker(ShapeRenderer renderer) {
		
		switch(checker) {
		case BLACK:
			drawChecker(renderer, Color.BLACK);
			break;
		case WHITE:
			drawChecker(renderer, Color.WHITE);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
	
	private void drawSelection(ShapeRenderer renderer) {
		
		switch(selection) {
		case GREEN:
			drawSelection(renderer, Color.GREEN);
			break;
		case RED:
			drawSelection(renderer, Color.RED);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
	
	private void drawPromotion(ShapeRenderer renderer) {
		
		switch(promotion) {
		case YES:
			drawPromotion(renderer, Color.GOLD);
			break;
		case NO:
			break;
		default:
			break;
		}
	}
	
	private void drawField(ShapeRenderer renderer, Color color) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(X + 2, Y + 2, Sizes.FIELD_SIZE.getValue() - 4, Sizes.FIELD_SIZE.getValue() - 4);
		renderer.end();
	}
	
	private void drawChecker(ShapeRenderer renderer, Color color) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.ellipse(X + 6, Y + 6, Sizes.FIELD_SIZE.getValue() - 12, Sizes.FIELD_SIZE.getValue() - 12);
		renderer.end();
	}
	
	private void drawSelection(ShapeRenderer renderer, Color color) {

		renderer.setColor(color);
		renderer.begin(ShapeType.Line);
		renderer.ellipse(X + 5, Y + 5, Sizes.FIELD_SIZE.getValue() - 10, Sizes.FIELD_SIZE.getValue() - 10);
		renderer.end();
	}
	
	private void drawPromotion(ShapeRenderer renderer, Color color) {

		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.triangle(X + 12, Y + 18, 
				X + Sizes.FIELD_SIZE.getValue() / 2, Y + Sizes.FIELD_SIZE.getValue() - 9, 
				X + Sizes.FIELD_SIZE.getValue() - 12, Y + 18);
		renderer.triangle(X + 12, Y + Sizes.FIELD_SIZE.getValue() - 18, 
				X + Sizes.FIELD_SIZE.getValue() / 2, Y + 9, 
				X + Sizes.FIELD_SIZE.getValue() - 12, Y  + Sizes.FIELD_SIZE.getValue() - 18);
		renderer.end();
	}
}
