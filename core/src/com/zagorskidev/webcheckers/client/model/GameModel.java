package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.GameMsg;
import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.util.Position;

/**
 * Game field containing domain objects.
 * @author tomek
 *
 */
public interface GameModel extends Drawable{

	public void addChecker(Position position, Checker checker, Promotion promotion);
	public void clear(Position position);
	public void selectChecker(Position position);
	public void clearSelection();
	public void selectCheckerToKill(Position position);
	public void setLabel(GameMsg msg);
	public ButtonType recognizeClickedButton(int xClick, int yClick);
	public void invert(boolean inverted);
	public void invertRequest();
}
