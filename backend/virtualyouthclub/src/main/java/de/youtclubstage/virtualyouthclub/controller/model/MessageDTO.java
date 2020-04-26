package de.youtclubstage.virtualyouthclub.controller.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MessageDTO {
    private UUID id ;

    private String from;

    private Date createDate;

    private String subject;

    private boolean read = false;
}
