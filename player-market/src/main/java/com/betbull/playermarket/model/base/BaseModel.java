package com.betbull.playermarket.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BaseModel {

	private Long id;
	private String name;
	private String nation;
	
	public BaseModel() {
		
	}
}
