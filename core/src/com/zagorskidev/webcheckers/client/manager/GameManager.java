package com.zagorskidev.webcheckers.client.manager;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.webcheckers.client.messages.MessagesConsumer;
import com.zagorskidev.webcheckers.client.messages.MessagesProducer;

/**
 * Central node granting communication between user input, messager and controller.
 * @author tomek
 *
 */
public interface GameManager extends InputProcessor, MessagesProducer, MessagesConsumer{
	
}
