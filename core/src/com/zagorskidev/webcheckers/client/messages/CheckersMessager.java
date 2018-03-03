package com.zagorskidev.webcheckers.client.messages;

import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class CheckersMessager implements Messager {

	private MessagesConsumer consumer;
	private WebSocket socket;
	
	@Override
	public void run() {
		WebSocketListener adapter = new WebSocketsListenerImpl(consumer);
		
		socket = WebSockets.newSocket("ws://127.0.0.1:8080/checkers/websocket");
		socket.addListener(adapter);
		socket.connect();
	}

	@Override
	public void sendMessage(Message message) {
		socket.send(message.serialize());
	}

	@Override
	public void registerMessagesConsumer(MessagesConsumer consumer) {
		this.consumer = consumer;
	}

}
