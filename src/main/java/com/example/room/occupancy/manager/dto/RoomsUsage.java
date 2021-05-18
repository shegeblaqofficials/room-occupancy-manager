package com.example.room.occupancy.manager.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RoomsUsage {

	private int totalEconomyGuest;
	
	private int totalPremiumGuest;
	
	private String totalEconomyIncome;
	
	private String totalPremiumIncome;
	
}
