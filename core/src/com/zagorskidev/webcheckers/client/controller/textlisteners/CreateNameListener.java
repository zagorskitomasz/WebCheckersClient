package com.zagorskidev.webcheckers.client.controller.textlisteners;

import com.badlogic.gdx.Input.TextInputListener;
import com.zagorskidev.webcheckers.client.controller.Controller;

public class CreateNameListener implements TextInputListener {

	private Controller controller;
	
	public CreateNameListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void input(String text) {
		//TODO send name to controller
	}

	@Override
	public void canceled() {}
}
