package com.betbull.playermarket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlayerMarketException extends RuntimeException {
	
	private static final long serialVersionUID = 6295262333531308200L;
	
	private String message;
	private int errorCode;
}
