package com.zagorskidev.webcheckers.client.messages;

import com.zagorskidev.webcheckers.client.messages.Message;
import com.zagorskidev.webcheckers.client.messages.Messager;
import com.zagorskidev.webcheckers.client.messages.MessagesConsumer;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class CheckersMessager implements Messager {

	private MessagesConsumer consumer;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(Message message) {
		System.out.println(message.CODE + " " + message.gameID + " " + (message.ARGS == null ? "empty" : message.ARGS[0]));
	}

	@Override
	public void registerMessagesConsumer(MessagesConsumer consumer) {
		this.consumer = consumer;
	}

}
