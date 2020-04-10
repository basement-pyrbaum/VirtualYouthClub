package de.youtclubstage.virtualyouthclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private UUID id;

    private String email;
}
