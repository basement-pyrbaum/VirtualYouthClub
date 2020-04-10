package de.youtclubstage.virtualyouthclub.controller.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
