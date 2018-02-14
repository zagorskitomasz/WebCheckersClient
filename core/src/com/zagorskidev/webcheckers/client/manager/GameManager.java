package com.zagorskidev.webcheckers.client.manager;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.webcheckers.client.draw.Drawable;
import com.zagorskidev.webcheckers.client.messages.MessagesConsumer;
import com.zagorskidev.webcheckers.client.messages.MessagesProducer;

public interface GameManager extends InputProcessor, Drawable, MessagesProducer, MessagesConsumer{
	
}
