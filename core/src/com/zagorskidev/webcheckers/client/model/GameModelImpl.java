package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.GameMsg;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.graphics.Drawer;
import com.zagorskidev.webcheckers.client.model.domain.Board;
import com.zagorskidev.webcheckers.client.model.domain.buttons.Button;
import com.zagorskidev.webcheckers.client.model.domain.buttons.InvertButton;
import com.zagorskidev.webcheckers.client.util.Position;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class GameModelImpl implements GameModel {

	private Board board;
	private Button invertButton;
	private GameMsg label;
	
	private Drawer drawer;
	
	public GameModelImpl(boolean inverted) {
		
		drawer = Drawer.getInstance();
		
		board = new Board(inverted);
		invertButton = new InvertButton();
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
	public void setLabel(GameMsg msg) {
		label = msg;
	}
	
	@Override
	public void draw() {
		
		board.draw();
		invertButton.draw();
		
		if(label != null)
			drawer.draw(label.getSprite(), (int)(100 * Sizes.WIDTH_FACTOR), (int)(20 * Sizes.HEIGHT_FACTOR));
	}

	@Override
	public ButtonType recognizeClickedButton(int xClick, int yClick) {
		
		if(invertButton.wasClicked(xClick, yClick))
			return ButtonType.INVERT;
		
		return null;
	}
	
	@Override
	public void invert(boolean inverted){
		board.invert(inverted);
		invertButton.highlight(false);
	}
	
	@Override
	public void invertRequest(){
		invertButton.highlight(true);
	}
}
