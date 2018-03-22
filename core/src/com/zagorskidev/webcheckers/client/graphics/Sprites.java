package com.zagorskidev.webcheckers.client.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum Sprites implements MediaContainer<Sprite>{

	BACKGROUND("background.png"),
	BTN_CREATE_PL("btn_create_pl.png"),
	BTN_INVERT_OFF("btn_invert_off.png"),
	BTN_INVERT_ON("btn_invert_on.png"),
	BTN_JOIN_PL("btn_join_pl.png"),
	CHECKER_BLACK("checker_black.png"),
	CHECKER_WHITE("checker_white.png"),
	CONNECTION_NOT("connection_not.png"),
	CONNECTION_OK("connection_ok.png"),
	DRAW("draw.png"),
	FULL_BOARD("full_board.png"),
	GAME_EXISTS("game_exists.png"),
	GAME_FULL("game_full.png"),
	GAME_NOT_EXIST("game_not_exist.png"),
	LOST("lost.png"),
	OPP_DISCONNECTED("opp_disconnected.png"),
	PROMOTION("promotion.png"),
	SELECTION_GRN("selection_grn.png"),
	SELECTION_RED("selection_red.png"),
	SERVER_DISCONNECTED("server_disconnected.png"),
	TITLE_PL("title_pl.png"),
	WAITING("waiting.png"),
	WON("won.png"),
	YOUR_MOVE("your_move.png");
	
	private Sprite sprite;
	
	private Sprites(String filename) {
		sprite = new Sprite(new Texture(filename));
	}
	
	@Override
	public Sprite getMedia() {
		return sprite;
	}
	
	@Override
	public Sprite getUniqueMedia() {
		return new Sprite(sprite);
	}
}
