package com.betbull.playermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betbull.playermarket.model.PlayerMarketResponse;
import com.betbull.playermarket.model.SimplePlayerMarketResponse;
import com.betbull.playermarket.service.PlayerMarketService;

@RestController
@RequestMapping("/api/playermarket")
public class PlayerMarketController {
	
	@Autowired
	private PlayerMarketService playerMarketService;
	
	@GetMapping("/getplayermarket")
	public PlayerMarketResponse getPlayerMarket() {
		return playerMarketService.createPlayerMarket();
	}
	
	@GetMapping("/getbasicplayermarket")
	public SimplePlayerMarketResponse getBasicPlayerMarket() {
		return playerMarketService.createSimplePlayerMarket();
	}

}
