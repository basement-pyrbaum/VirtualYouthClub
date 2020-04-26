package de.youtclubstage.virtualyouthclub.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
public class HoursOfOpening {

    @Id
    private UUID id = UUID.randomUUID();

    private Date from;

    private Date to;

    private String event;
}
