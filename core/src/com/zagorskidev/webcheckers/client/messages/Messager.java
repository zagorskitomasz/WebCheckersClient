package com.zagorskidev.webcheckers.client.messages;

/**
 * Sending and receiving messages from WebSocket adapter.
 * @author tomek
 *
 */
public interface Messager extends Runnable{

	public void sendMessage(Message message);
	public void registerMessagesConsumer(MessagesConsumer consumer);
}
