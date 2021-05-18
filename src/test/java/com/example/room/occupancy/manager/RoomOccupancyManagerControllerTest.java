package com.example.room.occupancy.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.room.occupancy.manager.dto.AvailableRooms;
import com.example.room.occupancy.manager.dto.RoomsUsage;
import com.example.room.occupancy.manager.service.RoomOccupancyManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class RoomOccupancyManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoomOccupancyManagerService roomOccupancyManagerService;

	@Test
	@DisplayName("Check possible room Usage and Income")
	void getPossibleRoomUsage_shouldReturnAvailableRoomsAndIncome() throws Exception {

		// given
		AvailableRooms availableRooms = AvailableRooms.builder()
				.availableEconomyRoom(3)
				.availablePremiumRoom(3)
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(availableRooms);
		
		// when
		given(roomOccupancyManagerService.calculateRoomUsage(ArgumentMatchers.any())).willReturn(RoomsUsage.builder()
				.totalEconomyGuest(3)
				.totalPremiumGuest(3)
				.totalEconomyIncome("EUR 167.99")
				.totalPremiumIncome("EUR 738.0")
				.build());

		//then
		mockMvc.perform(post("/")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("totalEconomyGuest").value(3))
				.andExpect(jsonPath("totalPremiumGuest").value(3))
				.andExpect(jsonPath("totalEconomyIncome").value("EUR 167.99"))
				.andExpect(jsonPath("totalPremiumIncome").value("EUR 738.0"));
	}

}
