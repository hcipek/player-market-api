package com.betbull.playermarket.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.betbull.playermarket.model.SimplePlayerMarketResponse;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PlayerMarketServiceTest {
	
	@Autowired
	private PlayerMarketService playerMarketService;
	
	@Test
	@Order(1)
	public void getContractFeeOfPlayerByIdTest() {
		SimplePlayerMarketResponse response = playerMarketService.getContractFeeOfPlayerById(1L);
		assertEquals("PLAYER_NOT_EXISTS", response.getDescription());
		assertEquals(703, response.getResultCode());
		assertEquals(CollectionUtils.isEmpty(response.getPlayerMarketList()), true);
	}

}
