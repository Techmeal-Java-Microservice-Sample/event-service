package com.example.event.service;

import java.util.List;

import com.example.event.dto.EventDto;

public interface EventService {

	EventDto createEvent(EventDto eventDto);
	List<EventDto> getAllEvents();
	List<EventDto> getEventsByType(String eventName);

}
