package com.betbull.playermarket.model;

import java.math.BigDecimal;
import java.util.Date;

import com.betbull.playermarket.model.base.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Player extends BaseModel{
	
	private String teamName;
	private Date careerBeginDate;
	private Date birthDate;
	private BigDecimal attackPower;
	private BigDecimal defencePower;
	private BigDecimal physicalPower;
	private BigDecimal overallPower;
	private String position;
	
	public Player() {
		super();
	}
}
