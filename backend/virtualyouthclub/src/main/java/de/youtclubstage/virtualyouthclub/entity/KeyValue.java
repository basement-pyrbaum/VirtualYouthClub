package de.youtclubstage.virtualyouthclub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue {

    @Id
    String key;

    String value;
}
