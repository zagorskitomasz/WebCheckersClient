package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.draw.Drawable;
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
	public void unselectChecker(Position position);
	public void selectCheckerToKill(Position position);
}