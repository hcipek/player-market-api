package com.betbull.playermarket.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BaseResponse {

	private int resultCode;
	private String description;
	
	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}
}
