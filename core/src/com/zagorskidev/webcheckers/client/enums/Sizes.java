package com.zagorskidev.webcheckers.client.enums;

public enum Sizes {

	BOARD_OFFSET(20),
	BOARD_MSG(100),
	FIELD_SIZE(50),
	FIELD_NUMBER(10),
	GAME_WIDTH(FIELD_NUMBER.getValue() * FIELD_SIZE.getValue() + 2 * BOARD_OFFSET.getValue()),
	GAME_HEIGHT(GAME_WIDTH.getValue() + BOARD_MSG.getValue());
	
	private int value;
	
	private Sizes(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
