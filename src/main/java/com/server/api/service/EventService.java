package com.server.api.service;

import com.server.api.model.EventDto;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventService {


    public List<EventDto> findAll() {

        EventDto event = new EventDto();
        event.setDate(Date.valueOf(LocalDate.now()));
        event.setName("Auto expo");
        event.setDescription("Some event ");
        List<EventDto> events =  new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            events.add(event);
        }

        return events;
    }

    public List<EventDto> findSpecialEvents() {
        EventDto event = new EventDto();
        event.setDate(Date.valueOf(LocalDate.now()));
        event.setName("Extreme Auto expo");
        event.setDescription("Special event ");
        List<EventDto> events =  new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            events.add(event);
        }

        return events;
    }
}
