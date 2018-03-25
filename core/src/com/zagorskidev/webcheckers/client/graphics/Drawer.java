package com.zagorskidev.webcheckers.client.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drawer {

	private static Drawer instance;
	
	private SpriteBatch spriteBatch;
	
	private Drawer() {
		spriteBatch = new SpriteBatch();
	}
	
	public static Drawer getInstance() {
		
		if(instance == null)
			instance = new Drawer();
		
		return instance;
	}
	
	public void draw(MediaContainer<Sprite> media, int x, int y) {
		
		Sprite sprite = media.getMedia();
		sprite.setPosition(x, y);
		sprite.draw(spriteBatch);
	}
	
	public void beginFrame() {
		
		for(MediaContainer<Sprite> container : Sprites.BACKGROUND.getAllMedia())
			container.resetStack();
		
		spriteBatch.begin();
	}
	
	public void endFrame() {
		spriteBatch.end();
	}
	
	public void dispose() {
		spriteBatch.dispose();
	}
}
