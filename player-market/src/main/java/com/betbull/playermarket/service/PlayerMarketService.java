package com.betbull.playermarket.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.betbull.playermarket.exception.PlayerMarketException;
import com.betbull.playermarket.model.Player;
import com.betbull.playermarket.model.PlayerMarketItem;
import com.betbull.playermarket.model.PlayerMarketItemDto;
import com.betbull.playermarket.model.PlayerMarketResponse;
import com.betbull.playermarket.model.PlayerResponse;
import com.betbull.playermarket.model.SimplePlayerMarketResponse;
import com.betbull.playermarket.model.Team;
import com.betbull.playermarket.model.TeamRequest;
import com.betbull.playermarket.model.TeamResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerMarketService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static String GET_ALL_PLAYERS_URL = "http://localhost:8082/api/player/getallplayers";
	private static String GET_TEAM_BY_NAME_URL = "http://localhost:8081/api/team/getteambyname";
	
	public PlayerMarketResponse createPlayerMarket() {
		PlayerMarketResponse playerMarketResponse = new PlayerMarketResponse();
		try {
			playerMarketResponse.setPlayerMarketList(createMarketItems());
			playerMarketResponse.setDescription("SUCCESS");
			playerMarketResponse.setResultCode(0);
		} catch (PlayerMarketException e) {
			playerMarketResponse.setPlayerMarketList(new ArrayList<>());
			playerMarketResponse.setDescription(e.getMessage());
			playerMarketResponse.setResultCode(e.getErrorCode());
		} catch (Exception e) {
			playerMarketResponse.setPlayerMarketList(new ArrayList<>());
			playerMarketResponse.setDescription("COMMON_UNKNOWN_ERROR");
			playerMarketResponse.setResultCode(900);
		}
		return playerMarketResponse;
	}
	
	public SimplePlayerMarketResponse createSimplePlayerMarket() {
		SimplePlayerMarketResponse playerMarketResponse = new SimplePlayerMarketResponse();
		try {
			playerMarketResponse.setPlayerMarketList(createSimpleMarketItems());
			playerMarketResponse.setDescription("SUCCESS");
			playerMarketResponse.setResultCode(0);
		} catch (PlayerMarketException e) {
			playerMarketResponse.setPlayerMarketList(new ArrayList<>());
			playerMarketResponse.setDescription(e.getMessage());
			playerMarketResponse.setResultCode(e.getErrorCode());
		} catch (Exception e) {
			playerMarketResponse.setPlayerMarketList(new ArrayList<>());
			playerMarketResponse.setDescription("COMMON_UNKNOWN_ERROR");
			playerMarketResponse.setResultCode(900);
		}
		return playerMarketResponse;
	}
	
	private List<PlayerMarketItem> createMarketItems() {
		List<PlayerMarketItem> itemList = new ArrayList<PlayerMarketItem>();
		PlayerResponse response = restTemplate.getForObject(GET_ALL_PLAYERS_URL, PlayerResponse.class); 
		if(CollectionUtils.isEmpty(response.getPlayerList()))
			throw new PlayerMarketException(response.getDescription(), response.getResultCode());
		for(Player player : response.getPlayerList()) {
			Team team = getTeamOfPlayer(player);
			itemList.add(createItem(player, team));
		}
		return itemList;
	}
	
	private Team getTeamOfPlayer(Player player) {
		if(player.getTeamName() == null) {
			return null;
		}
		TeamRequest request = new TeamRequest(player.getTeamName());
		TeamResponse response = restTemplate.postForObject(GET_TEAM_BY_NAME_URL, request, TeamResponse.class);
		if(CollectionUtils.isEmpty(response.getTeamList()))
			throw new PlayerMarketException(response.getDescription(), response.getResultCode());
		return response.getTeamList().stream().findFirst().orElse(null);
	}
	
	private PlayerMarketItem createItem(Player player, Team team) {
		PlayerMarketItem item = new PlayerMarketItem(player, team);
		item.setContractFee(calculateContractFee(player, team));
		item.setCurrencyCode(team.getCurrency().getCode());
		return item;
	}
	
	/**
	 * 
	 * @param player
	 * @param team
	 * @return contractFee
	 */
	private BigDecimal calculateContractFee(Player player, Team team) {
		BigDecimal transferFee = calculateTransferFee(player, team);
		BigDecimal teamCommission = transferFee.divide(BigDecimal.TEN, 3, RoundingMode.CEILING);
		
		return transferFee.add(teamCommission);
	}

	/**
	 * 
	 * @param player
	 * @param team
	 * @return transferFee 
	 */
	private BigDecimal calculateTransferFee(Player player, Team team) {
		BigDecimal monthsOfExperience = BigDecimal.valueOf(getMonthsOfExperience(player.getCareerBeginDate()));
		BigDecimal multiplier = BigDecimal.valueOf(100000);
		BigDecimal age = BigDecimal.valueOf(getAge(player.getBirthDate()));
		
		BigDecimal transferFee = monthsOfExperience.multiply(multiplier).divide(age, 3, RoundingMode.CEILING);
		log.info("{} months of experience is {}, age is {}. TransferFee is {}", player.getName(), monthsOfExperience.intValue(), age.intValue(), transferFee);
		return transferFee;
	}
	
	private long getMonthsOfExperience(Date date) {
		LocalDate today = LocalDate.now();
		LocalDate birth = date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		return ChronoUnit.MONTHS.between(birth, today);
		
	}
	
	private Integer getAge(Date date) {
		LocalDate today = LocalDate.now();
		LocalDate birth = date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		return Period.between(birth, today).getYears();
	}
	
	private List<PlayerMarketItemDto> createSimpleMarketItems() {
		List<PlayerMarketItemDto> dtoList = new ArrayList<PlayerMarketItemDto>();
		PlayerMarketResponse response = createPlayerMarket();
		for(PlayerMarketItem item : response.getPlayerMarketList()) {
			PlayerMarketItemDto dto = new PlayerMarketItemDto(item.getPlayer().getName(), item.getTeam().getName(), 
					item.getPlayer().getPosition(), item.getPlayer().getOverallPower().setScale(0, RoundingMode.CEILING).toPlainString(), 
					item.getContractFee().toPlainString().concat(" ").concat(item.getCurrencyCode()));
			dtoList.add(dto);
		}
		return dtoList;
	}

}
