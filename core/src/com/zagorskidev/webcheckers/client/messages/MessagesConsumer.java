package com.zagorskidev.webcheckers.client.messages;

/**
 * Consumes incoming messages.
 * @author tomek
 *
 */
public interface MessagesConsumer {

	public void consume(Message message);
}
