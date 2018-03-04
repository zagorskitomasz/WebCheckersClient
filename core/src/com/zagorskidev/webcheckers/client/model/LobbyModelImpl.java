package com.zagorskidev.webcheckers.client.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.webcheckers.client.enums.ButtonType;
import com.zagorskidev.webcheckers.client.enums.Sizes;
import com.zagorskidev.webcheckers.client.model.domain.buttons.Button;
import com.zagorskidev.webcheckers.client.model.domain.buttons.CreateButton;
import com.zagorskidev.webcheckers.client.model.domain.buttons.JoinButton;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class LobbyModelImpl implements LobbyModel {

	private Stage stage;
	private ShapeRenderer renderer;
	
	private Button createButton;
	private Button joinButton;
	
	private Label titleLabel;
	private Label messageLabel;
	
	public LobbyModelImpl(Stage stage, ShapeRenderer renderer) {
		
		this.stage = stage;
		this.renderer = renderer;
		
		stage.clear();
		
		titleLabel = createTitleLabel();
		messageLabel = createMessageLabel();
		createButtons();
		addLabels();
		
	}
	
	private Label createTitleLabel() {
		
		return createLabel("Web Checkers", Color.GREEN, 0, Sizes.GAME_HEIGHT - 100, 3);
	}
	
	private Label createMessageLabel() {
		
		return createLabel("", Color.GREEN, 100, Sizes.GAME_HEIGHT - 540, 2);
	}
	
	private void createButtons() {
		
		createButton = new CreateButton(stage, renderer);
		joinButton = new JoinButton(stage, renderer);
	}
	
	private Label createLabel(String text, Color color, float moveLeft, float y, float fontSize) {
		
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();

		Label label;
		label = new Label(text,labelStyle);
		label.setFontScale(fontSize, fontSize);
		label.setColor(color);
		label.setPosition(Sizes.GAME_WIDTH / 2 - fontSize * label.getWidth() / 2 - moveLeft, y);
		
		return label;
	}
	
	private void addLabels() {

		stage.addActor(titleLabel);
		stage.addActor(messageLabel);
	}
	
	@Override
	public void draw() {
		createButton.draw();
		joinButton.draw();
		messageLabel.act(0);
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		renderer.dispose();
	}

	@Override
	public ButtonType recognizeClickedButton(int xClick, int yClick) {
		
		if(createButton.wasClicked(xClick, yClick))
			return ButtonType.CREATE;
		
		if(joinButton.wasClicked(xClick, yClick))
			return ButtonType.JOIN;
		
		return null;
	}
	
	public void setLobbyLabel(String text, Color color) {
		
		messageLabel.setText(text);
		messageLabel.setColor(color);
	}
}
