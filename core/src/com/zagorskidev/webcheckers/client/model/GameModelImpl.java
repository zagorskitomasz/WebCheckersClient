package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.model.domain.Board;
import com.zagorskidev.webcheckers.client.util.Position;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class GameModelImpl implements GameModel {

	private Board board;
	
	public GameModelImpl(Stage stage, ShapeRenderer renderer, boolean inverted) {
		stage.clear();
		board = new Board(stage, renderer, inverted);
	}

	@Override
	public void addChecker(Position position, Checker checker, Promotion promotion) {
		
		board.addChecker(position, checker, promotion);
	}

	@Override
	public void clear(Position position) {
		
		board.clear(position);
	}

	@Override
	public void selectChecker(Position position) {
		
		board.selectChecker(position);
	}

	@Override
	public void clearSelection() {
		
		board.clearSelection();
	}

	@Override
	public void selectCheckerToKill(Position position) {
		
		board.selectCheckerToKill(position);
	}
	
	@Override 
	public void setLabel(String text, Color color) {
		board.setLabel(text, color);
	}
	
	@Override
	public void draw() {
		board.draw();
	}
	
	@Override
	public void dispose() {
		board.dispose();
	}
}
