package com.zagorskidev.webcheckers.client.model.domain;

import com.zagorskidev.webcheckers.client.enums.field.Checker;
import com.zagorskidev.webcheckers.client.enums.field.Promotion;
import com.zagorskidev.webcheckers.client.util.Position;

public class SimpleChecker {

	public final Position position;
	public final Checker type;
	public final Promotion promotion;
	
	private SimpleChecker() {
		
		position = null;
		type = null;
		promotion = null;
	}
	
	private SimpleChecker(Position position, Checker type, Promotion promotion) {
		
		this.position = position;
		this.type = type;
		this.promotion = promotion;
	}
	
	public static SimpleChecker parse(String serialized) {
		
		String[] fields = serialized.split("$");
		
		Position position = Position.parse(fields[0] + "$" + fields[1]);
		Promotion promotion = Character.isUpperCase(fields[2].charAt(0)) ? Promotion.YES : Promotion.NO;
		Checker type = "W".equals(fields[2].toUpperCase()) ? Checker.WHITE : Checker.BLACK;
		
		return new SimpleChecker(position, type, promotion);
	}
}
