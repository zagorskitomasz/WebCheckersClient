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
	
	private ShapeRenderer renderer;
	
	private Field field;
	private Checker checker;
	private Selection selection;
	private Promotion promotion;
	
	public BoardField(ShapeRenderer renderer, int x, int y, Field field) {
		
		X = x * Sizes.FIELD_SIZE + Sizes.BOARD_OFFSET;
		Y = Sizes.GAME_HEIGHT - ((y + 1) * Sizes.FIELD_SIZE + Sizes.BOARD_OFFSET);
		
		this.renderer = renderer;
		
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
	
	public void draw() {
		
		drawField();
		drawChecker();
		drawSelection();
		drawPromotion();
	}
	
	private void drawField() {
		
		switch(field) {
		case BRIGHT:
			drawField(Color.TAN);
			break;
		case DARK:
			drawField(Color.BROWN);
			break;
		default:
			break;
		}
	}
	
	private void drawChecker() {
		
		switch(checker) {
		case BLACK:
			drawChecker(Color.BLACK);
			break;
		case WHITE:
			drawChecker(Color.WHITE);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
	
	private void drawSelection() {
		
		switch(selection) {
		case GREEN:
			drawSelection(Color.GREEN);
			break;
		case RED:
			drawSelection(Color.RED);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
	
	private void drawPromotion() {
		
		switch(promotion) {
		case YES:
			drawPromotion(Color.GOLD);
			break;
		case NO:
			break;
		default:
			break;
		}
	}
	
	private void drawField(Color color) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(X + 2, Y + 2, Sizes.FIELD_SIZE - 4, Sizes.FIELD_SIZE - 4);
		renderer.end();
	}
	
	private void drawChecker(Color color) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.ellipse(X + 6, Y + 6, Sizes.FIELD_SIZE - 12, Sizes.FIELD_SIZE - 12);
		renderer.end();
	}
	
	private void drawSelection(Color color) {

		renderer.setColor(color);
		renderer.begin(ShapeType.Line);
		renderer.ellipse(X + 5, Y + 5, Sizes.FIELD_SIZE - 10, Sizes.FIELD_SIZE - 10);
		renderer.end();
	}
	
	private void drawPromotion(Color color) {

		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.triangle(X + 12, Y + 18, 
				X + Sizes.FIELD_SIZE / 2, Y + Sizes.FIELD_SIZE - 9, 
				X + Sizes.FIELD_SIZE - 12, Y + 18);
		renderer.triangle(X + 12, Y + Sizes.FIELD_SIZE - 18, 
				X + Sizes.FIELD_SIZE / 2, Y + 9, 
				X + Sizes.FIELD_SIZE - 12, Y  + Sizes.FIELD_SIZE - 18);
		renderer.end();
	}
}
