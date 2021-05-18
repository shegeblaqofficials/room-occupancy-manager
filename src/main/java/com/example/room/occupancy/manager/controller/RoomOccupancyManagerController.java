package com.example.room.occupancy.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.room.occupancy.manager.dto.AvailableRooms;
import com.example.room.occupancy.manager.dto.RoomsUsage;
import com.example.room.occupancy.manager.service.RoomOccupancyManagerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(value = "Room Occupancy Optimization Api operations")
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful"),
		@ApiResponse(responseCode = "201", description = "Created"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "404", description = "Not Found"),
		@ApiResponse(responseCode = "401", description = "Unauthorised"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),
		@ApiResponse(responseCode = "405", description = "Method Not Allowed"),
		@ApiResponse(responseCode = "400", description = "Service Unavailable") })
public class RoomOccupancyManagerController {

	@Autowired
	private RoomOccupancyManagerService roomOccupancyManagerService;
	

	@PostMapping("/")
	@ApiOperation(value = "Calculate Available Room and Income")
	public ResponseEntity<RoomsUsage> checkAvailableRooms(@RequestBody AvailableRooms availableRooms){
		
		RoomsUsage roomsUsage = roomOccupancyManagerService.calculateRoomUsage(availableRooms);
		return new ResponseEntity<RoomsUsage>(roomsUsage, HttpStatus.CREATED);
	}
}
