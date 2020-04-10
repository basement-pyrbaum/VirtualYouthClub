package de.youtclubstage.virtualyouthclub.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@Data
public class UserMessage {
    @Id
    private UUID id = UUID.randomUUID();

    private UUID from;

    private UUID to;

    private boolean isComplaint = false;

    private Date createDate = Calendar.getInstance().getTime();

    private String message;
    private String subject;

    private boolean read = false;

}
