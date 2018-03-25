package com.zagorskidev.webcheckers.client.graphics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public enum Sprites implements MediaContainer<Sprite>{

	BACKGROUND("background.png",1),
	BTN_CREATE_PL("btn_create_pl.png",1),
	BTN_INVERT_OFF("btn_invert_off.png",1),
	BTN_INVERT_ON("btn_invert_on.png",1),
	BTN_JOIN_PL("btn_join_pl.png",1),
	CHECKER_BLACK("checker_silver.png",16),
	CHECKER_WHITE("checker_gold.png",16),
	CONNECTION_NOT("connection_not.png",1),
	CONNECTION_OK("connection_ok.png",1),
	DRAW("draw.png",1),
	FULL_BOARD("full_board.png",1),
	GAME_EXISTS("game_exists.png",1),
	GAME_FULL("game_full.png",1),
	GAME_NOT_EXIST("game_not_exist.png",1),
	LOST("lost.png",1),
	OPP_DISCONNECTED("opp_disconnected.png",1),
	PROMOTION("promotion.png",32),
	SELECTION_GRN("selection_grn.png",1),
	SELECTION_RED("selection_red.png",16),
	SERVER_DISCONNECTED("server_disconnected.png",1),
	TITLE_PL("title_pl.png",1),
	WAITING("waiting.png",1),
	WON("won.png",1),
	YOUR_MOVE("your_move.png",1);
	
	private List<Sprite> sprites;
	private Iterator<Sprite> iterator;
	
	private Sprites(String filename, int amount) {
		
		sprites = new LinkedList<>();
		
		for(int i = 0; i < amount; i++)
			sprites.add(new Sprite(new Texture(filename)));
		
		resetStack();
	}
	
	@Override
	public Sprite getMedia() {
		
		return iterator.next();
	}

	@Override
	public void resetStack() {
		
		sprites.forEach(sprite -> sprite.setPosition(Sizes.GAME_WIDTH + 1, Sizes.GAME_HEIGHT + 1));
		iterator = sprites.iterator();
	}

	@Override
	public MediaContainer<Sprite>[] getAllMedia() {
		
		return Sprites.values();
	}
}
