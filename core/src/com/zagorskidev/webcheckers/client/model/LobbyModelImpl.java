package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.enums.Sizes;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class LobbyModelImpl implements LobbyModel {

	private Stage stage;
	private ShapeRenderer renderer;
	
	private Drawable btnCreate;
	private Drawable btnJoin;
	
	private static final int btnXPos = Sizes.GAME_WIDTH.getValue() / 2 - 120;
	private static final int btnCreateYPos = Sizes.GAME_HEIGHT.getValue() - 300;
	private static final int btnJoinYPos = Sizes.GAME_HEIGHT.getValue() - 480;
	private static final int btnXSize = 240;
	private static final int btnYSize = 100;
	
	public LobbyModelImpl(Stage stage, ShapeRenderer renderer) {
		
		this.stage = stage;
		this.renderer = renderer;
		
		stage.clear();
		
		createTitleLabel();
		createCreateLabel();
		createJoinLabel();
		
		createCreateButton();
		createJoinButton();
		
	}
	
	private void createTitleLabel() {
		
		createLabel("Web Checkers", Color.GREEN, 0, Sizes.GAME_HEIGHT.getValue() - 100, 3);
	}
	
	private void createCreateLabel() {
		
		createLabel("Create game", Color.BLACK, 0, Sizes.GAME_HEIGHT.getValue() - 255, 2);
	}
	
	private void createJoinLabel() {
		
		createLabel("Join game", Color.BLACK, 0, Sizes.GAME_HEIGHT.getValue() - 435, 2);
	}
	
	private void createCreateButton() {
		
		btnCreate = new Drawable() {

			@Override
			public void draw() {
				renderer.setColor(Color.YELLOW);
				renderer.begin(ShapeType.Filled);
				renderer.rect(btnXPos, btnCreateYPos, btnXSize, btnYSize);
				renderer.end();
			}
		};
	}
	
	private void createJoinButton() {
		
		btnJoin = new Drawable() {

			@Override
			public void draw() {
				renderer.setColor(Color.YELLOW);
				renderer.begin(ShapeType.Filled);
				renderer.rect(btnXPos, btnJoinYPos, btnXSize, btnYSize);
				renderer.end();
			}
		};
	}
	
	private void createLabel(String text, Color color, float moveLeft, float y, float fontSize) {
		
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();

		Label label;
		label = new Label(text,labelStyle);
		label.setFontScale(fontSize, fontSize);
		label.setColor(color);
		label.setPosition(Sizes.GAME_WIDTH.getValue() / 2 - fontSize * label.getWidth() / 2 - moveLeft, y);
		
		stage.addActor(label);
	}
	
	@Override
	public void draw() {
		btnCreate.draw();
		btnJoin.draw();
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		renderer.dispose();
	}
}
