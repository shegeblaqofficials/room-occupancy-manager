package com.example.room.occupancy.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvailableRooms {

	private int availablePremiumRoom;
	
	private int availableEconomyRoom;
	
}

