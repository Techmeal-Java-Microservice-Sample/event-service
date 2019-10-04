package com.example.event.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.event.entity.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	List<Event> findByEventType(String eventType);

}
