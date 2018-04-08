package com.zagorskidev.webcheckers.client.enums;

import com.badlogic.gdx.Gdx;

public class Sizes {

	public static final int GAME_WIDTH = Gdx.graphics.getWidth();
	public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
	public static final float RESIZE_FACTOR = GAME_WIDTH / 540f;
	public static final int BOARD_OFFSET = (int)(20 * RESIZE_FACTOR);
	public static final int BOARD_MSG = (int)(100 * RESIZE_FACTOR);
	public static final int FIELD_SIZE = (int)(50 * RESIZE_FACTOR);
	public static final int FIELD_NUMBER = 10;
	public static final int BOARD_HEIGHT = 2 * BOARD_OFFSET + FIELD_SIZE * FIELD_NUMBER;
	public static final int BUTTON_SIZE_X = (int)(340 * RESIZE_FACTOR);
	public static final int BUTTON_SIZE_Y = (int)(80 * RESIZE_FACTOR);
	public static final int BUTTON_PANEL_SIZE_Y = (int)(120 * RESIZE_FACTOR);
	public static final int SM_BUTTON_SIZE = (int)(60 * RESIZE_FACTOR);
	public static final int SM_BUTTON_PANEL_SIZE = (int)(100 * RESIZE_FACTOR);
}
