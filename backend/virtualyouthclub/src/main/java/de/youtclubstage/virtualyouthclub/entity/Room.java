package de.youtclubstage.virtualyouthclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private UUID id = UUID.randomUUID();

    @TextIndexed
    private String name;
}
