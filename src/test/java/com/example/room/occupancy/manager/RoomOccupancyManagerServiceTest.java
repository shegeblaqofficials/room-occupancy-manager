package com.example.room.occupancy.manager;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.example.room.occupancy.manager.dto.AvailableRooms;
import com.example.room.occupancy.manager.dto.RoomsUsage;
import com.example.room.occupancy.manager.service.RoomOccupancyManagerService;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class RoomOccupancyManagerServiceTest {
	

	@Autowired
	private RoomOccupancyManagerService roomOccupancyManagerService;

	//test data
	private double[] potentialGuests = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};

	@Test
	@DisplayName("Test with available rooms - Premium 3 and Economy 3")
	void test_premium_3_economy_3_shouldReturn_premium_3_economy_3() {
		
		// given
		AvailableRooms availableRooms = AvailableRooms.builder()
				.availableEconomyRoom(3)
				.availablePremiumRoom(3)
				.build();
		
		/// when
		RoomsUsage usage = roomOccupancyManagerService.calculateRoomUsage(availableRooms, potentialGuests);

		//then
		then(usage.getTotalEconomyGuest()).isEqualTo(3);
		then(usage.getTotalEconomyIncome()).isEqualTo("EUR 167.99");
		then(usage.getTotalPremiumGuest()).isEqualTo(3);
		then(usage.getTotalPremiumIncome()).isEqualTo("EUR 738.0");
	}
	
	@Test
	@DisplayName("Test with available rooms - Premium 7 and Economy 5")
	void test_premium_7_economy_5_shouldReturn_premium_6_economy_4() {
		
		// given
		AvailableRooms availableRooms = AvailableRooms.builder()
				.availableEconomyRoom(5)
				.availablePremiumRoom(7)
				.build();
		
		/// when
		RoomsUsage usage = roomOccupancyManagerService.calculateRoomUsage(availableRooms, potentialGuests);

		//then
		then(usage.getTotalEconomyGuest()).isEqualTo(4);
		then(usage.getTotalEconomyIncome()).isEqualTo("EUR 189.99");
		then(usage.getTotalPremiumGuest()).isEqualTo(6);
		then(usage.getTotalPremiumIncome()).isEqualTo("EUR 1054.0");
	}
	
	@Test
	@DisplayName("Test with available rooms - Premium 2 and Economy 7")
	void test_premium_2_economy_7_shouldReturn_premium_2_economy_4() {
		
		// given
		AvailableRooms availableRooms = AvailableRooms.builder()
				.availableEconomyRoom(7)
				.availablePremiumRoom(2)
				.build();
		
		/// when
		RoomsUsage usage = roomOccupancyManagerService.calculateRoomUsage(availableRooms, potentialGuests);

		//then
		then(usage.getTotalEconomyGuest()).isEqualTo(4);
		then(usage.getTotalEconomyIncome()).isEqualTo("EUR 189.99");
		then(usage.getTotalPremiumGuest()).isEqualTo(2);
		then(usage.getTotalPremiumIncome()).isEqualTo("EUR 583.0");
	}
	
	
	@Test
	@DisplayName("Test with available rooms - Premium 7 and Economy 1")
	void test_premium_7_economy_1_shouldReturn_premium_7_economy_1() {
		
		// given
		AvailableRooms availableRooms = AvailableRooms.builder()
				.availableEconomyRoom(1)
				.availablePremiumRoom(7)
				.build();
		
		/// when
		RoomsUsage usage = roomOccupancyManagerService.calculateRoomUsage(availableRooms, potentialGuests);

		//then
		then(usage.getTotalEconomyGuest()).isEqualTo(1);
		then(usage.getTotalEconomyIncome()).isEqualTo("EUR 45.0");
		then(usage.getTotalPremiumGuest()).isEqualTo(7);
		then(usage.getTotalPremiumIncome()).isNotEqualTo("EUR 1153.0");
	}
	
}
