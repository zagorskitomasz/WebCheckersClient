package com.zagorskidev.webcheckers.client.controller;

import com.zagorskidev.webcheckers.client.messages.Message;

/**
 * Communication between model and manager.
 * @author tomek
 *
 */
public interface Controller {
	
	/**
	 * Translates click coords to output message using current model state
	 * @param xClick
	 * @param yClick
	 * @return
	 */
	public Message actionToMessage(int xClick, int yClick);
	
	/**
	 * Modifying model state due to received input message.
	 * @param message
	 */
	public void executeMessageAction(Message message);
}
