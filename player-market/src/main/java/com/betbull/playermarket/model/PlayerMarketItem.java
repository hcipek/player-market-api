package com.betbull.playermarket.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlayerMarketItem {
	
	private Player player;
	private Team team;
	private BigDecimal contractFee;
	private String currencyCode;
	
	public PlayerMarketItem(Player player, Team team) {
		this.player = player;
		this.team = team;
	}

}
