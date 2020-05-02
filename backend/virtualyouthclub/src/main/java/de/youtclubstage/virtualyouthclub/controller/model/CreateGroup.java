package de.youtclubstage.virtualyouthclub.controller.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroup {
    private String name;
    private List<UUID> admins;
}
