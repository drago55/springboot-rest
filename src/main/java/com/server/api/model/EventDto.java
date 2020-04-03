package com.server.api.model;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private Date date;
}
