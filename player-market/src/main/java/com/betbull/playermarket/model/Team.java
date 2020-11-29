package com.betbull.playermarket.model;

import com.betbull.playermarket.model.base.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Team extends BaseModel{
	
	private Currency currency;

	public Team() {
		super();
	}
}
