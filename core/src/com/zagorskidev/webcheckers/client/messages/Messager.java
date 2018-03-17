package com.zagorskidev.webcheckers.client.messages;

/**
 * Sending and receiving messages from WebSocket adapter.
 * @author tomek
 *
 */
public interface Messager{

	public void sendMessage(Message message);
	public void registerMessagesConsumer(MessagesConsumer consumer);
	public void startWriteReadThreads();
	public void checkConnection();
	public void disconnected();
}
