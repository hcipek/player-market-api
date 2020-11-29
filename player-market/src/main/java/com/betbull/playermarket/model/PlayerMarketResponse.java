package com.betbull.playermarket.model;

import java.util.List;

import com.betbull.playermarket.model.base.BaseResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerMarketResponse extends BaseResponse {

	private List<PlayerMarketItem> playerMarketList;
	
	public PlayerMarketResponse() {
		// TODO Auto-generated constructor stub
	}
}
