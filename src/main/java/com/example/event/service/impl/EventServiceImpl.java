package com.example.event.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event.dto.EventDto;
import com.example.event.entity.Event;
import com.example.event.exception.NotFoundException;
import com.example.event.repository.EventRepository;
import com.example.event.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EventDto createEvent(EventDto eventDto) {
		Event eventToBeSaved = modelMapper.map(eventDto, Event.class);
		Event savedEvent = eventRepository.save(eventToBeSaved);
		return modelMapper.map(savedEvent, EventDto.class);
	}

	@Override
	public List<EventDto> getAllEvents() {
		List<Event> events = (List<Event>) eventRepository.findAll();
		if(events.isEmpty()) {
			throw new NotFoundException("Events not found");	
		}
		return events.stream()
						.map(e -> modelMapper.map(e, EventDto.class))
						.collect(Collectors.toList());
	}
	
	@Override
	public List<EventDto> getEventsByType(String eventType) {
		List<Event> events = eventRepository.findByEventType(eventType);
		if(events != null) {
			return events.stream()
					.map(e -> modelMapper.map(e, EventDto.class))
					.collect(Collectors.toList());
		} else {
			throw new NotFoundException("Events not found with type : "+eventType);	
		}
	}

}
