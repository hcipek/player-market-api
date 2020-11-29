package com.betbull.playermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlayerMarketItemDto {
	private String playerName;
	private String teamName;
	private String position;
	private String overallPower;
	private String contractFee;

}
