package com.example.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event.dto.EventDto;
import com.example.event.exception.ApplicationException;
import com.example.event.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("event")
@Api(value="Event Controller", description="operations pertaining to manage events", tags = {"Events"})
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping
	@ApiOperation(value = "Create Event", response = ResponseEntity.class)
	public ResponseEntity<EventDto> create(@ApiParam("Event information for a new event to be created.") 
												@RequestBody EventDto eventDto) {
		validate(eventDto);
		EventDto savedEventDto = eventService.createEvent(eventDto);
		return new ResponseEntity<>(savedEventDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation(value = "Get All Events", response = ResponseEntity.class)
	public ResponseEntity<List<EventDto>> getAllEvents() {
		List<EventDto> eventDtos = eventService.getAllEvents();
		return new ResponseEntity<>(eventDtos, HttpStatus.FOUND);
	}
	
	@GetMapping("/{eventType}")
	@ApiOperation(value = "Get Events by eventType", response = ResponseEntity.class)
	public ResponseEntity<List<EventDto>> getEventsByType(@ApiParam("Type of event to be obtain. Cannot be empty.")
													@PathVariable String eventType) {
		if(eventType == null) {
			throw new ApplicationException("event type should not be null");
		}
		
		if(!eventType.equalsIgnoreCase("INFO") && !eventType.equalsIgnoreCase("ERROR")) {
			throw new ApplicationException("invalid event type. only 'INFO' and 'ERROR' event type allowed");
		}
		
		List<EventDto> eventDtos = eventService.getEventsByType(eventType);
		return new ResponseEntity<>(eventDtos, HttpStatus.FOUND);
	}
	
	private void validate(EventDto eventDto) {
		if(eventDto == null) {
			throw new ApplicationException("eventDto should not be null");
		}
		
		if(eventDto.getEventType() == null || eventDto.getEventType().isEmpty()) {
			throw new ApplicationException("event type should not be null");
		}
		
		if(!eventDto.getEventType().equalsIgnoreCase("INFO") && !eventDto.getEventType().equalsIgnoreCase("ERROR")) {
			throw new ApplicationException("invalid event type. only 'INFO' and 'ERROR' event type allowed");
		}
		
		if(eventDto.getEventDescription() == null || eventDto.getEventDescription().isEmpty()) {
			throw new ApplicationException("event description should not be null");
		}
		
		if(eventDto.getEventDate() == null) {
			throw new ApplicationException("event date should not be null");
		}
	}
}
