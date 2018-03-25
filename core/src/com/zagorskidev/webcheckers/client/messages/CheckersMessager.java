package com.zagorskidev.webcheckers.client.messages;

import com.badlogic.gdx.utils.TimeUtils;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.github.czyzby.websocket.data.WebSocketException;
import com.zagorskidev.webcheckers.client.enums.MsgCode;

/**
 * Straight implementation.
 * @author tomek
 *
 */
public class CheckersMessager implements Messager {

	private static final long CHECK_INTERVAL = 5000;
	
	private MessagesConsumer consumer;
	private WebSocket socket;
	private WebSocketListener adapter;
	
	private long lastConnectionCheck;
	private boolean connected = false;
	
	@Override
	public void startWriteReadThreads() {
		
		adapter = new WebSocketsListenerImpl(consumer, this);
		
		socket = WebSockets.newSocket("wss://webcheckersserver.herokuapp.com/checkers/websocket");
		//socket = WebSockets.newSocket("ws://localhost:8080/checkers/websocket");
		socket.addListener(adapter);
		lastConnectionCheck = TimeUtils.millis() - CHECK_INTERVAL;
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
		
		if(socket != null && connected && socket.isOpen())
			socket.send(message.serialize());
	}

	@Override
	public void registerMessagesConsumer(MessagesConsumer consumer) {
		this.consumer = consumer;
		notifyConnection();
	}
	
	@Override
	public void disconnected() {
		connected = false;
	}
	
	@Override
	public void checkConnection(){
			
		long currentTime = TimeUtils.millis();
			
		if(lastConnectionCheck + CHECK_INTERVAL < currentTime) {
			lastConnectionCheck = currentTime;
			tryConnect();
			ping();
			notifyConnection();
		}
	}

	private void tryConnect() {
		
		if(!connected)
			connect();
	}	
	
	private void ping(){
		
		Message ping = new Message(null);
		sendMessage(ping);
	}
	
	private void notifyConnection(){
		consumer.consume(new Message(connected ? MsgCode.CONNECTED : MsgCode.DISCONNECTED, null, (String[])null));
	}
}
