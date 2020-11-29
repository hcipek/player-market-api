package com.betbull.playermarket.model;

import java.util.List;

import com.betbull.playermarket.model.base.BaseResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeamResponse extends BaseResponse {
	
	private List<Team> teamList;
	
	public TeamResponse() {
		// TODO Auto-generated constructor stub
	}
}
