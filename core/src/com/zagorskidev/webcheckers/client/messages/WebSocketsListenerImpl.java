package com.zagorskidev.webcheckers.client.messages;

import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketAdapter;
import com.github.czyzby.websocket.data.WebSocketCloseCode;
import com.github.czyzby.websocket.data.WebSocketException;
import com.zagorskidev.webcheckers.client.enums.MsgCode;

public class WebSocketsListenerImpl extends WebSocketAdapter {

	private MessagesConsumer consumer;
	private Messager messager;

	public WebSocketsListenerImpl(MessagesConsumer consumer, Messager messager) {

		if (consumer == null)
			throw new WebSocketException("MessagesConsumer not initialized.");

		this.consumer = consumer;
		this.messager = messager;
	}

	@Override
	public boolean onMessage(WebSocket webSocket, String packet) {
		
		if(packet != null)
			forwardMessage(packet);
		
		return FULLY_HANDLED;
	}
	
	@Override
	public boolean onClose(WebSocket webSocket, WebSocketCloseCode code, String reason) {
		
		notifyDisconnected();
		disconnectMessager();
		
		return FULLY_HANDLED;
	}
	
	@Override
	public boolean onError(WebSocket webSocket, Throwable exception) {
		
		notifyDisconnected();
		disconnectMessager();
		
		return FULLY_HANDLED;
	}
	
	private void disconnectMessager() {

		if(messager != null)
			messager.disconnected();
	}

	private void notifyDisconnected() {
		
		Message message = new Message(MsgCode.DISCONNECTED, null, (String[])null);
		consumer.consume(message);
	}
	
	private void forwardMessage(String serializedMessage) {
		
		Message message = Message.deserialize(serializedMessage);
		consumer.consume(message);
	}
}
