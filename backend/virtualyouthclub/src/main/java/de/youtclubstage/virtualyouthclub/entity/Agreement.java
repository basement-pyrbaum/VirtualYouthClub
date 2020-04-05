package de.youtclubstage.virtualyouthclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    @Id
    UUID user;

    Date createDate = Calendar.getInstance().getTime();
}
