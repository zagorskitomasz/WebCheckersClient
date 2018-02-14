package com.zagorskidev.webcheckers.client.util;

public class GameID {
	
	public final String NAME;
	public final String PASSWORD;
	
	public GameID(String name, String password) {
		
		NAME = name;
		PASSWORD = password;
	}
	
	@Override
	public boolean equals(Object object) {
		
		GameID otherID = (GameID) object;
		
		if(NAME.equals(otherID.NAME) && PASSWORD.equals(otherID.PASSWORD))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return NAME.hashCode();
	}
}
