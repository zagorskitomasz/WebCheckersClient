package com.zagorskidev.webcheckers.client.messages;

import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.github.czyzby.websocket.data.WebSocketException;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class CheckersMessager implements Messager {

	private MessagesConsumer consumer;
	private WebSocket socket;
	private WebSocketListener adapter;
	
	private boolean connected = false;
	
	@Override
	public void run() {
		adapter = new WebSocketsListenerImpl(consumer, this);
		
		socket = WebSockets.newSocket("wss://webcheckersserver.herokuapp.com/checkers/websocket");
		//socket = WebSockets.newSocket("ws://localhost:8080/checkers/websocket");
		socket.addListener(adapter);
	}

	private void connect() {
		
		try {
			socket.connect();
			connected = true;
		}
		catch(WebSocketException exception) {
			exception.printStackTrace();
			adapter.onError(socket, exception);
		}
	}

	@Override
	public void sendMessage(Message message) {
		socket.send(message.serialize());
	}

	@Override
	public void registerMessagesConsumer(MessagesConsumer consumer) {
		this.consumer = consumer;
	}

	@Override
	public void tryConnect() {
		
		if(!connected)
			connect();
	}
	
	@Override
	public void disconnected() {
		connected = false;
	}
}
