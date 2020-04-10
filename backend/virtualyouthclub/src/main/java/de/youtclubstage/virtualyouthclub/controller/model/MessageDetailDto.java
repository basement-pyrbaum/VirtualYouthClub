package de.youtclubstage.virtualyouthclub.controller.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MessageDetailDto {
    private UUID id;

    private String from;

    private String to;

    private Date createDate;

    private String message;
    private String subject;

    private boolean read = false;
}
