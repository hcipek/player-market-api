package com.betbull.playermarket.controller;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.betbull.playermarket.model.PlayerMarketRequest;
import com.betbull.playermarket.model.PlayerMarketResponse;
import com.betbull.playermarket.model.SimplePlayerMarketResponse;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PlayerMarketControllerTest {
	
	@Autowired
	private PlayerMarketController playerMarketController;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	@Order(1)
	public void getPlayerMarketTest() {
		PlayerMarketResponse response = playerMarketController.getPlayerMarket();
		assertEquals("SUCCESS", response.getDescription());
		assertEquals(0, response.getResultCode());
		assertNotNull(response.getPlayerMarketList());
	}
	
	@Test
	@Order(2)
	public void getBasicPlayerMarketTest() {
		SimplePlayerMarketResponse response = playerMarketController.getBasicPlayerMarket();
		assertEquals("SUCCESS", response.getDescription());
		assertEquals(0, response.getResultCode());
		assertNotNull(response.getPlayerMarketList());
	}
	
	@Test
	@Order(3)
	public void getContractFeeOfPlayerByIdTest() {
		PlayerMarketResponse marketResponse = playerMarketController.getPlayerMarket();
		Long id = marketResponse.getPlayerMarketList().stream().findFirst().get().getPlayer().getId();
		SimplePlayerMarketResponse response = playerMarketController.getContractFeeOfPlayerById(new PlayerMarketRequest(id));
		assertEquals("SUCCESS", response.getDescription());
		assertEquals(0, response.getResultCode());
		assertNotNull(response.getPlayerMarketList());
	}
	
	@Test
	@Order(4)
	public void getContractFeeOfPlayerByNullIdTest() {
		SimplePlayerMarketResponse response = playerMarketController.getContractFeeOfPlayerById(new PlayerMarketRequest(null));
		assertEquals("COMMON_UNKNOWN_ERROR", response.getDescription());
		assertEquals(900, response.getResultCode());
		assertNotNull(response.getPlayerMarketList());
	}

}
