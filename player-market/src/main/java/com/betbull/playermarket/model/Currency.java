package com.betbull.playermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Currency {
	
	private String name;
	private String code;
	private String symbol;
	
	public Currency() {
		
	}

}
