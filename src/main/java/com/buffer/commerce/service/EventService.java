package com.buffer.commerce.service;

import com.buffer.commerce.model.Event;
import com.buffer.commerce.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event getEvent(final String eventName) {
        return this.eventRepository.findOneByName(eventName);
    }
}
