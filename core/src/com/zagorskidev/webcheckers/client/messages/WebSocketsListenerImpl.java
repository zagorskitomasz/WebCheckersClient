package com.zagorskidev.webcheckers.client.messages;

import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketAdapter;
import com.github.czyzby.websocket.data.WebSocketException;

public class WebSocketsListenerImpl extends WebSocketAdapter {

	private MessagesConsumer consumer;

	public WebSocketsListenerImpl(MessagesConsumer consumer) {

		if (consumer == null)
			throw new WebSocketException("MessagesConsumer not initialized.");

		this.consumer = consumer;
	}

	@Override
	public boolean onMessage(final WebSocket webSocket, final String packet) {
		
		if(packet != null)
			forwardMessage(packet);
		
		return FULLY_HANDLED;
	}
	
	private void forwardMessage(String serializedMessage) {
		
		Message message = Message.deserialize(serializedMessage);
		consumer.consume(message);
	}
}
