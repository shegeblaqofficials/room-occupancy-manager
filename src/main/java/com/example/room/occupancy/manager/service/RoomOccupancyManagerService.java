package com.example.room.occupancy.manager.service;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.room.occupancy.manager.dto.AvailableRooms;
import com.example.room.occupancy.manager.dto.RoomsUsage;
import com.example.room.occupancy.manager.util.Constant;

@Service
public class RoomOccupancyManagerService {

	Logger logger = LoggerFactory.getLogger(RoomOccupancyManagerService.class);

	public RoomsUsage calculateRoomUsage(AvailableRooms availableRooms, double[] potentialGuests) {

		// sort the potentialGuests
		Arrays.sort(potentialGuests);

		int t = potentialGuests.length - 1;
		int totalUsedPremiumRooms = 0;
		int totalUsedEconomyRooms = 0;
		double totalPremiumIncome = 0;
		double totalEconomyIncome = 0;

		for (int i = t; i >= 0; i--) {

			double bidPrice = potentialGuests[i];

			if (totalUsedPremiumRooms < availableRooms.getAvailablePremiumRoom()
					&& bidPrice >= Constant.PREMIUM_MINIMUM_AMOUNT) {

				totalUsedPremiumRooms++;
				totalPremiumIncome += potentialGuests[i];
			} else {

				if (totalUsedPremiumRooms < availableRooms.getAvailablePremiumRoom()
						&& bidPrice < Constant.PREMIUM_MINIMUM_AMOUNT
						&& ((i + 1) > availableRooms.getAvailableEconomyRoom())) {

					totalUsedPremiumRooms++;
					totalPremiumIncome += potentialGuests[i];
				} else if (totalUsedEconomyRooms < availableRooms.getAvailableEconomyRoom()
						&& bidPrice < Constant.PREMIUM_MINIMUM_AMOUNT) {

					totalUsedEconomyRooms++;
					totalEconomyIncome += potentialGuests[i];
				}

			}

		}

		return RoomsUsage.builder()
				.totalEconomyGuest(totalUsedEconomyRooms)
				.totalEconomyIncome(Constant.CURRENCY + totalEconomyIncome)
				.totalPremiumGuest(totalUsedPremiumRooms)
				.totalPremiumIncome(Constant.CURRENCY + totalPremiumIncome)
				.build();
	}

}
