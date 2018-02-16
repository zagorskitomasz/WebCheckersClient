package com.zagorskidev.webcheckers.client.model;

import com.zagorskidev.webcheckers.client.enums.ModelType;

/**
 * Facade for specialized models which can be transparently switched by controllers.
 * @author tomek
 *
 */
public interface Model extends LobbyModel, GameModel{

	public void setModel(ModelType modelType);
}
