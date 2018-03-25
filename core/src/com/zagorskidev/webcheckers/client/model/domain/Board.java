package com.zagorskidev.webcheckers.client.model.domain;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.enums.field.Selection;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.graphics.Sprites;
import com.zagorskidev.webcheckers.client.util.Position;

public class Board implements Drawable {
	
	private Drawer drawer;
	
	private BoardField[][] board;
	
	public Board(boolean inverted) {
		
		drawer = Drawer.getInstance();
		
		board = new BoardField[Sizes.FIELD_NUMBER][Sizes.FIELD_NUMBER];
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
			board[i][j] = new BoardField(i, j);
		else 
			board[i][j] = new BoardField(maxFieldIndex() - i, maxFieldIndex() - j);
	}
	
	private int maxFieldIndex() {
		return Sizes.FIELD_NUMBER - 1;
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

	public void clearSelection() {
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].isSelected())
					board[i][j].setSelection(Selection.NONE);
			}
		}
	}

	public void selectCheckerToKill(Position position) {
		
		BoardField field = board[position.X][position.Y];
		field.setSelection(Selection.RED);
	}

	@Override
	public void draw() {
		
		drawer.draw(Sprites.BACKGROUND, 0, 0);
		drawer.draw(Sprites.FULL_BOARD, 0, Sizes.BOARD_MSG);
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j].draw();
			}
		}
	}

	public void invert(boolean inverted) {
		
		BoardField[][] tempBoard = new BoardField[board.length][board.length];
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				
				tempBoard[i][j] = board[i][j];

				if(!inverted) 
					tempBoard[i][j].setPosition(i, j);
				else 
					tempBoard[i][j].setPosition(maxFieldIndex() - i, maxFieldIndex() - j);
			}
		}
		board = tempBoard;
	}
}
