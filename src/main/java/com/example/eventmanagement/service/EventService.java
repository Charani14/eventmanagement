package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.EventDTO;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.exception.EventNotFoundException;
import com.example.eventmanagement.exception.InvalidEventException;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDTO createEvent(EventDTO dto) throws InvalidEventException {
        validateEvent(dto);
        Event event = mapToEntity(dto);
        return mapToDTO(eventRepository.save(event));
    }


    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public EventDTO getEventById(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found with id: " + id));
        return mapToDTO(event);
    }

    public EventDTO updateEvent(Long id, EventDTO dto)
            throws EventNotFoundException, InvalidEventException {

        validateEvent(dto);

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found with id: " + id));

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setEventDate(dto.getEventDate());

        return mapToDTO(eventRepository.save(event));
    }


    public void deleteEvent(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found with id: " + id));
        eventRepository.delete(event);
    }

    // 🔍 Find Events By Location
    public List<EventDTO> getEventsByLocation(String location) throws InvalidEventException {
        if (location == null || location.trim().isEmpty()) {
            throw new InvalidEventException("Location must not be empty");
        }

        return eventRepository.findByLocation(location)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<EventDTO> getUpcomingEvents() {
        return eventRepository.findByEventDateAfter(LocalDate.now())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private void validateEvent(EventDTO dto) throws InvalidEventException {

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new InvalidEventException("Event title must not be empty");
        }

        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
            throw new InvalidEventException("Event location must not be empty");
        }

        if (dto.getEventDate() == null) {
            throw new InvalidEventException("Event date is required");
        }

        if (dto.getEventDate().isBefore(LocalDate.now())) {
            throw new InvalidEventException("Event date cannot be in the past");
        }
    }


    private Event mapToEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setEventDate(dto.getEventDate());
        return event;
    }

    private EventDTO mapToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setEventDate(event.getEventDate());
        return dto;
    }
}
