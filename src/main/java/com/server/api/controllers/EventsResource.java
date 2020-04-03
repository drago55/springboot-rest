package com.server.api.controllers;

import com.server.api.model.EventDto;
import com.server.api.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsResource {

    private final Logger logger = LoggerFactory.getLogger(EventsResource.class);

    private final EventService eventService;

    @Autowired
    public EventsResource(final EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/events")
    public List<EventDto> getEvents(){
        logger.info("Fetching all events");
        return eventService.findAll();
    }

    @GetMapping(value = "/special")
    public List<EventDto> getSpecialEvents(){
        logger.info("Fetching all special events");
    return eventService.findSpecialEvents();
    }

}
