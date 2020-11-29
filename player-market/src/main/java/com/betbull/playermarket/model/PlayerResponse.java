package com.betbull.playermarket.model;

import java.util.List;

import com.betbull.playermarket.model.base.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlayerResponse extends BaseResponse{
	private List<Player> playerList;
	
	public PlayerResponse() {
		// TODO Auto-generated constructor stub
	}
}
