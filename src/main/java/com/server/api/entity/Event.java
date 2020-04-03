package com.server.api.entity;

import javax.persistence.Entity;
import java.util.Date;

//@Entity
public class Event {

    private Long id;
    private String name;
    private String description;
    private Date date;
}
