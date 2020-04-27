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
public class RegisterKeys {

    public RegisterKeys(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,5);
        expired = calendar.getTime();
    }

    @Id
    private UUID key;
    private Date expired;

}
