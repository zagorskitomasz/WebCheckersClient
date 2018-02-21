package com.zagorskidev.webcheckers.client.model.domain.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.webcheckers.client.enums.Sizes;

public class CreateButton extends Button {
	
	public CreateButton (Stage stage, ShapeRenderer renderer) {
		
		super(stage, renderer);
		
		initialize(Sizes.GAME_WIDTH / 2 - 120, Sizes.GAME_HEIGHT - 300);
		createLabel("Create game", Color.BLACK, 0, Sizes.GAME_HEIGHT - 255, 2);
	}
}
