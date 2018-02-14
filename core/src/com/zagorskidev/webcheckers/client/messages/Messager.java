package com.zagorskidev.webcheckers.client.messages;

public interface Messager extends Runnable{

	public void sendMessage(Message message);
	public void registerMessagesConsumer(MessagesConsumer consumer);
}
