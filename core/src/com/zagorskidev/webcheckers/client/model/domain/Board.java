package com.zagorskidev.webcheckers.client.model.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Field;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.enums.field.Selection;
import com.zagorskidev.webcheckers.client.util.Position;

public class Board implements Drawable {
	
	private Stage stage;
	private ShapeRenderer renderer;
	
	private Background background;
	private BoardField[][] board;
	
	public Board(Stage stage, ShapeRenderer renderer, boolean inverted) {
		
		this.stage = stage;
		this.renderer = renderer;
		
		background = new Background(stage, renderer);
		board = new BoardField[Sizes.FIELD_NUMBER.getValue()][Sizes.FIELD_NUMBER.getValue()];
		initialize(inverted);
	}
	
	private void initialize(boolean inverted) {
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				initializeField(i, j, inverted);
			}
		}	
	}

	private void initializeField(int i, int j, boolean inverted) {
		
		if(!inverted) 
			board[i][j] = new BoardField(renderer, i, j, chooseFieldColor(i, j));
		else 
			board[i][j] = new BoardField(renderer, maxFieldIndex() - i, maxFieldIndex() - j, chooseFieldColor(i, j));
	}

	private Field chooseFieldColor(int i, int j) {
		return (i + j) % 2 == 0 ? Field.DARK : Field.BRIGHT;
	}
	
	private int maxFieldIndex() {
		return Sizes.FIELD_NUMBER.getValue() - 1;
	}

	public void addChecker(Position position, Checker checker, Promotion promotion) {
		
		BoardField field = board[position.X][position.Y];
		field.setChecker(checker);
		field.setPromotion(promotion);
	}
	
	public void clear(Position position) {
		
		BoardField field = board[position.X][position.Y];
		field.clearField();
	}

	public void selectChecker(Position position) {
		
		BoardField field = board[position.X][position.Y];
		field.setSelection(Selection.GREEN);
	}

	public void unselectChecker(Position position) {
		
		BoardField field = board[position.X][position.Y];
		field.setSelection(Selection.NONE);
	}

	public void selectCheckerToKill(Position position) {
		
		BoardField field = board[position.X][position.Y];
		field.setSelection(Selection.RED);
	}
	
	public void setLabel(String text, Color color) {
		background.setLabel(text, color);
	}

	@Override
	public void draw() {
		
		background.draw();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j].draw();
			}
		}
	}
	
	@Override
	public void dispose() {
		
		stage.dispose();
		renderer.dispose();
	}
}
