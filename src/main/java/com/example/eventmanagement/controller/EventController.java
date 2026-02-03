package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.EventDTO;
import com.example.eventmanagement.exception.EventNotFoundException;
import com.example.eventmanagement.exception.InvalidEventException;
import com.example.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public EventDTO createEvent(@Valid @RequestBody EventDTO dto) throws InvalidEventException {
        logger.info("Creating event with title: {}", dto.getTitle());
        EventDTO createdEvent = eventService.createEvent(dto);
        logger.info("Event created with id: {}", createdEvent.getId());
        return createdEvent;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        logger.info("Fetching all events");
        List<EventDTO> events = eventService.getAllEvents();
        logger.info("Total events fetched: {}", events.size());
        return events;
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable Long id) throws EventNotFoundException {
        logger.info("Fetching event with id: {}", id);
        EventDTO event = eventService.getEventById(id);
        logger.info("Event fetched: {}", event.getTitle());
        return event;
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @Valid @RequestBody EventDTO dto)
            throws EventNotFoundException, InvalidEventException {
        logger.info("Updating event with id: {}", id);
        EventDTO updatedEvent = eventService.updateEvent(id, dto);
        logger.info("Event updated: {}", updatedEvent.getTitle());
        return updatedEvent;
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) throws EventNotFoundException {
        logger.info("Deleting event with id: {}", id);
        eventService.deleteEvent(id);
        logger.info("Event deleted with id: {}", id);
        return "Event deleted successfully";
    }

    // Extra APIs
    @GetMapping("/location/{location}")
    public List<EventDTO> getByLocation(@PathVariable String location) throws InvalidEventException {
        logger.info("Fetching events by location: {}", location);
        List<EventDTO> events = eventService.getEventsByLocation(location);
        logger.info("Events fetched for location '{}': {}", location, events.size());
        return events;
    }

    @GetMapping("/upcoming")
    public List<EventDTO> getUpcomingEvents() {
        logger.info("Fetching upcoming events");
        List<EventDTO> events = eventService.getUpcomingEvents();
        logger.info("Upcoming events count: {}", events.size());
        return events;
    }
}
