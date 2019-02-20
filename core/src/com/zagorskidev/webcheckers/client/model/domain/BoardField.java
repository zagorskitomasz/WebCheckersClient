package com.zagorskidev.webcheckers.client.model.domain;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.enums.field.Selection;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.graphics.MediaContainer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;

public class BoardField implements Drawable{
	
	private int X;
	private int Y;
	
	private Drawer drawer;
	
	private Checker checker;
	private Selection selection;
	private Promotion promotion;
	
	public BoardField(int x, int y) {
		
		X = (Sizes.BOARD_WIDTH - 2 * Sizes.BOARD_OFFSET) * x / Sizes.FIELD_NUMBER + Sizes.BOARD_OFFSET;
		Y = (int)(Sizes.GAME_HEIGHT * 1 / 7) + Sizes.BOARD_HEIGHT - (Sizes.BOARD_HEIGHT - 2 * Sizes.BOARD_OFFSET) * (y + 1) / Sizes.FIELD_NUMBER - Sizes.BOARD_OFFSET;
		
		drawer = Drawer.getInstance();
		
		checker = Checker.NONE;
		selection = Selection.NONE;
		promotion = Promotion.NO;
	}

	public void setPosition(int x, int y) {
		X = (Sizes.BOARD_WIDTH - 2 * Sizes.BOARD_OFFSET) * x / Sizes.FIELD_NUMBER + Sizes.BOARD_OFFSET;
		Y = (int)(Sizes.GAME_HEIGHT * 1 / 7) + Sizes.BOARD_HEIGHT - (Sizes.BOARD_HEIGHT - 2 * Sizes.BOARD_OFFSET) * (y + 1) / Sizes.FIELD_NUMBER - Sizes.BOARD_OFFSET;
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

		drawSelection();
		drawChecker();
		drawPromotion();
	}
	
	private void drawSelection() {
		
		switch(selection) {
		case GREEN:
			drawSelection(Sprites.SELECTION_GRN);
			break;
		case RED:
			drawSelection(Sprites.SELECTION_RED);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
	
	private void drawChecker() {
		
		switch(checker) {
		case BLACK:
			drawChecker(Sprites.CHECKER_BLACK);
			break;
		case WHITE:
			drawChecker(Sprites.CHECKER_WHITE);
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
			drawPromotion(Sprites.PROMOTION);
			break;
		case NO:
			break;
		default:
			break;
		}
	}
	
	private void drawSelection(MediaContainer<Sprite> sprite) {

		drawer.draw(sprite, X - (int)(10 * Sizes.WIDTH_FACTOR), Y - (int)(10 * Sizes.HEIGHT_FACTOR));
	}
	
	private void drawChecker(MediaContainer<Sprite> sprite) {

		drawer.draw(sprite, X, Y);
	}
	
	private void drawPromotion(MediaContainer<Sprite> sprite) {

		drawer.draw(sprite, X, Y);
	}
	
	public boolean isSelected() {
		return selection == Selection.GREEN;
	}
}
