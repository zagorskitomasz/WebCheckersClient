package com.zagorskidev.webcheckers.client.enums;

import com.badlogic.gdx.Gdx;

public class Sizes {

	public static final int GAME_WIDTH = Gdx.graphics.getWidth();
	public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
	public static final float HEIGHT_FACTOR = GAME_HEIGHT / 950f;
	public static final float WIDTH_FACTOR = GAME_WIDTH / 540f;
	public static final float TITLE_HEIGHT = (int)(350 * HEIGHT_FACTOR);
	public static final int BOARD_OFFSET = (int)(20 * WIDTH_FACTOR);
	public static final int FIELD_NUMBER = 10;
	public static final int FIELD_WIDTH = (int)(500 * WIDTH_FACTOR / FIELD_NUMBER);
	public static final int FIELD_HEIGHT = (int)(500 * HEIGHT_FACTOR / FIELD_NUMBER);
	public static final int BOARD_HEIGHT = (int)(540 * HEIGHT_FACTOR);
	public static final int BOARD_WIDTH = (int)(540 * WIDTH_FACTOR);
	public static final int BUTTON_SIZE_X = (int)(340 * WIDTH_FACTOR);
	public static final int BUTTON_SIZE_Y = (int)(80 * HEIGHT_FACTOR);
	public static final int BUTTON_PANEL_SIZE_Y = (int)(120 * HEIGHT_FACTOR);
	public static final int SM_BUTTON_SIZE = (int)(60 * WIDTH_FACTOR);
	public static final int SM_BUTTON_PANEL_SIZE = (int)(100 * WIDTH_FACTOR);
}
