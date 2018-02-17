package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	
	public GameModelImpl(boolean inverted) {
		board = new Board(inverted);
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
	public void unselectChecker(Position position) {
		
		board.unselectChecker(position);
	}

	@Override
	public void selectCheckerToKill(Position position) {
		
		board.selectCheckerToKill(position);
	}
	
	@Override
	public void draw(ShapeRenderer renderer) {
		board.draw(renderer);
	}
}
