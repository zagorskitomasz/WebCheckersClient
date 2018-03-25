package com.zagorskidev.webcheckers.client.graphics;

public interface MediaContainer<T> {

	public T getMedia();
	public void resetStack();
	public MediaContainer<T>[] getAllMedia();
}
